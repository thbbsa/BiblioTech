# BiblioTech System - Documentação Completa do Código Fonte

---

# Versão Inicial

## Classe Cliente (Versão Inicial)

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe que representa um cliente do sistema.
    
    Armazena informações como id, nome, email e senha,
    utilizadas para cadastro, autenticação e identificação
    do cliente no sistema.
    
    Possui métodos getters para acesso aos dados e sobrescreve
    o método toString() para exibir as informações de forma
    simplificada no console.
*/

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Cliente(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | " + email;
    }
}
```

## Classe Livro (Versão Inicial)

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe que representa um livro dentro do sistema.
    
    Armazena informações como título, autor, editora, ano de publicação,
    gênero, preço e controle de estoque (quantidade total e disponível).
    
    Possui métodos para verificar se o livro está disponível, reduzir e
    aumentar o estoque, além de getters e setters para manipulação dos dados.
    
    Também sobrescreve o método toString() para exibir os dados do livro
    de forma organizada no console.
*/

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;
    private String genero;
    private double preco;

    public Livro(int id, String titulo, String autor, String editora, int anoPublicacao, int quantidadeTotal,
                 int quantidadeDisponivel, String genero, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.genero = genero;
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public boolean reduzirEstoque() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    public void aumentarEstoque() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " | " + autor + " | " + editora +
                " | " + anoPublicacao + " | Estoque: " + quantidadeDisponivel +
                "/" + quantidadeTotal + " | " + genero + " | R$ " + preco;
    }
}
```

## Menu Principal Inicial

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe principal do sistema BiblioTech responsável por iniciar a aplicação.
    
    Exibe o menu inicial no console, permitindo ao usuário escolher entre
    acessar a área de cliente, a área de funcionário/administrador ou encerrar o sistema.
    
    Gerencia a navegação entre os menus, realiza a leitura das entradas
    do usuário e chama os métodos correspondentes para login e cadastro.
*/

import java.util.Scanner;

public class MainInicial {

    private static Scanner leitura = new Scanner(System.in);

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=============================");
            System.out.println("   BEM-VINDO AO BIBLIOTECH   ");
            System.out.println("=============================");
            System.out.println("1. Cliente");
            System.out.println("2. Funcionário / Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Área de Cliente - em desenvolvimento");
                    break;

                case 2:
                    System.out.println("Área de Funcionário/Administrador - em desenvolvimento");
                    break;

                case 0:
                    rodando = false;
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        leitura.close();
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        leitura.nextLine();
    }
}
```

---

# Versão Final - Modelos (Models)

## Classe Cliente (Versão Final)

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe model que representa um cliente do sistema.
    
    Armazena informações essenciais como id, nome, email e senha,
    utilizadas para cadastro, autenticação e identificação do cliente.
    
    Fornece métodos getters para acesso aos dados e sobrescreve
    o método toString() para exibição formatada no console.
*/

package models;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Cliente(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | " + email;
    }
}
```

## Classe Funcionário

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe model que representa um funcionário do sistema.
    
    Armazena informações básicas como id, nome, email e senha,
    utilizadas para autenticação e identificação do funcionário.
    
    Fornece métodos getters para acesso aos dados e sobrescreve
    o método toString() para exibição simplificada no console.
*/

package models;

public class Funcionario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Funcionario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + email;
    }
}
```

## Classe Livro (Versão Final)

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe model que representa um livro do acervo da biblioteca.
    
    Armazena informações completas como título, autor, editora, 
    ano de publicação, gênero, preço e controle de estoque.
    
    Implementa métodos para gerenciar disponibilidade, reduzir
    e aumentar estoque, além de getters, setters e exibição formatada.
*/

package models;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int quantidadeTotal;
    private int quantidadeDisponivel;
    private String genero;
    private double preco;

    public Livro(int id, String titulo, String autor, String editora, int anoPublicacao,
                 int quantidadeTotal, int quantidadeDisponivel, String genero, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.genero = genero;
        this.preco = preco;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getEditora() { return editora; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public int getQuantidadeTotal() { return quantidadeTotal; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public String getGenero() { return genero; }
    public double getPreco() { return preco; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setEditora(String editora) { this.editora = editora; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public void setQuantidadeTotal(int quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setPreco(double preco) { this.preco = preco; }

    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public boolean reduzirEstoque() {
        if (quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
            return true;
        }
        return false;
    }

    public void aumentarEstoque() {
        if (quantidadeDisponivel < quantidadeTotal) {
            quantidadeDisponivel++;
        }
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " | " + autor + " | " + editora +
                " | " + anoPublicacao + " | Estoque: " + quantidadeDisponivel +
                "/" + quantidadeTotal + " | " + genero + " | R$ " + preco;
    }
}
```

## Classe Empréstimo

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe model que representa um empréstimo de livro no sistema.
    
    Armazena informações como id do empréstimo, id do livro, id do cliente,
    data do empréstimo, data de devolução e status de devolução.
    
    Fornece métodos getters para acesso aos dados, setter para atualizar
    o status e exibição formatada das informações no console.
*/

package models;

public class Emprestimo {
    private int id;
    private int idLivro;
    private int idCliente;
    private String dataEmprestimo;
    private String dataDevolucao;
    private boolean devolvido;

    public Emprestimo(int id, int idLivro, int idCliente, String dataEmprestimo,
                      String dataDevolucao, boolean devolvido) {
        this.id = id;
        this.idLivro = idLivro;
        this.idCliente = idCliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public int getId() { return id; }
    public int getIdLivro() { return idLivro; }
    public int getIdCliente() { return idCliente; }
    public String getDataEmprestimo() { return dataEmprestimo; }
    public String getDataDevolucao() { return dataDevolucao; }
    public boolean isDevolvido() { return devolvido; }

    public void setDevolvido(boolean devolvido) { this.devolvido = devolvido; }

    @Override
    public String toString() {
        return id + " | Livro: " + idLivro + " | Cliente: " + idCliente +
                " | Empréstimo: " + dataEmprestimo + " | Devolução: " + dataDevolucao +
                " | Status: " + (devolvido ? "Devolvido" : "Pendente");
    }
}
```

## Classe Compra

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe model que representa uma compra de livro realizada por um cliente.
    
    Armazena informações como id da compra, id do cliente, id do livro,
    quantidade, preço unitário, preço total, data e status da compra.
    
    O preço total é calculado automaticamente. Fornece getters, setters
    e exibição formatada das informações da compra.
*/

package models;

public class Compra {
    private int id;
    private int idCliente;
    private int idLivro;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;
    private String dataCompra;
    private String status;

    public Compra(int id, int idCliente, int idLivro, int quantidade, double precoUnitario,
                  String dataCompra, String status) {
        this.id = id;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoUnitario * quantidade;
        this.dataCompra = dataCompra;
        this.status = status;
    }

    public int getId() { return id; }
    public int getIdCliente() { return idCliente; }
    public int getIdLivro() { return idLivro; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public double getPrecoTotal() { return precoTotal; }
    public String getDataCompra() { return dataCompra; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.precoTotal = this.precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Cliente: " + idCliente + " | Livro: " + idLivro +
                " | Quantidade: " + quantidade + " | Preço Unitário: R$ " + String.format("%.2f", precoUnitario) +
                " | Total: R$ " + String.format("%.2f", precoTotal) + " | Data: " + dataCompra +
                " | Status: " + status;
    }
}
```

---

# Versão Final - Repositórios (Repositories)

## LivroRepository

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe repositório responsável pelo acesso e gerenciamento de dados dos livros.
    
    Implementa operações CRUD (Create, Read, Update, Delete) e métodos de busca
    por título, autor, gênero e disponibilidade. Armazena os livros em memória
    em uma ArrayList durante a execução da aplicação.
*/

package repositories;

import models.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    public Livro salvar(String titulo, String autor, String editora, int anoPublicacao,
                        int quantidadeTotal, int quantidadeDisponivel, String genero, double preco) {
        Livro l = new Livro(proximoId++, titulo, autor, editora, anoPublicacao,
                quantidadeTotal, quantidadeDisponivel, genero, preco);
        livros.add(l);
        return l;
    }

    public Livro buscarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public List<Livro> buscarPorGenero(String genero) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.getGenero().toLowerCase().contains(genero.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public List<Livro> buscarTodos() {
        return new ArrayList<>(livros);
    }

    public List<Livro> buscarDisponíveis() {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros) {
            if (l.isDisponivel()) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    public void atualizar(Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == livroAtualizado.getId()) {
                livros.set(i, livroAtualizado);
                break;
            }
        }
    }

    public boolean deletar(int id) {
        return livros.removeIf(l -> l.getId() == id);
    }
}
```

## ClienteRepository

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe repositório responsável pelo acesso e gerenciamento de dados dos clientes.
    
    Implementa operações CRUD e métodos de busca por id, email e nome.
    Armazena os clientes em memória em uma ArrayList durante a execução da aplicação.
*/

package repositories;

import models.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public Cliente salvar(String nome, String email, String senha) {
        Cliente c = new Cliente(proximoId++, nome, email, senha);
        clientes.add(c);
        return c;
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarPorEmail(String email) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cliente> buscarTodos() {
        return new ArrayList<>(clientes);
    }

    public boolean deletar(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
}
```

## FuncionarioRepository

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe repositório responsável pelo acesso e gerenciamento de dados dos funcionários.
    
    Implementa operações CRUD e métodos de busca por id, email.
    Armazena os funcionários em memória em uma ArrayList durante a execução da aplicação.
*/

package repositories;

import models.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    public Funcionario salvar(String nome, String email, String senha) {
        Funcionario f = new Funcionario(proximoId++, nome, email, senha);
        funcionarios.add(f);
        return f;
    }

    public Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public Funcionario buscarPorEmail(String email) {
        for (Funcionario f : funcionarios) {
            if (f.getEmail().equals(email)) {
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> buscarTodos() {
        return new ArrayList<>(funcionarios);
    }

    public boolean deletar(int id) {
        return funcionarios.removeIf(f -> f.getId() == id);
    }
}
```

## EmprestimoRepository

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe repositório responsável pelo acesso e gerenciamento de dados dos empréstimos.
    
    Implementa operações CRUD e métodos de busca por cliente, livro, status de devolução
    e detecção de atrasos. Mantém duas listas: uma para empréstimos ativos e outra para devolvidos.
*/

package repositories;

import models.Emprestimo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Emprestimo> devolvidos = new ArrayList<>();
    private int proximoId = 1;

    public Emprestimo salvar(int idLivro, int idCliente, String dataEmprestimo, String dataDevolucao) {
        Emprestimo e = new Emprestimo(proximoId++, idLivro, idCliente, dataEmprestimo, dataDevolucao, false);
        emprestimos.add(e);
        return e;
    }

    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) {
                return e;
            }
        }
        for (Emprestimo e : devolvidos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Emprestimo> buscarTodos() {
        return new ArrayList<>(emprestimos);
    }

    public List<Emprestimo> buscarPorCliente(int idCliente) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getIdCliente() == idCliente) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public List<Emprestimo> buscarPorLivro(int idLivro) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.getIdLivro() == idLivro) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public List<Emprestimo> buscarTodosDevolvidos() {
        return new ArrayList<>(devolvidos);
    }

    public List<Emprestimo> buscarDevolvidosPorCliente(int idCliente) {
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : devolvidos) {
            if (e.getIdCliente() == idCliente) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public List<Emprestimo> buscarAtrasados(String dataAtual) {
        List<Emprestimo> resultado = new ArrayList<>();
        LocalDate hoje = LocalDate.parse(dataAtual);
        for (Emprestimo e : emprestimos) {
            LocalDate dataDevolucao = LocalDate.parse(e.getDataDevolucao());
            if (hoje.isAfter(dataDevolucao)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public boolean registrarDevolucao(int idEmprestimo) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == idEmprestimo) {
                e.setDevolvido(true);
                devolvidos.add(e);
                emprestimos.remove(e);
                return true;
            }
        }
        return false;
    }

    public boolean deletar(int id) {
        return devolvidos.removeIf(e -> e.getId() == id);
    }

    public int contarAtivos() {
        return emprestimos.size();
    }

    public int contarDevolvidos() {
        return devolvidos.size();
    }
}
```

## CompraRepository

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe repositório responsável pelo acesso e gerenciamento de dados das compras.
    
    Implementa operações CRUD e métodos de busca por cliente, livro e status.
    Permite filtragem de compras por status (pendente, concluída, cancelada).
    Armazena os dados em memória em uma ArrayList durante a execução.
*/

package repositories;

import models.Compra;
import java.util.ArrayList;
import java.util.List;

public class CompraRepository {

    private List<Compra> compras = new ArrayList<>();
    private int proximoId = 1;

    public Compra salvar(int idCliente, int idLivro, int quantidade, double precoUnitario,
                         String dataCompra, String status) {
        Compra c = new Compra(proximoId++, idCliente, idLivro, quantidade, precoUnitario, dataCompra, status);
        compras.add(c);
        return c;
    }

    public Compra buscarPorId(int id) {
        for (Compra c : compras) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Compra> buscarTodos() {
        return new ArrayList<>(compras);
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

    public List<Compra> buscarPendentes() {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getStatus().equals("pendente")) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Compra> buscarConcluidas() {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getStatus().equals("concluída")) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Compra> buscarCanceladas() {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getStatus().equals("cancelada")) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void atualizarStatus(int idCompra, String novoStatus) {
        Compra c = buscarPorId(idCompra);
        if (c != null) {
            c.setStatus(novoStatus);
        }
    }

    public boolean deletar(int id) {
        return compras.removeIf(c -> c.getId() == id);
    }
}
```

---

# Versão Final - Serviços (Services)

## LivroService

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe serviço responsável pela lógica de negócio relacionada aos livros.
    
    Implementa validações para criação, atualização e exclusão de livros.
    Gerencia operações de estoque (reduzir e aumentar) e fornece métodos
    de busca por diversos critérios (título, autor, gênero, disponibilidade).
*/

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
            System.out.println("Título não pode estar vazio!");
        }

        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("Autor não pode estar vazio!");
        }

        if (editora == null || editora.trim().isEmpty()) {
            System.out.println("Editora não pode estar vazia!");
        }

        if (anoPublicacao <= 0) {
            System.out.println("Ano de publicação deve ser positivo!");
        }

        if (preco <= 0) {
            System.out.println("Preço deve ser maior que zero!");
        }

        if (quantidadeTotal <= 0) {
            System.out.println("Quantidade total deve ser positiva!");
        }

        if (quantidadeDisponivel > quantidadeTotal) {
            System.out.println("Quantidade disponível não pode ser maior que total!");
        }

        if (genero == null || genero.trim().isEmpty()) {
            System.out.println("Gênero não pode estar vazio!");
        }

        return repository.salvar(titulo, autor, editora, anoPublicacao,
                quantidadeTotal, quantidadeDisponivel, genero, preco);
    }

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

    public List<Livro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título não pode estar vazio!");
        }
        return repository.buscarPorTitulo(titulo);
    }

    public List<Livro> buscarPorAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            System.out.println("Autor não pode estar vazio!");
        }
        return repository.buscarPorAutor(autor);
    }

    public List<Livro> buscarPorGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            System.out.println("Gênero não pode estar vazio!");
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
        if (quantidadeDisponivel >= 0 && quantidadeDisponivel <= quantidadeTotal) {
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

    public boolean deletarLivro(int id) {
        buscarPorId(id);
        return repository.deletar(id);
    }
}
```

---

# Versão Final - Controladores (Controllers)

## ClienteController

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe controladora responsável por gerenciar as operações de cliente.
    
    Orquestra a interação entre a view (entrada do usuário) e o service (lógica).
    Implementa métodos para cadastro, login, listagem e exclusão de clientes,
    além de visualização de perfil com formatação adequada.
*/

package controller;

import models.Cliente;
import services.ClienteService;
import java.util.List;
import java.util.Scanner;

public class ClienteController {
    private ClienteService service;
    private Scanner scanner;

    public ClienteController(ClienteService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = service.criarCliente(nome, email, senha);

        if (cliente == null) {
            System.out.println("Algo deu errado!");
        } else {
            System.out.println("✔ Cadastro realizado com sucesso!");
            System.out.println("ID do cliente: " + cliente.getId());
        }
    }

    public Cliente fazerlogin() {
        System.out.println("\n--- LOGIN DE CLIENTE ---");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Cliente cliente = service.fazerLogin(email, senha);

        if (cliente == null) {
            System.out.println("Aconteceu algo errado!");
        } else {
            System.out.println("✔ Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + cliente.getNome() + "!");

            return cliente;
        }

        return null;
    }

    public void listarClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");

        List<Cliente> clientes = service.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void buscarPorNome() {
        System.out.print("\nDigite o nome para buscar: ");
        String nome = scanner.nextLine();
        List<Cliente> clientes = service.buscarPorNome(nome);

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado com esse nome.");
        } else {
            System.out.println("\n--- RESULTADOS ---");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void deletarCliente() {
        listarClientes();
        System.out.print("\nID do cliente a deletar: ");

        int id = Integer.parseInt(scanner.nextLine());
        service.deletarCliente(id);
        System.out.println("✔ Cliente deletado com sucesso!");
    }

    public void visualizarPerfil(Cliente cliente) {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║        PERFIL DO CLIENTE          ║");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
    }
}
```

---

# Versão Final - Inicialização (Main)

## Main

```java
/*
    Autores:
    - Thiago Barbosa De Oliveira
    - Enzo Munarin Do Nascimento
    - Italo Trindade Oliveira

    Projeto - BiblioTech
    
    Descrição:
    Classe principal que inicializa a aplicação BiblioTech.
    
    Responsável pela criação e injeção de dependência de todos os repositórios,
    serviços, controladores e views. Exibe mensagens de carregamento formatadas
    e credentials do admin. Inicia o MenuPrincipal que gerencia a navegação.
*/

import repositories.*;
import services.*;
import controller.*;
import views.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printHeader("INICIANDO BIBLIOTECH SYSTEM...");

        // ============================================
        // CRIAR TODOS OS REPOSITORIES
        // ============================================
        printSection("📦 Carregando repositórios...");

        var livroRepository = new LivroRepository();
        var funcionarioRepository = new FuncionarioRepository();
        var clienteRepository = new ClienteRepository();
        var emprestimoRepository = new EmprestimoRepository();
        var compraRepository = new CompraRepository();

        // ============================================
        // CRIAR TODOS OS SERVICES
        // ============================================
        printSection("⚙️  Inicializando serviços...");

        var livroService = new LivroService(livroRepository);
        var funcionarioService = new FuncionarioService(funcionarioRepository);
        var clienteService = new ClienteService(clienteRepository);
        var emprestimoService = new EmprestimoService(
                emprestimoRepository, livroService, clienteService);
        var compraService = new CompraService(
                compraRepository, livroService, clienteService);

        // ============================================
        // CRIAR TODOS OS CONTROLLERS
        // ============================================
        printSection("🎮 Configurando controllers...");

        var livroController = new LivroController(livroService, scanner);
        var funcionarioController = new FuncionarioController(funcionarioService, scanner);
        var clienteController = new ClienteController(clienteService, scanner);
        var emprestimoController = new EmprestimoController(emprestimoService, scanner);
        var compraController = new CompraController(compraService, scanner);

        // ============================================
        // CRIAR VIEWS (Menus)
        // ============================================
        printSection("🎨 Carregando interfaces...\n");

        var menuAdmin = new MenuAdministrador(
                funcionarioController,
                livroController,
                emprestimoController,
                compraController,
                scanner
        );

        var menuPrincipal = new MenuPrincipal(
                clienteController,
                funcionarioController,
                livroController,
                emprestimoController,
                compraController,
                menuAdmin,
                scanner
        );

        // ============================================
        //  INICIAR APLICAÇÃO
        // ============================================
        printSuccess("Sistema pronto!\n");

        printCredentials();

        menuPrincipal.exibir();

        scanner.close();
    }

    private static void printHeader(String message) {
        String header = """
                ╔════════════════════════════════════╗
                ║   %s   ║
                ╚════════════════════════════════════╝
                """.formatted(centerText(message, 30));
        System.out.println(header);
    }

    private static void printSection(String message) {
        System.out.println(message);
    }

    private static void printSuccess(String message) {
        System.out.println("✔ " + message);
    }

    private static void printCredentials() {
        String credentials = """
                Credenciais Admin (para teste):
                  📧 Email: italo@gmail.com
                  🔐 Senha: 123456
                """;
        System.out.println(credentials);
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int totalPadding = width - text.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
}
```

---

## Arquitetura do Sistema

O sistema BiblioTech foi desenvolvido seguindo a arquitetura em camadas:

1. **Models** - Representam as entidades do sistema
2. **Repositories** - Gerenciam o acesso aos dados em memória
3. **Services** - Contêm a lógica de negócio com validações
4. **Controllers** - Orquestram a entrada e saída de dados
5. **Views** - Apresentam os menus interativos
6. **Main** - Inicializa e conecta toda a aplicação

Esta arquitetura garante:
- ✅ Separação de responsabilidades
- ✅ Facilita testes unitários
- ✅ Manutenção simplificada
- ✅ Reutilização de código
- ✅ Escalabilidade futura