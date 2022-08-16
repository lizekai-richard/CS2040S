///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////

import java.util.Arrays;

/**
 * class ShiftRegister
 * @author
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    private int size;
    private int tap;
    private int[] bits;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        this.size = size;
        this.tap = tap;
        this.bits = new int[size];
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        // check if the length of seed equals to size
        if(seed.length != this.size) {
            System.out.println("The size of seed doesn't match the expected size");
            return ;
        }
        for(int i = 0; i < this.size; ++i) {
            // check if there is illegal component
            if(seed[i] != 0 && seed[i] != 1) {
                System.out.println("Error!");
            } else {
                this.bits[i] = seed[i];
            }
        }
    }

    /**
     * shift
     * @return
     * Description:
     */
    @Override
    public int shift() {
        // TODO:
        int tapBit = this.bits[this.tap];
        int mostSignificantBit = this.bits[this.size-1];
        // compute the feedback as described
        int feedback = mostSignificantBit ^ tapBit;
        // since we didn't reverse the seed, every element should move onr step to the right, instead of left
        for(int i = this.size - 1; i > 0; --i) {
            this.bits[i] = this.bits[i - 1];
        }
        this.bits[0] = feedback;
        return feedback;
    }

    /**
     * generate
     * @param k
     * @return
     * Description:
     */
    @Override
    public int generate(int k) {
        // TODO:
        int[] resultBits = new int[k];
        for(int i = 0; i < k; ++i) {
            resultBits[i] = this.shift();
        }
        return this.toBinary(resultBits);
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toBinary(int[] array) {
        // TODO:
        int n = array.length;
        int ans = 0;
        for(int i = 0; i < n; ++i) {
            ans = (int) (ans + array[i] * Math.pow(2, n - i - 1));
        }
        return ans;
    }

    public int[] getBits() {
        return Arrays.copyOf(this.bits, this.bits.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.bits);
    }
}
