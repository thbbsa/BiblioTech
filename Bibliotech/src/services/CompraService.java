package services;

import models.Cliente;
import models.Compra;
import models.Livro;
import repositories.CompraRepository;

import java.time.LocalDate;
import java.util.List;

public class CompraService {
    private CompraRepository repository;
    private LivroService livroService;
    private ClienteService clienteService;

    public CompraService(CompraRepository repository, LivroService livroService,
                         ClienteService clienteService) {
        this.repository = repository;
        this.livroService = livroService;
        this.clienteService = clienteService;
    }

    public Compra criarCompra(int idCliente, int idLivro, int quantidade) {
        Cliente cliente = clienteService.buscarPorId(idCliente);
        Livro libro = livroService.buscarPorId(idLivro);

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade comprada deve ser maior que zero.");
        }

        if (libro.getQuantidadeDisponivel() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente! Disponível: " + libro.getQuantidadeDisponivel());
        }

        String dataCompra = LocalDate.now().toString();
        return repository.salvar(idCliente, idLivro, quantidade, libro.getPreco(), dataCompra, "pendente");
    }

    public Compra buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID consultado deve ser um número positivo.");
        }

        Compra compra = repository.buscarPorId(id);
        if (compra == null) {
            throw new IllegalArgumentException("Nenhuma compra foi encontrada com o ID " + id);
        }

        return compra;
    }

    public List<Compra> buscarPorCliente(int idCliente) {
        clienteService.buscarPorId(idCliente);
        return repository.buscarPorCliente(idCliente);
    }

    public List<Compra> buscarPorLivro(int idLivro)  {
        livroService.buscarPorId(idLivro);
        return repository.buscarPorLivro(idLivro);
    }

    public List<Compra> listarTodas() {
        return repository.buscarTodos();
    }

    public List<Compra> listarPendentes() {
        return repository.buscarPendentes();
    }

    public List<Compra> listarConcluidas() {
        return repository.buscarConcluidas();
    }

    public List<Compra> listarCanceladas() {
        return repository.buscarCanceladas();
    }

    public void confirmarCompra(int idCompra) {
        Compra compra = buscarPorId(idCompra);

        if (!compra.getStatus().equals("pendente")) {
            throw new IllegalStateException("Apenas compras pendentes podem ser confirmadas.");
        }

        livroService.reduzirEstoque(compra.getIdLivro(), compra.getQuantidade());
        repository.atualizarStatus(idCompra, "concluída");
    }

    public void cancelarCompra(int idCompra) {
        Compra compra = buscarPorId(idCompra);

        if (!compra.getStatus().equals("pendente")) {
            throw new IllegalStateException("Apenas compras pendentes podem ser canceladas.");
        }

        repository.atualizarStatus(idCompra, "cancelada");
    }

    public void reembolsoCompra(int idCompra) {
        Compra compra = buscarPorId(idCompra);

        if (!compra.getStatus().equals("concluída")) {
            throw new IllegalStateException("Apenas compras concluídas podem ser reembolsadas.");
        }

        livroService.aumentarEstoque(compra.getIdLivro(), compra.getQuantidade());
        repository.atualizarStatus(idCompra, "cancelada");
    }

    public double calcularTotalGastoCliente(int idCliente) {
        clienteService.buscarPorId(idCliente);

        List<Compra> comprasCliente = repository.buscarPorCliente(idCliente);
        double total = 0;

        for (Compra c : comprasCliente) {
            if (c.getStatus().equals("concluída")) {
                total += c.getPrecoTotal();
            }
        }

        return total;
    }

    public double calcularTotalVendas() {
        List<Compra> todasConcluidas = repository.buscarConcluidas();
        double total = 0;

        for (Compra c : todasConcluidas) {
            total += c.getPrecoTotal();
        }

        return total;
    }

    public boolean deletarCompra(int id) {
        buscarPorId(id);

        boolean deletou = repository.deletar(id);
        if (!deletou) {
            throw new IllegalStateException("Apenas compras canceladas podem ser deletadas do sistema.");
        }
        return true;
    }
}