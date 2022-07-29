package com.example.crex;

public class MultiViewClass {
    CardType cardType;
    Match m;
    String date;

    public MultiViewClass(CardType cardType, Match m, String date) {
        this.cardType = cardType;
        this.m = m;
        this.date = date;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Match getM() {
        return m;
    }

    public void setM(Match m) {
        this.m = m;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MultiViewClass{" +
                "cardType=" + cardType +
                ", m=" + m +
                ", date='" + date + '\'' +
                '}';
    }
}
