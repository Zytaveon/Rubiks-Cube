package rubik;

import rubik.Rubik.*;
import java.util.*;

public class Search {

    // Need a block object for turns
    static Block block = new Block();

    // The inital strings given
    static Node startNode = null;
    static Node endNode = null;

    // The Linked List of Parent nodes
    // Starts with the root nodes
    // Since birectional search uses two simultaneous BFS trees, need two different
    // vairables.

    // Top always means top tree for all variables
    // Bottom always means bottom tree for all variables

    static Node topParentTracker = null;
    static Node bottomParentTracker = null;

    // The Linked List of child nodes created
    // No child nodes to start
    static Node topChildTracker = null;
    static Node bottomChildTracker = null;
    static Node childTracker = null;

    // The last created child, will use when match is found
    static Node childNode = null;

    // Turn used to get from parent to the child node.
    // Turnback is how to go from child to parent node.
    char turn;
    char turnBack;

    // Hashmaps for top and bottom node trees
    // Map<char[], Node> topMap = new HashMap<>();
    // Map<char[], Node> bottomMap = new HashMap<>();

    static Map<String, Node> topMap = new HashMap<>();
    static Map<String, Node> bottomMap = new HashMap<>();

    public static String BiDirectionalSearch(char[] mixedCube, char[] finalCube) {

        // Make sure strings don't match.
        // If so, return an empty move list
        if (Arrays.equals(mixedCube, finalCube)) {
            return "Same cube works";
        }

        Search.startNode = new Node(mixedCube);
        Search.endNode = new Node(finalCube);

        Search.topMap.put(new String(mixedCube), startNode);
        Search.bottomMap.put(new String(finalCube), endNode);

        Search.topParentTracker = Search.startNode;
        Search.bottomChildTracker = Search.endNode;

        char[] solvedBlockString = null;

        while (true) {

            // Creating top search tree with children
            // If returns true, find top and bottom nodes and get returnMoveString
            // If false, create bottomchildren
            solvedBlockString = createChildren(Search.topParentTracker, childNode, topChildTracker, Search.topMap,
                    Search.bottomMap);

            if (solvedBlockString != null) {
                return getReturnString(topMap.get(new String(solvedBlockString)),
                        bottomMap.get(new String(solvedBlockString)));
            }

            Search.topParentTracker = Search.childTracker;
            Search.topChildTracker = null;
            Search.childTracker = null;

            // Creating bottom search tree with children
            // If returns true, find top and bottom nodes and get returnMoveString
            // If false, restart while loop and create more top children nodes
            solvedBlockString = createChildren(Search.bottomParentTracker, childNode, bottomChildTracker,
                    Search.bottomMap, Search.topMap);
            if (solvedBlockString != null) {
                return getReturnString(Search.topMap.get(new String(solvedBlockString)),
                        Search.bottomMap.get(new String(solvedBlockString)));
            }

            Search.bottomParentTracker = Search.childTracker;
            bottomChildTracker = null;
            childNode = null;

        }
    }

    public static String BiDirectionalSearch(char[] mixedCube) {

        char[] newSolvedBlock = {
                'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R',
                'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B',
                'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O',
                'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G',
                'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y',
                'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'
        };

        return BiDirectionalSearch(mixedCube, newSolvedBlock);

    }

    /*
     * Creates children nodes for the parentNodes linked list
     * While creating children it also tries to match the new child node to a node
     * in the diffMap
     * If no match is found, adds the child to its sameMap
     * 
     * If it finds a match, will stop creating children and return true
     * If it doesn't find a match and runs out of children to make, returns false
     * 
     * The childrenNodeHead should only be changed when first child is created or
     * when it == null
     * The parentnode doesn't need head, since should have track of it in the method
     * that called it
     * 
     * After method completes, in the method that called this one,
     * childrenNodeHead should be assigned to its respective parentNode head
     * 
     * If a match is found, the last created child should have the blockstring
     * needed to find other hashmap node value
     * 
     */

    public static char[] createChildren(
            Node parentNode,
            Node childNode,
            Node childrenNodeHead,
            Map<String, Node> sameMap,
            Map<String, Node> diffMap) {

        // No more parentNodes
        // Nore more children to make
        // Switch Trees
        if (parentNode == null) {
            return null;
        }

        Block block = new Block();
        block.setBlockString(parentNode.getBlockString());

        // Create 12 children nodes for parent
        for (int i = 0; i < 12; ++i) {

            // If node is already in map, don't want to turnBlock twice, or create node
            char[] newNodeString = block.turnBlockWithString('\0', i);

            /*
             * Check if new string is in same side hashmap
             * If so, do nothing
             * If not, create new node and link it to other nodes, or set head if no nodes
             * yet
             * After node is created, see if it exists in other side map
             * If not, do nothing and test/create next child
             * If so, have found node in both trees so search is over and should have
             * solution
             */

            if (!sameMap.containsKey(new String(newNodeString))) {
                Node newNode = new Node(
                        block.turnBlockWithString('\0', i),
                        getTurn(i),
                        parentNode,
                        null);

                // No children Nodes created yet.
                if (childrenNodeHead == null) {
                    childrenNodeHead = newNode;
                    childNode = newNode;
                    Search.childTracker = newNode;
                }

                else {
                    childNode.next = newNode;
                    childNode = newNode;
                }

                sameMap.put(new String(childNode.getBlockString()), childNode);

                // Found match in different map, so search is over.
                if (diffMap.containsKey(new String(childNode.blockString))) {
                    return childNode.blockString;
                }
            }
        }

        return createChildren(parentNode.next, childNode, childrenNodeHead, sameMap, diffMap);

    }

    public static char getTurn(int i) {

        switch (i) {
            case 0:
                return 'R';
            case 1:
                return 'r';
            case 2:
                return 'L';
            case 3:
                return 'l';
            case 4:
                return 'U';
            case 5:
                return 'u';
            case 6:
                return 'D';
            case 7:
                return 'd';
            case 8:
                return 'F';
            case 9:
                return 'f';
            case 10:
                return 'B';
            case 11:
                return 'b';
            default:
                return '\0';
        }

    }

    /*
     * If i is even, then it is a regular turn
     * Adding one will get the prime version
     * 
     * If i is odd, then it is a prime turn
     * Subtracting one will get the regular version
     */

    public static char getTurnBack(char turn) {
        switch (turn) {
            case 'R':
                return 'r';
            case 'r':
                return 'R';
            case 'L':
                return 'l';
            case 'l':
                return 'L';
            case 'U':
                return 'u';
            case 'u':
                return 'U';
            case 'D':
                return 'd';
            case 'd':
                return 'D';
            case 'F':
                return 'f';
            case 'f':
                return 'F';
            case 'B':
                return 'b';
            case 'b':
                return 'B';
            default:
                return 'Q';
        }
    }

    public static String getReturnString(Node top, Node bottom) {
        String moves = "";

        while (top.turn != '\0') {
            moves = top.getTurn() + moves;
            top = top.getParent();
        }

        while (bottom.turn != '\0') {
            moves = getTurnBack(bottom.getTurn()) + moves;
            bottom = bottom.getParent();
        }

        return moves;
    }

}
