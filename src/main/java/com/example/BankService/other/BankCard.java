package com.example.BankService.other;

import java.util.Objects;

public class BankCard {
    private final String num;
    private Integer money;
    private final String cvc;
    private final String date;

    public BankCard(String num, Integer money, String cvc, String date) {
        this.num = num;
        this.money = money;
        this.cvc = cvc;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(num, bankCard.num);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(num);
    }

    public boolean checkInfo(String cvc, String date){
        return this.cvc.equals(cvc) && this.date.equals(date);
    }

    public boolean spendMoney(Integer sum){
        if (money >= sum){
            money -= sum;
            return true;
        } else return false;
    }

    public void addMoney(Integer sum){
        money += sum;
    }

    public String getNum() {
        return num;
    }

    public Integer getMoney() {
        return money;
    }
}
