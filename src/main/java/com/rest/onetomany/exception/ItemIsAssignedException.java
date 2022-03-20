package com.rest.onetomany.exception;

import java.text.MessageFormat;

public class ItemIsAssignedException extends RuntimeException {




    public ItemIsAssignedException(final Long itemId, final Long cartId) {
        super(MessageFormat.format("item {0} is already assigned to cart{1}", itemId, cartId));
    }
}
