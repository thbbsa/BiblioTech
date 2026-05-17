package repositories;

import models.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    // CREATE - Salvar novo livro
    public Livro salvar(String titulo, String autor, String editora, int anoPublicacao,
                        int quantidadeTotal, int quantidadeDisponivel, String genero, double preco) {
        Livro l = new Livro(proximoId++, titulo, autor, editora, anoPublicacao,
                quantidadeTotal, quantidadeDisponivel, genero, preco);

        livros.add(l);
        return l;
    }

    // READ - Buscar por ID
    public Livro buscarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    // READ - Buscar por Título (útil para pesquisa)
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // READ - Buscar por Autor
    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // READ - Buscar por Gênero
    public List<Livro> buscarPorGenero(String genero) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getGenero().toLowerCase().contains(genero.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // READ - Buscar todos
    public List<Livro> buscarTodos() {
        return new ArrayList<>(livros);
    }

    // READ - Buscar apenas livros disponíveis
    public List<Livro> buscarDisponíveis() {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.isDisponivel()) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // UPDATE - Atualizar livro
    public void atualizar(Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == livroAtualizado.getId()) {
                livros.set(i, livroAtualizado);
                break;
            }
        }
    }

    // DELETE - Deletar livro
    public boolean deletar(int id) {
        Livro livro = buscarPorId(id);

        if (livro == null) {
            return false;
        }

        return livros.removeIf(l -> l.getId() == id);
    }
}