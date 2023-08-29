public class BST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LinkedList.NoSuchElementException {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.insert(3);
        bst.insert(1);
        bst.insert(4);
        bst.insert(6);
        bst.insert(9);
        bst.insert(2);
        bst.insert(9);
        bst.insert(5);
        bst.insert(7);
        bst.insert(9);
        bst.insert(4);
        bst.insert(1);

        System.out.println("--PreOrder--");
        bst.printPreOrder(bst.root);

        System.out.println("--PostOrder--");
        bst.printPostOrder(bst.root);

        System.out.println("--InOrder--");
        bst.printInOrder(bst.root);

        System.out.println("--LevelOrder--");
        bst.printLevelOrder(bst.root);

        Integer min = bst.findMin();
        System.out.println("--Minimum--");
        System.out.println(min);

        Integer max = bst.findMax();
        System.out.println("--Maximum--");
        System.out.println(max);

        System.out.println("--Number of duplicates of an element--");
        System.out.println(bst.numberOfDuplicates(9));

        System.out.println("--Find and replace all duplicates of an element A with element B--");
        bst.findAndReplaceAllDuplicates(4, 12);

        System.out.println("--PreOrder--");
        bst.printPreOrder(bst.root);

        System.out.println("--Remove all existing duplicates--");
        bst.removeAllDuplicates(bst.root);

        System.out.println("--PreOrder--");
        bst.printPreOrder(bst.root);

        System.out.println("--Remove all existing duplicates of an element--");
        bst.removeDuplicatesOfElement(1);

        System.out.println("--PreOrder--");
        bst.printPreOrder(bst.root);

        System.out.println("--Remove one duplicate of an element--");
        bst.removeOneDuplicateOfElement(1);

        System.out.println("--PreOrder--");
        bst.printPreOrder(bst.root);

        System.out.println("--Print all the elements with duplicates and its number--");
        bst.printDuplicates(bst.root);

        System.out.println("--Number of all duplicates --");
        System.out.println(bst.noOfDuplicates(bst.root));

        System.out.println("--Show duplicated elements --");
        bst.showDuplicates(bst.root);

        System.out.println("--Show non-duplicated elements --");
        bst.showSingleElements(bst.root);
    }
}