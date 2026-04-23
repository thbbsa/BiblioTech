import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    public void adicionarFuncionario(Funcionario f) {
        System.out.println(f);
        funcionarios.add(f);
    }

    public Funcionario salvaFuncionario(String nome, String email, String senha) {
        Funcionario f = new Funcionario(proximoId++, nome, email, senha);
        this.adicionarFuncionario(f);
        return f;
    }

    public boolean deletarFuncionario(int id) {
        return this.getFuncionarios().removeIf(f -> id == f.getId());
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void exibirMenuFunc(int tipoUsuario, Administrador adm) {
        Scanner leitura = new Scanner(System.in);

        boolean rodandoMenu = true;

        while (rodandoMenu) {

            if (tipoUsuario == 1) {

                System.out.println("\n--- MENU FUNCIONÁRIO ---");
                System.out.println("1. Consultar Produto/Livro");
                System.out.println("2. Empréstimo de Livro");
                System.out.println("3. Consultar Empréstimo");
                System.out.println("0. Voltar");
                System.out.print("Escolha: ");

                int opFunc = leitura.nextInt();
                leitura.nextLine();

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

                    case 0:
                        rodandoMenu = false;
                        break;

                    default:
                        System.out.println("Opção inválida");
                }

            } else if (tipoUsuario == 2) {

                System.out.println("\n--- MENU ADMINISTRADOR ---");
                System.out.println("1. Consultar Estoque");
                System.out.println("4. Consultar Livro");
                System.out.println("5. Criar Livro");
                System.out.println("6. Editar Livro");
                System.out.println("7. Deletar Livro");
                System.out.println("8. Empréstimo de Livro");
                System.out.println("9. Consultar Empréstimo");
                System.out.println("10. Cadastrar Funcionário");
                System.out.println("11. Listar Funcionários");
                System.out.println("12. Excluir Funcionário");
                System.out.println("0. Voltar");
                System.out.print("Escolha: ");

                int opAdm = leitura.nextInt();
                leitura.nextLine();

                switch (opAdm) {

                    case 1:
                        System.out.println("Consultando estoque...");
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
                        System.out.print("Digite seu nome: ");
                        String nome = leitura.nextLine();

                        System.out.print("Digite seu email: ");
                        String email = leitura.nextLine();

                        System.out.print("Digite sua senha: ");
                        String senha = leitura.nextLine();

                        adm.cadastrarFuncionario(nome, email, senha);

                        System.out.println("\nPressione ENTER para voltar...");
                        leitura.nextLine(); // ou leitura.nextLine() duas vezes se precisar limpar buffer

                        System.out.println("Funcionário cadastrado!");
                        break;

                    case 11:
                        System.out.println("Listando funcionários...");

                        if (this.getFuncionarios().size() <= 0) {
                            System.out.println("Não há funcionarios cadastrados.");
                        }

                        for (Funcionario f : this.getFuncionarios()) {
                            System.out.println(
                                    f.getId() + " - " +
                                            f.getNome() + " - " +
                                            f.getEmail()
                            );
                        }

                        System.out.println("\nPressione ENTER para voltar...");
                        leitura.nextLine(); // ou leitura.nextLine() duas vezes se precisar limpar buffer
                        break;

                    case 12:
                        System.out.print("Digite o Id do Funcionario que deseja excluir: ");
                        int id = leitura.nextInt();

                        adm.excluirFuncionario(id);
                        break;

                    case 0:
                        rodandoMenu = false;
                        break;

                    default:
                        System.out.println("Opção inválida");
                }
            }
        }
    }

    public void realizarLoginFunc(int tipoUsuario, String emailDigitado, String senhaDigitada) {

        switch (tipoUsuario) {

            case 1:
                System.out.println("Funcionario logado");
                break;

            case 2:
                System.out.println("Administrador");

                Administrador admCadastrado =
                        new Administrador("italo", "italo@gmail.com", "123456", this);

                if (admCadastrado.getEmail().equals(emailDigitado)
                        && admCadastrado.getSenha().equals(senhaDigitada)) {

                    this.exibirMenuFunc(tipoUsuario, admCadastrado);

                } else {
                    System.out.println("Acesso Negado!");
                }
                break;

            default:
                System.out.println("Opção inválida");
        }
    }
}