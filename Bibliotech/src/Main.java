import repositories.*;
import services.*;
import controller.*;
import views.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHeader("INICIANDO BIBLIOTECH SYSTEM...");


            // ============================================
            // CRIAR TODOS OS REPOSITORIES
            // ============================================
            printSection("📦 Carregando repositórios...");

            var livroRepository = new LivroRepository();
            var funcionarioRepository = new FuncionarioRepository();
            var clienteRepository = new ClienteRepository();
            var emprestimoRepository = new EmprestimoRepository();
            var compraRepository = new CompraRepository();

            // ============================================
            // CRIAR TODOS OS SERVICES
            // ============================================
            printSection("⚙️  Inicializando serviços...");

            var livroService = new LivroService(livroRepository);
            var funcionarioService = new FuncionarioService(funcionarioRepository);
            var clienteService = new ClienteService(clienteRepository);
            var emprestimoService = new EmprestimoService(
                    emprestimoRepository, livroService, clienteService);
            var compraService = new CompraService(
                    compraRepository, livroService, clienteService);

            // ============================================
            // CRIAR TODOS OS CONTROLLERS
            // ============================================
            printSection("🎮 Configurando controllers...");

            var livroController = new LivroController(livroService, scanner);
            var funcionarioController = new FuncionarioController(funcionarioService, scanner);
            var clienteController = new ClienteController(clienteService, scanner);
            var emprestimoController = new EmprestimoController(emprestimoService, scanner);
            var compraController = new CompraController(compraService, scanner);

            // ============================================
            // CRIAR VIEWS (Menus)
            // ============================================
            printSection("🎨 Carregando interfaces...\n");

            var menuAdmin = new MenuAdministrador(
                    funcionarioController,
                    livroController,
                    emprestimoController,
                    compraController,
                    scanner
            );

            var menuPrincipal = new MenuPrincipal(
                    clienteController,
                    funcionarioController,
                    livroController,
                    emprestimoController,
                    compraController,
                    menuAdmin,
                    scanner
            );

            // ============================================
            //  INICIAR APLICAÇÃO
            // ============================================
            printSuccess("Sistema pronto!\n");

            menuPrincipal.exibir();

            scanner.close();;
    }

    private static void printHeader(String message) {
        String header = """
                ╔════════════════════════════════════╗
                ║   %s   ║
                ╚════════════════════════════════════╝
                """.formatted(centerText(message, 30));
        System.out.println(header);
    }

    private static void printSection(String message) {
        System.out.println(message);
    }

    private static void printSuccess(String message) {
        System.out.println("✔ " + message);
    }

    private static void printCredentials() {
        String credentials = """
                Credenciais Admin (para teste):
                  📧 Email: italo@email.com
                  🔐 Senha: 123456
                """;
        System.out.println(credentials);
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int totalPadding = width - text.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
}