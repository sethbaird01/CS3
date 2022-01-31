import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WorstFit {

    PriorityQueue<Disk> q;
    // priority queue of disks to add files to
    // unordered list of disks / for later use

    public WorstFit(File file) throws FileNotFoundException {
        this.q = new PriorityQueue<Disk>();

        // import from file
        // initialize list and queue of disks

        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            addData(Integer.parseInt(scan.nextLine()));
        }
        scan.close();
    }

    private Double totalUsed() {
        Integer temp = 0;
        for (Disk disk : this.q) {
            temp += disk.spaceUsed();
        }
        return Double.valueOf(temp);
    }

    private void addData(Integer dataIn) {
        // check if a new disk is needed
        // if yes then call createDisk(data)
        // if not add to existing disk

        // go through list of disks, see if there is free space
        // if free space add, if not go next
        // if end of list add new disk createDisk()

        if (this.q.isEmpty()) {
            createDisk(dataIn);
            return;
        } else {
            for (int i = 0; i < this.q.size(); i++) {// using priority queue
                Disk disk = this.q.poll();
                if (disk.spaceFree() >= dataIn) {
                    disk.add(dataIn);
                    this.q.offer(disk);
                    return;
                }
                this.q.offer(disk);
            }
            // no disk with free space, add one
            createDisk(dataIn);
            return;
        }
    }

    private void createDisk(Integer dataIn) {
        // makes new disk object, adds data to it, adds to lists
        Disk d = new Disk();
        d.add(dataIn);
        this.q.add(d);
    }

    private Integer totalFiles() {
        Integer out = 0;
        for (Disk disk : this.q) {
            out += disk.getList().size();
        }
        return out;
    }

    public void print() {
        System.out.println("Total size = " + (this.totalUsed() / 1000000.0) + " GB");
        System.out.println("Disks required = " + this.q.size());
        while (this.totalFiles() < 100 && !this.q.isEmpty()) {
            System.out.println(this.q.poll());
        }
    }
    // read the Set of file sizes (0<n<1000000)
    // output the number of disks used
    // output the (sum of all file sizes / 1000000)
    // if number of files < 100, print disks in order of remaining space
    // printing out the disk - remaining space or contents

    public static void main(String[] args) throws FileNotFoundException {
        WorstFit wf = new WorstFit(new File("data/input20.txt"));
        wf.print();
    }

}
