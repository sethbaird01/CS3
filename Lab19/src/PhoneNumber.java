public class PhoneNumber {
    String num;

    public PhoneNumber(String num) {
        this.num = num;
    }

    // @Override
    // public int hashCode() {
    //     // TODO Auto-generated method stub
    //     return super.hashCode();
    // }

    @Override
    public boolean equals(Object obj) {
        return this.num.equals(((PhoneNumber) (obj)).num);
    }

    public PhoneNumber(int in) {
        this.num = in+"";
    }

    @Override
    public String toString() {
        return this.num;
    }
}
