package controller;

import models.Cliente;
import models.Funcionario;
import services.FuncionarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioController {
    private FuncionarioService service;
    private Scanner scanner;

    public FuncionarioController(FuncionarioService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public Funcionario fazerlogin() {
        System.out.println("\n--- LOGIN DE FUNCIONARIO ---");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chama Service (que valida!)
        Funcionario funcionario = service.fazerLogin(email, senha);

        System.out.println("✔ Login realizado com sucesso!");
        System.out.println("Bem-vindo, " + funcionario.getNome() + "!");

        return funcionario;
    }

    public void cadastrarFuncionario() {
        System.out.println("\n--- CADASTRAR FUNCIONÁRIO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Chama Service (que valida!)
        Funcionario func = service.criarFuncionario(nome, email, senha);

        System.out.println("✔ Funcionário cadastrado com sucesso! ID: " + func.getId());

    }

    public void listarFuncionarios() {
        System.out.println("\n--- FUNCIONÁRIOS CADASTRADOS ---");

        List<Funcionario> funcionarios = service.listarTodos();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f: funcionarios) {
                System.out.println(f);
            }
        }
    }

    public void deletarFuncionario() {
        listarFuncionarios();
        System.out.print("ID do funcionário a deletar: ");

        int id = Integer.parseInt(scanner.nextLine());
        boolean func = service.deletarFuncionario(id);

        if (func == false) {
            System.out.println("Não há funcionario com esse id!");
        } else {
            System.out.println("✔ Funcionário deletado com sucesso!");
        }
    }

    public void visualizarPerfil(Funcionario funcionario) {
        System.out.println("\n╔══════════════════════════════════===╗");
        System.out.println("║        PERFIL DO Funciinario          ║");
        System.out.println("╚═══════════════════════════════════=====");
        System.out.println("ID: " + funcionario.getId());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Email: " + funcionario.getEmail());
    }
}
