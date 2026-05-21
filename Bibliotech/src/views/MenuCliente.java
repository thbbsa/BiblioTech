package views;

import models.Cliente;
import controller.ClienteController;
import controller.LivroController;
import controller.EmprestimoController;
import controller.CompraController;
import java.util.Scanner;

public class MenuCliente {

    private Cliente clienteLogado;
    private ClienteController clienteController;
    private LivroController livroController;
    private EmprestimoController emprestimoController;
    private CompraController compraController;
    private Scanner scanner;

    public MenuCliente(Cliente clienteLogado,
                       ClienteController clienteController,
                       LivroController livroController,
                       EmprestimoController emprestimoController,
                       CompraController compraController,
                       Scanner scanner) {
        this.clienteLogado = clienteLogado;
        this.clienteController = clienteController;
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
                System.out.println("║     MENU CLIENTE - " + clienteLogado.getNome() + "     ║");
                System.out.println("╚════════════════════════════════════╝");

                System.out.println("\n📚 -- LIVROS --");
                System.out.println("1. Consultar Livros");
                System.out.println("2. Livros Disponíveis");
                System.out.println("3. Buscar Livro");

                System.out.println("\n📖 -- EMPRÉSTIMOS --");
                System.out.println("4. Realizar Empréstimo");
                System.out.println("5. Meus Empréstimos");
                System.out.println("6. Devolver Livro");

                System.out.println("\n🛍️  -- COMPRAS --");
                System.out.println("7. Comprar Livro");
                System.out.println("8. Minhas Compras");

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
                        emprestimoController.realizarEmprestimo(clienteLogado.getId());
                        pausa();
                        break;

                    case "5":
                        emprestimoController.listarEmprestimosPorCliente(clienteLogado.getId());
                        pausa();
                        break;

                    case "6":
                        emprestimoController.devolverEmprestimo();
                        pausa();
                        break;

                    // ========== COMPRAS ==========
                    case "7":
                        compraController.criarCompraCliente(clienteLogado, livroController);
                        pausa();
                        break;

                    case "8":
                        compraController.verMinhasComprasCliente(clienteLogado);
                        pausa();
                        break;

                    // ========== PERFIL ==========
                    case "9":
                        clienteController.visualizarPerfil(clienteLogado);
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
                System.out.println("\n❌ Ocorreu um erro inesperado no menu do cliente: " + e.getMessage());
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