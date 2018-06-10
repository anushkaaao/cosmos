/* Part of Cosmos by OpenGenus Foundation */

class SinglyLinkedList<T> {

	private Node head;

	public SinglyLinkedList() {
		head = null;
	}

	public void insertHead(T data) {
		Node<T> newNode = new Node<>(data); //Create a new link with a value attached to it
		newNode.next = head; //Set the new link to point to the current head
		head = newNode; //Now set the new link to be the head
	}

	public Node insertNth(T data, int position) {
		Node<T> newNode = new Node<>(data);

		if (position == 0) {
			newNode.next = head;
			return newNode;
		}

		Node current = head;

		while (--position > 0) {
			current = current.next;
		}

		newNode.next = current.next;
		current.next = newNode;
		return head;
	}

	public Node deleteHead() {
		Node temp = head;
		head = head.next; //Make the second element in the list the new head, the Java garbage collector will later remove the old head
		return temp;
	}
		
	public Node deleteAtFront() {
		Node temp = head.next;
		head.next = temp.next;
		return temp;
	}
	
	public Node deleteAtEnd() {
		if(head == null)
	        return head;
		Node last = head;
		Node plast = null;
		while(last.next != null) {
			plast = last;
			last = last.next;
		}
		plast.next = null;
		return last;
	}
	public Node deleteAtNth(int position) {
		if(position == 0) {
			deleteAtFront();
			return;
		}
		Node current = head;
		Node prevcurrent = null;
		while(--position > 0) {
			prevcurrent = current;
			current = current.next;
		}
		prevcurrent.next = current.next;
		return current;
	}
		
	public void reverse(){
		head = reverseList(head);
	}

	private Node reverseList(Node node){
		if (node == null || node.next == null) return node;
		Node reversedHead = reverseList(node.next);
		node.next.next = node;
		node.next = null;
		return reversedHead;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public String toString() {
		String s = new String();
		Node current = head;
		while (current != null) {
			s += current.getValue() + " -> ";
			current = current.next;
		}
		return s;
	}

	/**
	 * Node is a private inner class because only the SinglyLinkedList class should have access to it
	 */
	private class Node<T> {
		/** The value of the node */
		public T value;
		/** Point to the next node */
		public Node next; //This is what the link will point to

		public Node(T value) {
			this.value = value;
		}

		public T getValue() {
			return value;
		}
	}

	public static void main(String args[]) {
		SinglyLinkedList<Integer> myList = new SinglyLinkedList<>();

		System.out.println(myList.isEmpty()); //Will print true

		myList.insertHead(5);
		myList.insertHead(7);
		myList.insertHead(10);

		System.out.println(myList); // 10(head) --> 7 --> 5

		myList.deleteHead();

		System.out.println(myList); // 7(head) --> 5
	}
}
