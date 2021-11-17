public class FireCell {

    public enum Status {
        DIRT, GREEN, FIRE
    }

    private Status status;

    public FireCell() {
        status = Status.DIRT;
        if (Math.random() <= 0.60)
            status = Status.GREEN;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status n) {
        status = n;
    }
}
