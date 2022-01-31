import java.util.LinkedList;

public class Disk implements Comparable<Disk> {

    LinkedList<Integer> list;
    // list of file (sizes) stored in disk

    Integer used;
    // dynamically increasing size used in KB

    public Disk() {
        this.list = new LinkedList<Integer>();
        this.used = 0;
    }

    // returns used space in KB
    public Integer spaceUsed() {
        return this.used;
    }

    public Integer spaceFree() {
        return 1000000- this.used;
    }

    public LinkedList<Integer> getList(){
        return this.list;
    }

    public void add(Integer in) {
        this.list.add(in);
        this.used += in;
    }

    @Override
    public int compareTo(Disk in) {
        return this.spaceUsed() - in.spaceUsed();
    }

    @Override
    public String toString() {
        String out = this.spaceFree() + ": ";

        for (Integer integer : this.list) {
            out += integer + " ";
        }
        return out;
    }
}
