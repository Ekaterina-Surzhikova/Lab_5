public class Cat implements Meowable {
    private String name;
    private int meowCount;

    public Cat(String name) {
        this.name = name;
        this.meowCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getMeowCount() {
        return meowCount;
    }

    public void resetMeowCount() {
        this.meowCount = 0;
    }

    @Override
    public void meow() {
        meowCount++;
        System.out.println(name + ": мяу!");
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }
}