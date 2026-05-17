package models;/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech

    Descrição:
    Classe que representa um administrador do sistema.

    Armazena informações como nome, email e senha, utilizadas
    para autenticação e identificação do administrador.

    Possui acesso ao sistema (classe Sistema), permitindo realizar
    ações administrativas como cadastrar e excluir funcionários.

    Atua como um nível de controle superior dentro do sistema,
    sendo responsável pelo gerenciamento de usuários do tipo funcionário.
*/

public class Administrador {
    private int id;
    private String nome;
    private String email;
    private String senha;

    // Construtor
    public Administrador(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters
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

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}