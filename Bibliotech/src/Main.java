/*
    Enzo Munarin Do Nascimento
    Italo Trindade Oliveira
    Thiago Barbosa De Oliveira

    Projeto - BiblioTech

    Desc
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- BEM-VINDO AO BIBLIOTECH ---");
            System.out.println("1. Cliente");
            System.out.println("2. Funcionário / Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int tipoUsuario = leitura.nextInt();
            leitura.nextLine();

            switch (tipoUsuario) {
                case 1:
                    System.out.println("Acessando área do Cliente...");
                    break;

                case 2:
                    menuInterno(leitura);
                    break;

                case 0:
                    rodando = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        leitura.close();
    }

    public static void menuInterno(Scanner leitura) {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- ÁREA INTERNA ---");
            System.out.println("1. Funcionário");
            System.out.println("2. Administrador");
            System.out.println("0. Voltar");
            System.out.print("Sua escolha: ");

            int subTipo = leitura.nextInt();
            leitura.nextLine();

            if (subTipo == 0) {
                voltar = true;
                continue;
            }

            System.out.print("Digite seu email: ");
            String email = leitura.nextLine();

            System.out.print("Digite sua senha: ");
            String senha = leitura.nextLine();

            Sistema sistema = new Sistema();
            sistema.realizarLoginFunc(subTipo, email, senha);

            System.out.println("\nPressione ENTER para continuar...");
            leitura.nextLine();
        }
    }
}