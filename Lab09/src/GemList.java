public class GemList {

	private class Node {
		private Gem gem;
		private Node next;

		Node(Gem in) {
			this.gem = in;
		}

		@Override
		public String toString() {
			return this.gem.toString();
		}
	}

	private Node head;
	private int size;

	public GemList() {
		this.size = 0;
		this.head = null;
	}

	public GemList(Gem i) {
		this.size = 1;
		this.head = new Node(i);
	}

	public int getSize() {
		return this.size;
	}

	public void insertBefore(Gem i, int idx) {// tested working
		int index = 0;
		this.size++;

		if (this.head == null) {
			// goal index irrelevant, just insert element
			this.head = new Node(i);

			return;
		}

		if (idx <= 0) { // edge case, clicking before the first element
			Node temp = this.head;
			this.head = new Node(i);
			this.head.next = temp;

			return;
		}

		// add to last element
		if (idx >= size) {
			Node current = this.head;
			while (current.next != null) {
				current = current.next;
			} // [1, 2, 3, null]
			current.next = new Node(i);

			return;
		}

		// add to target index
		Node current = this.head;
		while (current.next != null) {
			if (index == idx - 1) {
				Node temp = current.next;
				current.next = new Node(i);
				current.next.next = temp;

				return;
			}
			current = current.next;
			index++;
		}
		if (index == idx - 1) {
			Node temp = current.next;
			current.next = new Node(i);
			current.next.next = temp;

			return;
		}

		System.out.println("error adding element, index: " + idx);
	}

	public int score() {// tested working
		// TODO implement wild gems

		if (this.head == null) {
			return 0;
		}

		int out = 0;
		Node current = this.head;
		int currMulti = 0;
		int multiLen = 0;
		while (current.next != null) {
			if (
					current.gem.getType().equals(current.next.gem.getType())
					|| current.next.gem.getType().toString().equals("WILD")
					|| current.gem.getType().toString().equals("WILD")) { // this statement hurts

				// multiplier goes here
				if (currMulti != 0) {// continuing multiplier, increment len and add to currMulti
					currMulti += current.gem.getPoints();
					multiLen++;
				} else {// beginning multiplier, change values accordingly
					currMulti = current.gem.getPoints();
					multiLen = 1;
				}
			}

			else {

				if (current.next.gem.getType().toString().equals("WILD")) {//may not be necessary
					if (currMulti != 0) {// continuing multiplier, increment len and add to currMulti
						currMulti += current.gem.getPoints();
						multiLen++;
					} else {// beginning multiplier, change values accordingly
						currMulti = current.gem.getPoints();
						multiLen = 1;
					}
				}

				if (currMulti != 0) {// multiplier over, take currMulti * multiLen and add it to `out`
					currMulti += current.gem.getPoints();
					multiLen++;
					out += (currMulti * multiLen);
					currMulti = 0;
					multiLen = 0;
				} else {// there is no multiplier, just add value
					out += current.gem.getPoints();
				}
			}
			current = current.next;
		} // wloop closing

		if (currMulti != 0) {// multiplier over, take currMulti * multiLen and add it to `out`
			currMulti += current.gem.getPoints();
			multiLen++;
			out += (currMulti * multiLen);
			currMulti = 0;
			multiLen = 0;
		} else {// there is no multiplier, just add value
			out += current.gem.getPoints();
		}

		// my brain hurts why is this so complicated
		return out;
	}

	public void draw(double y) { // tested working
		final int maxGems = 16;
		Node current = this.head;
		int x = 0;
		if (head == null) {
			return;
		}
		while (current.next != null) {
			Gem g = current.gem;
			g.draw(1.0 / maxGems * (x++ + 0.5), y);
			current = current.next;
		}
		Gem g = current.gem;
		g.draw(1.0 / maxGems * (x++ + 0.5), y);
	}

	@Override
	public String toString() { // tested working
		Node current = this.head;
		String out = "";
		if (current == null) {
			return "";
		}
		while (current.next != null) {
			out += current.gem.getType() + " -> ";
			current = current.next;
		}
		out += current.gem.getType() + " -> ";

		return out.substring(0, out.length() - 4);
	}

	public static void main(String[] args) {
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.9);

		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99); // not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.7);

		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.6);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.5);

		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.4);

		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.getSize() + ", score = " + list.score());
		list.draw(0.3);
	}
}