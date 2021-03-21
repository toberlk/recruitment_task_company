package com.zen.task.infrastructure.employee;

import com.zen.task.domain.compensation.SalaryDetails;
import com.zen.task.domain.employee.Employee;
import com.zen.task.domain.employee.EmployeeRepository;
import com.zen.task.domain.employee.NoSuchEmployeeFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class H2EmployeeRepository implements EmployeeRepository {

    private final H2JpaEmployeeRepository repository;

    public H2EmployeeRepository(final H2JpaEmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchEmployeeFoundException("Not found"));
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> findByCompanyId(UUID companyId) {
        return repository.findByCompanyId(companyId);
    }

    @Override
    public List<SalaryDetails> averageSalaries(UUID companyId, List<String> jobTitles) {
        return repository.averageSalaries(companyId, jobTitles);
    }
}
