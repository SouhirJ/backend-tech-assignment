package com.sm360.OnlineAdvertisement.exceptions;

public class TierLimitReachedException extends RuntimeException {
    private String message;
    public TierLimitReachedException(String message) {
        super(message);
        this.message = message;
    }
}
