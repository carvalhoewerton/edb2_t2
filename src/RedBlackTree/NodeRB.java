package RedBlackTree;

public class NodeRB {

    int value;
    NodeRB left;
    NodeRB right;
    NodeRB parent;
    Color color;

    public NodeRB(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = Color.RED;
    }

    public int getValue() {
        return value;
    }

    public NodeRB getLeft() {
        return left;
    }

    public NodeRB getRight() {
        return right;
    }

    public NodeRB getParent() {
        return parent;
    }

    public Color getColor() {
        return color;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(NodeRB left) {
        this.left = left;
    }

    public void setRight(NodeRB right) {
        this.right = right;
    }

    public void setParent(NodeRB parent) {
        this.parent = parent;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
