public class Tour {

	private class Node {
		Point p;
		Node next;

		public Node(Point p) {
			this.p = p;
			this.next = this; // circularity
		}

		public Node(Point p, Node next) {
			this.p = p;
			this.next = next;
		}

		public Point getPoint() {
			return this.p;
		}

		@Override
		public String toString() {
			return p.toString();
		}
	}

	Node head;
	int size;

	/** create an empty tour */
	public Tour() {
		// this.head = new Node();
		// this.head.next = this.head;
		// this.size = 0;

		// these don't need initializing because it is done in the heuristic methods
	}

	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d) {
		Node head = new Node(a, new Node(b, new Node(c, new Node(d))));
		this.size = 4;
		this.head = head;
	}

	/** print tour (one point per line) to std output */
	public void show() {
		Node current = this.head;
		while (current.next != null && current.next != current && current.next != this.head) { // last condition prevents circular loop
			System.out.println(current.toString());
			current = current.next;
		}
	}

	/** draw the tour using StdDraw */
	public void draw() {
		Node current = this.head;
		while (current.next != null && current.next != current && current.next != this.head) { // last condition prevents circular loop
			current.p.drawTo(current.next.p);
			current = current.next;
		}
		current.p.drawTo(current.next.p); //accounts for loop back to head node
	}

	/** return number of nodes in the tour */
	public int size() {
		return size;
	}

	public Node get(int i) {// copied from MyLinkedList lab
		int index = 0;
		if (this.head == null || this.size == 0 || (i > 0 && this.head == this.head.next)) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = this.head;
			while (current.next != this.head /* circularity checker */ && index < size) {// stops when either the list
																							// loops too far (past the
																							// circular list's "end")
																							// found by comparing next
																							// element to "first" element
				if (index == i) {
					return current;
				}
				current = current.next;
				index++;
			}
			if (index == i) {
				return current;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public void insert(Point p, int index) {
		Node in = new Node(p, null); //null for now but will be assigned later in the method
		Node current = this.head;
 
			// going to the goal index or "last" element in the list
			for (int i = 0; i < index && (current.next != this.head && current.next != null); i++) {
				current = current.next;
			}
 
		in.next = current.next;
 		current.next = in;
 	}

	/**
	 * return the total distance "traveled", from start to all nodes and back to start
	 */
	public double distance() {
		Node current = this.head;
		double out = 0.0;

		while (current.next != null && current.next != current && current.next != this.head) { // last condition prevents circular loop
			out += current.p.distanceTo(current.next.p);
			current = current.next;
		}
		out += current.p.distanceTo(current.next.p); //accounts for loop back to head node

		return out;
	}

	public String toString() { // copied with modifications from MyLinkedList lab

		String out = "[";
		Node current = this.head;

		while (current != current.next && current != null) {// prevent circular
			out += current.p + ", ";
			current = current.next;
		}

		out += "]";
		return out.replace(", ]", "]");
	}

	/** insert p using nearest neighbor heuristic */
	public void insertNearest(Point p) {
		if (this.size == 0) {
			this.size = 1;
			this.head = new Node(p);
			return;
		}

		double dist = Double.POSITIVE_INFINITY;
		int nearest = 0; // the index of nearest point

		for (int i = 0; i < this.size; i++) {
			if (this.get(i).getPoint().distanceTo(p) < dist) {
				dist = this.get(i).getPoint().distanceTo(p);// stores smallest distance
				nearest = i;// index of point with smallest distance
			}
		}

		this.insert(p, nearest);// adding the point
		this.size++;
	}

	/** insert p using smallest increase heuristic */
	public void insertSmallest(Point p) {

		if (this.size == 0) {
			this.size = 1;
			this.head = new Node(p);
			return;
		}

		double smallestIncrease = Double.POSITIVE_INFINITY;
		int idx = 0;
		double distOrig, distNew;

		//when this calls get(size-1), speed could be improved greatly by storing a tail node

		for (int i = 1; i < this.size; i++) {
			distOrig = this.get(i).getPoint().distanceTo(this.get(i - 1).getPoint());// distance from A to B
			distNew = p.distanceTo(this.get(i).getPoint()) + p.distanceTo(this.get(i - 1).getPoint());// distance from A
																										// to P to B
			if (distNew - distOrig <= smallestIncrease) {
				smallestIncrease = distNew - distOrig;
				idx = i - 1;
			}
		}
		distOrig = this.head.getPoint().distanceTo(this.get(this.size - 1).getPoint());// distance from first to last
		distNew = p.distanceTo(this.head.getPoint()) + p.distanceTo(this.get(this.size - 1).getPoint());// distance
																											// from
																											// first
																											// to P to
																											// last
		if (distNew - distOrig <= smallestIncrease) {
			smallestIncrease = distNew - distOrig;
			idx = this.size - 1;
		}

		this.insert(p, idx);// adding the point
		this.size++;
	}

	//third heuristic - makes a cool visual, not very practical
	public void thirdHeuristic(Point p) {

		if (this.size == 0) {
			this.size = 1;
			this.head = new Node(p);
			return;
		}

		double greatestIncrease = Double.NEGATIVE_INFINITY;
		int idx = 0;
		double distOrig, distNew;

		//when this calls get(size-1), speed could be improved greatly by storing a tail node

		for (int i = 1; i < this.size; i++) {
			distOrig = this.get(i).getPoint().distanceTo(this.get(i - 1).getPoint());// distance from A to B
			distNew = p.distanceTo(this.get(i).getPoint()) + p.distanceTo(this.get(i - 1).getPoint());// distance from A
																										// to P to B
			if (distNew - distOrig >= greatestIncrease) {
				greatestIncrease = distNew - distOrig;
				idx = i - 1;
			}
		}
		distOrig = this.head.getPoint().distanceTo(this.get(this.size - 1).getPoint());// distance from first to last
		distNew = p.distanceTo(this.head.getPoint()) + p.distanceTo(this.get(this.size - 1).getPoint());// distance
																											// from
																											// first
																											// to P to
																											// last
		if (distNew - distOrig >= greatestIncrease) {
			greatestIncrease = distNew - distOrig;
			idx = this.size - 1;
		}

		this.insert(p, idx);// adding the point
		this.size++;
	}

}