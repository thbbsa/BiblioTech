import java.util.ArrayList;
import java.util.Scanner;

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
        sistema.salvaFuncionario(nome, email, senha);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void excluirFuncionario(int id) {
        Scanner leitura = new Scanner(System.in);

        if (sistema.deletarFuncionario(id)) {
            System.out.println("Removido com sucesso!");

            System.out.println("\nPressione ENTER para continuar...");
            leitura.nextLine(); // ou leitura.nextLine() duas vezes se precisar limpar buffer
        } else {
            System.out.println("Erro: Funcionário não encontrado.");

            System.out.println("\nPressione ENTER para continuar...");
            leitura.nextLine(); // ou leitura.nextLine() duas vezes se precisar limpar buffer
        }
    }
}
