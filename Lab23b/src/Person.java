public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age;
    }

    int getAge() {
        return this.age;
    }
}