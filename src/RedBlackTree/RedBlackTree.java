package RedBlackTree;

public class RedBlackTree {
    private NodeRB root;

    public NodeRB find(int value) {
        NodeRB current = root;
        while (current != null) {
            if (value == current.getValue()) {
                return current; // nó encontrado
            } else if (value < current.getValue()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public void insert(int value) {
        NodeRB newNode = new NodeRB(value);
        root = bstInsert(root, newNode);
        fixInsert(newNode);
    }

    private NodeRB bstInsert(NodeRB root, NodeRB node) {
        if (root == null) {
            return node;
        }

        if (node.getValue() < root.getValue()) {
            root.setLeft(bstInsert(root.getLeft(), node));
            root.getLeft().setParent(root);
        } else if (node.getValue() > root.getValue()) {
            root.setRight(bstInsert(root.getRight(), node));
            root.getRight().setParent(root);
        }
        return root;
    }

    private void fixInsert(NodeRB node) {
        NodeRB parent, grandParent, uncle;

        while (node != root && node.getParent().getColor() == Color.RED) {
            parent = node.getParent();
            grandParent = parent.getParent();

            if (parent == grandParent.getLeft()) {
                uncle = grandParent.getRight();

                if (uncle != null && uncle.getColor() == Color.RED) {
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    node = grandParent;
                } else {
                    if (node == parent.getRight()) {
                        node = parent;
                        rotateLeft(node);
                        parent = node.getParent();
                    }
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    rotateRight(grandParent);
                }
            } else {
                uncle = grandParent.getLeft();

                if (uncle != null && uncle.getColor() == Color.RED) {
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    node = grandParent;
                } else {
                    if (node == parent.getLeft()) {
                        node = parent;
                        rotateRight(node);
                        parent = node.getParent();
                    }
                    parent.setColor(Color.BLACK);
                    grandParent.setColor(Color.RED);
                    rotateLeft(grandParent);
                }
            }
        }

        root.setColor(Color.BLACK);
    }

    private void rotateLeft(NodeRB node) {
        NodeRB rightChild = node.getRight();
        NodeRB parent = node.getParent();

        node.setRight(rightChild.getLeft());
        if (rightChild.getLeft() != null) rightChild.getLeft().setParent(node);

        rightChild.setLeft(node);
        node.setParent(rightChild);

        rightChild.setParent(parent);
        if (parent == null) {
            root = rightChild;
        } else if (node == parent.getLeft()) {
            parent.setLeft(rightChild);
        } else {
            parent.setRight(rightChild);
        }
    }

    private void rotateRight(NodeRB node) {
        NodeRB leftChild = node.getLeft();
        NodeRB parent = node.getParent();

        node.setLeft(leftChild.getRight());
        if (leftChild.getRight() != null) leftChild.getRight().setParent(node);

        leftChild.setRight(node);
        node.setParent(leftChild);

        leftChild.setParent(parent);
        if (parent == null) {
            root = leftChild;
        } else if (node == parent.getLeft()) {
            parent.setLeft(leftChild);
        } else {
            parent.setRight(leftChild);
        }
    }

    public void printTreeVisual() {
        System.out.println("--- Início da Árvore RN ---");
        printTreeVisual(root, 0);
        System.out.println("--- Fim da Árvore ---");
    }

    private void printTreeVisual(NodeRB node, int level) {
        if (node == null) return;

        printTreeVisual(node.getRight(), level + 1);

        for (int i = 0; i < level; i++) System.out.print("    ");

        String colorPrefix = (node.getColor() == Color.RED) ? "R" : "B";
        System.out.println("[" + colorPrefix + node.getValue() + "]");

        printTreeVisual(node.getLeft(), level + 1);
    }

    public void delete(int value) {
        NodeRB nodeToDelete = find(value);
        if (nodeToDelete != null) {
            deleteNode(nodeToDelete);
        }
    }

    private void deleteNode(NodeRB node) {
        NodeRB y = node;
        NodeRB x;
        Color originalColor = y.getColor();

        if (node.getLeft() == null) {
            x = node.getRight();
            transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            x = node.getLeft();
            transplant(node, node.getLeft());
        } else {
            y = minimum(node.getRight());
            originalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == node) {
                if (x != null) x.setParent(y);
            } else {
                transplant(y, y.getRight());
                y.setRight(node.getRight());
                y.getRight().setParent(y);
            }
            transplant(node, y);
            y.setLeft(node.getLeft());
            y.getLeft().setParent(y);
            y.setColor(node.getColor());
        }

        if (originalColor == Color.BLACK) {
            fixDelete(x, node.getParent());
        }
    }

    private void transplant(NodeRB u, NodeRB v) {
        if (u.getParent() == null) {
            root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        if (v != null) {
            v.setParent(u.getParent());
        }
    }

    private NodeRB minimum(NodeRB node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private void fixDelete(NodeRB x, NodeRB parent) {
        while ((x != root) && (x == null || x.getColor() == Color.BLACK)) {
            if (x == parent.getLeft()) {
                NodeRB w = parent.getRight();

                if (w != null && w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    parent.setColor(Color.RED);
                    rotateLeft(parent);
                    w = parent.getRight();
                }

                if ((w.getLeft() == null || w.getLeft().getColor() == Color.BLACK) &&
                        (w.getRight() == null || w.getRight().getColor() == Color.BLACK)) {
                    w.setColor(Color.RED);
                    x = parent;
                    parent = x.getParent();
                } else {
                    if (w.getRight() == null || w.getRight().getColor() == Color.BLACK) {
                        if (w.getLeft() != null) w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rotateRight(w);
                        w = parent.getRight();
                    }
                    w.setColor(parent.getColor());
                    parent.setColor(Color.BLACK);
                    if (w.getRight() != null) w.getRight().setColor(Color.BLACK);
                    rotateLeft(parent);
                    x = root;
                }
            } else {
                NodeRB w = parent.getLeft();

                if (w != null && w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    parent.setColor(Color.RED);
                    rotateRight(parent);
                    w = parent.getLeft();
                }

                if ((w.getLeft() == null || w.getLeft().getColor() == Color.BLACK) &&
                        (w.getRight() == null || w.getRight().getColor() == Color.BLACK)) {
                    w.setColor(Color.RED);
                    x = parent;
                    parent = x.getParent();
                } else {
                    if (w.getLeft() == null || w.getLeft().getColor() == Color.BLACK) {
                        if (w.getRight() != null) w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        rotateLeft(w);
                        w = parent.getLeft();
                    }
                    w.setColor(parent.getColor());
                    parent.setColor(Color.BLACK);
                    if (w.getLeft() != null) w.getLeft().setColor(Color.BLACK);
                    rotateRight(parent);
                    x = root;
                }
            }
        }
        if (x != null) x.setColor(Color.BLACK);
    }

    public long medirTempo(Runnable func) {
        long start = System.nanoTime();
        func.run();
        long end = System.nanoTime();
        return end - start;
    }



}
