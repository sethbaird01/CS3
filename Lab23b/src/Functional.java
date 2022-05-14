import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Functional {
    public static void main(String[] args) {

        // print on separate lines
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        nums.forEach(n -> System.out.println(n));

        // remove even
        nums.removeIf(n -> n % 2 == 0);
        System.out.println(nums);

        // count odd numbers
        nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println(nums.stream().filter(n -> n % 2 == 1).count());

        // print all elements beginning with 'a'
        List<String> list = new ArrayList<>(Arrays.asList("hello", "and", "goodbye"));
        list.stream().filter(t -> t.startsWith("a")).forEach(n -> System.out.println(n));

        // double value of all numbers
        nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println(nums.stream().map(n -> n * 2).collect(Collectors.toList()));

        // codingbat problem
        nums = new ArrayList<>(Arrays.asList(3, 1, 4));
        System.out.println(nums.stream().map(n -> n * n).map(n -> n + 10)
                .filter(n -> (!n.toString().endsWith("5") && !n.toString().endsWith("6")))
                .collect(Collectors.toList()));

        // apply 12% tax
        List<Double> prices = new ArrayList<>(Arrays.asList(10.0, 20.0, 50.0));
        System.out.println(prices.stream().map(n -> n * 1.12).collect(Collectors.toList()));

        // create, sum, output
        Arrays.stream(new int[] { 1, 2, 3, 4 }).reduce((t, u) -> t + u).ifPresent(n -> System.out.println(n));

        // convert int list to taxes double list
        nums = new ArrayList<>(Arrays.asList(10, 20, 50));
        prices = nums.stream().map(n -> n * 1.12).collect(Collectors.toList());
        System.out.println(prices);

        // find max number
        nums.stream().max((o1, o2) -> o1 - o2).ifPresent(n -> System.out.println(n));
        // without using max()
        System.out.println(nums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0));

        // jbutton
        JButton button = new JButton("Click here");
        JFrame frame = new JFrame("Button test"); // window to contain the button

        //
        button.addActionListener(e -> System.out.println("Button clicked!"));

        frame.setSize(200, 200); // set the size of the container window
        frame.add(button); // add button to the window
        frame.setVisible(true); // make frame visible

        // oldest person in a list

        List<Person> users = new ArrayList<>();
        users.add(new Person("Sarah", 40));
        users.add(new Person("Peter", 50));
        users.add(new Person("Lucy", 60));
        users.add(new Person("Albert", 20));
        users.add(new Person("Charlie", 30));

        //
        System.out.println(users.stream().mapToInt(p -> p.age).max());
        // without using mapToInt (may be implementing Comparable in-line?- not sure)
        System.out.println(users.stream().sorted((o1, o2) -> ((Person) o2).age - ((Person) o1).age)
                .collect(Collectors.toList()).get(0));

    }
}
