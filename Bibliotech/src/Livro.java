/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech

    Descrição:
    Classe que representa um livro dentro do sistema.

    Armazena informações como título, autor, editora, ano de publicação,
    gênero, preço e controle de estoque (quantidade total e disponível).

    Possui métodos para verificar se o livro está disponível, reduzir e
    aumentar o estoque, além de getters e setters para manipulação dos dados.

    Também sobrescreve o método toString() para exibir os dados do livro
    de forma organizada no console.
*/

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;
    private String genero;
    private double preco;

    public Livro(int id, String titulo, String autor, String editora, int anoPublicacao, int quantidadeTotal,
                 int quantidadeDisponivel, String genero, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.genero = genero;
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public boolean reduzirEstoque() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    public void aumentarEstoque() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
    }

    public double getPreco() {
        return preco;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getGenero() {
        return genero;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " | " + autor + " | " + editora +
                " | " + anoPublicacao + " | Estoque: " + quantidadeDisponivel +
                "/" + quantidadeTotal + " | " + genero + " | R$ " + preco;
    }
}