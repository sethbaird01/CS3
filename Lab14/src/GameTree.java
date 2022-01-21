import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree {

	Node root, current;
	String fileName;

	private class Node {
		String data;
		Node left, right;

		public Node(String data) {
			this.data = data;
			left = right = null;
		}
	}

	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *                 this is the name of the file we need to import the game
	 *                 questions
	 *                 and answers from.
	 */
	public GameTree(String fileName) {
		try {
			this.fileName = fileName;
			File obj = new File(fileName);
			Scanner reader = new Scanner(obj);
			root = readIn(reader);
			reader.close();
			current = root;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Node readIn(Scanner reader) {
		if (!reader.hasNextLine()) {
			return null;
		}

		String currLine = reader.nextLine().trim();
		Node curr = new Node(currLine);
		if (curr.data.endsWith("?")) {
			curr.right = readIn(reader);
			curr.left = readIn(reader);
		}
		return curr;
	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *             The question to add where the old answer was.
	 * @param newA
	 *             The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA) {
		String temp = current.data;
		current.data = newQ;
		current.right = new Node(newA);
		current.left = new Node(temp);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer() {
		return !current.data.endsWith("?");

	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer. Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent() {
		return current.data;

	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
		current = yesOrNo == Choice.Yes ? current.right : current.left;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart() {
		current = root;
	}

	@Override
	public String toString() {
		// used stringbuilder this time
		StringBuilder sb = new StringBuilder("");
		stringHelper(sb, root, 0);
		return sb.toString();
	}

	public void stringHelper(StringBuilder sb, Node curr, int depth) {
		if (curr != null) {
			stringHelper(sb, curr.left, depth + 1);
			StringBuilder indent = new StringBuilder("");
			for (int i = 0; i < depth; i++) {
				indent.append("- ");
			}
			sb.append(indent);
			sb.append(curr.data + "\n");
			stringHelper(sb, curr.right, depth + 1);
		}
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(fileName));
			saveHelper(out, root);
			out.close();
		} catch (IOException io) {
			System.out.println("IOException: " + fileName);
		}
	}

	private void saveHelper(PrintWriter out, Node root) {
		if (root != null) {
			out.println(root.data);
			saveHelper(out, root.left);
			saveHelper(out, root.right);
		}
	}
}
