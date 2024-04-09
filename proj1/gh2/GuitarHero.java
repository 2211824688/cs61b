package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] guitarStrings = new GuitarString[keyboard.length()];

        for (int i = 0; i < keyboard.length(); i++) {
            double freq = 440 * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(freq);
        }

        while (true) {
            int index = -1;
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                if (index >= 0) {
                    guitarStrings[index].pluck();
                }
            }
            double sample = 0;
            for (int i = 0; i < guitarStrings.length; i++) {
                sample += guitarStrings[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < guitarStrings.length; i++) {
                guitarStrings[i].tic();
            }
        }
    }
}
