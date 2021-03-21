package com.zen.task.domain.company;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
public class Company {

    @Id
    @Column(columnDefinition = "uniqueidentifier", unique = true)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate operationCloseDate;

    public Company(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    Company() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getOperationCloseDate() {
        return operationCloseDate;
    }

    public void setOperationCloseDate(LocalDate operationCloseDate) {
        this.operationCloseDate = operationCloseDate;
    }

}
