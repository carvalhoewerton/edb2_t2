package AVL;

public class AVLTree {
    private Node root;

    public void insert(int value) {
        root = insert(root, value);
    }


    private int height(Node node) {
        return (node != null) ? node.getHeight() : -1;
    }

    private Node rotateLeft(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    private Node rotateRightLeft(Node z) {
        z.setRight(rotateRight(z.getRight()));
        return rotateLeft(z);
    }

    private Node rotateLeftRight(Node z) {
        z.setLeft(rotateLeft(z.getLeft()));
        return rotateRight(z);
    }


    private Node balance(Node node, int value) {
        int balance = node.getBalanceFactor();

        // LL
        if (balance > 1 && value < node.getLeft().getValue()) {
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && value > node.getRight().getValue()) {
            return rotateLeft(node);
        }

        // LR
        if (balance > 1 && value > node.getLeft().getValue()) {
            return rotateLeftRight(node);
        }

        // RL
        if (balance < -1 && value < node.getRight().getValue()) {
            return rotateRightLeft(node);
        }

        return node;
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.getValue()) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insert(node.getRight(), value));
        } else {
            return node;
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        return balance(node, value);
    }

    private Node findMin(Node node) {
        Node current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private Node balanceDelete(Node node) {
        int balance = node.getBalanceFactor();

        if (balance > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                return rotateLeftRight(node);
            }
            else {
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                return rotateRightLeft(node);
            }
            else {
                return rotateLeft(node);
            }
        }

        return node;
    }


    public void delete(int value) {
        root = delete(root, value);
    }


    private Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(delete(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(delete(node.getRight(), value));
        } else {

            if (node.getLeft() == null || node.getRight() == null) {
                Node temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node successor = findMin(node.getRight());
                node.setValue(successor.getValue());
                node.setRight(delete(node.getRight(), successor.getValue()));
            }
        }

        if (node == null) {
            return null;
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        return balanceDelete(node);
    }



    public Node find(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.getValue()) {
                return current;
            } else if (value < current.getValue()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }



    public void printTreeVisual() {
        System.out.println("--- Início da Árvore (vista de lado) ---");
        printTreeVisual(root, 0);
        System.out.println("--- Fim da Árvore ---");
    }


    private void printTreeVisual(Node node, int level) {
        if (node == null) {
            return;
        }

        printTreeVisual(node.getRight(), level + 1);


        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println("[" + node.getValue() + "]");

        printTreeVisual(node.getLeft(), level + 1);
    }

    public long medirTempo(Runnable operacao) {
        long inicio = System.nanoTime();
        operacao.run();
        long fim = System.nanoTime();
        return fim - inicio;
    }
}