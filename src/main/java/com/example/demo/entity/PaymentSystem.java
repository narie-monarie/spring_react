package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment_system")
@NoArgsConstructor
public class PaymentSystem {
    @Id
    @SequenceGenerator(name = "payment_system_sequence", sequenceName = "payment_system_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_system_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "label", nullable = false, unique = true, columnDefinition = "TEXT")
    private String label;

    public PaymentSystem(String label){
        this.label = label;
    }
}
