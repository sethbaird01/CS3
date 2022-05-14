import java.util.Arrays;

public class ThieveryTuple {
    int[] v;
    int[] w;
    int itemCount;
    int weightMax;

    public ThieveryTuple(int[] v, int[] w, int itemCount, int weightMax) {
        this.v = v;
        this.w = w;
        this.itemCount = itemCount;
        this.weightMax = weightMax;
    }

    public ThieveryTuple decreaseiMax(int dec) {
        return new ThieveryTuple(v, w, itemCount - dec, weightMax);
    }

    public ThieveryTuple decreaseiMaxwMax(int decI, int decW) {
        return new ThieveryTuple(v, w, itemCount - decI, weightMax - decW);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                ((ThieveryTuple) obj).v == this.v &&
                ((ThieveryTuple) obj).w == this.w &&
                ((ThieveryTuple) obj).itemCount == this.itemCount &&
                ((ThieveryTuple) obj).weightMax == this.weightMax;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(v) ^ Arrays.hashCode(w) * itemCount / weightMax;
    }
}
