package views;

import models.Cliente;
import models.Funcionario;
import controller.FuncionarioController;
import controller.ClienteController;
import controller.LivroController;
import controller.EmprestimoController;
import controller.CompraController;
import java.util.Scanner;

public class MenuPrincipal {

    private ClienteController clienteController;
    private FuncionarioController funcionarioController;
    private LivroController livroController;
    private EmprestimoController emprestimoController;
    private CompraController compraController;
    private MenuAdministrador menuAdmin;
    private Scanner scanner;

    public MenuPrincipal(ClienteController clienteController,
                         FuncionarioController funcionarioController,
                         LivroController livroController,
                         EmprestimoController emprestimoController,
                         CompraController compraController,
                         MenuAdministrador menuAdmin,
                         Scanner scanner) {
        this.clienteController = clienteController;
        this.funcionarioController = funcionarioController;
        this.livroController = livroController;
        this.emprestimoController = emprestimoController;
        this.compraController = compraController;
        this.menuAdmin = menuAdmin;
        this.scanner = scanner;
    }

    public void exibir() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║   BEM-VINDO AO BIBLIOTECH SYSTEM   ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("\n1. Cadastro de Cliente");
            System.out.println("2. Login Cliente");
            System.out.println("3. Login Funcionário");
            System.out.println("4. Login Administrador");
            System.out.println("0. Sair");
            System.out.print("\nEscolha: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    clienteController.cadastrarCliente();
                    pausa();
                    break;

                case "2":
                    fazerLoginCliente();
                    break;

                case "3":
                    fazerLoginFuncionario();
                    break;

                case "4":
                    fazerLoginAdmin();
                    break;

                case "0":
                    rodando = false;
                    System.out.println("\n✔ Obrigado por usar BiblioTech! Até logo!");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    // Login de Cliente
    private void fazerLoginCliente() {
        Cliente cliente = clienteController.fazerlogin();

        if (cliente != null) {
            MenuCliente menuCliente = new MenuCliente(
                    cliente,
                    clienteController,
                    livroController,
                    emprestimoController,
                    compraController,
                    scanner
            );

            menuCliente.exibir();
        }
    }

    // Login de Funcionário
    private void fazerLoginFuncionario() {
        Funcionario funcionario = funcionarioController.fazerlogin();

        if (funcionario != null) {
            MenuFuncionario menuFuncionario = new MenuFuncionario(
                    funcionario,
                    funcionarioController,
                    livroController,
                    emprestimoController,
                    compraController,
                    scanner
            );

            menuFuncionario.exibir();
        }
    }

    // Login do Administrador
    private void fazerLoginAdmin() {
        System.out.println("\n--- LOGIN ADMINISTRADOR ---");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Admin fixo
        if (email.equals("italo@gmail.com") && senha.equals("123456")) {
            System.out.println("✔ Administrador logado com sucesso!");
            System.out.println("Bem-vindo!\n");

            // Exibe menu do administrador
            menuAdmin.exibir();

        } else {
            System.out.println("❌ Email ou senha incorretos!");
        }
    }

    // Pausa para o usuário ler
    private void pausa() {
        System.out.print("\n[Pressione ENTER para continuar...]");
        scanner.nextLine();
    }
}