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
        try {
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
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar empréstimos ativos: " + e.getMessage());
        }
    }

    // LISTAR empréstimos de um cliente
    public void listarEmprestimosPorCliente(int idCliente) {
        try {
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
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar seus empréstimos: " + e.getMessage());
        }
    }

    // LISTAR empréstimos devolvidos
    public void listarDevolucoes() {
        try {
            System.out.println("\n--- EMPRÉSTIMOS DEVOLVIDOS ---");
            List<Emprestimo> emprestimos = service.listarDevolvidos();

            if (emprestimos.isEmpty()) {
                System.out.println("Nenhum empréstimo devolvido.");
            } else {
                for (Emprestimo e : emprestimos) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar devoluções: " + e.getMessage());
        }
    }

    // LISTAR ATRASADOS
    public void listarAtrasados() {
        try {
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
        } catch (Exception e) {
            System.out.println("❌ Erro ao obter lista de atrasados: " + e.getMessage());
        }
    }

    // CRIAR novo empréstimo
    public void realizarEmprestimo(int idCliente) {
        try {
            System.out.println("\n--- NOVO EMPRÉSTIMO ---");

            System.out.print("ID do livro: ");
            int idLivro = Integer.parseInt(scanner.nextLine());

            System.out.print("Quantos dias de empréstimo (máx 30): ");
            int dias = Integer.parseInt(scanner.nextLine());

            // Chama Service (que valida!)
            Emprestimo emprestimo = service.criarEmprestimo(idLivro, idCliente, dias);

            if (emprestimo == null) {
                System.out.println("❌ Não foi possível realizar o empréstimo. Verifique a disponibilidade do livro.");
            } else {
                System.out.println("✔ Empréstimo realizado com sucesso!");
                System.out.println("ID: " + emprestimo.getId());
                System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID do livro e a quantidade de dias devem ser números inteiros válidos.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    // REGISTRAR DEVOLUÇÃO
    public void devolverEmprestimo() {
        try {
            listarEmprestimos();

            System.out.print("\nID do empréstimo a devolver: ");
            int id = Integer.parseInt(scanner.nextLine());

            Emprestimo emprestimo = service.buscarPorId(id);

            if (emprestimo == null) {
                System.out.println("❌ Nenhum empréstimo ativo encontrado com o ID informado.");
            } else {
                // Verifica se está atrasado antes de registrar o fechamento
                if (service.estaAtrasado(id)) {
                    double multa = service.calcularMultaAtraso(id);
                    System.out.println("⚠️  Empréstimo atrasado!");
                    System.out.println("Multa a ser paga: R$ " + String.format("%.2f", multa));
                }

                service.registrarDevolucao(id);
                System.out.println("✔ Devolução registrada com sucesso!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID do empréstimo precisa ser um número inteiro válido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao processar devolução: " + e.getMessage());
        }
    }

    // ESTATÍSTICAS
    public void mostrarEstatisticas() {
        try {
            System.out.println("\n--- ESTATÍSTICAS DE EMPRÉSTIMOS ---");
            int ativos = service.totalEmprestimosAtivos();
            int devolvidos = service.totalEmprestimosDevolvidos();

            System.out.println("Empréstimos ativos: " + ativos);
            System.out.println("Empréstimos devolvidos: " + devolvidos);
            System.out.println("Total: " + (ativos + devolvidos));
        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar estatísticas: " + e.getMessage());
        }
    }
}