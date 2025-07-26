package qgfOracle;
/**
 * @author Ethan Mc Donald
 *
 */
public class IdGenerator {
	private static Integer nextId = 1;

	public static synchronized Integer nextPlayerId() { return nextId++; }
}