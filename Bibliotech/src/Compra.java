/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech

    Descrição:
    Classe que representa uma compra de livro no sistema.

    Armazena informações como id da compra, id do cliente, id do livro,
    quantidade adquirida, preço unitário, preço total, data da compra
    e status da compra (pendente, concluída ou cancelada).

    O preço total é calculado automaticamente com base na quantidade
    e no preço unitário.

    Possui métodos getters para acesso aos dados, setters para atualização
    de status e quantidade, além de sobrescrever o método toString() para
    exibir as informações de forma organizada no console.
*/


import java.time.LocalDate;

public class Compra {
    private int id;
    private int idCliente;
    private int idLivro;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;
    private String dataCompra;
    private String status; // "pendente", "concluída", "cancelada"

    public Compra(int id, int idCliente, int idLivro, int quantidade, double precoUnitario, String dataCompra, String status) {
        this.id = id;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoUnitario * quantidade;
        this.dataCompra = dataCompra;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.precoTotal = this.precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Cliente: " + idCliente + " | Livro: " + idLivro +
                " | Quantidade: " + quantidade + " | Preço Unitário: R$ " + String.format("%.2f", precoUnitario) +
                " | Total: R$ " + String.format("%.2f", precoTotal) + " | Data: " + dataCompra +
                " | Status: " + status;
    }
}