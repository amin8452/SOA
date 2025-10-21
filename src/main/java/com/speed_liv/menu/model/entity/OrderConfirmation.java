package com.speed_liv.menu.model.entity;

import com.speed_liv.menu.model.Plat;

public class OrderConfirmation {

    private boolean accepted;
    private double totalPrice;
    private Plat plat;
    private String message;

    public OrderConfirmation(boolean accepted, double totalPrice, Plat plat, String message) {
        this.accepted = accepted;
        this.totalPrice = totalPrice;
        this.plat = plat;
        this.message = message;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Plat getPlat() {
        return plat;
    }

    public String getMessage() {
        return message;
    }
}
