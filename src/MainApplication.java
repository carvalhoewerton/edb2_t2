
public class MainApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Erro: Informe 'avl' ou 'rb' como argumento.");
            return;
        }

        String choice = args[0].toLowerCase();

        switch (choice) {
            case "avl":
                System.out.println("Executando AVLTreeApplication...");
                AVL.AVLTreeApplication.main(new String[]{});
                break;
            case "rb":
                System.out.println("Executando RBTreeApplication...");
                RedBlackTree.RBTreeApplication.main(new String[]{});
                break;
            default:
                System.out.println("Argumento inválido! Use 'avl' ou 'rb'.");
        }
    }
}
