package repositories;

import models.Emprestimo;

import java.util.List;

import models.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Emprestimo> emprestimosDevolvidos = new ArrayList<>();
    private int proximoId = 1;
    private int proximoIdDevolvido = 1;

    // CREATE - Salvar novo empréstimo (ativo)
    public Emprestimo salvar(int idLivro, int idCliente, String dataEmprestimo,
                             String dataDevolucao) {
        Emprestimo e = new Emprestimo(proximoId++, idLivro, idCliente,
                dataEmprestimo, dataDevolucao, false);
        emprestimos.add(e);
        return e;
    }

    // CREATE - Salvar empréstimo devolvido
    public Emprestimo salvarDevolvido(int idLivro, int idCliente,
                                      String dataEmprestimo, String dataDevolucao) {
        Emprestimo eDevolvido = new Emprestimo(proximoIdDevolvido++, idLivro,
                idCliente, dataEmprestimo,
                dataDevolucao, true);
        emprestimosDevolvidos.add(eDevolvido);
        return eDevolvido;
    }

    // READ - Buscar por ID (empréstimos ativos)
    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // READ - Buscar por ID (empréstimos devolvidos)
    public Emprestimo buscarDevolvidoPorId(int id) {
        for (Emprestimo e : emprestimosDevolvidos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // READ - Buscar todos ativos
    public List<Emprestimo> buscarTodos() {
        return new ArrayList<>(emprestimos);
    }

    // READ - Buscar todos devolvidos
    public List<Emprestimo> buscarTodosDevolvidos() {
        return new ArrayList<>(emprestimosDevolvidos);
    }

    // READ - Buscar empréstimos por cliente
    public List<Emprestimo> buscarPorCliente(int idCliente) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getIdCliente() == idCliente) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // READ - Buscar empréstimos devolvidos por cliente
    public List<Emprestimo> buscarDevolvidosPorCliente(int idCliente) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimosDevolvidos) {
            if (e.getIdCliente() == idCliente) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // READ - Buscar empréstimos por livro
    public List<Emprestimo> buscarPorLivro(int idLivro) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getIdLivro() == idLivro) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // READ - Buscar atrasados
    public List<Emprestimo> buscarAtrasados(String dataAtual) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getDataDevolucao().compareTo(dataAtual) < 0) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // UPDATE - Registrar devolução
    public boolean registrarDevolucao(int id) {
        Emprestimo emp = buscarPorId(id);
        if (emp != null) {
            emprestimos.remove(emp);
            emp.setDevolvido(true);
            emprestimosDevolvidos.add(emp);
            return true;
        }
        return false;
    }

    // DELETE - Deletar empréstimo (apenas devolvidos)
    public boolean deletar(int id) {
        return emprestimosDevolvidos.removeIf(e -> e.getId() == id);
    }

    // COUNT - Total de empréstimos ativos
    public int contarAtivos() {
        return emprestimos.size();
    }

    // COUNT - Total de empréstimos devolvidos
    public int contarDevolvidos() {
        return emprestimosDevolvidos.size();
    }
}