package mochila;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MochilaBottomUp {

    public ResultadoMochila resolver(List<Item> itens, int capacidadeMaxima) {
        int n = itens.size();
        // Tabela dp[i][c]: melhor valor usando os 'i' primeiros itens com capacidade 'c'
        int[][] dp = new int[n + 1][capacidadeMaxima + 1];

        // Preenchimento iterativo de baixo para cima
        for (int i = 1; i <= n; i++) {
            Item atual = itens.get(i - 1);

            for (int c = 0; c <= capacidadeMaxima; c++) {
                if (atual.getPeso() <= c) {
                    int incluir = atual.getValor() + dp[i - 1][c - atual.getPeso()];
                    int naoIncluir = dp[i - 1][c];
                    dp[i][c] = Math.max(incluir, naoIncluir);
                } else {
                    // Item excede a capacidade 'c' atual
                    dp[i][c] = dp[i - 1][c];
                }
            }
        }

        List<Item> escolhidos = reconstruirItens(itens, n, capacidadeMaxima, dp);
        return new ResultadoMochila(dp[n][capacidadeMaxima], escolhidos);
    }

    // Backtracking na tabela dp para recuperar os itens selecionados
    private List<Item> reconstruirItens(List<Item> itens, int n, int capacidadeMaxima, int[][] dp) {
        List<Item> escolhidos = new ArrayList<>();
        int capRestante = capacidadeMaxima;

        for (int i = n; i > 0; i--) {
            // Se o valor ótimo mudou em relação à linha anterior, o item 'i' foi incluído
            if (dp[i][capRestante] != dp[i - 1][capRestante]) {
                Item atual = itens.get(i - 1);
                escolhidos.add(atual);
                capRestante -= atual.getPeso();
            }
        }

        Collections.reverse(escolhidos);
        return escolhidos;
    }
}