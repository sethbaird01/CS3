import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

public class BoggleSolver {
	Dictionary d;
	// BoggleBoard b;
	HashSet<String> set;

	// Initializes the data structure using the given array of strings as the
	// dictionary.
	// (You can assume each word in the dictionary contains only the uppercase
	// letters A - Z.)
	public BoggleSolver(String dictionaryName) {
		try {
			d = new Dictionary(new File(dictionaryName));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found");
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable
	// object
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		// hashset stores all words
		set = new HashSet<String>();

		findWords(board, d);

		// returning hashset which now has all combinations
		return set;
	}

	// recursive word finding algorithm
	void findWordsUtil(BoggleBoard b, boolean visited[][], int i, int j, String str, Dictionary d) {
		int rows = b.rows(), cols = b.cols();

		// mark current cell as visited and add current character to str
		visited[i][j] = true;
		str += b.getLetter(i, j);

		// add current string if it meets criteria
		if (str.length() > 2 && d.doesExist(str)) {
			this.set.add(str);
		}

		// check 8 neighboring squares of boggle[i][j]
		for (int row = (i - 1); row <= (i + 1) && row < rows; row++) {
			for (int col = (j - 1); col <= (j + 1) && col < cols; col++) {
				if (row >= 0 && col >= 0 && !visited[row][col]) {
					findWordsUtil(b, visited, row, col, str, d);
				}
			}
		}

		// remove current character from string and mark current cell's visited to false
		str = "" + str.charAt(str.length() - 1);
		visited[i][j] = false;
	}

	// finds all words present in dictionary (helper method)
	void findWords(BoggleBoard b, Dictionary d) {

		int rows = b.rows(), cols = b.cols();
		// mark all characters not visited
		boolean visited[][] = new boolean[b.rows()][b.cols()];
		String str = "";

		// go over every character in board,
		// then look for all words starting with this character
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				findWordsUtil(b, visited, i, j, str, d);
			}
		}
	}

	// returns the score of the given word if its in the dictionary, zero otherwise.
	public int scoreOf(String word) {
		if (!d.doesExist(word)) {
			return 0;
		}

		switch (word.length()) {
			case 0:
				return 0;

			case 1:
				return 0;

			case 2:
				return 0;

			case 3:
				return 1;

			case 4:
				return 1;

			case 5:
				return 2;

			case 6:
				return 3;

			case 7:
				return 5;

			default: // 8 or greater
				return 11;
		}

	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH = "./data/";
		BoggleBoard board = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); // should print 84

		// new BoggleGame(4, 4);
	}

}
