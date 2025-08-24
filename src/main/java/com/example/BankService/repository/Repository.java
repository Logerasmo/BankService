package com.example.BankService.repository;

import com.example.BankService.other.BankCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Repository {
    private Map<String, BankCard> cardMap = new HashMap<>();

    public boolean addCard(BankCard card){
        if (cardMap.containsKey(card.getNum())) return false;
        cardMap.put(card.getNum(), card);
        return true;
    }

    public Optional<BankCard> getCardByNumber(String num){
        return Optional.ofNullable(cardMap.get(num));
    }
}
