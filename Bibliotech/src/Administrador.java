public class Administrador {
    private String nome;
    private String email;
    private String senha;
    private Sistema sistema;

    public Administrador(String nome, String email, String senha, Sistema sistema) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sistema = sistema;

    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void cadastrarFuncionario(String nome, String email, String senha) {
        // manda pro sistema guardar
        sistema.criarFuncionario(nome, email, senha);

        System.out.println("Funcionário cadastrado com sucesso!");
    }
}
