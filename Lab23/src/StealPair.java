import java.util.Arrays;

public class StealPair {
    int[] a;
    int b;

    public StealPair(int[] a, int b) {
        this.a = a;
        this.b = b;
    }

    public StealPair add(int plus){
        return new StealPair(this.a, b + plus);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && ((StealPair) obj).a == this.a && ((StealPair) obj).b == this.b;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a) ^ b;
    }
}
