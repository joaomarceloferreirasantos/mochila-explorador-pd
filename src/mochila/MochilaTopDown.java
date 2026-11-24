package mochila;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MochilaTopDown {

    public ResultadoMochila resolver(List<Item> itens, int capacidadeMaxima) {
        int n = itens.size();
        // Inicializa a matriz de memoização com -1 (indicando estados ainda não visitados)
        int[][] memo = new int[n + 1][capacidadeMaxima + 1];
        for (int[] linha : memo) {
            Arrays.fill(linha, -1);
        }

        int valorMaximo = calcularMemoizado(itens, n, capacidadeMaxima, memo);
        List<Item> escolhidos = reconstruirItens(itens, n, capacidadeMaxima, memo);

        return new ResultadoMochila(valorMaximo, escolhidos);
    }

    private int calcularMemoizado(List<Item> itens, int n, int capacidade, int[][] memo) {
        // Caso base
        if (n == 0 || capacidade == 0) {
            return 0;
        }

        // Retorna imediatamente se o subproblema já foi resolvido antes
        if (memo[n][capacidade] != -1) {
            return memo[n][capacidade];
        }

        Item atual = itens.get(n - 1);

        if (atual.getPeso() > capacidade) {
            memo[n][capacidade] = calcularMemoizado(itens, n - 1, capacidade, memo);
        } else {
            int incluir = atual.getValor() + calcularMemoizado(itens, n - 1, capacidade - atual.getPeso(), memo);
            int naoIncluir = calcularMemoizado(itens, n - 1, capacidade, memo);
            memo[n][capacidade] = Math.max(incluir, naoIncluir);
        }

        return memo[n][capacidade];
    }

    // Reconstrução utilizando os valores já armazenados na tabela memoizada
    private List<Item> reconstruirItens(List<Item> itens, int n, int capacidadeMaxima, int[][] memo) {
        List<Item> escolhidos = new ArrayList<>();
        int capRestante = capacidadeMaxima;

        for (int i = n; i > 0; i--) {
            Item atual = itens.get(i - 1);
            int otimoSemItem = calcularMemoizado(itens, i - 1, capRestante, memo);

            if (memo[i][capRestante] != otimoSemItem) {
                escolhidos.add(atual);
                capRestante -= atual.getPeso();
            }
        }

        Collections.reverse(escolhidos);
        return escolhidos;
    }
}