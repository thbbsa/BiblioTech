package repositories;

import models.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoIdFuncionario = 1;

    public Funcionario salvar(String nome, String email, String senha) {
        Funcionario f = new Funcionario(proximoIdFuncionario++, nome, email, senha);
        funcionarios.add(f);
        return f;
    }

    // busca por id
    public Funcionario buscarPorId(int id) {
        for (Funcionario f: funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // busca por email
    public Funcionario buscarPorEmail(String email) {
        for (Funcionario f: funcionarios) {
            if (email.equals(f.getEmail())) {
                return f;
            }
        }

        return null;
    }

    // busca por todos
    public List<Funcionario> buscarTodos() {
        return new ArrayList<>(funcionarios);
    }

    // Delete - Deletar Funcionario
    public boolean deletar(int id) {
        Funcionario func = buscarPorId(id);

        if (func == null) {
            return false;
        }

        return funcionarios.removeIf(f -> f.getId() == id);
    }
}
