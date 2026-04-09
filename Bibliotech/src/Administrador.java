public class Administrador {
    private String nome;
    private String email;
    private String senha;

    public Administrador(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }
}
