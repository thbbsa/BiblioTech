package controller;

import models.Emprestimo;
import services.EmprestimoService;
import java.util.List;
import java.util.Scanner;

public class EmprestimoController {

    private EmprestimoService service;
    private Scanner scanner;

    public EmprestimoController(EmprestimoService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    // LISTAR todos os empréstimos
    public void listarEmprestimos() {
        System.out.println("\n--- EMPRÉSTIMOS ATIVOS ---");

        List<Emprestimo> emprestimos = service.listarTodos();

        if (emprestimos.isEmpty()) {
                System.out.println("Nenhum empréstimo ativo.");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println(e);

                // Mostra se está atrasado
                if (service.estaAtrasado(e.getId())) {
                    double multa = service.calcularMultaAtraso(e.getId());
                    System.out.println("⚠️  ATRASADO! Multa: R$ " + String.format("%.2f", multa));
                }
            }
        }
    }

    // LISTAR empréstimos de um cliente
    public void listarEmprestimosPorCliente(int idCliente) {
        System.out.println("\n--- MEUS EMPRÉSTIMOS ---");
        List<Emprestimo> emprestimos = service.listarPorCliente(idCliente);

        if (emprestimos.isEmpty()) {
            System.out.println("Você não tem empréstimos ativos.");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println(e);

                // Mostra se está atrasado
                if (service.estaAtrasado(e.getId())) {
                    double multa = service.calcularMultaAtraso(e.getId());
                    System.out.println("⚠️  ATRASADO! Multa: R$ " + String.format("%.2f", multa));
                }
            }
        }
    }

    // LISTAR empréstimos devolvidos
    public void listarDevolucoes() {
        System.out.println("\n--- EMPRÉSTIMOS DEVOLVIDOS ---");
        List<Emprestimo> emprestimos = service.listarDevolvidos();

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo devolvido.");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println(e);
            }
        }
    }

    // LISTAR ATRASADOS
    public void listarAtrasados() {
        System.out.println("\n--- EMPRÉSTIMOS ATRASADOS ---");
        List<Emprestimo> atrasados = service.listarAtrasados();

        if (atrasados.isEmpty()) {
            System.out.println("Nenhum empréstimo atrasado.");
        } else {
            System.out.println("⚠️  TOTAL ATRASADO: " + atrasados.size());
            for (Emprestimo e : atrasados) {
                System.out.println(e);
                double multa = service.calcularMultaAtraso(e.getId());
                System.out.println("💰 Multa: R$ " + String.format("%.2f", multa));
            }
        }
    }

    // CRIAR novo empréstimo
    public void realizarEmprestimo(int idCliente) {
        System.out.println("\n--- NOVO EMPRÉSTIMO ---");

        System.out.print("ID do livro: ");
        int idLivro = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantos dias de empréstimo (máx 30): ");
        int dias = Integer.parseInt(scanner.nextLine());

        // Chama Service (que valida!)
        Emprestimo emprestimo = service.criarEmprestimo(idLivro, idCliente, dias);

        System.out.println("✔ Empréstimo realizado com sucesso!");
        System.out.println("ID: " + emprestimo.getId());
        System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());

    }

    // REGISTRAR DEVOLUÇÃO
    public void devolverEmprestimo() {
        listarEmprestimos();

        System.out.print("\nID do empréstimo a devolver: ");

        int id = Integer.parseInt(scanner.nextLine());

        Emprestimo emprestimo = service.buscarPorId(id);

        if (emprestimo == null) {
            System.out.println("Não há esse id");
        } else {
            // Verifica se está atrasado
            if (service.estaAtrasado(id)) {
                double multa = service.calcularMultaAtraso(id);
                System.out.println("⚠️  Empréstimo atrasado!");
                System.out.println("Multa: R$ " + String.format("%.2f", multa));
            }

            service.registrarDevolucao(id);
            System.out.println("✔ Devolução registrada com sucesso!");
        }
    }

    // ESTATÍSTICAS
    public void mostrarEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DE EMPRÉSTIMOS ---");
        int ativos = service.totalEmprestimosAtivos();
        int devolvidos = service.totalEmprestimosDevolvidos();

        System.out.println("Empréstimos ativos: " + ativos);
        System.out.println("Empréstimos devolvidos: " + devolvidos);
        System.out.println("Total: " + (ativos + devolvidos));
    }
}