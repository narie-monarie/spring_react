package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", columnDefinition = "TEXT")
    private long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "username", nullable = false, unique = true, columnDefinition = "TEXT")
    private String username;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    @JsonIgnore
    private  String email;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Expense> expenses;

    public Client(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void addExpense(Expense expense){
        if(!this.expenses.contains(expense)){
            this.expenses.add(expense);
            expense.setClient(this);
        }
    }
    public void removeExpense(Expense expense){
        if(this.expenses.contains(expense)){
            this.expenses.remove(expense);
            expense.setClient(null);
        }
    }
}
