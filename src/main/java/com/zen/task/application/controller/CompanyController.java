package com.zen.task.application.controller;

import com.zen.task.domain.company.Company;
import com.zen.task.domain.company.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyRepository repository;

    public CompanyController(final CompanyRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/random")
    public Company addRandom() {
        Company c = new Company(UUID.randomUUID(), "Company_" + System.currentTimeMillis());
        repository.save(c);
        return c;
    }

    @PostMapping
    public Company createCompany(@RequestBody Company companyRequest) {
        Company c = new Company(UUID.randomUUID(), companyRequest.getName());
        c.setOperationCloseDate(companyRequest.getOperationCloseDate());
        return repository.save(c);
    }

    @PutMapping("{companyId}")
    public Company updateCompany(@PathVariable UUID companyId, @RequestBody Company companyRequest) {
        Company c = repository.findById(companyId);
        c.setName(companyRequest.getName());
        c.setOperationCloseDate(companyRequest.getOperationCloseDate());
        return repository.save(c);
    }

    @GetMapping
    public List<Company> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable UUID id) {
        return repository.findById(id);
    }

}
