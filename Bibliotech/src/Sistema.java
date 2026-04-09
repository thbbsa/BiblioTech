import java.util.Scanner;

public class Sistema {
    public static void realizarLoginFunc(int tipoUsuario, String emailDigitado, String senhaDigitada) {
        switch (tipoUsuario) {
            case 1:
                System.out.println("Funcionario");
                break;
            case 2:
                System.out.println("Administrador");
                Administrador admCadastrado = new Administrador("italo", "italo@gmail.com", "123456");

                if (admCadastrado.getEmail().equals(emailDigitado) && admCadastrado.getSenha().equals(senhaDigitada)) {
                    System.out.println("Acesso Liberado!");
                } else {
                    System.out.println("Acesso Negado!");
                }
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }
}
