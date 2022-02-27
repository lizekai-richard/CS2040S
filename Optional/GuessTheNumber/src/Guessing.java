public class Guessing {

    // Your local variables here
    private int low = 0;
    private int high = 1000;
    private int curGuess = 0;
    private int pastGuess = 0;
    /**
     * Implement how your algorithm should make a guess here
     */
    public int guess() {
        curGuess = (low + high) / 2;
        if (curGuess == pastGuess) {
            return 1000;
        } else {
            pastGuess = curGuess;
            return curGuess;
        }
    }

    /**
     * Implement how your algorithm should update its guess here
     */
    public void update(int answer) {
        if(answer == -1) {
            low  = curGuess;
        } else  {
            high = curGuess;
        }
    }
}
