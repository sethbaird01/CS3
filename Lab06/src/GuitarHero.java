
public class GuitarHero {

    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440 * Math.pow(1.05956, (i - 24)));
        }

        char key = 'q';
        int keyIndex = keyboard.indexOf(key);

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

            double sample = strings[keyboard.indexOf(key)].sample();

            if (keyIndex != -1) {

                StdAudio.play(sample);

                // advance the simulation of each guitar string by one step
                strings[keyIndex].tic();
            }
        }

    }

}
