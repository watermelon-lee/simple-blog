package com.model;

public interface AccountDAO {
    boolean isUserExisted(Account account);
    void addAccount(Account account);
    Account getAccount(Account account);
}
