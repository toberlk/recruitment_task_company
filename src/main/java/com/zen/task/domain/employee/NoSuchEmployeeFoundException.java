package com.zen.task.domain.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchEmployeeFoundException extends RuntimeException {

        public NoSuchEmployeeFoundException(final String message) {
        super(message);
    }
}
