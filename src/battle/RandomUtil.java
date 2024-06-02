package battle;

import java.util.Random;

public class RandomUtil {
	private static final Random RANDOM = new Random();
	
	public static int randomInclusive(int min, int maxInc) {
		return random(min, maxInc + 1);
	}
	
	public static int random(int min, int max) {
		return RANDOM.nextInt(max - min) + min;
	}
	
	public static int random(int max) {
		return RANDOM.nextInt(max);
	}
	
	public static int randomInclusive(int maxInc) {
		return random(maxInc + 1);
	}
}
