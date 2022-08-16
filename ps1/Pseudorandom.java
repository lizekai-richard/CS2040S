public class Pseudorandom {
    public static void main(String[] args) {
        int[] seed = new int[] {1, 1, 0, 1, 0, 0, 1, 0, 1};
        int maximumEpochs = 1000;
        int numberZeros = 0;
        int numberOnes = 0;

        ShiftRegister shifter = new ShiftRegister(9, 3);
        shifter.setSeed(seed);
        for(int i = 0; i < maximumEpochs; ++i) {
            int output = shifter.shift();
            if(output == 0) {
                numberZeros++;
            } else {
                numberOnes++;
            }
        }
        System.out.println("# Zeros: " + numberZeros);
        System.out.println("# Ones: "+ numberOnes);

        /* result:
        # Zeros: 501
        # Ones: 499
        */
    }
}
