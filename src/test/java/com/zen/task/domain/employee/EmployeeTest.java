package com.zen.task.domain.employee;

import com.zen.task.domain.company.Company;
import com.zen.task.domain.company.CompanyAlreadyClosedException;
import com.zen.task.domain.compensation.SalaryDetails;
import com.zen.task.infrastructure.company.H2CompanyRepository;
import com.zen.task.infrastructure.employee.H2EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    H2EmployeeRepository employeeRepository;

    @Autowired
    H2CompanyRepository companyRepository;

    private Company mkSampleCompany() {
        UUID companyId = UUID.randomUUID();
        String name = "Company_" + companyId;
        return new Company(companyId, name);
    }

    private Employee mkSampleEmployee(Company company) {
        Company c = (company == null) ? mkSampleCompany(): company;
        UUID employeeId = UUID.randomUUID();
        return new Employee(employeeId, c, "John", "Doe", LocalDate.of(2020, 1, 1), null, "Some job title", 100.0);
    }

    @Test
    void inMemoryDatabaseTestSave() {
        Company c = companyRepository.save(mkSampleCompany());

        Employee e = mkSampleEmployee(c);
        employeeRepository.save(e);
        Employee result = employeeRepository.findById(e.getId());

        assertEquals(e.getId(), result.getId());
        assertEquals(c.getId(), result.getCompany().getId());
    }

    @Test
    void inMemoryDatabaseAverageCalculationTest() {
        Company c = companyRepository.save(mkSampleCompany());

        Employee e1 = mkSampleEmployee(c);
        e1.setJobTitle("title1");
        e1.setSalary(100.0);

        Employee e2 = mkSampleEmployee(c);
        e2.setJobTitle("title1");
        e2.setSalary(200.0);

        employeeRepository.save(e1);
        employeeRepository.save(e2);

        List<SalaryDetails> averages = employeeRepository.averageSalaries(e1.getCompany().getId(), List.of("title1","not existing title"));

        assertEquals(1, averages.size(), "Should contain only one item for existing job title");
        assertTrue(averages.contains(new SalaryDetails("title1", 150.0)), "Should returned salary be average of two values");
    }
}
