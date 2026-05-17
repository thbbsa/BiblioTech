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
                System.out.println("Nome não pode estar vazio!");
            } else if (email == null || !email.contains("@") || !email.contains(".")) {
                System.out.println("Email Inválido");
            } else if (repository.buscarPorEmail(email) != null) {
                System.out.println("Email já Cadastrado!");
            } else if (senha == null || senha.length() <= 6) {
                System.out.println("Senha deve ter pelo menos 6 caracteres!\"");
            } else {
                return repository.salvar(nome, email, senha);
            }

            return null;
    }

    public Cliente buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID deve ser positivo!");
        }

        Cliente cliente = repository.buscarPorId(id);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
        }

        return cliente;
    }

    public Cliente buscarPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email inválido!");
        }

        Cliente cliente = repository.buscarPorEmail(email);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
        }

        return cliente;
    }

    public List<Cliente> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome não pode estar vazio!");
        }

        return repository.buscarPorNome(nome);
    }

    public List<Cliente> listarTodos() {
        return repository.buscarTodos();
    }

    public Cliente fazerLogin(String email, String senha) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email não pode estar vazio!");
            return null;
        }

        if (senha == null || senha.isEmpty()) {
            System.out.println("Senha não pode estar vazia!");
            return null;
        }

        Cliente cliente = repository.buscarPorEmail(email);

        if (cliente == null) {
            System.out.println("Email não cadastrado!");
            return null;
        }

        if (!cliente.getSenha().equals(senha)) {
            System.out.println("Senha incorreta!");
            return null;
        }

        return cliente;
    }

    public boolean deletarCliente(int id) {
        buscarPorId(id); // Valida se existe
        return repository.deletar(id);
    }
}
