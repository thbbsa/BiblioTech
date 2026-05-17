package controller;

import models.Cliente;
import services.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteController {
    private ClienteService service;
    private Scanner scanner;

    public ClienteController(ClienteService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chama Service (que valida!)
        Cliente cliente = service.criarCliente(nome, email, senha);

        if (cliente == null) {
            System.out.println("Algo deu errado!");
        } else {
            System.out.println("✔ Cadastro realizado com sucesso!");
            System.out.println("ID do cliente: " + cliente.getId());
        }
    }

    public Cliente fazerlogin() {
        System.out.println("\n--- LOGIN DE CLIENTE ---");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chama Service (que valida!)
        Cliente cliente = service.fazerLogin(email, senha);

        if (cliente == null) {
            System.out.println("Aconteceu algo errado!");
        } else {
            System.out.println("✔ Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + cliente.getNome() + "!");

            return cliente;
        }

        return null;
    }



    public void listarClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");

        List<Cliente> clientes = service.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void buscarPorNome() {
        System.out.print("\nDigite o nome para buscar: ");
        String nome = scanner.nextLine();
        List<Cliente> clientes = service.buscarPorNome(nome);

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado com esse nome.");
        } else {
            System.out.println("\n--- RESULTADOS ---");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void deletarCliente() {
        listarClientes();
        System.out.print("\nID do cliente a deletar: ");

        int id = Integer.parseInt(scanner.nextLine());
        service.deletarCliente(id);
        System.out.println("✔ Cliente deletado com sucesso!");
    }

    public void visualizarPerfil(Cliente cliente) {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║        PERFIL DO CLIENTE          ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
    }
}
