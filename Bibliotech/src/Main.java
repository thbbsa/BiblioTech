/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech
 */

import java.util.Scanner;

public class Main {

    private static Scanner leitura = new Scanner(System.in);
    private static Sistema sistema = new Sistema(); // criado uma única vez

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=============================");
            System.out.println("   BEM-VINDO AO BIBLIOTECH   ");
            System.out.println("=============================");
            System.out.println("1. Cliente");
            System.out.println("2. Funcionário / Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;

                case 2:
                    menuLogin();
                    break;

                case 0:
                    rodando = false;
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        leitura.close();
    }

    // ========================
    // MENU CLIENTE
    // ========================

    private static void menuCliente() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- ÁREA DO CLIENTE ---");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Email: ");
                    String email = leitura.nextLine();

                    System.out.print("Senha: ");
                    String senha = leitura.nextLine();

                    sistema.realizarLoginCliente(email, senha);
                    pausar();
                    break;

                case 2:
                    sistema.cadastrarCliente();
                    break;

                case 0:
                    voltar = true;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // MENU DE LOGIN INTERNO
    // ========================

    private static void menuLogin() {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- ÁREA INTERNA ---");
            System.out.println("1. Funcionário");
            System.out.println("2. Administrador");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            int tipoUsuario = leitura.nextInt();
            leitura.nextLine();

            if (tipoUsuario == 0) {
                voltar = true;
                continue;
            }

            if (tipoUsuario != 1 && tipoUsuario != 2) {
                System.out.println("Opção inválida.");
                continue;
            }

            System.out.print("Email: ");
            String email = leitura.nextLine();

            System.out.print("Senha: ");
            String senha = leitura.nextLine();

            sistema.realizarLoginFunc(tipoUsuario, email, senha);

            pausar();
        }
    }

    // ========================
    // UTILITÁRIO
    // ========================

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        leitura.nextLine();
    }
}