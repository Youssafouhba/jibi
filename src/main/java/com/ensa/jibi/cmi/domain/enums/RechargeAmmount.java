package com.ensa.jibi.cmi.domain.enums;

public enum RechargeAmmount {
    RECHARGE_10DH(10),
    RECHARGE_20DH(20),
    RECHARGE_50DH(50),
    RECHARGE_70DH(70),
    RECHARGE_80DH(80),
    RECHARGE_90DH(90),
    RECHARGE_100DH(100);

    private final int value;

    RechargeAmmount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
