package com.cashcontrol.cashcontrol.entity;

public enum Status {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
