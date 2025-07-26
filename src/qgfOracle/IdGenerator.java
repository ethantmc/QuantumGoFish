package qgfOracle;

public class IdGenerator {
	private static Integer nextId = 1;

	public static synchronized Integer nextPlayerId() { return nextId++; }
}