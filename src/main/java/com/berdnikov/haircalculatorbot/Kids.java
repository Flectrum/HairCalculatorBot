package com.berdnikov.haircalculatorbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Kids implements HairType {
    @Override
    public String getPrice(String message) {
        double lengthPrice=0;
        String  messages [] = message.trim().split("[\s]");
        double length= Double.parseDouble(messages[1]);
        switch ((int) length) {
            case 30 -> lengthPrice = 2.75;
            case 40 -> lengthPrice = 2.95;
            case 50 -> lengthPrice = 3.15;
            case 60 -> lengthPrice = 3.35;
        }
        double pieces= Double.parseDouble(messages[2]);
        double price = lengthPrice*pieces;

        return String.valueOf(price);
    }
}
