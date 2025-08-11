package com.example.BankingProject.Controller;

import com.example.BankingProject.Model.AccountModel;
import com.example.BankingProject.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    //  FE
    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody AccountModel accountModel) {
        if (accountService.checkAccExist(accountModel.getAccountNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Account Already Exist");
        }
        accountService.createAccount(accountModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Created");
    }

    @GetMapping("/getallAccount")
    public List<AccountModel> getAllAccount() {
        return accountService.getAllAccount();
    }

    //    FE - client
    @GetMapping("/getName/{accountNumber}")
    public ResponseEntity<String> getNameConcise(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getName(accountNumber));
    }

    //    FE - client
    @GetMapping("/getBalance/{accountNumber}")
    public ResponseEntity<String> getBalance(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }

    @GetMapping("/getAccountById/{id}")
    public AccountModel getAccountByid(@PathVariable Long id) {
        return accountService.getAccountByid(id);
    }

    @PostMapping("/updateAccount/{id}")
    public AccountModel updateAccountById(@PathVariable Long id, @RequestBody AccountModel accountModel) {
        return accountService.updateAccountById(id, accountModel);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public String deleteAccountByid(@PathVariable Long id) {
        return accountService.deleteAccountByid(id);
    }

    // FE - client
    @GetMapping("/getbyAccountNumber/{accountNum}")
    public AccountModel getAccountByAccountNumber(@PathVariable String accountNum) {
        return accountService.getAccountByAccountNumber(accountNum);
    }

    @GetMapping("/getAccountGreaterThan")
    public List<AccountModel> getAccountGreaterThan(@RequestParam BigDecimal amount) {
        return accountService.getAccountGreaterThan(amount);
    }

    @GetMapping("/checkPinNumber/{id}")
    public String ValidatePinNumber(@PathVariable Long id, @RequestParam String pin) {
        return accountService.ValidatePinNumber(id, pin);
    }

    @GetMapping("/getAccountslessthanMinimumBalance/{amount}")
    public List<AccountModel> getAccountslessthanMinimumBalance(@PathVariable BigDecimal amount) {
        return accountService.getAccountslessthanMinimumBalance(amount);
    }

    //    FE - client
    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestParam String accountNumber, @RequestParam String password) {
        AccountModel accountModel = null;
        accountModel = accountService.getAccountByAccountNumber(accountNumber);
        if (accountModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }
        if (!(accountModel.getPassword().equals(password))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid account or password number");
        }
        return ResponseEntity.ok().build();
    }
}
