import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BoggleSolver
{
	Dictionary d;

	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		try {
			d  = new Dictionary(new File(dictionaryName));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary file not found");
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		//hashset stores all words
		HashSet<String> set = new HashSet<String>();

		for (int r = 0; r < board.rows(); r++) {
			for (int c = 0; c < board.cols(); c++) {
				recurse(board, r, c);
			}
		}

		//returning hashset which now has all combinations
		return set;
	}

	List<Character> getNeighbors(BoggleBoard board, int r, int c){
        List<Character> list = new ArrayList<Character>();
    try {
        list.add(board.getLetter(r-1, c));
    } catch (Exception e) {}
    try {
        list.add(board.getLetter(r, c-1));
    } catch (Exception e) {}
        //current
    try {
        list.add(board.getLetter(r, c+1));
    } catch (Exception e) {}
    try {
        list.add(board.getLetter(r+1, c));
    } catch (Exception e) {}

    return list;
    }

	public List<String> recurse(BoggleBoard b, int x, int y, List<String> list){


		//replace list, implement using the hashset fmor above
		String currWord = "";

		//returns all valid words starting with selected lexstter
		//maybe checks against hashset then no need to return array
		//new hashset per board

		if(currWord.length() > 2 && d.doesExist(currWord) ){
			list.add(currword);
		}


		return recurse(b, /* TODO */, list);
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		if(!d.doesExist(word)){
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

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
