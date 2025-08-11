package com.example.BankingProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class TransactionModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String type;  // deposit, withdrawal, transfer
    private BigDecimal amount;
    private LocalDate date;
    private String accountNumber ;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private AccountModel accountModel;


    public TransactionModel(Long id, String type, BigDecimal amount, LocalDate date, AccountModel accountModel) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.accountModel = accountModel;
    }

    public TransactionModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
