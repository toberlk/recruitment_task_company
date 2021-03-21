package com.zen.task.infrastructure.employee;

import com.zen.task.domain.compensation.SalaryDetails;
import com.zen.task.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface H2JpaEmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(nativeQuery=true)
    List<SalaryDetails> averageSalaries(@Param("companyId") UUID companyId, @Param("jobTitles") List<String> jobTitles);

    List<Employee> findByCompanyId(UUID companyId);
}
