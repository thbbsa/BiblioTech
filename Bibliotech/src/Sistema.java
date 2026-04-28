import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;
    private Scanner leitura = new Scanner(System.in);

    // ========================
    // GERENCIAMENTO DE FUNCIONÁRIOS
    // ========================

    public Funcionario salvaFuncionario(String nome, String email, String senha) {
        Funcionario f = new Funcionario(proximoId++, nome, email, senha);
        funcionarios.add(f);
        return f;
    }

    public boolean deletarFuncionario(int id) {
        return funcionarios.removeIf(f -> f.getId() == id);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    // ========================
    // LOGIN
    // ========================

    public void realizarLoginFunc(int tipoUsuario, String emailDigitado, String senhaDigitada) {
        switch (tipoUsuario) {
            case 1:
                System.out.println("Funcionário logado!");
                exibirMenuFuncionario();
                break;

            case 2:
                Administrador adm = new Administrador("italo", "italo@gmail.com", "123456", this);

                if (adm.getEmail().equals(emailDigitado) && adm.getSenha().equals(senhaDigitada)) {
                    System.out.println("Administrador logado!");
                    exibirMenuAdministrador(adm);
                } else {
                    System.out.println("Acesso negado! Email ou senha incorretos.");
                }
                break;

            default:
                System.out.println("Tipo de usuário inválido.");
        }
    }

    // ========================
    // MENU FUNCIONÁRIO
    // ========================

    private void exibirMenuFuncionario() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1. Consultar Livro");
            System.out.println("2. Empréstimo de Livro");
            System.out.println("3. Consultar Empréstimo");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1: consultarLivro(); break;
                case 2: realizarEmprestimo(); break;
                case 3: consultarEmprestimo(); break;
                case 0: rodando = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // MENU ADMINISTRADOR
    // ========================

    private void exibirMenuAdministrador(Administrador adm) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("-- Livros --");
            System.out.println("1. Consultar Livro");
            System.out.println("2. Criar Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("-- Estoque e Empréstimos --");
            System.out.println("5. Consultar Estoque");
            System.out.println("6. Empréstimo de Livro");
            System.out.println("7. Consultar Empréstimo");
            System.out.println("-- Funcionários --");
            System.out.println("8. Cadastrar Funcionário");
            System.out.println("9. Listar Funcionários");
            System.out.println("10. Excluir Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1: consultarLivro(); break;
                case 2: criarLivro(); break;
                case 3: editarLivro(); break;
                case 4: deletarLivro(); break;
                case 5: consultarEstoque(); break;
                case 6: realizarEmprestimo(); break;
                case 7: consultarEmprestimo(); break;
                case 8: menuCadastrarFuncionario(adm); break;
                case 9: listarFuncionarios(); break;
                case 10: menuExcluirFuncionario(adm); break;
                case 0: rodando = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // AÇÕES DE FUNCIONÁRIOS
    // ========================

    private void menuCadastrarFuncionario(Administrador adm) {
        System.out.print("Nome: ");
        String nome = leitura.nextLine();

        System.out.print("Email: ");
        String email = leitura.nextLine();

        System.out.print("Senha: ");
        String senha = leitura.nextLine();

        adm.cadastrarFuncionario(nome, email, senha);
        pausar();
    }

    private void listarFuncionarios() {
        System.out.println("\n--- FUNCIONÁRIOS CADASTRADOS ---");

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        }

        pausar();
    }

    private void menuExcluirFuncionario(Administrador adm) {
        System.out.print("Digite o ID do funcionário que deseja excluir: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        adm.excluirFuncionario(id);
        pausar();
    }

    // ========================
    // AÇÕES DE LIVROS (a implementar)
    // ========================

    private void consultarLivro() {
        System.out.println("Consultando livros...");
    }

    private void criarLivro() {
        System.out.println("Criando livro...");
    }

    private void editarLivro() {
        System.out.println("Editando livro...");
    }

    private void deletarLivro() {
        System.out.println("Deletando livro...");
    }

    private void consultarEstoque() {
        System.out.println("Consultando estoque...");
    }

    private void realizarEmprestimo() {
        System.out.println("Realizando empréstimo...");
    }

    private void consultarEmprestimo() {
        System.out.println("Consultando empréstimos...");
    }

    // ========================
    // UTILITÁRIO
    // ========================

    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        leitura.nextLine();
    }
}