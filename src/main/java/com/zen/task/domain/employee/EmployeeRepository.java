package com.zen.task.domain.employee;

import com.zen.task.domain.compensation.SalaryDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository {

    Employee findById(UUID id);

    Employee save(Employee employee);

    List<Employee> findByCompanyId(UUID companyId);

    @Query(nativeQuery=true)
    List<SalaryDetails> averageSalaries(@Param("companyId") UUID companyId, @Param("jobTitles") List<String> jobTitles);
}
