package controller;

import models.Livro;
import services.LivroService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivroController {

    private LivroService service;
    private Scanner scanner;

    public LivroController(LivroService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    // Consultar/Listar todos os livros
    public void consultarLivros() {
        try {
            System.out.println("\n--- LIVROS CADASTRADOS ---");
            List<Livro> livros = service.listarTodos();

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
            } else {
                for (Livro l : livros) {
                    System.out.println(l);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar livros: " + e.getMessage());
        }
    }

    // Listar apenas livros disponíveis
    public void consultarLivrosDisponíveis() {
        try {
            System.out.println("\n--- LIVROS DISPONÍVEIS ---");
            List<Livro> livros = service.listarDisponíveis();

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro disponível.");
            } else {
                for (Livro l : livros) {
                    System.out.println(l);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar livros disponíveis: " + e.getMessage());
        }
    }

    // Buscar por título
    public void buscarPorTitulo() {
        try {
            System.out.print("\nDigite o título para buscar: ");
            String titulo = scanner.nextLine();

            List<Livro> livros = service.buscarPorTitulo(titulo);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado com esse título.");
            } else {
                System.out.println("\n--- RESULTADOS ---");
                for (Livro l : livros) {
                    System.out.println(l);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar livro por título: " + e.getMessage());
        }
    }

    // Buscar por autor
    public void buscarPorAutor() {
        try {
            System.out.print("\nDigite o autor para buscar: ");
            String autor = scanner.nextLine();

            List<Livro> livros = service.buscarPorAutor(autor);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado com esse autor.");
            } else {
                System.out.println("\n--- RESULTADOS ---");
                for (Livro l : livros) {
                    System.out.println(l);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar livro por autor: " + e.getMessage());
        }
    }

    // Buscar por gênero
    public void buscarPorGenero() {
        try {
            System.out.print("\nDigite o gênero para buscar: ");
            String genero = scanner.nextLine();

            List<Livro> livros = service.buscarPorGenero(genero);

            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado com esse gênero.");
            } else {
                System.out.println("\n--- RESULTADOS ---");
                for (Livro l : livros) {
                    System.out.println(l);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar livro por gênero: " + e.getMessage());
        }
    }

    // Criar novo livro
    public void criarLivro() {
        try {
            System.out.println("\n--- CADASTRAR LIVRO ---");

            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Autor: ");
            String autor = scanner.nextLine();

            System.out.print("Editora: ");
            String editora = scanner.nextLine();

            System.out.print("Ano de publicação: ");
            int anoPublicacao = Integer.parseInt(scanner.nextLine());

            System.out.print("Quantidade total: ");
            int quantidadeTotal = Integer.parseInt(scanner.nextLine());

            System.out.print("Quantidade disponível: ");
            int quantidadeDisponivel = Integer.parseInt(scanner.nextLine());

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            System.out.print("Preço (use ponto para decimais): ");
            double preco = Double.parseDouble(scanner.nextLine());

            int anoAtual = LocalDate.now().getYear();

            if (anoPublicacao > anoAtual) {
                System.out.println("❌ Algo deu errado ao cadastrar o livro. O ano de publicação não pode ser maior que o ano atual.");
            } else {
                // Chama Service (que valida!)
                Livro livro = service.criarLivro(titulo, autor, editora, anoPublicacao,
                        quantidadeTotal, quantidadeDisponivel, genero, preco);

                if (livro == null) {
                    System.out.println("❌ Algo deu errado ao cadastrar o livro. Verifique as regras de validação.");
                } else {
                    System.out.println("✔ Livro cadastrado com sucesso! ID: " + livro.getId());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro de formato: Ano, quantidades e preço precisam ser valores numéricos válidos.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    // Editar livro
    public void editarLivro() {
        try {
            consultarLivros();
            System.out.print("\nID do livro a editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            // Busca o livro (valida)
            Livro livro = service.buscarPorId(id);

            if (livro == null) {
                System.out.println("❌ ID inválido! Livro não encontrado.");
                return;
            }

            System.out.println("\n--- EDITAR LIVRO ---");
            System.out.println("1. Título (" + livro.getTitulo() + ")");
            System.out.println("2. Autor (" + livro.getAutor() + ")");
            System.out.println("3. Editora (" + livro.getEditora() + ")");
            System.out.println("4. Ano de Publicação (" + livro.getAnoPublicacao() + ")");
            System.out.println("5. Quantidade Total (" + livro.getQuantidadeTotal() + ")");
            System.out.println("6. Quantidade Disponível (" + livro.getQuantidadeDisponivel() + ")");
            System.out.println("7. Gênero (" + livro.getGenero() + ")");
            System.out.println("8. Preço (R$ " + livro.getPreco() + ")");
            System.out.println("0. Cancelar");
            System.out.print("Escolha o campo: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Novo título: ");
                    String titulo = scanner.nextLine();
                    service.atualizarLivro(id, titulo, null, null, 0, 0, 0, null, 0);
                    System.out.println("✔ Título atualizado!");
                    break;

                case "2":
                    System.out.print("Novo autor: ");
                    String autor = scanner.nextLine();
                    service.atualizarLivro(id, null, autor, null, 0, 0, 0, null, 0);
                    System.out.println("✔ Autor atualizado!");
                    break;

                case "3":
                    System.out.print("Nova editora: ");
                    String editora = scanner.nextLine();
                    service.atualizarLivro(id, null, null, editora, 0, 0, 0, null, 0);
                    System.out.println("✔ Editora atualizada!");
                    break;

                case "4":
                    System.out.print("Novo ano: ");
                    int ano = Integer.parseInt(scanner.nextLine());
                    service.atualizarLivro(id, null, null, null, ano, 0, 0, null, 0);
                    System.out.println("✔ Ano atualizado!");
                    break;

                case "5":
                    System.out.print("Nova quantidade total: ");
                    int qtTotal = Integer.parseInt(scanner.nextLine());
                    service.atualizarLivro(id, null, null, null, 0, qtTotal, 0, null, 0);
                    System.out.println("✔ Quantidade total atualizada!");
                    break;

                case "6":
                    System.out.print("Nova quantidade disponível: ");
                    int qtDisp = Integer.parseInt(scanner.nextLine());
                    service.atualizarLivro(id, null, null, null, 0, 0, qtDisp, null, 0);
                    System.out.println("✔ Quantidade disponível atualizada!");
                    break;

                case "7":
                    System.out.print("Novo gênero: ");
                    String genero = scanner.nextLine();
                    service.atualizarLivro(id, null, null, null, 0, 0, 0, genero, 0);
                    System.out.println("✔ Gênero atualizado!");
                    break;

                case "8":
                    System.out.print("Novo preço: ");
                    double preco = Double.parseDouble(scanner.nextLine());
                    service.atualizarLivro(id, null, null, null, 0, 0, 0, null, preco);
                    System.out.println("✔ Preço atualizado!");
                    break;

                case "0":
                    System.out.println("Edição cancelada.");
                    break;

                default:
                    System.out.println("❌ Opção inválida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O valor digitado precisa corresponder ao formato numérico esperado para o campo.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao editar livro: " + e.getMessage());
        }
    }

    // Deletar livro
    public void deletarLivro() {
        try {
            consultarLivros();
            System.out.print("\nID do livro a deletar: ");
            int id = Integer.parseInt(scanner.nextLine());
            boolean livroDeletado = service.deletarLivro(id);

            if (!livroDeletado) {
                System.out.println("❌ Livro não existe!");
            } else {
                System.out.println("✔ Livro deletado com sucesso!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: O ID do livro precisa ser um número inteiro válido.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao deletar livro: " + e.getMessage());
        }
    }
}