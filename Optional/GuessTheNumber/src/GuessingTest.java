import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GuessingTest {


    @Test
    public void guess() {
        for(int hiddenNumber = 0; hiddenNumber <= 1000; ++hiddenNumber) {
            Random r = new Random();
            // System.out.println(hiddenNumber);
            Guessing g = new Guessing();

            int remainingGuesses = 10;
            while (remainingGuesses >= 0) {
                int guess = g.guess();
                // System.out.println(guess);
                if (guess == hiddenNumber) {
                    break;
                } else if(guess > hiddenNumber) {
                    g.update(1);
                    remainingGuesses--;
                } else {
                    g.update(-1);
                    remainingGuesses--;
                }
            }
            assertTrue(remainingGuesses >= 0);
        }

    }
}