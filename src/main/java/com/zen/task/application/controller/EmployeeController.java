package com.zen.task.application.controller;

import com.zen.task.domain.company.Company;
import com.zen.task.domain.company.CompanyAlreadyClosedException;
import com.zen.task.domain.company.CompanyRepository;
import com.zen.task.domain.compensation.BonusCalculator;
import com.zen.task.domain.compensation.SalaryDetails;
import com.zen.task.domain.employee.Employee;
import com.zen.task.domain.employee.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class EmployeeController {
    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;

    public EmployeeController(final EmployeeRepository employeeRepository, final CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping("{companyId}/employees")
    public Employee createEmployee(@PathVariable UUID companyId, @RequestBody Employee employee) {
        Company c = companyRepository.findById(companyId);
        assertNotClosed(c);
        employee.setCompany(c);
        employee.setId(UUID.randomUUID());
        return employeeRepository.save(employee);
    }

    @PutMapping("{companyId}/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable UUID companyId, @PathVariable UUID employeeId, @RequestBody Employee employeeRequest) {
        assertNotClosed(companyId);

        Employee e = employeeRepository.findById(employeeId);
        assertEmployeeMatchCompany(companyId, e);

        e.setFirstName(employeeRequest.getFirstName());
        e.setLastName(employeeRequest.getLastName());
        e.setEmploymentStartDate(employeeRequest.getEmploymentStartDate());
        e.setEmploymentEndDate(employeeRequest.getEmploymentEndDate());
        e.setSalary(employeeRequest.getSalary());
        e.setJobTitle(employeeRequest.getJobTitle());

        return employeeRepository.save(e);
    }

    private void assertEmployeeMatchCompany(UUID companyId, Employee e) {
        if (!companyId.equals(e.getCompany().getId())) {
            throw new IllegalArgumentException("Employee does not match the company");
        }
    }

    @GetMapping("{companyId}/employees")
    public List<Employee> getEmployees(@PathVariable UUID companyId) {
        assertNotClosed(companyId);
        return employeeRepository.findByCompanyId(companyId);
    }

    @GetMapping("{companyId}/employees/{employeeId}/bonus")
    public Double calculateBonus(@PathVariable UUID companyId, @PathVariable UUID employeeId) {
        assertNotClosed(companyId);
        Employee e = employeeRepository.findById(employeeId);
        return BonusCalculator.calculate(e.getEmploymentStartDate(), LocalDate.now(), e.getSalary());
    }

    @GetMapping("{companyId}/employees/salaries-average")
    public List<SalaryDetails> getAverageSalaryForCompanyAndJobTitle(@PathVariable UUID companyId, @RequestParam List<String> jobTitles) {
        assertNotClosed(companyId);
        return employeeRepository.averageSalaries(companyId, jobTitles);
    }

    private void assertNotClosed(UUID companyId) {
        Company c = companyRepository.findById(companyId);
        assertNotClosed(c);
    }

    private void assertNotClosed(Company c) {
        if (c.getOperationCloseDate()!=null && c.getOperationCloseDate().isBefore(LocalDate.now()))
            throw new CompanyAlreadyClosedException("Company " + c.getId() +" already closed the business");
    }

}
