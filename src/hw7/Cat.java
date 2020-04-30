package hw7;

import java.sql.SQLOutput;
import java.util.SortedMap;

public class Cat {
    private String name;
    private boolean fullNess; //Сытость

    final int APPETITE = 14;

    public Cat(String name) {
        this.name = name;
        this.fullNess = false;
    }

    public void eat(Plate plate) {
        fullNess = plate.decreaseFood(APPETITE);
        if (fullNess) {
            System.out.println(this.name + " поел, наелся!");
            System.out.println("Сытость: " + fullNess);
        } else {
            System.out.println(this.name + " не поел!");
            System.out.println("Сытость: " + fullNess);
        }
        plate.info();
    }


}
