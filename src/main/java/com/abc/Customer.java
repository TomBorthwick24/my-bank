package com.abc;

import com.abc.account_types.AccountFactory;
import com.abc.account_types.BaseAccount;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<BaseAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<BaseAccount>();
    }

    public String getName() {
        return name;
    }
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BaseAccount openAccount(Constants.AccountTypes accountType) {
        AccountFactory accountFactory = new AccountFactory();
        BaseAccount account = accountFactory.openAccount(accountType);

        accounts.add(account);

        return account;
    }

    public double getTotalInterestEarned() {
        double total = 0;
        for (BaseAccount a : accounts)
            total += a.getInterestEarned();
        return total;
    }

    public String getStatement() {
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (BaseAccount account : accounts) {
            statement += account.getAccountSummary() + "\n";
            total += account.sumTransactions();
        }
        statement += "Total In All Accounts: " + toDollars(total);
        return statement;
    }

// Do we need this? abs?
    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
