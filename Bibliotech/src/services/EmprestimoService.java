package services;

import models.Emprestimo;
import models.Livro;
import models.Cliente;
import repositories.EmprestimoRepository;
import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private EmprestimoRepository repository;
    private LivroService livroService;
    private ClienteService clienteService;

    public EmprestimoService(EmprestimoRepository repository, LivroService livroService,
                             ClienteService clienteService) {
        this.repository = repository;
        this.livroService = livroService;
        this.clienteService = clienteService;
    }

    public Emprestimo criarEmprestimo(int idLivro, int idCliente, int dias) {
        Cliente cliente = clienteService.buscarPorId(idCliente);
        Livro livro = livroService.buscarPorId(idLivro);

        if (dias <= 0) {
            throw new IllegalArgumentException("A quantidade de dias de empréstimo deve ser um número positivo.");
        }

        if (dias > 30) {
            throw new IllegalArgumentException("O prazo máximo permitido para empréstimo é de 30 dias.");
        }

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new IllegalStateException("Livro não disponível em estoque para realizar novos empréstimos.");
        }

        String dataEmprestimo = LocalDate.now().toString();
        String dataDevolucao = LocalDate.now().plusDays(dias).toString();

        livroService.reduzirEstoque(idLivro, 1);

        return repository.salvar(idLivro, idCliente, dataEmprestimo, dataDevolucao);
    }

    public Emprestimo buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID consultado deve ser um número positivo.");
        }

        Emprestimo emprestimo = repository.buscarPorId(id);
        if (emprestimo == null) {
            throw new IllegalArgumentException("Nenhum empréstimo foi encontrado com o ID " + id);
        }

        return emprestimo;
    }

    public List<Emprestimo> listarTodos() {
        return repository.buscarTodos();
    }

    public List<Emprestimo> listarPorCliente(int idCliente) {
        clienteService.buscarPorId(idCliente);
        return repository.buscarPorCliente(idCliente);
    }

    public List<Emprestimo> listarDevolvidos() {
        return repository.buscarTodosDevolvidos();
    }

    public List<Emprestimo> listarDevolvidosPorCliente(int idCliente)  {
        clienteService.buscarPorId(idCliente);
        return repository.buscarDevolvidosPorCliente(idCliente);
    }

    public List<Emprestimo> listarAtrasados() {
        String dataAtual = LocalDate.now().toString();
        return repository.buscarAtrasados(dataAtual);
    }

    public void registrarDevolucao(int idEmprestimo)  {
        Emprestimo emp = buscarPorId(idEmprestimo);

        livroService.aumentarEstoque(emp.getIdLivro(), 1);

        boolean sucesso = repository.registrarDevolucao(idEmprestimo);
        if (!sucesso) {
            throw new IllegalStateException("Falha interna ao registrar o retorno do empréstimo no banco de dados.");
        }
    }

    public double calcularMultaAtraso(int idEmprestimo)  {
        Emprestimo emp = buscarPorId(idEmprestimo);

        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.parse(emp.getDataDevolucao());

        if (hoje.isAfter(dataDevolucao)) {
            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(dataDevolucao, hoje);
            return diasAtraso * 5.0;
        }

        return 0;
    }

    public boolean estaAtrasado(int idEmprestimo)  {
        Emprestimo emp = buscarPorId(idEmprestimo);
        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.parse(emp.getDataDevolucao());
        return hoje.isAfter(dataDevolucao);
    }

    public boolean deletarEmprestimo(int id)  {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID informado para exclusão deve ser positivo.");
        }

        boolean deletou = repository.deletar(id);
        if (!deletou) {
            throw new IllegalStateException("Não foi possível deletar o registro. Apenas empréstimos já devolvidos podem ser excluídos.");
        }

        return true;
    }

    public int totalEmprestimosAtivos() {
        return repository.contarAtivos();
    }

    public int totalEmprestimosDevolvidos() {
        return repository.contarDevolvidos();
    }
}