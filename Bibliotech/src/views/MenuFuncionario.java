package views;

import java.util.Scanner;
import controller.CompraController;
import controller.EmprestimoController;
import controller.FuncionarioController;
import controller.LivroController;
import models.Funcionario;

public class MenuFuncionario {

    private Funcionario funcionarioLogado;
    private FuncionarioController funcionarioController;
    private LivroController livroController;
    private EmprestimoController emprestimoController;
    private CompraController compraController;
    private Scanner scanner;

    public MenuFuncionario(Funcionario funcionarioLogado,
                           FuncionarioController funcionarioController,
                           LivroController livroController,
                           EmprestimoController emprestimoController,
                           CompraController compraController,
                           Scanner scanner) {
        this.funcionarioLogado = funcionarioLogado;
        this.funcionarioController = funcionarioController;
        this.livroController = livroController;
        this.emprestimoController = emprestimoController;
        this.compraController = compraController;
        this.scanner = scanner;
    }

    public void exibir() {
        boolean rodando = true;

        while (rodando) {
            try {
                System.out.println("\n╔════════════════════════════════════╗");
                System.out.println("║   MENU FUNCIONÁRIO - " + funcionarioLogado.getNome() + "     ║");
                System.out.println("╚════════════════════════════════════╝");

                System.out.println("\n📚 -- LIVROS --");
                System.out.println("1. Consultar Livros");
                System.out.println("2. Livros Disponíveis");
                System.out.println("3. Buscar Livro");

                System.out.println("\n📖 -- EMPRÉSTIMOS --");
                System.out.println("4. Listar Empréstimos");
                System.out.println("5. Registrar Devolução");
                System.out.println("6. Listar Devoluções");

                System.out.println("\n🛍️  -- COMPRAS --");
                System.out.println("7. Realizar compra de Livro");
                System.out.println("8. Listar Compras");

                System.out.println("\n👤 -- PERFIL --");
                System.out.println("9. Ver Perfil");

                System.out.println("\n0. Sair");
                System.out.print("\nEscolha: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    // ========== LIVROS ==========
                    case "1":
                        livroController.consultarLivros();
                        pausa();
                        break;

                    case "2":
                        livroController.consultarLivrosDisponíveis();
                        pausa();
                        break;

                    case "3":
                        menuBuscarLivro();
                        pausa();
                        break;

                    // ========== EMPRÉSTIMOS ==========
                    case "4":
                        emprestimoController.listarEmprestimos();
                        pausa();
                        break;

                    case "5":
                        emprestimoController.devolverEmprestimo();
                        pausa();
                        break;

                    case "6":
                        emprestimoController.listarDevolucoes();
                        pausa();
                        break;
                    // ========== Compras ==========
                    case "7":
                        compraController.criarCompra();
                        pausa();
                        break;
                    case "8":
                        compraController.listarCompras();
                        pausa();
                        break;

                    // ========== PERFIL ==========
                    case "9":
                        funcionarioController.visualizarPerfil(funcionarioLogado);
                        pausa();
                        break;

                    case "0":
                        rodando = false;
                        System.out.println("✔ Saindo... Até logo!");
                        break;

                    default:
                        System.out.println("❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n❌ Ocorreu um erro inesperado no menu do funcionário: " + e.getMessage());
                pausa();
            }
        }
    }

    private void menuBuscarLivro() {
        try {
            System.out.println("\n--- BUSCAR LIVRO ---");
            System.out.println("1. Por Título");
            System.out.println("2. Por Autor");
            System.out.println("3. Por Gênero");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    livroController.buscarPorTitulo();
                    break;
                case "2":
                    livroController.buscarPorAutor();
                    break;
                case "3":
                    livroController.buscarPorGenero();
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao acessar o menu de busca: " + e.getMessage());
        }
    }

    private void pausa() {
        System.out.print("\n[Pressione ENTER para continuar...]");
        scanner.nextLine();
    }
}