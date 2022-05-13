public class Pair {
    int a;
    int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && ((Pair) obj).a == this.a && ((Pair) obj).b == this.b;
    }

    @Override
    public int hashCode() {
        return a ^ b + (b * 9); // good enough?
    }
}