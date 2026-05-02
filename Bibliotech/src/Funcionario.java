/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech

    Descrição:
    Classe que representa um funcionário do sistema.

    Armazena informações básicas como id, nome, email e senha,
    utilizadas para autenticação e identificação do funcionário.

    Possui métodos getters para acesso aos dados e sobrescreve
    o método toString() para exibir as informações de forma
    simplificada no console.
*/

public class Funcionario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Funcionario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + email;
    }
}