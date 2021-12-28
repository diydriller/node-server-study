public abstract class Life {

    private int age;
    public abstract void move();

    public Life(int age) {
        this.age = age;
    }
    public void age(){
        age++;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
