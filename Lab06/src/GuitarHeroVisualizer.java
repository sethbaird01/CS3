public class GuitarHeroVisualizer {
    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440 * Math.pow(1.05956, (i - 24)));
            // System.out.println("hz: "+(440 * Math.pow(1.05956, (i - 24)))+ " key:
            // "+keyboard.charAt(i));
        }
        char key = 'q';
        double x = 0;
        int keyIndex = keyboard.indexOf(key);

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char temp = StdDraw.nextKeyTyped();
                if (keyboard.contains(String.valueOf(temp))) {
                    key = temp;
                    keyIndex = keyboard.indexOf(key);
                    strings[keyIndex].pluck();
                }
            }

            if (keyIndex != -1) {

                double sample = strings[keyIndex].sample();

                int n = 10; // smaller number, finer resolution
                int m = 10000; // bigger number, longer line
                double increment = 0.001; // smaller number, closer dots

                // plot dot at x (always adding small increment) and y (frequency of pitch
                // scaled to >-1, <1)

                if (strings[keyIndex].time() % n == 0) {
                    StdDraw.point(x, (0.4 + (sample*0.75)));
                    x += increment;
                }

                if (strings[keyIndex].time() % m == 0) {// change (n*m) to however many increments wide the canvas
                                                              // is
                    StdDraw.clear();
                    x = 0;
                }

                // advance the simulation of each guitar string by one step
                strings[keyIndex].tic();
            }
        }
    }

}
