import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private int proximoIdFuncionario = 1;
    private int proximoIdLivro = 1;
    private Scanner leitura = new Scanner(System.in);

    // ========================
    // GERENCIAMENTO DE FUNCIONÁRIOS
    // ========================

    public Funcionario salvaFuncionario(String nome, String email, String senha) {
        Funcionario f = new Funcionario(proximoIdFuncionario++, nome, email, senha);
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
    // GERENCIAMENTO DE LIVROS
    // ========================

    public Livro salvarLivro(String titulo, String autor, String editora, int anoPublicacao, int quantidadeTotal,
                             int quantidadeDisponivel, String genero) {
        Livro l = new Livro(proximoIdLivro++, titulo, autor, editora, anoPublicacao, quantidadeTotal, quantidadeDisponivel, genero);
        livros.add(l);
        return l;
    }

    public boolean deletarLivro(int id) {
        return livros.removeIf(l -> l.getId() == id);
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
            System.out.println("\n=============================");
            System.out.println("       MENU FUNCIONÁRIO      ");
            System.out.println("=============================");
            System.out.println("1. Consultar Livros");
            System.out.println("2. Realizar Empréstimo");
            System.out.println("3. Consultar Empréstimos");
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
            System.out.println("\n=============================");
            System.out.println("     MENU ADMINISTRADOR      ");
            System.out.println("=============================");
            System.out.println("-- Livros --");
            System.out.println("1. Consultar Livros");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("-- Empréstimos --");
            System.out.println("5. Realizar Empréstimo");
            System.out.println("6. Consultar Empréstimos");
            System.out.println("-- Funcionários --");
            System.out.println("7. Cadastrar Funcionário");
            System.out.println("8. Listar Funcionários");
            System.out.println("9. Excluir Funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1: consultarLivro(); break;
                case 2: criarLivro(); break;
                case 3: editarLivro(); break;
                case 4: deletarLivro(); break;
                case 5: realizarEmprestimo(); break;
                case 6: consultarEmprestimo(); break;
                case 7: menuCadastrarFuncionario(adm); break;
                case 8: listarFuncionarios(); break;
                case 9: menuExcluirFuncionario(adm); break;
                case 0: rodando = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // AÇÕES DE FUNCIONÁRIOS
    // ========================

    private void menuCadastrarFuncionario(Administrador adm) {
        System.out.println("\n--- CADASTRAR FUNCIONÁRIO ---");
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
        listarFuncionarios();
        System.out.print("ID do funcionário a excluir: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        adm.excluirFuncionario(id);
        pausar();
    }

    // ========================
    // AÇÕES DE LIVROS
    // ========================

    private void consultarLivro() {
        System.out.println("\n--- LIVROS CADASTRADOS ---");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro l : livros) {
                System.out.println(l);
            }
        }

        pausar();
    }

    private void criarLivro() {
        System.out.println("\n--- CADASTRAR LIVRO ---");

        System.out.print("Título: ");
        String titulo = leitura.nextLine();

        System.out.print("Autor: ");
        String autor = leitura.nextLine();

        System.out.print("Editora: ");
        String editora = leitura.nextLine();

        System.out.print("Ano de publicação: ");
        int anoPublicacao = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Quantidade total: ");
        int qntTotalLivros = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Quantidade disponível: ");
        int qntDisponivelLivros = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Gênero: ");
        String genero = leitura.nextLine();

        salvarLivro(titulo, autor, editora, anoPublicacao, qntTotalLivros, qntDisponivelLivros, genero);
        System.out.println("✔ Livro cadastrado com sucesso!");
        pausar();
    }

    private void editarLivro() {
        consultarLivro();

        System.out.print("ID do livro que deseja editar: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        boolean encontrado = false;

        for (Livro l : livros) {
            if (id == l.getId()) {
                encontrado = true;

                System.out.println("\n--- EDITAR LIVRO ---");
                System.out.println("1. Título");
                System.out.println("2. Autor");
                System.out.println("3. Editora");
                System.out.println("4. Ano de Publicação");
                System.out.println("5. Quantidade Total");
                System.out.println("6. Quantidade Disponível");
                System.out.println("7. Gênero");
                System.out.println("0. Cancelar");
                System.out.print("Campo a editar: ");
                int campoEditar = leitura.nextInt();
                leitura.nextLine();

                switch (campoEditar) {
                    case 1:
                        System.out.print("Novo título: ");
                        l.setTitulo(leitura.nextLine());
                        break;
                    case 2:
                        System.out.print("Novo autor: ");
                        l.setAutor(leitura.nextLine());
                        break;
                    case 3:
                        System.out.print("Nova editora: ");
                        l.setEditora(leitura.nextLine());
                        break;
                    case 4:
                        System.out.print("Novo ano de publicação: ");
                        l.setAnoPublicacao(leitura.nextInt());
                        leitura.nextLine();
                        break;
                    case 5:
                        System.out.print("Nova quantidade total: ");
                        l.setQuantidadeTotal(leitura.nextInt());
                        leitura.nextLine();
                        break;
                    case 6:
                        System.out.print("Nova quantidade disponível: ");
                        l.setQuantidadeDisponivel(leitura.nextInt());
                        leitura.nextLine();
                        break;
                    case 7:
                        System.out.print("Novo gênero: ");
                        l.setGenero(leitura.nextLine());
                        break;
                    case 0:
                        System.out.println("Edição cancelada.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

                if (campoEditar != 0) {
                    System.out.println("✔ Livro atualizado com sucesso!");
                }

                pausar();
            }
        }

        if (!encontrado) {
            System.out.println("Erro: nenhum livro encontrado com esse ID.");
            pausar();
        }
    }

    private void deletarLivro() {
        consultarLivro();

        System.out.print("ID do livro que deseja excluir: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        if (deletarLivro(id)) {
            System.out.println("✔ Livro removido com sucesso!");
        } else {
            System.out.println("Erro: nenhum livro encontrado com esse ID.");
        }

        pausar();
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