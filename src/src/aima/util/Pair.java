package aima.util;

public class Pair {
	private int first;

	private int second;

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public Pair(int a, int b) {
		first = a;
		second = b;
	}

	public int hashCode() {
		return 0;
	}

	public String toString() {
		return "( " + first + " , " + second + " ) ";
	}
}