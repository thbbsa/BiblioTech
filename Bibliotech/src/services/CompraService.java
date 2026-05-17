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

        Livro livro = livroService.buscarPorId(idLivro);

        if (quantidade <= 0) {
            System.out.println("Estoque insuficiente! Disponível: " + livro.getQuantidadeDisponivel());
        }

        String dataCompra = LocalDate.now().toString();
        Compra compra = repository.salvar(idCliente, idLivro, quantidade,
                livro.getPreco(), dataCompra, "pendente");

        return compra;
    }

    public Compra buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID deve ser positivo!");
        }

        Compra compra = repository.buscarPorId(id);

        if (compra == null) {
            System.out.println("Compra não encontrada!");
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
            System.out.println("Apenas compras pendentes podem ser confirmadas!");
        }

        livroService.reduzirEstoque(compra.getIdLivro(), compra.getQuantidade());

        // Muda status para concluída
        repository.atualizarStatus(idCompra, "concluída");
    }

    public void cancelarCompra(int idCompra) throws IllegalArgumentException {
        Compra compra = buscarPorId(idCompra);

        if (!compra.getStatus().equals("pendente")) {
            System.out.println("Apenas compras pendentes podem ser canceladas!");
        }

        // Muda status para cancelada
        repository.atualizarStatus(idCompra, "cancelada");
    }

    public void reembolsoCompra(int idCompra) throws IllegalArgumentException {
        Compra compra = buscarPorId(idCompra);

        if (!compra.getStatus().equals("concluída")) {
            System.out.println("Apenas compras concluídas podem ser reembolsadas!");
        }

        // Aumenta estoque do livro
        livroService.aumentarEstoque(compra.getIdLivro(), compra.getQuantidade());

        // Muda status para cancelada
        repository.atualizarStatus(idCompra, "cancelada");
    }

    public double calcularTotalGastoCliente(int idCliente) throws IllegalArgumentException {
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

    public boolean deletarCompra(int id) throws IllegalArgumentException {
        buscarPorId(id);

        boolean deletou = repository.deletar(id);
        if (!deletou) {
            System.out.println("Apenas compras canceladas podem ser deletadas!");
        }
        return true;
    }

}
