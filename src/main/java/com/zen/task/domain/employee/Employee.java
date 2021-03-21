package com.zen.task.domain.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zen.task.domain.company.Company;
import com.zen.task.domain.compensation.SalaryDetails;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@NamedNativeQuery(
        name = "Employee.averageSalaries",
        query = "SELECT job_title as jobTitle, avg(salary) as avgSalary FROM Employee WHERE company_id = :companyId and job_title IN (:jobTitles) GROUP by job_title",
        resultSetMapping = "Employee.SalariesDetailsResult"
)
@SqlResultSetMapping(
        name = "Employee.SalariesDetailsResult",
        classes = {
                @ConstructorResult(
                        targetClass = SalaryDetails.class,
                        columns = {
                                @ColumnResult(name = "jobTitle"),
                                @ColumnResult(name = "avgSalary", type = Double.class)
                        })}
)
public class Employee {
    @Id
    @Column(columnDefinition = "uniqueidentifier")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "companyId", nullable = false)
    @JsonIgnore
    private Company company;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate employmentStartDate;

    @Column(columnDefinition = "DATE")
    private LocalDate employmentEndDate;

    @Column(nullable = false)
    private String jobTitle;

    @Column
    private Double salary;

    Employee() {

    }

    public Employee(UUID id, Company company, String firstName, String lastName, LocalDate employmentStartDate, LocalDate employmentEndDate, String jobTitle, Double salary) {
        this.id = id;
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentStartDate = employmentStartDate;
        this.employmentEndDate = employmentEndDate;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(LocalDate employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public LocalDate getEmploymentEndDate() {
        return employmentEndDate;
    }

    public void setEmploymentEndDate(LocalDate employmentEndDate) {
        this.employmentEndDate = employmentEndDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
