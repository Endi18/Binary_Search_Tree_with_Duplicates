
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    public BinarySearchTree() {
        root = null;
    }

    public void insert(AnyType x) throws LinkedList.NoSuchElementException {
        root = insert(x, root);
    }

    public void remove(AnyType x) throws ItemNotFoundException, LinkedList.NoSuchElementException {
        root = remove(x, root);
    }

    public void removeMin() throws ItemNotFoundException {
        root = removeMin(root);
    }

    public AnyType findMin() throws LinkedList.NoSuchElementException {
        return elementAt(findMin(root));
    }

    public AnyType findMax() throws LinkedList.NoSuchElementException {
        return elementAt(findMax(root));
    }

    public AnyType find(AnyType x) throws LinkedList.NoSuchElementException {
        return elementAt(find(x, root));
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Internal method to get element field.
     *
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private AnyType elementAt(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {

        return t == null ? null : new LinkedList<>(t.element).getFirst();

    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode<LinkedList<AnyType>> find(AnyType x, BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {
        while (t != null) {
            if (x.compareTo(new LinkedList<>(t.element).getFirst()) < 0) {
                t = t.left;
            } else if (x.compareTo(new LinkedList<>(t.element).getFirst()) > 0) {
                t = t.right;
            } else {
                return t; // Match
            }
        }

        return null; // Not found
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected BinaryNode<LinkedList<AnyType>> findMin(BinaryNode<LinkedList<AnyType>> t) {
        if (t != null) {
            while (t.left != null) {
                t = t.left;
            }
        }

        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode<LinkedList<AnyType>> findMax(BinaryNode<LinkedList<AnyType>> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }

        return t;
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    public BinaryNode<LinkedList<AnyType>> insert(AnyType x, BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {
        if (t == null) {
            LinkedList<AnyType> l = new LinkedList<>();
            l.add(x);
            t = new BinaryNode<>(l);
        } else if (x.compareTo(new LinkedList<>(t.element).getFirst()) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(new LinkedList<>(t.element).getFirst()) > 0) {
            t.right = insert(x, t.right);
        } else {
            t.element.addFirst(x);  //Duplicate

        }
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     *
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if t is empty.
     */
    protected BinaryNode<LinkedList<AnyType>> removeMin(BinaryNode<LinkedList<AnyType>> t) throws ItemNotFoundException {
        if (t == null) {
            throw new ItemNotFoundException();
        } else if (t.left != null) {
            t.left = removeMin(t.left);
            return t;
        } else {
            return t.right;
        }
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode<LinkedList<AnyType>> remove(AnyType x, BinaryNode<LinkedList<AnyType>> t) throws ItemNotFoundException, LinkedList.NoSuchElementException {
        if (t == null) {
            throw new ItemNotFoundException(x.toString());
        }
        if (x.compareTo(new LinkedList<>(t.element).getFirst()) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(new LinkedList<>(t.element).getFirst()) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = removeMin(t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    public BinaryNode<LinkedList<AnyType>> root;

    // Print tree rooted at current node using preorder traversal.
    public void printPreOrder(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {


        t.element.showList();//Node

        if (t.left != null) {
            printPreOrder(t.left); // Left
        }
        if (t.right != null) {
            printPreOrder(t.right); // Right
        }
    }

    // Print tree rooted at current node using postorder traversal.
    public void printPostOrder(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {
        if (t.left != null) // Left
        {
            printPostOrder(t.left);
        }
        if (t.right != null) // Right
        {
            printPostOrder(t.right);
        }
        t.element.showList(); // Node
    }

    // Print tree rooted at current node using inorder traversal.
    public void printInOrder(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {
        if (t.left != null) // Left
        {
            printInOrder(t.left);
        }
        t.element.showList(); // Node
        if (t.right != null) {
            printInOrder(t.right); // Right
        }
    }

    // Print tree rooted at current node using level order traversal.
    public void printLevelOrder(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException {
        int level = 1;

        while (printLevel(t, level)) {
            level++;
        }

    }

    public boolean printLevel(BinaryNode<LinkedList<AnyType>> t, int level) throws LinkedList.NoSuchElementException {
        //base case
        if (t == null) {
            return false;
        }

        if (level == 1) {
            t.element.showList();
            return true;
        }

        boolean left = printLevel(t.left, level - 1);
        boolean right = printLevel(t.right, level - 1);

        return left || right;
    }

    public int numberOfDuplicates(AnyType x) throws LinkedList.NoSuchElementException
    {
        BinaryNode<LinkedList<AnyType>> tn = find(x, root);
        return tn.element.size();

    }

    public void findAndReplaceAllDuplicates(AnyType A, AnyType B) throws LinkedList.NoSuchElementException
    {
        BinaryNode<LinkedList<AnyType>> tn = find(A, root);

        int noOfDuplicates = tn.element.size();
        if (noOfDuplicates>1)
        {
            while (noOfDuplicates>0)
            {
                tn.element.set(tn.element.getIndex(A), B);
                noOfDuplicates--;
            }
        }
    }
    public void removeDuplicatesOfElement(AnyType x) throws LinkedList.NoSuchElementException
    {
        BinaryNode<LinkedList<AnyType>> tn = find(x, root);
        tn.element.remove(1,tn.element.size()-1);

    }
    public void removeOneDuplicateOfElement(AnyType x) throws LinkedList.NoSuchElementException
    {
        BinaryNode<LinkedList<AnyType>> tn = find(x, root);
        tn.element.remove(0,0);
    }
    public void removeAllDuplicates(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException
    {

        t.element.remove(1,t.element.size()-1);
        if (t.left != null) // Left
        {
            removeAllDuplicates(t.left);
        }

        if (t.right != null) {
            removeAllDuplicates(t.right); // Right
        }
    }

    public void printDuplicates(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException
    {
        BinaryNode<LinkedList<AnyType>> tn = find(t.element.getFirst(), t);

        int noOfDuplicates = tn.element.size();

        if (noOfDuplicates >1)
        {
            System.out.println("Element " + t.element.getFirst() + " repeated " + noOfDuplicates + " times!");
        }

        if (t.left != null) // Left
        {
            printDuplicates(t.left);
        }

        if (t.right != null) {
            printDuplicates(t.right); // Right
        }
    }

    int noOfTotalDuplicates = 0;
    public int noOfDuplicates(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException
    {

        BinaryNode<LinkedList<AnyType>> tn = find(t.element.getFirst(), t);

        int noOfDuplicates = tn.element.size();
//        
//        if (noOfDuplicates >=1)
//        {
        noOfTotalDuplicates+=noOfDuplicates;
//        }

        if (t.left != null) // Left
        {
            noOfDuplicates(t.left);
        }

        if (t.right != null) {
            noOfDuplicates(t.right); // Right
        }
        return noOfTotalDuplicates;
    }


    public void showDuplicates(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException
    {

        BinaryNode<LinkedList<AnyType>> tn = find(t.element.getFirst(), t);

        int noOfDuplicates = tn.element.size();

        if (noOfDuplicates >1)
        {
            System.out.println("Element " + t.element.getFirst() + " repeated " + noOfDuplicates + " times!");
        }

        if (t.left != null) // Left
        {
            showDuplicates(t.left);
        }

        if (t.right != null) {
            showDuplicates(t.right); // Right
        }

    }

    public void showSingleElements(BinaryNode<LinkedList<AnyType>> t) throws LinkedList.NoSuchElementException
    {

        BinaryNode<LinkedList<AnyType>> tn = find(t.element.getFirst(), t);

        int noOfDuplicates = tn.element.size();

        if (noOfDuplicates ==1)
        {
            System.out.println("Element " + t.element.getFirst() + " repeated " + noOfDuplicates + " time!");
        }

        if (t.left != null) // Left
        {
            showSingleElements(t.left);
        }

        if (t.right != null) {
            showSingleElements(t.right); // Right
        }

    }

    public static class ItemNotFoundException extends Exception {

        public ItemNotFoundException(String toString) {
            super(toString);
        }

        public ItemNotFoundException() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}

// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// this package.
class BinaryNode<LinkedList> {
// Constructor

    BinaryNode(LinkedList theElement) {
        element = theElement;
        left = right = null;
    }

    // Data; accessible by other package routines
    LinkedList element; // The data in the node
    BinaryNode<LinkedList> left; // Left child
    BinaryNode<LinkedList> right; // Right child

}