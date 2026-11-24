package mochila;

import java.util.List;

public class ResultadoMochila {
    private int valorMaximo;
    private int pesoTotal;
    private List<Item> itensEscolhidos;

    public ResultadoMochila(int valorMaximo, List<Item> itensEscolhidos) {
        this.valorMaximo = valorMaximo;
        this.itensEscolhidos = itensEscolhidos;
        this.pesoTotal = calcularPesoTotal(itensEscolhidos);
    }

    private int calcularPesoTotal(List<Item> itens) {
        int soma = 0;
        for (Item item : itens) {
            soma += item.getPeso();
        }
        return soma;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public List<Item> getItensEscolhidos() {
        return itensEscolhidos;
    }
}