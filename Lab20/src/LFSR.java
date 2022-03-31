public class LFSR {
	private int N; // number of bits in the LFSR
	private int[] reg; // reg[i] = ith bit of LFSR, reg[0] is leftmost bit
	private int tap; // index of the tap bit

	/** constructor to create LFSR with the given initial seed and tap */
	public LFSR(String seed, int tap) {
		this.N = seed.length();
		this.reg = new int[this.N];
		for (int i = 0; i < this.N; i++) {
			reg[i] = Integer.parseInt(seed.charAt(i) + "");
		}
		this.tap = tap;
	}

	/** simulate one step and return the new bit as 0 or 1 */
	public int step() {
		int newBit = this.reg[0] ^ this.reg[this.N - this.tap - 1];

		// System.out.println("tap: " + this.tap + " N: " + this.N);
		// System.out.println(this.reg[this.N - 1] + " ^ " + this.reg[this.N - this.tap - 1] + " = " + newBit);

		System.arraycopy(this.reg, 1, this.reg, 0, this.N - 1);
		this.reg[this.N - 1] = newBit;
		return newBit; // replace
	}

	/** simulate k steps and return k-bit integer */
	public int generate(int k) {
		for (int i = 0; i < k; i++) {
			this.step();
		}
		return Integer.parseInt(this.toString().substring(N-k, N), 2); 
	}

	@Override
	public String toString() {
		String out = "";
		for (int i : this.reg) {
			out += i;
		}
		return out;
	}

	public static void main(String[] args) {
		// In Eclipse, Ctrl + / (front slash) toggles comments of highlighted portion
		// test01();
		// test02();
		// test03();
		// test04();
	}

	/** test toString() and constructor */
	public static void test01() {
		LFSR lfsr = new LFSR("01101000010", 8);
		System.out.println(lfsr + "\n");

		// should output: 01101000010
	}

	/** test step() method */
	public static void test02() {
		LFSR lfsr = new LFSR("01101000010", 8);

		for (int i = 0; i < 10; i++) {
			int bit = lfsr.step();
			System.out.println(lfsr + " " + bit);
		}
		/*
		 * should output:
		 * 
		 * 11010000101 1
		 * 10100001011 1
		 * 01000010110 0
		 * 10000101100 0
		 * 00001011001 1
		 * 00010110010 0
		 * 00101100100 0
		 * 01011001001 1
		 * 10110010010 0
		 * 01100100100 0
		 */
	}

	/** test generate() method */
	public static void test03() {
		System.out.println();

		LFSR lfsr = new LFSR("01101000010", 8);

		for (int i = 0; i < 10; i++) {
			int r = lfsr.generate(5);
			System.out.println(lfsr + " " + r);
		}
		/*
		 * should output:
		 * 
		 * 00001011001 25
		 * 01100100100 4
		 * 10010011110 30
		 * 01111011011 27
		 * 01101110010 18
		 * 11001011010 26
		 * 01101011100 28
		 * 01110011000 24
		 * 01100010111 23
		 * 01011111101 29
		 */
	}

	/** test generate() method again */
	public static void test04() {
		System.out.println();

		LFSR lfsr = new LFSR("01101000010100010000", 16);

		for (int i = 0; i < 10; i++) {
			int r = lfsr.generate(8);
			System.out.println(lfsr + " " + r);
		}
		/*
		 * should output:
		 * 01010001000000101010 42
		 * 00000010101011011001 217
		 * 10101101100100010111 23
		 * 10010001011111000001 193
		 * 01111100000100011010 26
		 * 00010001101010011100 156
		 * 10101001110010011100 156
		 * 11001001110011100111 231
		 * 11001110011110000111 135
		 * 01111000011110111101 189
		 */
	}
}
