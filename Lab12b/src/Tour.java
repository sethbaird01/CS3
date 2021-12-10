public class Tour {

	private class Node {
		Point p;
		Node next;

		public Node(Point p) {
			this.p = p;
			this.next = this; // circularity
		}

		public Node(Point p, Node next) {
			if (next == null) {
				throw new IllegalArgumentException("Given node is null! stop it");
			}
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
		System.out.println("printing");
		Node cur = this.head;
		while (cur.next != null && cur.next != cur) {
			System.out.println(cur.toString());
			cur = cur.next;
		}
	}

	/** draw the tour using StdDraw */
	public void draw() {
		Node current = this.head;

		while (current != current.next && current != null && current.next != this.head) {// prevent circular
			current.p.drawTo(current.next.p);
			current = current.next;
		}
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

	public void insert(Point p, int idx) {
		if (/* idx != 0 && */idx > this.size - 1) {
			throw new IndexOutOfBoundsException();
		}

		// if (this.head == null) { // may never happen
		// 	this.head = new Node(p, this.head);
		// 	return;
		// }

		//TODO broken

		if (idx == 0) {
			Node tempOld = this.head;
			this.head = new Node(p, tempOld);
			return;
		}

		// if (idx == 1) {
		// 	Node tempOld = this.head.next;
		// 	this.head.next = new Node(p, tempOld);
		// 	return;
		// }

		Node current = this.head;
		int index = 0;

		//bad bad bad
		while (current != current.next && current != null) {// prevent reiteration
			System.out.println("current index: "+index+" goal index: "+idx+" size: "+size);
			if (index == idx) {//index correct, set node
				Node oldNext = current.next;
				current = new Node(p, oldNext);
				// return;
			}
			index++;
			current = current.next;
		}

		if (index == idx) {//index correct, set node
			Node oldNext = current.next;
			current = new Node(p, oldNext);
			// return;
		}

	}

	/**
	 * return the total distance "traveled", from start to all nodes and back to start
	 */
	public double distance() {
		Node current = this.head;
		double out = 0.0;

		while (current != current.next && current != null && current.next != this.head) {// prevent circular
			out += current.p.distanceTo(current.next.p);
			current = current.next;
		}

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

		// System.out.println(this);

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

	// TODO third heuristic

	// find "concentric" algorithm that favors points farthest away from center, small modification to insertNearest
}