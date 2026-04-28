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
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void cadastrarFuncionario(String nome, String email, String senha) {
        sistema.salvaFuncionario(nome, email, senha);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void excluirFuncionario(int id) {
        boolean removido = sistema.deletarFuncionario(id);

        if (removido) {
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Erro: Funcionário não encontrado.");
        }
    }
}