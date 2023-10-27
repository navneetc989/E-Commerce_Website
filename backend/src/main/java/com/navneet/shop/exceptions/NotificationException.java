package com.navneet.shop.exceptions;

import com.navneet.shop.validation.Notification;

public class NotificationException extends DomainException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotificationException(final String message, final Notification aNotification) {
        super(message, aNotification.getErrors());
    }
}