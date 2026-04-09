import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.add(f);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void exibirMenuFunc(int tipoUsuario, Administrador adm) {

        Scanner leitura = new Scanner(System.in);

        switch (tipoUsuario) {

            case 1:
                System.out.println("\n--- MENU FUNCIONÁRIO ---");
                System.out.println("1. Consultar Produto/Livro");
                System.out.println("2. Empréstimo de Livro");
                System.out.println("3. Consultar Empréstimo");
                System.out.print("Escolha: ");

                int opFunc = leitura.nextInt();

                switch (opFunc) {
                    case 1:
                        System.out.println("Consultando livros...");
                        break;
                    case 2:
                        System.out.println("Realizando empréstimo...");
                        break;
                    case 3:
                        System.out.println("Consultando empréstimos...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
                break;

            case 2:
                System.out.println("\n--- MENU ADMINISTRADOR ---");
                System.out.println("1. Consultar Estoque");
                System.out.println("2. Criar Estoque");
                System.out.println("3. Deletar Estoque");
                System.out.println("4. Consultar Livro");
                System.out.println("5. Criar Livro");
                System.out.println("6. Editar Livro");
                System.out.println("7. Deletar Livro");
                System.out.println("8. Empréstimo de Livro");
                System.out.println("9. Consultar Empréstimo");
                System.out.println("10. Cadastrar Funcionário");
                System.out.println("11. Listar Funcionários");
                System.out.println("12. Excluir Funcionário");
                System.out.print("Escolha: ");

                int opAdm = leitura.nextInt();
                leitura.nextLine(); // limpar buffer

                switch (opAdm) {
                    case 1:
                        System.out.println("Consultando estoque...");
                        break;
                    case 2:
                        System.out.println("Criando estoque...");
                        break;
                    case 3:
                        System.out.println("Deletando estoque...");
                        break;
                    case 4:
                        System.out.println("Consultando livros...");
                        break;
                    case 5:
                        System.out.println("Criando livro...");
                        break;
                    case 6:
                        System.out.println("Editando livro...");
                        break;
                    case 7:
                        System.out.println("Deletando livro...");
                        break;
                    case 8:
                        System.out.println("Realizando empréstimo...");
                        break;
                    case 9:
                        System.out.println("Consultando empréstimos...");
                        break;

                    case 10:
                        // cadastro real
                        System.out.print("Digite seu nome: ");
                        String nome = leitura.nextLine();

                        System.out.print("Digite seu email: ");
                        String email = leitura.nextLine();

                        System.out.print("Digite sua senha: ");
                        String senha = leitura.nextLine();

                        // chama o cadastro (via sistema)
                        adm.cadastrarFuncionario(nome, email, senha);

                        System.out.println("Funcionário cadastrado!");
                        break;
                    case 11:
                        System.out.println("Listando funcionários...");
                        break;

                    case 12:
                        System.out.println("Excluindo funcionário...");
                        break;

                    default:
                        System.out.println("Opção inválida");
                }
                break;

            default:
                System.out.println("Opção inválida");
        }
    }

    public void realizarLoginFunc(int tipoUsuario, String emailDigitado, String senhaDigitada) {
        switch (tipoUsuario) {
            case 1:
                System.out.println("Funcionario");
                break;
            case 2:
                System.out.println("Administrador");
                Administrador admCadastrado = new Administrador("italo", "italo@gmail.com", "123456", new Sistema());

                if (admCadastrado.getEmail().equals(emailDigitado) && admCadastrado.getSenha().equals(senhaDigitada)) {
                    this.exibirMenuFunc(tipoUsuario, admCadastrado);
                } else {
                    System.out.println("Acesso Negado!");
                }
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }
}
