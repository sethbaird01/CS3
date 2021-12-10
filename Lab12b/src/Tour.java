public class Tour {

	private class Node {
		Point p;
		Node next;

		public Node() {
			this.p = null;
			this.next = null;
		}

		public Node(Point p) {
			this.p = p;
			this.next = null;
		}

		public Node(Point p, Node next) {
			this.p = p;
			this.next = next;
		}

		public Point getPoint() {
			return this.p;
		}
	}

	Node head;
	int size;

	/** create an empty tour */
	public Tour() {
		this.head = new Node();
		this.size = 0;
	}

	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d) {
		Node head = new Node(a, new Node(b, new Node(c, new Node(d))));
		this.size = 4;
		this.head = head;
	}

	/** print tour (one point per line) to std output */
	public void show() {
		Node cur = this.head;
		while (cur.next != null && cur.next != cur) {
			System.out.println(cur.toString());
			cur = cur.next;
		}
	}

	/** draw the tour using StdDraw */
	public void draw() {
		Node cur = this.head;
		while (cur.next != null && cur.next != cur) {
			cur.p.draw();
			cur = cur.next;
		}
	}

	/** return number of nodes in the tour */
	public int size() {
		return size;
	}

	public Node get(int i) {// copied from MyLinkedList lab
		System.out.println("getting element from index " + i + ". size of list: " + this.size);
		int index = 0;
		if (this.head == null) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = this.head;
			while (current.next != null && current.next != current) {
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

	public void insert(Point p, int idx) {// copied from MyLinkedList lab
		System.out.println("adding element to index " + idx);
		if (idx != 0 && idx > this.size - 1) {
			throw new IndexOutOfBoundsException();
		}
		this.size++;

		if (this.head == null) {
			this.head = new Node(p);
			return;
		}

		int index = 0;
		Node current = this.head;
		while (current.next != null && current.next != current) { // stops when
			if (index + 1 == idx) {
				Node n = current.next;
				current.next = new Node(p);
				current.next.next = n;
			}
			current = current.next;
			index++;
		}
		if (index == idx) {
			Node n = current.next;
			current = new Node(p);
			current.next = n;
		}

	}

	/**
	 * return the total distance "traveled", from start to all nodes and back to
	 * start
	 */
	public double distance() {
		Node cur = this.head;
		double out = 0.0;
		while (cur.next != null && cur.next != cur) {
			out += cur.p.distanceTo(cur.next.p);
			cur = cur.next;
		}
		return 0.0;
	}

	public String toString() { // tested working
		String out = "[";
		Node current = this.head;
		while (current != null) {
			out += current.p + ", ";
			current = current.next;
		}
		out += "]";
		return out.replace(", ]", "]");
	}

	/** insert p using nearest neighbor heuristic */
	public void insertNearest(Point p) {
		System.out.println(this);
		if (this.size == 0) {
			this.size = 1;
			head = new Node(p);
			return;
		}

		double dist = Double.POSITIVE_INFINITY;
		int nearest = 0;

		for (int i = 0; i < this.size; i++) {
			if (this.get(i).getPoint().distanceTo(p) < dist) {
				dist = this.get(i).getPoint().distanceTo(p);// keeps track of smallest distance
				nearest = i;
			}
		}

		this.insert(p, nearest);// adding the point

		this.size++;
	}

	/** insert p using smallest increase heuristic */
	public void insertSmallest(Point p) {
		System.out.println(this);

		if (this.size == 0) {
			this.size = 1;
			head = new Node(p);
			return;
		}

		double smallestIncrease = Double.POSITIVE_INFINITY;
		int idx = 0;
		double distOrig, distNew;
		for (int i = 1; i < this.size; i++) {
			distOrig = this.get(i).getPoint().distanceTo(this.get(i - 1).getPoint());// distance from A to B
			distNew = p.distanceTo(this.get(i).getPoint()) + p.distanceTo(this.get(i - 1).getPoint());// distance from A
																										// to
																										// P to B
			if (distNew - distOrig <= smallestIncrease) {
				smallestIncrease = distNew - distOrig;
				idx = i - 1;
			}
		}
		distOrig = this.get(0).getPoint().distanceTo(this.get(this.size - 1).getPoint());// distance from first to last
		distNew = p.distanceTo(this.get(0).getPoint()) + p.distanceTo(this.get(this.size - 1).getPoint());// distance
																											// from
																											// first
		// to P to last
		if (distNew - distOrig <= smallestIncrease) {
			smallestIncrease = distNew - distOrig;
			idx = this.size - 1;
		}

		this.insert(p, idx);
		this.size++;
	}

	// TODO third heuristic
}