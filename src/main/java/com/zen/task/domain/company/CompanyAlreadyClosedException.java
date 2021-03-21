package com.zen.task.domain.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CompanyAlreadyClosedException extends RuntimeException {
    public CompanyAlreadyClosedException(final String message) {
        super(message);
    }
}
