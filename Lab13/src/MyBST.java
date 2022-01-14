
public class MyBST {

    BSTNode root;

    private class BSTNode {
        Integer val;
        BSTNode left, right;

        public BSTNode(Integer val) {
            this.val = val;
            left = right = null;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }

    int size() {
        return size(this.root);
    }

    int size(BSTNode n) {// ternary is so nice
        return (n == null) ? 0 : 1 + this.size(n.left) + this.size(n.right);
    }

    void insert(Integer n) {
        if (this.root == null) {
            this.root = new BSTNode(n);
        } else {
            findInsertion(n, this.root);
        }
    }

    void findInsertion(Integer n, BSTNode node) {
        // given insertion value and subtree to search
        // only looks directly at node.left and node.right then recursively searches for
        // the goal
        if (n < node.val) {
            if (node.left == null) {
                node.left = new BSTNode(n);
            } else {// node not null, traverse further
                findInsertion(n, node.left);
            }
        }

        if (n > node.val) {
            if (node.right == null) {
                node.right = new BSTNode(n);
            } else {// node not null, traverse further
                findInsertion(n, node.right);
            }
        }

    }

    boolean contains(Integer n) {
        return contains(n, this.root);
    }

    boolean contains(Integer n, BSTNode node) {
        return (node != null && node.val == n) ? true
                : ((node.left != null && this.contains(n, node.left)) ||
                        (node.right != null && this.contains(n, node.right)));
    }

    Integer getMax() {
        return getMax(this.root);
    }

    Integer getMax(BSTNode node) {
        return (node.right == null) ? node.val : getMax(node.right);
    }

    Integer getMin() {
        return getMin(this.root);
    }

    Integer getMin(BSTNode node) {
        return (node.left == null) ? node.val : getMin(node.left);
    }

    void delete(Integer n) {
        BSTNode delParent = findTargetParent(root, n);
        if (delParent == null) {
            // element to delete doesnt exist
            return;
        }

        // node to delete has no children case (left)
        if (n < delParent.val && // confirms left is the correct deletion
                delParent.left.left == null && // leaf check 1
                delParent.left.right == null) { // leaf check 2
            // deleting element, replacing with null - as if it was never there
            delParent.left = null;
            return;
        }

        // node to delete has no children case (right)
        // no logic comments since it is extrapolated from above left case
        if (n > delParent.val &&
                delParent.right.left == null &&
                delParent.right.right == null) {
            delParent.right = null;
            return;
        }

        /*
        */

        // node to delete has one child case (left)
        if (n < delParent.val && // left is to be deleted
                delParent.left.left == null ^ delParent.left.right == null) { // node to be deleted has one child
            // xor checks if one is null but not both
            // in other words, makes sure theres one child only on element to be deleted
            // (delParent.left)

            // now check which side of delParent.left is null
            // if delParent.left.left is null, set delParent.left to delParent.left.right
            // because delParent.left.right is not null, it is the only successor to
            // delParent.left.
            delParent.left = delParent.left.left == null ? delParent.left.right : delParent.left.left;
            return;
        }

        // node to delete has one child case (right)
        // no logic comments since it is extrapolated from above left case
        if (n > delParent.val &&
                delParent.right.left == null ^ delParent.right.right == null) {
            delParent.right = delParent.right.left == null ? delParent.right.right : delParent.right.left;
            return;
        }

        /*
        */

        // node to delete has two children case (left)
        if (n < delParent.val &&
                delParent.left.left != null &&
                delParent.left.right != null) {
            //i had pseudocode here explaning my thought process but then i had to redo this at the last minute and cant be bothered to rewrite it ! i am very frustrated with bst's

            BSTNode successor = traverseGreatest(delParent.left.left);
            BSTNode leftSub = delParent.left.left;
            BSTNode rightSub = delParent.left.right;

            deleteGreatest(delParent.left.left);

            delParent.left = new BSTNode(successor.val);
            delParent.left.left = leftSub;
            delParent.left.right = rightSub;

            return;
        }

        // node to delete has two children case (right)
        // no logic comments since it is extrapolated from above left case
        if (n > delParent.val &&
                delParent.right.left != null &&
                delParent.right.right != null) {

            BSTNode successor = traverseGreatest(delParent.right.left);
            BSTNode leftSub = delParent.right.left;
            BSTNode rightSub = delParent.right.right;

            deleteGreatest(delParent.right.left);

            delParent.right = new BSTNode(successor.val);;
            delParent.right.left = leftSub;
            delParent.right.right = rightSub;

            return;
        }

    }

    void inOrder() {
        inOrder(this.root);
    }
    
    void inOrder(BSTNode node) {
    
        if(node != null){
            inOrder(node.left);
            System.out.print(node.val+ " ");

            inOrder(node.right);
        }
    }

    void print() {
        print(this.root, 0);
    }

    void print(BSTNode node, int dist) {

        if(node != null){
            print(node.right, dist +1);
            String indent = ""; //indentation represented in a string

            for (int i = 0; i < dist; i++) {
                indent += "    ";
            }
            System.out.print(indent);
            System.out.print(node.val + "\n");
            print(node.left, dist +1);
        }
    }
     

    // helper methods below

    BSTNode findTargetParent(BSTNode n, Integer target) {
        // case where current node is correct parent
        if (n == null) {
            return null;

        }

        if (n.left != null && n.left.val == target) {
            return n;
        }
        if (n.right != null && n.right.val == target) {
            return n;
        }

        // "else"
        if (target > n.val) {
            return findTargetParent(n.right, target);
        }
        if (target < n.val) {
            return findTargetParent(n.left, target);
        }

        // if target is less, check if n.left is correct
        // if not, search one level deeper
        // if n.left is correct, return current node

        // not found
        return null;
    }

    BSTNode traverseGreatest(BSTNode del) {
        // argument: subtree root
        // returns: the node with the greatest value under that root

        return del.right == null ? del : traverseGreatest(del.right);
    }

    void deleteGreatest(BSTNode node) {
        // argument: root of subtree to find greatest element
        // finds greatest value element and sets it to null

        if (node.right.right == null) {
            // redone at last minute, no psuedocode or logic comments
            node.right = null;
        } else {
            deleteGreatest(node.right);
        }

    }

}
