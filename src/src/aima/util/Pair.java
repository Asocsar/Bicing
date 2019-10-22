package aima.util;

public class Pair {
	private int first;

	private int second;

    public Pair() {
        
    }

    public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public void setFirst(int a) {first = a;}

	public void setSecond (int a) {second = a;}

	public void sumFirst(int a) {first += a;}

	public void sumSecond(int a) {second += a;}

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