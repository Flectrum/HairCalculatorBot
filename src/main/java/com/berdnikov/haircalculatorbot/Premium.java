package com.berdnikov.haircalculatorbot;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Premium implements HairType {
    @Override
    public  String getPrice(String message) {
        double lengthPrice=0;
        String  messages [] = message.trim().split("[!,?._'@\\s\\s]+");
        double length= Double.parseDouble(messages[1]);
        switch ((int) length) {
            case 40 -> lengthPrice = 2.60;
            case 50 -> lengthPrice = 2.80;
            case 60 -> lengthPrice = 2.97;
            case 70 -> lengthPrice = 3.12;
            case 80 -> lengthPrice = 3.52;
            case 90 -> lengthPrice = 3.72;
        }
        double pieces= Double.parseDouble(messages[2]);
        double price = lengthPrice*pieces;

        return String.valueOf(price);
    }
}
