package hw7;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Cat[] cat = {
                new Cat("Барсик"),
                new Cat("Мурзик"),
                new Cat("Пыжик"),
                new Cat("Верблюжонок"),
                new Cat("Димка"),
                new Cat("Морфей"),
                new Cat("Кот в сапогах"),
                new Cat("Котяра")
        };
        Plate plate = new Plate(100);
        for (Cat myCat : cat) {
            myCat.eat(plate);
        }
        plate.addFood(30);
        cat[7].eat(plate);


    }

}
