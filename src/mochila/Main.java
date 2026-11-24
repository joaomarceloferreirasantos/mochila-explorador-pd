package mochila;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Conjunto obrigatório de itens para teste
        List<Item> itens = Arrays.asList(
            new Item(1, "Lanterna", 2, 6),
            new Item(2, "Barraca", 4, 10),
            new Item(3, "Faca", 1, 3),
            new Item(4, "Cobertor", 3, 7),
            new Item(5, "Cantil", 2, 5),
            new Item(6, "Mapa", 1, 4),
            new Item(7, "Corda", 5, 9)
        );

        int[] capacidades = {6, 10, 15};

        MochilaRecursiva versaoA = new MochilaRecursiva();
        MochilaTopDown versaoB = new MochilaTopDown();
        MochilaBottomUp versaoC = new MochilaBottomUp();

        for (int W : capacidades) {
            System.out.println("============================================================");
            System.out.println("TESTE PARA CAPACIDADE DA MOCHILA W = " + W);
            System.out.println("============================================================");

            ResultadoMochila resA = versaoA.resolver(itens, W);
            ResultadoMochila resB = versaoB.resolver(itens, W);
            ResultadoMochila resC = versaoC.resolver(itens, W);

            imprimirResultado("Versão A - Recursão Ingênua", resA, W);
            imprimirResultado("Versão B - Top-Down (Memoização)", resB, W);
            imprimirResultado("Versão C - Bottom-Up (Tabulação)", resC, W);

            boolean iguais = (resA.getValorMaximo() == resB.getValorMaximo())
                          && (resB.getValorMaximo() == resC.getValorMaximo());

            System.out.println("  -> Validação entre versões: " + (iguais ? "OK (Valores idênticos)" : "DIVERGÊNCIA"));
            System.out.println();
        }
    }

    private static void imprimirResultado(String titulo, ResultadoMochila resultado, int capacidadeMaxima) {
        System.out.println("  [" + titulo + "]");
        System.out.println("    Valor Ótimo: " + resultado.getValorMaximo());
        System.out.println("    Peso Total Ocupado: " + resultado.getPesoTotal() + " / " + capacidadeMaxima);
        System.out.print("    Itens Escolhidos: ");

        List<Item> escolhidos = resultado.getItensEscolhidos();
        for (int i = 0; i < escolhidos.size(); i++) {
            Item item = escolhidos.get(i);
            System.out.print(item.getNome() + " (P:" + item.getPeso() + ", V:" + item.getValor() + ")");
            if (i < escolhidos.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }
}