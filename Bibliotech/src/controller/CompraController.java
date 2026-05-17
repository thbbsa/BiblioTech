package controller;

import models.Cliente;
import models.Compra;
import services.CompraService;
import java.util.List;
import java.util.Scanner;

public class CompraController {

    private CompraService service;
    private Scanner scanner;

    public CompraController(CompraService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    // Listar todas as compras
    public void listarCompras() {
        System.out.println("\n--- TODAS AS COMPRAS ---");
        List<Compra> compras = service.listarTodas();

        if (compras.isEmpty()) {
            System.out.println("Nenhuma compra registrada.");
        } else {
            for (Compra c : compras) {
                System.out.println(c);
            }
        }

    }

    // Listar compras pendentes
    public void listarPendentes() {
        System.out.println("\n--- COMPRAS PENDENTES ---");

        List<Compra> compras = service.listarPendentes();

        if (compras.isEmpty()) {
                System.out.println("Nenhuma compra pendente.");
        } else {
            for (Compra c : compras) {
                    if (c.getStatus().equals("pendente")) {
                        System.out.println(c);
                    }
            }
        }
    }

    // Listar compras concluídas
    public void listarConcluidas() {
        System.out.println("\n--- COMPRAS CONCLUÍDAS ---");

        List<Compra> compras = service.listarConcluidas();

        if (compras.isEmpty()) {
                System.out.println("Nenhuma compra registrada.");
        } else {
            for (Compra c : compras) {
                if (c.getStatus().equals("concluída")) {
                    System.out.println(c);
                }
            }
        }
    }

    // Listar compras canceladas
    public void listarCanceladas() {
        System.out.println("\n--- COMPRAS CANCELADAS ---");

        List<Compra> compras = service.listarCanceladas();

        if (compras.isEmpty()) {
                System.out.println("Nenhuma livro disponivel.");
        } else {
            for (Compra c : compras) {
                System.out.println(c);
            }
        }
    }

    // Buscar compras de um cliente
    public void buscarComprasCliente() {
        System.out.print("\nDigite o ID do cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        List<Compra> compras = service.buscarPorCliente(idCliente);

        if (compras.isEmpty()) {
                System.out.println("Cliente não possui compras.");
        } else {
            System.out.println("\n--- COMPRAS DO CLIENTE " + idCliente + " ---");
            for (Compra c : compras) {
                    System.out.println(c);
            }

            // Mostra total gasto
            double total = service.calcularTotalGastoCliente(idCliente);
            System.out.println("\nTotal gasto (concluídas): R$ " +
                    String.format("%.2f", total));
            }
    }

    // Buscar compras de um cliente
    public void verMinhasComprasCliente(Cliente clientelogado) {
        List<Compra> compras = service.buscarPorCliente(clientelogado.getId());

        if (compras.isEmpty()) {
            System.out.println("Cliente não possui compras.");
        } else {
            System.out.println("\n--- COMPRAS DO CLIENTE " + clientelogado.getId() + " ---");
            for (Compra c : compras) {
                System.out.println(c);
            }

            // Mostra total gasto
            double total = service.calcularTotalGastoCliente(clientelogado.getId());
            System.out.println("\nTotal gasto (concluídas): R$ " +
                    String.format("%.2f", total));
        }
    }

    // Criar nova compra
    public void criarCompra() {
        System.out.println("\n--- NOVA COMPRA ---");

        listarCompras();

        System.out.print("ID do cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        System.out.print("ID do livro: ");
        int idLivro = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());


        Compra compra = service.criarCompra(idCliente, idLivro, quantidade);

        System.out.println("✔ Compra criada com sucesso!");
        System.out.println("ID: " + compra.getId());
        System.out.println("Total: R$ " + String.format("%.2f", compra.getPrecoTotal()));
        System.out.println("Status: " + compra.getStatus());

    }

    public void criarCompraCliente(Cliente cliente) {
        System.out.println("\n--- NOVA COMPRA ---");

        System.out.println("Nenhuma compra registrada.");
        listarCompras();

        System.out.print("ID do livro: ");
        int idLivro = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());


        Compra compra = service.criarCompra(cliente.getId(), idLivro, quantidade);

        System.out.println("✔ Compra criada com sucesso!");
        System.out.println("ID: " + compra.getId());
        System.out.println("Total: R$ " + String.format("%.2f", compra.getPrecoTotal()));
        System.out.println("Status: " + compra.getStatus());
    }

    // Confirmar compra (muda para concluída e reduz estoque)
    public void confirmarCompra() {
        listarPendentes();
        System.out.print("\nID da compra a confirmar: ");
        int id = Integer.parseInt(scanner.nextLine());

        service.confirmarCompra(id);
        System.out.println("✔ Compra confirmada com sucesso!");
        System.out.println("Estoque foi reduzido.");
    }

    // Cancelar compra (muda para cancelada)
    public void cancelarCompra() {
        listarPendentes();
        System.out.print("\nID da compra a cancelar: ");
        int id = Integer.parseInt(scanner.nextLine());


        service.cancelarCompra(id);
        System.out.println("✔ Compra cancelada com sucesso!");

    }

    // Reembolsar compra (cancela e aumenta estoque)
    public void reembolsoCompra() {
        listarConcluidas();
        System.out.print("\nID da compra para reembolso: ");

            int id = Integer.parseInt(scanner.nextLine());
            service.reembolsoCompra(id);

            System.out.println("✔ Reembolso processado com sucesso!");
            System.out.println("Estoque foi aumentado.");
    }

    // Visualizar relatório de vendas
    public void relatorioVendas() {
        System.out.println("\n--- RELATÓRIO DE VENDAS ---");
            List<Compra> todasConcluidas = service.listarConcluidas();
            double totalVendas = service.calcularTotalVendas();

            System.out.println("Total de vendas concluídas: " + todasConcluidas.size());
            System.out.println("Total arrecadado: R$ " + String.format("%.2f", totalVendas));

            if (!todasConcluidas.isEmpty()) {
                double mediaVenda = totalVendas / todasConcluidas.size();
                System.out.println("Ticket médio: R$ " + String.format("%.2f", mediaVenda));
            }
    }
}