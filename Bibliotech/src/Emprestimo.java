/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech

    Descrição:
    Classe que representa um empréstimo de livro no sistema.

    Armazena informações como o id do empréstimo, id do livro,
    id do cliente, data do empréstimo, data de devolução e o
    status indicando se o livro foi devolvido ou não.

    Possui métodos getters para acesso aos dados, um setter para
    atualizar o status de devolução e sobrescreve o método toString()
    para exibir as informações de forma organizada no console.
*/

public class Emprestimo {
    int id;
    int idLivro;
    int idCliente;
    String dataEmprestimo;
    String dataDevolucao;
    boolean devolvido;

    Emprestimo(int id, int idLivro, int idCliente, String dataEmprestimo, String dataDevolucao,
               boolean devolvido) {
        this.id = id;
        this.idLivro = idLivro;
        this.idCliente = idCliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public int getId() {
        return id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    @Override
    public String toString() {
        return id + " | Livro: " + idLivro + " | Cliente: " + idCliente +
                " | Empréstimo: " + dataEmprestimo + " | Devolução: " + dataDevolucao +
                " | Status: " + (devolvido ? "Devolvido" : "Pendente");
    }
}
