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
            throw new IllegalArgumentException("O nome do funcionário não pode estar vazio.");
        }

        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("O formato do e-mail informado é inválido.");
        }

        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 6 caracteres.");
        }

        if (repository.buscarPorEmail(email) != null) {
            throw new IllegalStateException("Este e-mail já está cadastrado para outro funcionário.");
        }

        return repository.salvar(nome, email, senha);
    }

    public Funcionario fazerLogin(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail de login é obrigatório.");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha de login é obrigatória.");
        }

        Funcionario funcionario = repository.buscarPorEmail(email);

        if (funcionario == null || !funcionario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Credenciais inválidas. E-mail ou senha incorretos.");
        }

        return funcionario;
    }

    public Funcionario buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID consultado deve ser um número positivo.");
        }

        Funcionario func = repository.buscarPorId(id);

        if (func == null) {
            throw new IllegalArgumentException("Nenhum funcionário foi encontrado com o ID " + id);
        }

        return func;
    }

    public Funcionario buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail para busca não pode estar vazio.");
        }

        Funcionario func = repository.buscarPorEmail(email);

        if (func == null) {
            throw new IllegalArgumentException("Nenhum funcionário cadastrado com o e-mail informado.");
        }

        return func;
    }

    public List<Funcionario> listarTodos() {
        return repository.buscarTodos();
    }

    public boolean deletarFuncionario(int id) {
        buscarPorId(id);
        return repository.deletar(id);
    }
}