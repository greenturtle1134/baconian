package lib;

public interface MathLib {
	public static int intPow(int base, int exp) {
		int c=0;
		for(int i=0; i<exp; i++) {
			c*=base;
		}
		return c;
	}
}
