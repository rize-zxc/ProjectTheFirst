package com.example.buysell.models;

public class ServerStatus {
    private boolean available;

    public ServerStatus(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}