package com.mayan.ecommerce.dtos;

import com.mayan.ecommerce.entities.Payment;

import java.time.Instant;

public class PaymentDTO {
    private Long id;
    private Instant moment;

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment entity ) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
