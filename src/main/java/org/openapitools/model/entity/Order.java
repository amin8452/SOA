package org.openapitools.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Order {

    @NotNull
    private Long restaurantId;

    @NotNull
    private Long platId;

    @Min(1)
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = false)
    private double expectedUnitPrice;

    public Order() {
        // Default constructor for Jackson
    }

    public Order(Long restaurantId, Long platId, int quantity, double expectedUnitPrice) {
        this.restaurantId = restaurantId;
        this.platId = platId;
        this.quantity = quantity;
        this.expectedUnitPrice = expectedUnitPrice;
    }

    @JsonProperty("restaurantId")
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @JsonProperty("platId")
    public Long getPlatId() {
        return platId;
    }

    public void setPlatId(Long platId) {
        this.platId = platId;
    }

    @JsonProperty("quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("expectedUnitPrice")
    public double getExpectedUnitPrice() {
        return expectedUnitPrice;
    }

    public void setExpectedUnitPrice(double expectedUnitPrice) {
        this.expectedUnitPrice = expectedUnitPrice;
    }
}
