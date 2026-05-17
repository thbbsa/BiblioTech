package repositories;

import models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public Cliente salvar(String nome, String email, String senha) {
        Cliente c = new Cliente(proximoId++, nome, email, senha);
        clientes.add(c);
        return c;
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Cliente buscarPorEmail(String email) {
        for (Cliente c : clientes) {
            if (email.equals(c.getEmail())) {
                return c;
            }
        }

        return null;
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> resultado = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c.getNome() == nome) {
                resultado.add(c);
            }
        }

        return resultado;
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<Cliente>();
    }

    public void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteAtualizado.getId()) {
                clientes.set(i, clienteAtualizado);
                break;
            }
        }
    }

    public boolean deletar(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
