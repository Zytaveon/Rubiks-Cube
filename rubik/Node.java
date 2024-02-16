package rubik;

public class Node {

    char[] blockString;
    char turn;
    Node parent;
    Node next;

    // Constructors
    // --------------------
    // Initialize to null. Shouldn't be called.
    public Node() {
        blockString = null;
        turn = '\0';
        parent = null;
        next = null;
    }

    // No parent or turn. Root node.
    public Node(char[] blockString) {
        this.blockString = blockString;
        this.turn = '\0';
        this.parent = null;
        this.next = null;
    }

    // Regular node.
    public Node(char[] blockString, char turn, Node parent, Node next) {
        this.blockString = blockString;
        this.turn = turn;
        this.parent = parent;
        this.next = next;
    }

    // Getters
    // --------------------
    public char[] getBlockString() {
        return blockString;
    }

    public char getTurn() {
        return turn;
    }

    public Node getParent() {
        return parent;
    }

    public Node getNext() {
        return next;
    }

}
