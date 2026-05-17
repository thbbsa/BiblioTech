package views;

import controller.FuncionarioController;
import controller.LivroController;
import controller.EmprestimoController;
import controller.CompraController;
import java.util.Scanner;

public class MenuAdministrador {

    private FuncionarioController funcionarioController;
    private LivroController livroController;
    private EmprestimoController emprestimoController;
    private CompraController compraController;
    private Scanner scanner;

    public MenuAdministrador(FuncionarioController funcionarioController,
                             LivroController livroController,
                             EmprestimoController emprestimoController,
                             CompraController compraController,
                             Scanner scanner) {
        this.funcionarioController = funcionarioController;
        this.livroController = livroController;
        this.emprestimoController = emprestimoController;
        this.compraController = compraController;
        this.scanner = scanner;
    }

    public void exibir() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║    MENU ADMINISTRADOR            ║");
            System.out.println("╚══════════════════════════════════╝");

            System.out.println("\n📚 -- LIVROS --");
            System.out.println("1. Consultar Livros");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Deletar Livro");

            System.out.println("\n📖 -- EMPRÉSTIMOS --");
            System.out.println("5. Listar Empréstimos");
            System.out.println("6. Listar Atrasados");
            System.out.println("7. Registrar Devolução");
            System.out.println("8. Listar Devoluções");
            System.out.println("9. Estatísticas");

            System.out.println("\n👥 -- FUNCIONÁRIOS --");
            System.out.println("10. Cadastrar Funcionário");
            System.out.println("11. Listar Funcionários");
            System.out.println("12. Deletar Funcionário");

            System.out.println("\n🛍️  -- COMPRAS --");
            System.out.println("13. Listar Compras");
            System.out.println("14. Listar Pendentes");
            System.out.println("15. Confirmar Compra");
            System.out.println("16. Cancelar Compra");
            System.out.println("17. Reembolsar Compra");
            System.out.println("18. Relatório Vendas");

            System.out.println("\n0. Sair");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                // ========== LIVROS ==========
                case "1":
                    livroController.consultarLivros();
                    pausa();
                    break;
                case "2":
                    livroController.criarLivro();
                    pausa();
                    break;
                case "3":
                    livroController.editarLivro();
                    pausa();
                    break;
                case "4":
                    livroController.deletarLivro();
                    pausa();
                    break;

                // ========== EMPRÉSTIMOS ==========
                case "5":
                    emprestimoController.listarEmprestimos();
                    pausa();
                    break;
                case "6":
                    emprestimoController.listarAtrasados();
                    pausa();
                    break;
                case "7":
                    emprestimoController.devolverEmprestimo();
                    pausa();
                    break;
                case "8":
                    emprestimoController.listarDevolucoes();
                    pausa();
                    break;
                case "9":
                    emprestimoController.mostrarEstatisticas();
                    pausa();
                    break;

                // ========== FUNCIONÁRIOS ==========
                case "10":
                    funcionarioController.cadastrarFuncionario();
                    pausa();
                    break;
                case "11":
                    funcionarioController.listarFuncionarios();
                    pausa();
                    break;
                case "12":
                    funcionarioController.deletarFuncionario();
                    pausa();
                    break;

                // ========== COMPRAS ==========
                case "13":
                    compraController.listarCompras();
                    pausa();
                    break;
                case "14":
                    compraController.listarPendentes();
                    pausa();
                    break;
                case "15":
                    compraController.confirmarCompra();
                    pausa();
                    break;
                case "16":
                    compraController.cancelarCompra();
                    pausa();
                    break;
                case "17":
                    compraController.reembolsoCompra();
                    pausa();
                    break;
                case "18":
                    compraController.relatorioVendas();
                    pausa();
                    break;

                // ========== SAIR ==========
                case "0":
                    rodando = false;
                    System.out.println("✔ Até logo!");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    // Pausa para o usuário ler
    private void pausa() {
        System.out.print("\n[Pressione ENTER para continuar...]");
        scanner.nextLine();
    }
}