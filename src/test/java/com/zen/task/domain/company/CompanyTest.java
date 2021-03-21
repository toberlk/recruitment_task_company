package com.zen.task.domain.company;

import com.zen.task.infrastructure.company.H2CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CompanyTest {

    @Autowired
    H2CompanyRepository repository;

    @Test
    void inMemoryDatabaseTestSave() {
        UUID id = UUID.randomUUID();
        String name = "ZEN.COM";
        repository.save(new Company(id, name));
        Company result = repository.findById(id);

        assertEquals(id,result.getId());
        assertEquals(name,result.getName());
    }

    @Test
    void mockitoTest() {
        Company company = mock(Company.class);
        when(company.getName()).thenReturn("ZEN.COM");

        assertEquals("ZEN.COM", company.getName());
    }
}
