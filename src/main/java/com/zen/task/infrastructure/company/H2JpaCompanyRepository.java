package com.zen.task.infrastructure.company;

import com.zen.task.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface H2JpaCompanyRepository extends JpaRepository<Company, UUID> {

}
