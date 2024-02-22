package com.axmayn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

    String response;
    public ResourceNotFound(String response)
    {
        super(response);
    }
}
