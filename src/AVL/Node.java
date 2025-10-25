package AVL;

public class Node {
    int value;
    Node left;
    Node right;
    int height;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public int getHeight() {
        return height;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getBalanceFactor() {
        int leftHeight = (left != null) ? left.getHeight() : -1;
        int rightHeight = (right != null) ? right.getHeight() : -1;
        return leftHeight - rightHeight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}