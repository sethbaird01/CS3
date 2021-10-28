
// import java.awt.Font;
import java.awt.Color;
import java.util.Random;

enum GemType {
	GREEN, BLUE, ORANGE, WILD; // define the different types of Gems, comma delimited
}

public class Gem { // tested working

	private int value;
	private GemType type;

	static final int[] possibleValues = { 0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50 };
	static final Random rand = new Random();
	static final String imageParentFolder = "/Users/seth/Documents/GitHub/CS3/Lab09/src/"; //must have ending slash

	public Gem() {
		this.value = possibleValues[rand.nextInt(possibleValues.length)];
		// wild will be spawned 1/10 chance

		int totalProb = rand.nextInt(10);
		boolean lucky = totalProb == 8; // any number would do here, since all numbers <=10 have the same chance

		if (lucky) {
			this.type = GemType.WILD; // picks between colored gems
		} else {
			this.type = GemType.values()[rand.nextInt(3)]; // picks between colored gems
		}
	}

	public Gem(GemType type, int points) {
		this.value = points;
		this.type = type;
	}

	@Override
	public String toString() {
		return type + " " + value;
	}

	public GemType getType() {
		return this.type;
	}

	public int getPoints() {
		return this.value;
	}

	public void draw(double x, double y) {
		String path = imageParentFolder + "gem_" + this.type.toString().toLowerCase() + ".png";
		StdDraw.picture(x, y, path);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(x, y, String.valueOf(this.value));
		StdDraw.setPenColor(Color.BLACK);

	}

	/** Tester main method */
	public static void main(String[] args) {
		final int maxGems = 16;

		// Create a gem of each type
		Gem green = new Gem(GemType.GREEN, 10);
		Gem blue = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green + ", " + green.getType() + ", " + green.getPoints());
		System.out.println(blue + ", " + blue.getType() + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++) {
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
