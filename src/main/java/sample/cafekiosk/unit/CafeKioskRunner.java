package sample.cafekiosk.unit;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

public class CafeKioskRunner {

    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano(), 1);
        System.out.println(">> 아메리카노 추가");

        cafeKiosk.add(new Latte(), 1);
        System.out.println(">> 라떼 추가");

        int totalPrice = cafeKiosk.calculateTotalPrice();
        System.out.println("total price: " + totalPrice);
    }
}
