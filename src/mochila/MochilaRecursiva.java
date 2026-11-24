package mochila;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MochilaRecursiva {

    public ResultadoMochila resolver(List<Item> itens, int capacidadeMaxima) {
        int n = itens.size();
        int valorMaximo = calcularRecursivo(itens, n, capacidadeMaxima);
        List<Item> escolhidos = reconstruirItens(itens, n, capacidadeMaxima);

        return new ResultadoMochila(valorMaximo, escolhidos);
    }

    // Explora todas as combinações possíveis de inclusão (1) ou exclusão (0)
    private int calcularRecursivo(List<Item> itens, int n, int capacidade) {
        // Caso base: nenhum item restante ou mochila sem espaço
        if (n == 0 || capacidade == 0) {
            return 0;
        }

        Item atual = itens.get(n - 1);

        // Se o item não cabe na capacidade restante, ele é ignorado
        if (atual.getPeso() > capacidade) {
            return calcularRecursivo(itens, n - 1, capacidade);
        }

        // Compara o ganho entre incluir o item atual ou deixá-lo para trás
        int incluir = atual.getValor() + calcularRecursivo(itens, n - 1, capacidade - atual.getPeso());
        int naoIncluir = calcularRecursivo(itens, n - 1, capacidade);

        return Math.max(incluir, naoIncluir);
    }

    // Identifica quais itens compõem a solução ótima refazendo a decisão
    private List<Item> reconstruirItens(List<Item> itens, int n, int capacidadeMaxima) {
        List<Item> escolhidos = new ArrayList<>();
        int capRestante = capacidadeMaxima;

        for (int i = n; i > 0; i--) {
            Item atual = itens.get(i - 1);
            int otimoComItem = calcularRecursivo(itens, i, capRestante);
            int otimoSemItem = calcularRecursivo(itens, i - 1, capRestante);

            if (otimoComItem != otimoSemItem) {
                escolhidos.add(atual);
                capRestante -= atual.getPeso();
            }
        }

        Collections.reverse(escolhidos);
        return escolhidos;
    }
}