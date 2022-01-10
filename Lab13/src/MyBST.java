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
        return (n == null) ? 0 : 1 + this.size(n.left) + this.size(n.left);
    }

    void insert(Integer n) {

    }

    void findParent(Integer n) {
        // traverse left and right until a child node is greater than n
        // not sure what to do on null case
    }

    boolean contains(Integer n) {
        return contains(n, this.root);
    }

    boolean contains(Integer n, BSTNode node) {
        return node == null ? false : (this.contains(n, node.left) || this.contains(n, node.right));
    }

    Integer getMax() {
        return getMax(this.root);
    }

    Integer getMax(BSTNode node){
        return (node.right == null) ? node.val : getMax(node.right);
    }

    Integer getMin() {
        return getMin(this.root);
    }

    Integer getMin(BSTNode node){
        return (node.left == null) ? node.val : getMin(node.left);
    }

    void delete(Integer n) {
        BSTNode delParent = findTargetParent(root, n);

        //right node deletion - leaf case
        if(n > delParent.val && delParent.right.left == null && delParent.right.right == null){
        
        }

        //left node deletion - leaf case
        if(n < delParent.val && delParent.left.left == null && delParent.left.right == null ){

        }



        // //one child case
        // if(n > delParent.val){//deleting right child
        //     //decide where to link to 
        // }else{//deleting left child

        // }

        // //two children case
        // if(n > delParent.val){//deleting right child
        //     //decide where to link to 
        // }else{//deleting left child

        // }
    }

    BSTNode findTargetParent(BSTNode n, Integer target) {
        //case where current node is correct parent
        if(n.left.val == target || n.right.val == target){
            return n;
        }

        //"else"
        if(target > n.val){
            return findTargetParent(n.right, target);
        }
        if(target < n.val){
            return findTargetParent(n.left, target);
        }

        //if target is less, check if n.left is correct
        //if not, search one level deeper
        //if n.left is correct, return current node

        //not found
        return null;
    }

    void inOrder() {
    }

    void print() {

        // printing by backwards traversal
        // traverse to leaf nodes
        // once determining if they are leaves (.left and .right are null) -
        // print those with " ".repeat(height) recording height on the way down
        // then print the node above that pair (i guess this would have to be stored beforehand?)
        // this is starting to seem like a bad plan

        //
        // wouldnt it be easier to write a gui,,,
    }

}
