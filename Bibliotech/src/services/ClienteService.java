package services;

import models.Cliente;
import repositories.ClienteRepository;
import java.util.List;

public class ClienteService {
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente criarCliente(String nome, String email, String senha) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode estar vazio.");
        }
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("O formato do e-mail informado é inválido.");
        }
        if (repository.buscarPorEmail(email) != null) {
            throw new IllegalStateException("Este e-mail já está cadastrado no sistema.");
        }
        if (senha == null || Math.min(senha.length(), 6) < 6) {
            throw new IllegalArgumentException("A senha deve conter pelo menos 6 caracteres.");
        }

        return repository.salvar(nome, email, senha);
    }

    public Cliente buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID consultado deve ser um número positivo.");
        }

        Cliente cliente = repository.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Nenhum cliente foi encontrado com o ID " + id);
        }

        return cliente;
    }

    public Cliente buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail para busca não pode estar vazio.");
        }

        Cliente cliente = repository.buscarPorEmail(email);
        if (cliente == null) {
            throw new IllegalArgumentException("Nenhum cliente cadastrado com o e-mail informado.");
        }

        return cliente;
    }

    public List<Cliente> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome para busca não pode estar vazio.");
        }

        return repository.buscarPorNome(nome);
    }

    public List<Cliente> listarTodos() {
        return repository.buscarTodos();
    }

    public Cliente fazerLogin(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail de login é obrigatório.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha de login é obrigatória.");
        }

        Cliente cliente = repository.buscarPorEmail(email);
        if (cliente == null || !cliente.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Credenciais inválidas. E-mail ou senha incorretos.");
        }

        return cliente;
    }

    public boolean deletarCliente(int id) {
        buscarPorId(id);
        return repository.deletar(id);
    }
}