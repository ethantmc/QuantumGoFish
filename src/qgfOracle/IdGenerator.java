package qgfOracle;

public class IdGenerator {
	private static long nextId = 1;

	public static synchronized long nextPlayerId() {
		return nextId++;
	}
}