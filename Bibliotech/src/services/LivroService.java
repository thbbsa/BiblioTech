package services;

import models.Livro;
import repositories.LivroRepository;
import java.util.List;

public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro criarLivro(String titulo, String autor, String editora, int anoPublicacao,
                            int quantidadeTotal, int quantidadeDisponivel, String genero, double preco) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do livro não pode estar vazio.");
        }

        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor do livro não pode estar vazio.");
        }

        if (editora == null || editora.trim().isEmpty()) {
            throw new IllegalArgumentException("A editora do livro não pode estar vazia.");
        }

        if (anoPublicacao <= 0) {
            throw new IllegalArgumentException("O ano de publicação deve ser um número positivo.");
        }

        if (preco <= 0) {
            throw new IllegalArgumentException("O preço do livro deve ser maior que zero.");
        }

        if (quantidadeTotal <= 0) {
            throw new IllegalArgumentException("A quantidade total de livros deve ser positiva.");
        }

        if (quantidadeDisponivel > quantidadeTotal) {
            throw new IllegalArgumentException("A quantidade disponível não pode ser maior que a quantidade total.");
        }

        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero do livro não pode estar vazio.");
        }

        return repository.salvar(titulo, autor, editora, anoPublicacao,
                quantidadeTotal, quantidadeDisponivel, genero, preco);
    }

    public Livro buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID consultado deve ser um número positivo.");
        }

        Livro livro = repository.buscarPorId(id);
        if (livro == null) {
            throw new IllegalArgumentException("Nenhum livro foi encontrado com o ID " + id);
        }

        return livro;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título para busca não pode estar vazio.");
        }
        return repository.buscarPorTitulo(titulo);
    }

    public List<Livro> buscarPorAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor para busca não pode estar vazio.");
        }
        return repository.buscarPorAutor(autor);
    }

    public List<Livro> buscarPorGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("O gênero para busca não pode estar vazio.");
        }
        return repository.buscarPorGenero(genero);
    }

    public List<Livro> listarTodos() {
        return repository.buscarTodos();
    }

    public List<Livro> listarDisponíveis() {
        return repository.buscarDisponíveis();
    }

    public void atualizarLivro(int id, String titulo, String autor, String editora,
                               int anoPublicacao, int quantidadeTotal, int quantidadeDisponivel,
                               String genero, double preco) {

        Livro livro = buscarPorId(id);

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
        if (quantidadeDisponivel >= 0) {
            if (quantidadeDisponivel > livro.getQuantidadeTotal()) {
                throw new IllegalArgumentException("A quantidade disponível não pode ser maior que a quantidade total.");
            }
            livro.setQuantidadeDisponivel(quantidadeDisponivel);
        }
        if (genero != null && !genero.trim().isEmpty()) {
            livro.setGenero(genero);
        }

        repository.atualizar(livro);
    }

    public void reduzirEstoque(int idLivro, int quantidade) {
        Livro livro = buscarPorId(idLivro);

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade para redução deve ser positiva.");
        }

        if (livro.getQuantidadeDisponivel() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente! Disponível: " + livro.getQuantidadeDisponivel());
        }

        for (int i = 0; i < quantidade; i++) {
            livro.reduzirEstoque();
        }

        repository.atualizar(livro);
    }

    public void aumentarEstoque(int idLivro, int quantidade) {
        Livro livro = buscarPorId(idLivro);

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade para aumento deve ser positiva.");
        }

        if (livro.getQuantidadeDisponivel() + quantidade > livro.getQuantidadeTotal()) {
            throw new IllegalStateException("A quantidade disponível não pode ultrapassar a quantidade total informada.");
        }

        for (int i = 0; i < quantidade; i++) {
            livro.aumentarEstoque();
        }

        repository.atualizar(livro);
    }

    public boolean deletarLivro(int id) {
        buscarPorId(id);
        return repository.deletar(id);
    }
}