package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "expense")
@NoArgsConstructor
public class Expense {
    @Id
    @SequenceGenerator(name = "expense_sequence", sequenceName = "expense_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_sequence")
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "label", nullable = false, updatable = false)
    private  String label;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "expense_category_fk"))
    private Category category;
    @Column(name = "expense_date", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate expenseDate;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "payment_system_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "expense_system_fk"))
    private PaymentSystem paymentSystem;

    @Column(name = "amount", nullable = false)
    private Float amount;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "client_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "client_expense_fk"))
    @JsonBackReference
    private Client client;
}
