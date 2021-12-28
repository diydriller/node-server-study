public class Student extends Person {
    public Student(int age) {
        super(age);
    }

    @Override
    public void move() {
        System.out.println("move by 2 legs");
    }

    @Override
    public void say() {
        System.out.println("student say");
    }
}
