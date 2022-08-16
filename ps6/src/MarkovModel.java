import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no character
	public static final char NOCHARACTER = (char) 0;

	private final int order;
	public HashMap<String, HashMap<Character, Integer>> table;

	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of characters to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		this.order = order;
		this.table = new HashMap<>();

		// Initialize the random number generator
		generator.setSeed(seed);
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model here
		for(int i = 0; i < text.length() - this.order; ++i) {
			String kgram = text.substring(i, i + this.order);
			char nextCharacter = text.charAt(i + this.order);
			if (table.containsKey(kgram)) {
				HashMap<Character, Integer> map = table.get(kgram);
				if (map.containsKey(nextCharacter)) {
					int times = map.get(nextCharacter);
					map.put(nextCharacter, times + 1);
				} else {
					map.put(nextCharacter, 1);
				}
			} else {
				HashMap<Character, Integer> map = new HashMap<>();
				map.put(nextCharacter, 1);
				table.put(kgram, map);
			}
		}
	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		if (kgram.length() != order) {
			throw new IllegalArgumentException();
		} else if (!table.containsKey(kgram)) {
			return 0;
		} else {
			HashMap<Character, Integer> map = table.get(kgram);
			int times = 0;
			for (Character c: map.keySet()) {
				times += map.get(c);
			}
			return times;
		}
	}

	/**
	 * Returns the number of times the character c appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, char c) {
		if (kgram.length() != order) {
			throw new IllegalArgumentException();
		} else if (!table.containsKey(kgram)) {
			return 0;
		} else {
			HashMap<Character, Integer> map = table.get(kgram);
			return map.getOrDefault(c, 0);
		}
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOCHARACTER if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public char nextCharacter(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.
		if (!table.containsKey(kgram)) {
			return NOCHARACTER;
		}
		HashMap<Character, Integer> map = table.get(kgram);
		if (map.isEmpty()) {
			return NOCHARACTER;
		}
		int totalTimes = getFrequency(kgram);
		int randomNumber = generator.nextInt(totalTimes) + 1;
		char nextCharacter = NOCHARACTER;
		Object[] keys = map.keySet().toArray();
		Arrays.sort(keys);

		for(Object k: keys) {
			Character c = (Character) k;
			int times = map.get(c);
			if (times >= randomNumber) {
				nextCharacter = c;
				break;
			} else {
				randomNumber -= times;
			}
		}
		return nextCharacter;
	}

}
