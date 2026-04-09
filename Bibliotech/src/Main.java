import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);

        System.out.println("--- BEM-VINDO AO BIBLIOTECH ---");
        System.out.println("Como você deseja acessar o sistema?");
        System.out.println("1. Cliente");
        System.out.println("2. Funcionário / Administrador");
        System.out.print("Escolha uma opção: ");

        int tipoUsuario = leitura.nextInt();
        leitura.nextLine();

        if (tipoUsuario == 1) {
            System.out.println("Acessando área do Cliente...");
        } else if (tipoUsuario == 2) {
            System.out.println("\n--- ÁREA INTERNA ---");
            System.out.println("1. Entrar como Funcionário");
            System.out.println("2. Entrar como Administrador");
            System.out.print("Sua escolha: ");

            int subTipo = leitura.nextInt();
            leitura.nextLine();

            System.out.print("Digite seu email: ");
            String emailDigitado = leitura.nextLine();

            System.out.print("Digite sua senha: ");
            String senhaDigitada = leitura.nextLine();

            // Aqui você chama a função de login pedindo email e senha
            Sistema.realizarLoginFunc(subTipo, emailDigitado, senhaDigitada);

        } else {
            System.out.println("Opção inválida. Reinicie o sistema.");
        }
    }
}