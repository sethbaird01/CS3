public class Employee {
    String name;
    int ID;

    Employee(int ID, String name){
        this.name = name;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return this.ID + " | " + this.name;
    }
}
