package repositories;

import models.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraRepository {
    private List<Compra> compras = new ArrayList<>();
    private int proximoId = 1;

    public Compra salvar(int idCliente, int idLivro, int quantidade,
                         double precoUnitario, String dataCompra, String status) {
        Compra c = new Compra(proximoId++, idCliente, idLivro, quantidade,
                precoUnitario, dataCompra, status);

        compras.add(c);

        return c;
    }

    public Compra buscarPorId(int id) {
        for (Compra c: compras) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public List<Compra> buscarPorCliente(int idCliente) {
        List<Compra> resultado = new ArrayList<>();

        for (Compra c : compras) {
            if (c.getIdCliente() == idCliente) {
                resultado.add(c);
            }
        }

        return resultado;
    }

    public List<Compra> buscarPorLivro(int idLivro) {
        List<Compra> resultado = new ArrayList<>();

        for (Compra c : compras) {
            if (c.getIdLivro() == idLivro) {
                resultado.add(c);
            }
        }

        return resultado;
    }

    public List<Compra> buscarPorStatus(String status) {
        List<Compra> resultado = new ArrayList<>();

        for (Compra c : compras) {
            resultado.add(c);
        }

        return resultado;
    }

    public List<Compra> buscarTodos() {
        return new ArrayList<>(compras);
    }

    public List<Compra> buscarPendentes() {
        return buscarPorStatus("pendente");
    }

    public List<Compra> buscarConcluidas() {
        return buscarPorStatus("concluída");
    }

    public List<Compra> buscarCanceladas() {
        return buscarPorStatus("cancelada");
    }

    public void atualizarStatus(int id, String novoStatus) {
        Compra compra = buscarPorId(id);
        if (compra != null) {
            compra.setStatus(novoStatus);
        }
    }

    public void atualizarQuantidade(int id, int novaQuantidade) {
        Compra compra = buscarPorId(id);
        if (compra != null) {
            compra.setQuantidade(novaQuantidade);
        }
    }

    public boolean deletar(int id) {
        Compra c = buscarPorId(id);
        if (c != null && c.getStatus().equals("cancelada")) {
            return compras.remove(c);
        }
        return false;
    }
}
