import java.util.*;

public class ShiftRepeat {
    public static boolean contain(List<int[]> list, int[] output) {
        for(int[] seq: list) {
            boolean flag = true;
            for(int i = 0; i < seq.length; ++i) {
                if(seq[i] != output[i]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] seed = new int[] {1, 1, 0, 1, 0, 0, 1, 0, 1, 1};
        int[] timesPerTap = new int[10];
        int maximumEpochs = 1000;
        for(int tap = 0; tap < seed.length; ++tap) {
            int epoch = 0;
            List<int[]> outputs = new ArrayList<>();
            ShiftRegister shifter = new ShiftRegister(10, tap);
            shifter.setSeed(seed);
            outputs.add(seed);
            while (epoch <= maximumEpochs) {
                shifter.shift();
                epoch++;
                if(contain(outputs, shifter.getBits())) {
                    timesPerTap[tap] = epoch;
                    break;
                } else {
                    outputs.add(shifter.getBits());
                }
            }
        }

        for(int i = 0; i < 10; ++i) {
            System.out.println("Tap: " + i + " Times to repeat: " + timesPerTap[i]);
        }

        /* result
        Tap: 0 Times to repeat: 889
        Tap: 1 Times to repeat: 42
        Tap: 2 Times to repeat: 0
        Tap: 3 Times to repeat: 62
        Tap: 4 Times to repeat: 15
        Tap: 5 Times to repeat: 62
        Tap: 6 Times to repeat: 0
        Tap: 7 Times to repeat: 42
        Tap: 8 Times to repeat: 889
        Tap: 9 Times to repeat: 11

        It seems that tap = 0 or 8 is good
         */
    }
}
