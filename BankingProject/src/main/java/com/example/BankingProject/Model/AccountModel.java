package com.example.BankingProject.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
public class AccountModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;
    private String accountNumber;
    private BigDecimal balance;
    private String pin;
    private String password;

    @OneToMany(mappedBy = "accountModel", cascade = CascadeType.ALL)
    private List<TransactionModel> transactions;

    public AccountModel(Long id, String name, String accountNumber, BigDecimal balance, String pin, String password) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
        this.password = password;
    }

    public AccountModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
