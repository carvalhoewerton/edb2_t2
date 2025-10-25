package AVL;

public class AVLTreeApplication {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        System.out.println("--- Árvore Inicial ---");
        tree.printTreeVisual();

        int[] inserir = {10, 5, 15, 3};
        int[] buscar = {10, 5, 15, 99};
        int[] deletar = {15, 3, 5, 10};

        double[] temposInserir = new double[inserir.length];
        double[] temposBuscar = new double[buscar.length];
        double[] temposDeletar = new double[deletar.length];

        System.out.println("\n--- Inserções ---");
        for (int i = 0; i < inserir.length; i++) {
            int val = inserir[i];
            temposInserir[i] = tree.medirTempo(() -> tree.insert(val)) / 1_000_000_000.0;
            System.out.printf("Inserir %d levou: %.9f s%n", val, temposInserir[i]);
            tree.printTreeVisual();
        }

        System.out.println("\n--- Buscas ---");
        for (int i = 0; i < buscar.length; i++) {
            int val = buscar[i];
            temposBuscar[i] = tree.medirTempo(() -> {
                Node n = tree.find(val);
                System.out.println(n != null ? "Encontrado: " + n.getValue() : "Não encontrado");
            }) / 1_000_000_000.0;
            System.out.printf("Buscar %d levou: %.9f s%n", val, temposBuscar[i]);
            tree.printTreeVisual();
        }

        System.out.println("\n--- Deleções ---");
        for (int i = 0; i < deletar.length; i++) {
            int val = deletar[i];
            temposDeletar[i] = tree.medirTempo(() -> tree.delete(val)) / 1_000_000_000.0;
            System.out.printf("Deletar %d levou: %.9f s%n", val, temposDeletar[i]);
            tree.printTreeVisual();
        }

        // Imprime tabela final em segundos
        System.out.println("\n--- Tabela Final de Tempos (s) ---");
        System.out.printf("%-10s %-15s %-15s %-15s%n", "Index", "Inserção", "Busca", "Deleção");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%-10d %-15.9f %-15.9f %-15.9f%n", i + 1, temposInserir[i], temposBuscar[i], temposDeletar[i]);
        }
    }
}
