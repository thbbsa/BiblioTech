package services;

import models.Cliente;
import models.Funcionario;
import repositories.FuncionarioRepository;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario criarFuncionario(String nome, String email, String senha) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode estar vazio!");
        }

        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Email inválido!");
        }

        if (senha == null || senha.length() < 6) {
            System.out.println("Senha deve ter 6+ caracteres!");
        }

        if (repository.buscarPorEmail(email) != null) {
            System.out.println("Email já cadastrado!");
        }

        return repository.salvar(nome, email, senha);
    }

    public Funcionario fazerLogin(String email, String senha) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email não pode estar vazio!");
        }

        if (senha == null || senha.isEmpty()) {
            System.out.println("Senha não pode estar vazia!");
        }

        Funcionario funcionario = repository.buscarPorEmail(email);

        if (funcionario == null) {
            System.out.println("Email não cadastrado!");
        }

        if (!funcionario.getSenha().equals(senha)) {
            System.out.println("Senha incorreta!");
        }

        return funcionario;
    }

    public Funcionario buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID deve ser positivo!");
        }

        Funcionario func = repository.buscarPorId(id);

        if (func == null) {
            System.out.println("Funcionário não encontrado!");
        }

        return func;
    }

    public Funcionario buscarPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email inválido!");
        }

        Funcionario func = repository.buscarPorEmail(email);

        if (func == null) {
            System.out.println("Funcionário não encontrado!");
        }

        return func;
    }

    public List<Funcionario> listarTodos() {
        return repository.buscarTodos();
    }

    public boolean deletarFuncionario(int id) {
        return repository.deletar(id);
    }
}
