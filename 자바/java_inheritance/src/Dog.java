public class Dog extends Animal{
    public Dog(int age) {
        super(age);
    }

    @Override
    public void move() {
        System.out.println("move by 4 legs");
    }

    @Override
    public void bark() {
        System.out.println("dog bark");
    }
}
