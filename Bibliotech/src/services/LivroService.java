package services;

import models.Livro;
import repositories.LivroRepository;
import java.util.List;

public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    // CRIAR com validações
    public Livro criarLivro(String titulo, String autor, String editora, int anoPublicacao,
                            int quantidadeTotal, int quantidadeDisponivel, String genero, double preco)
            throws IllegalArgumentException {

        // Validação 1: Título vazio?
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título não pode estar vazio!");
        }

        // Validação 2: Autor vazio?
        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("Autor não pode estar vazio!");
        }

        // Validação 3: Editora vazia?
        if (editora == null || editora.trim().isEmpty()) {
            System.out.println("Editora não pode estar vazia!");
        }

        // Validação 4: Ano válido?
        if (anoPublicacao <= 0) {
            System.out.println("Ano de publicação deve ser positivo!");
        }

        // Validação 5: Preço válido?
        if (preco <= 0) {
            System.out.println("Preço deve ser maior que zero!");
        }

        // Validação 6: Quantidade válida?
        if (quantidadeTotal <= 0) {
            System.out.println("Quantidade total deve ser positiva!");
        }

        // Validação 7: Quantidade disponível não pode ser maior que total
        if (quantidadeDisponivel > quantidadeTotal) {
            System.out.println(
                    "Quantidade disponível não pode ser maior que total!");
        }

        // Validação 8: Gênero vazio?
        if (genero == null || genero.trim().isEmpty()) {
            System.out.println("Gênero não pode estar vazio!");
        }

        // Se passou em TUDO → salva
        return repository.salvar(titulo, autor, editora, anoPublicacao,
                quantidadeTotal, quantidadeDisponivel, genero, preco);
    }

    // BUSCAR por ID com validação
    public Livro buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID deve ser positivo!");
        }

        Livro livro = repository.buscarPorId(id);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
        }

        return livro;
    }

    // BUSCAR por Título
    public List<Livro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título não pode estar vazio!");
        }
        return repository.buscarPorTitulo(titulo);
    }

    // BUSCAR por Autor
    public List<Livro> buscarPorAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("Autor não pode estar vazio!");
        }
        return repository.buscarPorAutor(autor);
    }

    // BUSCAR por Gênero
    public List<Livro> buscarPorGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            System.out.println("Gênero não pode estar vazio!");
        }
        return repository.buscarPorGenero(genero);
    }

    // LISTAR todos
    public List<Livro> listarTodos() {
        return repository.buscarTodos();
    }

    // LISTAR apenas disponíveis
    public List<Livro> listarDisponíveis() {
        return repository.buscarDisponíveis();
    }

    // ATUALIZAR com validação
    public void atualizarLivro(int id, String titulo, String autor, String editora,
                               int anoPublicacao, int quantidadeTotal, int quantidadeDisponivel,
                               String genero, double preco) {

        // Busca e valida se existe
        Livro livro = buscarPorId(id);

        // Validações (mesmas de criar)
        if (titulo != null && !titulo.trim().isEmpty()) {
            livro.setTitulo(titulo);
        }
        if (autor != null && !autor.trim().isEmpty()) {
            livro.setAutor(autor);
        }
        if (editora != null && !editora.trim().isEmpty()) {
            livro.setEditora(editora);
        }
        if (anoPublicacao > 0) {
            livro.setAnoPublicacao(anoPublicacao);
        }
        if (preco > 0) {
            livro.setPreco(preco);
        }
        if (quantidadeTotal > 0) {
            livro.setQuantidadeTotal(quantidadeTotal);
        }
        if (quantidadeDisponivel >= 0 && quantidadeDisponivel <= quantidadeTotal) {
            livro.setQuantidadeDisponivel(quantidadeDisponivel);
        }
        if (genero != null && !genero.trim().isEmpty()) {
            livro.setGenero(genero);
        }

        repository.atualizar(livro);
    }

    // REDUZIR estoque com validação
    public void reduzirEstoque(int idLivro, int quantidade) {
        Livro livro = buscarPorId(idLivro);

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser positiva!");
        }

        if (livro.getQuantidadeDisponivel() < quantidade) {
            System.out.println("Estoque insuficiente! Disponível: "
                    + livro.getQuantidadeDisponivel());
        }

        for (int i = 0; i < quantidade; i++) {
            livro.reduzirEstoque();
        }

        repository.atualizar(livro);
    }

    // AUMENTAR estoque com validação
    public void aumentarEstoque(int idLivro, int quantidade) {
        Livro livro = buscarPorId(idLivro);

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser positiva!");
        }

        if (livro.getQuantidadeDisponivel() + quantidade > livro.getQuantidadeTotal()) {
            System.out.println("Quantidade não pode ultrapassar o total!");
        }

        for (int i = 0; i < quantidade; i++) {
            livro.aumentarEstoque();
        }

        repository.atualizar(livro);
    }

    // DELETAR com validação
    public boolean deletarLivro(int id) {
        buscarPorId(id); // Valida se existe
        return repository.deletar(id);
    }
}