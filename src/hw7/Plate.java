package hw7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void info() {
        System.out.println("Еда: " + food);
    }

    public int getFood() {
        return food;
    }

    public boolean decreaseFood(int amount) {
        if (food-amount >= 0) {
            food -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {

        food += amount;
        System.out.println("В тарелку добавили еду.");
        info();

    }

}
