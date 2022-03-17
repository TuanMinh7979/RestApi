package com.rest.onetomany.exception;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super(MessageFormat.format("Could not find Resouce with id {}", id));

    }
}
