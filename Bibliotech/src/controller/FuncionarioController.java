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
        try {
            System.out.println("\n--- LOGIN DE FUNCIONARIO ---");

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // Chama Service (que valida!)
            Funcionario funcionario = service.fazerLogin(email, senha);

            if (funcionario == null) {
                System.out.println("❌ Login inválido! Verifique o e-mail e a senha.");
                return null;
            }

            System.out.println("✔ Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + funcionario.getNome() + "!");
            return funcionario;

        } catch (Exception e) {
            System.out.println("❌ Erro ao tentar realizar o login: " + e.getMessage());
            return null;
        }
    }

    public void cadastrarFuncionario() {
        try {
            System.out.println("\n--- CADASTRAR FUNCIONÁRIO ---");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            // Chama Service (que valida!)
            Funcionario func = service.criarFuncionario(nome, email, senha);

            if (func == null) {
                System.out.println("❌ Não foi possível cadastrar o funcionário. Verifique os dados.");
            } else {
                System.out.println("✔ Funcionário cadastrado com sucesso! ID: " + func.getId());
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public void listarFuncionarios() {
        try {
            System.out.println("\n--- FUNCIONÁRIOS CADASTRADOS ---");

            List<Funcionario> funcionarios = service.listarTodos();

            if (funcionarios.isEmpty()) {
                System.out.println("Nenhum funcionário cadastrado.");
            } else {
                for (Funcionario f: funcionarios) {
                    System.out.println(f);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar funcionários: " + e.getMessage());
        }
    }

    public void deletarFuncionario() {
        try {
            listarFuncionarios();
            System.out.print("ID do funcionário a deletar: ");

            // Evita quebra se o usuário digitar letras no ID
            int id = Integer.parseInt(scanner.nextLine());
            boolean func = service.deletarFuncionario(id);

            if (!func) {
                System.out.println("❌ Não há funcionário com esse ID!");
            } else {
                System.out.println("✔ Funcionário deletado com sucesso!");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID precisa ser um número inteiro válido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    public void visualizarPerfil(Funcionario funcionario) {
        try {
            if (funcionario == null) {
                System.out.println("❌ Nenhum funcionário selecionado/logado para exibir o perfil.");
                return;
            }

            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║        PERFIL DO FUNCIONÁRIO          ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Email: " + funcionario.getEmail());

        } catch (Exception e) {
            System.out.println("❌ Erro ao visualizar perfil: " + e.getMessage());
        }
    }
}