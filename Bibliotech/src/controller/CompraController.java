package controller;

import models.Cliente;
import models.Compra;
import models.Livro;
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
        try {
            System.out.println("\n--- TODAS AS COMPRAS ---");
            List<Compra> compras = service.listarTodas();

            if (compras.isEmpty()) {
                System.out.println("Nenhuma compra registrada.");
            } else {
                for (Compra c : compras) {
                    System.out.println(c);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar todas as compras: " + e.getMessage());
        }
    }

    // Listar compras pendentes
    public void listarPendentes() {
        try {
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
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar compras pendentes: " + e.getMessage());
        }
    }

    // Listar compras concluídas
    public void listarConcluidas() {
        try {
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
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar compras concluídas: " + e.getMessage());
        }
    }

    // Listar compras canceladas
    public void listarCanceladas() {
        try {
            System.out.println("\n--- COMPRAS CANCELADAS ---");
            List<Compra> compras = service.listarCanceladas();

            if (compras.isEmpty()) {
                System.out.println("Nenhum livro disponivel.");
            } else {
                for (Compra c : compras) {
                    System.out.println(c);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar compras canceladas: " + e.getMessage());
        }
    }

    // Buscar compras de um cliente
    public void buscarComprasCliente() {
        try {
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
                System.out.println("\nTotal gasto (concluídas): R$ " + String.format("%.2f", total));
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID do cliente precisa ser um número inteiro válido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar compras do cliente: " + e.getMessage());
        }
    }

    // Buscar compras do cliente logado
    public void verMinhasComprasCliente(Cliente clientelogado) {
        try {
            if (clientelogado == null) {
                System.out.println("❌ Nenhum cliente logado.");
                return;
            }

            List<Compra> compras = service.buscarPorCliente(clientelogado.getId());

            if (compras.isEmpty()) {
                System.out.println("Você não possui compras.");
            } else {
                System.out.println("\n--- COMPRAS DO CLIENTE " + clientelogado.getId() + " ---");
                for (Compra c : compras) {
                    System.out.println(c);
                }

                // Mostra total gasto
                double total = service.calcularTotalGastoCliente(clientelogado.getId());
                System.out.println("\nTotal gasto (concluídas): R$ " + String.format("%.2f", total));
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao visualizar suas compras: " + e.getMessage());
        }
    }

    // Criar nova compra (Admin)
    public void criarCompra() {
        try {
            System.out.println("\n--- NOVA COMPRA ---");
            listarCompras();

            System.out.print("ID do cliente: ");
            int idCliente = Integer.parseInt(scanner.nextLine());

            System.out.print("ID do livro: ");
            int idLivro = Integer.parseInt(scanner.nextLine());

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            Compra compra = service.criarCompra(idCliente, idLivro, quantidade);

            if (compra == null) {
                System.out.println("❌ Não foi possível registrar a compra. Verifique os IDs informados.");
            } else {
                System.out.println("✔ Compra criada com sucesso!");
                System.out.println("ID: " + compra.getId());
                System.out.println("Total: R$ " + String.format("%.2f", compra.getPrecoTotal()));
                System.out.println("Status: " + compra.getStatus());
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Os campos ID e Quantidade precisam ser valores numéricos inteiros.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar compra: " + e.getMessage());
        }
    }

    // Criar nova compra (Cliente logado)
    public void criarCompraCliente(Cliente cliente, LivroController livroController) {
        try {
            if (cliente == null) {
                System.out.println("❌ É necessário estar logado para realizar uma compra.");
                return;
            }

            System.out.println("\n--- NOVA COMPRA ---");
            livroController.consultarLivrosDisponíveis();

            System.out.print("ID do livro: ");
            int idLivro = Integer.parseInt(scanner.nextLine());

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            Compra compra = service.criarCompra(cliente.getId(), idLivro, quantidade);

            if (compra == null) {
                System.out.println("❌ Não foi possível realizar a compra. Verifique se o livro está disponível.");
            } else {
                System.out.println("✔ Compra criada com sucesso!");
                System.out.println("ID: " + compra.getId());
                System.out.println("Total: R$ " + String.format("%.2f", compra.getPrecoTotal()));
                System.out.println("Status: " + compra.getStatus());
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID do livro e a Quantidade precisam ser números inteiros.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao realizar compra: " + e.getMessage());
        }
    }

    // Confirmar compra (muda para concluída e reduz estoque)
    public void confirmarCompra() {
        try {
            listarPendentes();
            System.out.print("\nID da compra a confirmar: ");
            int id = Integer.parseInt(scanner.nextLine());

            service.confirmarCompra(id);
            System.out.println("✔ Compra confirmada com sucesso!");
            System.out.println("Estoque foi reduzido.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID da compra precisa ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao confirmar compra: " + e.getMessage());
        }
    }

    // Cancelar compra (muda para cancelada)
    public void cancelarCompra() {
        try {
            listarPendentes();
            System.out.print("\nID da compra a cancelar: ");
            int id = Integer.parseInt(scanner.nextLine());

            service.cancelarCompra(id);
            System.out.println("✔ Compra cancelada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID da compra precisa ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao cancelar compra: " + e.getMessage());
        }
    }

    // Reembolsar compra (cancela e aumenta estoque)
    public void reembolsoCompra() {
        try {
            listarConcluidas();
            System.out.print("\nID da compra para reembolso: ");
            int id = Integer.parseInt(scanner.nextLine());

            service.reembolsoCompra(id);
            System.out.println("✔ Reembolso processado com sucesso!");
            System.out.println("Estoque foi aumentado.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID da compra precisa ser um número inteiro.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao processar reembolso: " + e.getMessage());
        }
    }

    // Visualizar relatório de vendas
    public void relatorioVendas() {
        try {
            System.out.println("\n--- RELATÓRIO DE VENDAS ---");
            List<Compra> todasConcluidas = service.listarConcluidas();
            double totalVendas = service.calcularTotalVendas();

            System.out.println("Total de vendas concluídas: " + todasConcluidas.size());
            System.out.println("Total arrecadado: R$ " + String.format("%.2f", totalVendas));

            if (!todasConcluidas.isEmpty()) {
                double mediaVenda = totalVendas / todasConcluidas.size();
                System.out.println("Ticket médio: R$ " + String.format("%.2f", mediaVenda));
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório de vendas: " + e.getMessage());
        }
    }
}