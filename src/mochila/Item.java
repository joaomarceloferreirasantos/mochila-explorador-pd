package mochila;

public class Item {
    private int id;
    private String nome;
    private int peso;
    private int valor;

    public Item(int id, String nome, int peso, int valor) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }
}