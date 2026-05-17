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

    // CRIAR EMPRÉSTIMO com validações
    public Emprestimo criarEmprestimo(int idLivro, int idCliente, int dias) {

        // Validação 1: Cliente existe?
        Cliente cliente = clienteService.buscarPorId(idCliente);

        // Validação 2: Livro existe?
        Livro livro = livroService.buscarPorId(idLivro);

        // Validação 3: Dias válido?
        if (dias <= 0) {
            System.out.println("Dias de empréstimo deve ser positivo!");
        }

        if (dias > 30) {
            System.out.println("Máximo de 30 dias de empréstimo!");
        }

        // Validação 4: Livro tem estoque?
        if (livro.getQuantidadeDisponivel() <= 0) {
            System.out.println("Livro não disponível em estoque!");
        }

        // Se passou em TUDO → cria empréstimo
        String dataEmprestimo = LocalDate.now().toString();
        String dataDevolucao = LocalDate.now().plusDays(dias).toString();

        // Reduz estoque
        livroService.reduzirEstoque(idLivro, 1);

        return repository.salvar(idLivro, idCliente, dataEmprestimo, dataDevolucao);
    }

    // BUSCAR por ID com validação
    public Emprestimo buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID deve ser positivo!");
        }

        Emprestimo emprestimo = repository.buscarPorId(id);
        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado!");
            return null;
        }

        return emprestimo;
    }

    // LISTAR todos ativos
    public List<Emprestimo> listarTodos() {
        return repository.buscarTodos();
    }

    // LISTAR empréstimos de um cliente
    public List<Emprestimo> listarPorCliente(int idCliente) {
        // Valida se cliente existe
        clienteService.buscarPorId(idCliente);
        return repository.buscarPorCliente(idCliente);
    }

    // LISTAR devoluções
    public List<Emprestimo> listarDevolvidos() {
        return repository.buscarTodosDevolvidos();
    }

    // LISTAR devoluções de um cliente
    public List<Emprestimo> listarDevolvidosPorCliente(int idCliente)  {
        // Valida se cliente existe
        clienteService.buscarPorId(idCliente);
        return repository.buscarDevolvidosPorCliente(idCliente);
    }

    // LISTAR empréstimos atrasados
    public List<Emprestimo> listarAtrasados() {
        String dataAtual = LocalDate.now().toString();
        return repository.buscarAtrasados(dataAtual);
    }

    // REGISTRAR DEVOLUÇÃO
    public void registrarDevolucao(int idEmprestimo)  {
        Emprestimo emp = buscarPorId(idEmprestimo);

        // Aumenta estoque do livro
        livroService.aumentarEstoque(emp.getIdLivro(), 1);

        // Registra devolução
        boolean sucesso = repository.registrarDevolucao(idEmprestimo);
        if (!sucesso) {
            System.out.println("Erro ao registrar devolução!");
        }
    }

    // CALCULAR multa por atraso
    public double calcularMultaAtraso(int idEmprestimo)  {
        Emprestimo emp = buscarPorId(idEmprestimo);

        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.parse(emp.getDataDevolucao());

        if (hoje.isAfter(dataDevolucao)) {
            long diasAtraso = java.time.temporal.ChronoUnit.DAYS.between(dataDevolucao, hoje);
            return diasAtraso * 5.0; // R$ 5 por dia de atraso
        }

        return 0;
    }

    // VERIFICAR se está atrasado
    public boolean estaAtrasado(int idEmprestimo)  {
        Emprestimo emp = buscarPorId(idEmprestimo);
        LocalDate hoje = LocalDate.now();
        LocalDate dataDevolucao = LocalDate.parse(emp.getDataDevolucao());
        return hoje.isAfter(dataDevolucao);
    }

    // DELETAR empréstimo devolvido
    public boolean deletarEmprestimo(int id)  {
        if (id <= 0) {
            System.out.println("ID deve ser positivo!");
        }

        boolean deletou = repository.deletar(id);
        if (!deletou) {
            System.out.println("Apenas empréstimos devolvidos podem ser deletados!");
        }

        return true;
    }

    // ESTATÍSTICAS
    public int totalEmprestimosAtivos() {
        return repository.contarAtivos();
    }

    public int totalEmprestimosDevolvidos() {
        return repository.contarDevolvidos();
    }
}