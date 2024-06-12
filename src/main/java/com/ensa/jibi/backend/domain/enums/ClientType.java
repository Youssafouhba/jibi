package com.ensa.jibi.backend.domain.enums;

public enum ClientType {
    Hsab_1(200),
    Hsab_2(5000),
    Hsab_3(20000),
    Hsab_PRO(0);

    private final int accountLimit;

    ClientType(int accountLimit) {
        this.accountLimit = accountLimit;
    }

    public int getAccountLimit() {
        return accountLimit;
    }
}
