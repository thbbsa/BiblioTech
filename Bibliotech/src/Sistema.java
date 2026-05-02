import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimosDevolvidos = new ArrayList<>();
    private ArrayList<Compra> compras = new ArrayList<>();
    private int proximoIdFuncionario = 1;
    private int proximoIdCliente = 1;
    private int proximoIdLivro = 1;
    private int proximoEmprestimo = 1;
    private int proximoEmprestimoDevolvido = 1;
    private int proximoIdCompra = 1;
    private Scanner leitura = new Scanner(System.in);

    // ========================
    // GERENCIAMENTO DE FUNCIONÁRIOS
    // ========================

    public Funcionario salvaFuncionario(String nome, String email, String senha) {
        Funcionario f = new Funcionario(proximoIdFuncionario++, nome, email, senha);
        funcionarios.add(f);
        return f;
    }

    public boolean deletarFuncionario(int id) {
        return funcionarios.removeIf(f -> f.getId() == id);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    // ========================
    // GERENCIAMENTO DE CLIENTES
    // ========================

    public Cliente salvarCliente(String nome, String email, String senha) {
        Cliente c = new Cliente(proximoIdCliente++, nome, email, senha);
        clientes.add(c);
        return c;
    }

    // ========================
    // GERENCIAMENTO DE LIVROS
    // ========================

    public Livro salvarLivro(String titulo, String autor, String editora, int anoPublicacao, int quantidadeTotal,
                             int quantidadeDisponivel, String genero, double preco) {
        Livro l = new Livro(proximoIdLivro++, titulo, autor, editora, anoPublicacao, quantidadeTotal, quantidadeDisponivel, genero, preco);
        livros.add(l);
        return l;
    }

    public boolean deletarLivro(int id) {
        return livros.removeIf(l -> l.getId() == id);
    }

    // ========================
    // GERENCIAMENTO DE EMPRÉSTIMOS
    // ========================

    public Emprestimo salvarEmprestimo(int idLivro, int idCliente, String dataEmprestada, String dataDevolvida, boolean devolvido) {
        Emprestimo e = new Emprestimo(proximoEmprestimo++, idLivro, idCliente, dataEmprestada, dataDevolvida, devolvido);
        emprestimos.add(e);
        return e;
    }

    public Emprestimo salvarEmprestimoDevolvidos(int idLivro, int idCliente, String dataEmprestada, String dataDevolucao, boolean devolvido) {
        Emprestimo eDevolvido = new Emprestimo(proximoEmprestimoDevolvido++, idLivro, idCliente, dataEmprestada, dataDevolucao, devolvido);
        emprestimosDevolvidos.add(eDevolvido);
        return eDevolvido;
    }

    // ========================
    // GERENCIAMENTO DE COMPRAS
    // ========================

    public Compra salvarCompra(int idCliente, int idLivro, int quantidade, double precoUnitario, String dataCompra, String status) {
        Compra c = new Compra(proximoIdCompra++, idCliente, idLivro, quantidade, precoUnitario, dataCompra, status);
        compras.add(c);
        return c;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public Compra encontrarCompraPorId(int id) {
        for (Compra c : compras) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean cancelarCompra(int id) {
        Compra c = encontrarCompraPorId(id);
        if (c != null && c.getStatus().equals("pendente")) {
            c.setStatus("cancelada");
            return true;
        }
        return false;
    }

    public boolean confirmarCompra(int id) {
        Compra c = encontrarCompraPorId(id);
        if (c != null && c.getStatus().equals("pendente")) {
            c.setStatus("concluída");
            return true;
        }
        return false;
    }

    // ========================
    // LOGIN
    // ========================

    public void realizarLoginCliente(String emailDigitado, String senhaDigitada) {
        for (Cliente c : clientes) {
            if (c.getEmail().equals(emailDigitado) && c.getSenha().equals(senhaDigitada)) {
                System.out.println("Cliente logado!");
                exibirMenuCliente(c);
                return;
            }
        }
        System.out.println("Acesso negado! Email ou senha incorretos.");
    }

    public void realizarLoginFunc(int tipoUsuario, String emailDigitado, String senhaDigitada) {
        switch (tipoUsuario) {
            case 1:
                for (Funcionario f : funcionarios) {
                    if (f.getEmail().equals(emailDigitado) && f.getSenha().equals(senhaDigitada)) {
                        System.out.println("Funcionário logado!");
                        exibirMenuFuncionario();
                        return;
                    }
                }
                System.out.println("Acesso negado! Email ou senha incorretos.");
                break;

            case 2:
                Administrador adm = new Administrador("italo", "italo@gmail.com", "123456", this);

                if (adm.getEmail().equals(emailDigitado) && adm.getSenha().equals(senhaDigitada)) {
                    System.out.println("Administrador logado!");
                    exibirMenuAdministrador(adm);
                } else {
                    System.out.println("Acesso negado! Email ou senha incorretos.");
                }
                break;

            default:
                System.out.println("Tipo de usuário inválido.");
        }
    }

    // ========================
    // MENU CLIENTE
    // ========================

    private void exibirMenuCliente(Cliente clienteLogado) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=============================");
            System.out.println("         MENU CLIENTE        ");
            System.out.println("=============================");
            System.out.println("1. Consultar Livros");
            System.out.println("2. Realizar Empréstimo");
            System.out.println("3. Consultar Meus Empréstimos");
            System.out.println("4. Comprar Livro");
            System.out.println("5. Consultar Minhas Compras");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1: consultarLivro(); break;
                case 2: realizarEmprestimoCliente(clienteLogado); break;
                case 3: consultarEmprestimoCliente(clienteLogado); break;
                case 4: realizarCompraCliente(clienteLogado); break;
                case 5: consultarComprasCliente(clienteLogado); break;
                case 0: rodando = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // MENU FUNCIONÁRIO
    // ========================

    private void exibirMenuFuncionario() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=============================");
            System.out.println("       MENU FUNCIONÁRIO      ");
            System.out.println("=============================");
            System.out.println("1. Consultar Livros");
            System.out.println("2. Realizar Empréstimo");
            System.out.println("3. Consultar Empréstimos");
            System.out.println("4. Registrar Devolução");
            System.out.println("5. Consultar Devoluções");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1: consultarLivro(); break;
                case 2: realizarEmprestimo(); break;
                case 3: consultarEmprestimo(); break;
                case 4: registrarDevolucao(); break;
                case 5: consultarDevolucoes(); break;
                case 0: rodando = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // MENU ADMINISTRADOR
    // ========================

    private void exibirMenuAdministrador(Administrador adm) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=============================");
            System.out.println("     MENU ADMINISTRADOR      ");
            System.out.println("=============================");
            System.out.println("-- Livros --");
            System.out.println("1. Consultar Livros");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Editar Livro");
            System.out.println("4. Deletar Livro");
            System.out.println("-- Empréstimos --");
            System.out.println("5. Realizar Empréstimo");
            System.out.println("6. Consultar Empréstimos");
            System.out.println("7. Registrar Devolução");
            System.out.println("8. Consultar Devoluções");
            System.out.println("-- Funcionários --");
            System.out.println("9. Cadastrar Funcionário");
            System.out.println("10. Listar Funcionários");
            System.out.println("11. Excluir Funcionário");
            System.out.println("-- Clientes --");
            System.out.println("12. Listar Clientes");
            System.out.println("-- Compras --");
            System.out.println("13. Listar Compras");
            System.out.println("14. Confirmar Compra");
            System.out.println("15. Cancelar Compra");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1: consultarLivro(); break;
                case 2: criarLivro(); break;
                case 3: editarLivro(); break;
                case 4: deletarLivro(); break;
                case 5: realizarEmprestimo(); break;
                case 6: consultarEmprestimo(); break;
                case 7: registrarDevolucao(); break;
                case 8: consultarDevolucoes(); break;
                case 9: menuCadastrarFuncionario(adm); break;
                case 10: listarFuncionarios(); break;
                case 11: menuExcluirFuncionario(adm); break;
                case 12: listarClientes(); break;
                case 13: listarCompras(); break;
                case 14: confirmarCompraPendente(); break;
                case 15: cancelarCompraPendente(); break;
                case 0: rodando = false; break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ========================
    // AÇÕES DE CLIENTES
    // ========================

    public void cadastrarCliente() {
        System.out.println("\n--- CADASTRAR CLIENTE ---");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();

        System.out.print("Email: ");
        String email = leitura.nextLine();

        System.out.print("Senha: ");
        String senha = leitura.nextLine();

        salvarCliente(nome, email, senha);
        System.out.println("✔ Cadastro realizado com sucesso!");
        pausar();
    }

    private void listarClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }

        pausar();
    }

    private void realizarEmprestimoCliente(Cliente clienteLogado) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro disponível.");
            pausar();
            return;
        }

        consultarLivro();

        System.out.print("ID do livro: ");
        int idLivro = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Quantos dias de empréstimo: ");
        int dias = leitura.nextInt();
        leitura.nextLine();

        String dataEmprestimo = LocalDate.now().toString();
        String dataDevolucao = LocalDate.now().plusDays(dias).toString();

        salvarEmprestimo(idLivro, clienteLogado.getId(), dataEmprestimo, dataDevolucao, false);
        System.out.println("✔ Empréstimo realizado com sucesso!");
        pausar();
    }

    private void consultarEmprestimoCliente(Cliente clienteLogado) {
        System.out.println("\n--- MEUS EMPRÉSTIMOS ---");

        boolean encontrou = false;

        for (Emprestimo e : emprestimos) {
            if (e.getIdCliente() == clienteLogado.getId()) {
                System.out.println(e);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Você não possui empréstimos ativos.");
        }

        pausar();
    }

    // ========================
    // AÇÕES DE COMPRAS - CLIENTE
    // ========================

    private void realizarCompraCliente(Cliente clienteLogado) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro disponível.");
            pausar();
            return;
        }

        consultarLivro();

        System.out.print("ID do livro que deseja comprar: ");
        int idLivro = leitura.nextInt();
        leitura.nextLine();

        Livro livroSelecionado = null;
        for (Livro l : livros) {
            if (l.getId() == idLivro) {
                livroSelecionado = l;
                break;
            }
        }

        if (livroSelecionado == null) {
            System.out.println("Erro: livro não encontrado.");
            pausar();
            return;
        }

        if (!livroSelecionado.isDisponivel()) {
            System.out.println("Erro: livro indisponível no estoque.");
            pausar();
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = leitura.nextInt();
        leitura.nextLine();

        if (quantidade > livroSelecionado.getQuantidadeDisponivel()) {
            System.out.println("Erro: quantidade insuficiente em estoque.");
            pausar();
            return;
        }

        salvarCompra(clienteLogado.getId(), idLivro, quantidade, livroSelecionado.getPreco(), LocalDate.now().toString(), "pendente");
        System.out.println("✔ Compra realizada com sucesso!");
        System.out.println("Subtotal: R$ " + String.format("%.2f", livroSelecionado.getPreco() * quantidade));
        pausar();
    }

    private void consultarComprasCliente(Cliente clienteLogado) {
        System.out.println("\n--- MINHAS COMPRAS ---");

        boolean encontrou = false;

        for (Compra c : compras) {
            if (c.getIdCliente() == clienteLogado.getId()) {
                System.out.println(c);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Você não possui compras registradas.");
        }

        pausar();
    }

    // ========================
    // AÇÕES DE FUNCIONÁRIOS
    // ========================

    private void menuCadastrarFuncionario(Administrador adm) {
        System.out.println("\n--- CADASTRAR FUNCIONÁRIO ---");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();

        System.out.print("Email: ");
        String email = leitura.nextLine();

        System.out.print("Senha: ");
        String senha = leitura.nextLine();

        adm.cadastrarFuncionario(nome, email, senha);
        pausar();
    }

    private void listarFuncionarios() {
        System.out.println("\n--- FUNCIONÁRIOS CADASTRADOS ---");

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        }

        pausar();
    }

    private void menuExcluirFuncionario(Administrador adm) {
        listarFuncionarios();
        System.out.print("ID do funcionário a excluir: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        adm.excluirFuncionario(id);
        pausar();
    }

    // ========================
    // AÇÕES DE LIVROS
    // ========================

    private void consultarLivro() {
        System.out.println("\n--- LIVROS CADASTRADOS ---");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro l : livros) {
                System.out.println(l);
            }
        }

        pausar();
    }

    private void criarLivro() {
        System.out.println("\n--- CADASTRAR LIVRO ---");

        System.out.print("Título: ");
        String titulo = leitura.nextLine();

        System.out.print("Autor: ");
        String autor = leitura.nextLine();

        System.out.print("Editora: ");
        String editora = leitura.nextLine();

        System.out.print("Ano de publicação: ");
        int anoPublicacao = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Quantidade total: ");
        int qntTotalLivros = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Quantidade disponível: ");
        int qntDisponivelLivros = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Gênero: ");
        String genero = leitura.nextLine();

        System.out.print("Digite o preço do livro: ");
        double preco = leitura.nextDouble();

        salvarLivro(titulo, autor, editora, anoPublicacao, qntTotalLivros, qntDisponivelLivros, genero, preco);
        System.out.println("✔ Livro cadastrado com sucesso!");
        pausar();
    }

    private void editarLivro() {
        consultarLivro();

        System.out.print("ID do livro que deseja editar: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        boolean encontrado = false;

        for (Livro l : livros) {
            if (id == l.getId()) {
                encontrado = true;

                System.out.println("\n--- EDITAR LIVRO ---");
                System.out.println("1. Título");
                System.out.println("2. Autor");
                System.out.println("3. Editora");
                System.out.println("4. Ano de Publicação");
                System.out.println("5. Quantidade Total");
                System.out.println("6. Quantidade Disponível");
                System.out.println("7. Gênero");
                System.out.println("8. Preço");
                System.out.println("0. Cancelar");
                System.out.print("Campo a editar: ");
                int campoEditar = leitura.nextInt();
                leitura.nextLine();

                switch (campoEditar) {
                    case 1:
                        System.out.print("Novo título: ");
                        l.setTitulo(leitura.nextLine());
                        break;
                    case 2:
                        System.out.print("Novo autor: ");
                        l.setAutor(leitura.nextLine());
                        break;
                    case 3:
                        System.out.print("Nova editora: ");
                        l.setEditora(leitura.nextLine());
                        break;
                    case 4:
                        System.out.print("Novo ano de publicação: ");
                        l.setAnoPublicacao(leitura.nextInt());
                        leitura.nextLine();
                        break;
                    case 5:
                        System.out.print("Nova quantidade total: ");
                        l.setQuantidadeTotal(leitura.nextInt());
                        leitura.nextLine();
                        break;
                    case 6:
                        System.out.print("Nova quantidade disponível: ");
                        l.setQuantidadeDisponivel(leitura.nextInt());
                        leitura.nextLine();
                        break;
                    case 7:
                        System.out.print("Novo gênero: ");
                        l.setGenero(leitura.nextLine());
                        break;
                    case 8:
                        System.out.print("Novo preço: ");
                        l.setPreco(leitura.nextDouble());
                        leitura.nextLine();
                        break;
                    case 0:
                        System.out.println("Edição cancelada.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }

                if (campoEditar != 0) {
                    System.out.println("✔ Livro atualizado com sucesso!");
                }

                pausar();
            }
        }

        if (!encontrado) {
            System.out.println("Erro: nenhum livro encontrado com esse ID.");
            pausar();
        }
    }

    private void deletarLivro() {
        consultarLivro();

        System.out.print("ID do livro que deseja excluir: ");
        int id = leitura.nextInt();
        leitura.nextLine();

        if (deletarLivro(id)) {
            System.out.println("✔ Livro removido com sucesso!");
        } else {
            System.out.println("Erro: nenhum livro encontrado com esse ID.");
        }

        pausar();
    }

    // ========================
    // AÇÕES DE EMPRÉSTIMOS
    // ========================

    private void realizarEmprestimo() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            pausar();
            return;
        }

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            pausar();
            return;
        }

        consultarLivro();
        listarClientes();

        System.out.print("ID do livro: ");
        int idLivro = leitura.nextInt();
        leitura.nextLine();

        System.out.print("ID do cliente: ");
        int idCliente = leitura.nextInt();
        leitura.nextLine();

        System.out.print("Data do empréstimo (dd/mm/aaaa): ");
        String dataEmprestada = leitura.nextLine();

        System.out.print("Quantos dias de empréstimo: ");
        int dias = leitura.nextInt();
        leitura.nextLine();

        String dataDevolucao = LocalDate.now().plusDays(dias).toString();

        salvarEmprestimo(idLivro, idCliente, dataEmprestada, dataDevolucao, false);
        System.out.println("✔ Empréstimo realizado com sucesso!");
        pausar();
    }

    private void consultarEmprestimo() {
        System.out.println("\n--- EMPRÉSTIMOS REALIZADOS ---");

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo cadastrado.");
        } else {
            for (Emprestimo e : emprestimos) {
                System.out.println(e);
            }
        }

        pausar();
    }

    private void registrarDevolucao() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não há empréstimos.");
            pausar();
        } else {
            consultarEmprestimo();
            System.out.print("Digite o id do empréstimo: ");
            int idEmprestimo = leitura.nextInt();
            leitura.nextLine();

            for (Emprestimo e : emprestimos) {
                if (idEmprestimo == e.getId()) {
                    salvarEmprestimoDevolvidos(e.getIdLivro(), e.getIdCliente(), e.getDataEmprestimo(), e.getDataDevolucao(), true);
                    break;
                }
            }

            emprestimos.removeIf(e -> e.getId() == idEmprestimo);
            System.out.println("✔ Devolução registrada com sucesso!");
            pausar();
        }
    }

    private void consultarDevolucoes() {
        System.out.println("\n--- EMPRÉSTIMOS DEVOLVIDOS ---");

        if (emprestimosDevolvidos.isEmpty()) {
            System.out.println("Nenhuma devolução registrada.");
        } else {
            for (Emprestimo e : emprestimosDevolvidos) {
                System.out.println(e);
            }
        }

        pausar();
    }

    // ========================
    // AÇÕES DE COMPRAS - ADMINISTRADOR
    // ========================

    private void listarCompras() {
        System.out.println("\n--- TODAS AS COMPRAS ---");

        if (compras.isEmpty()) {
            System.out.println("Nenhuma compra registrada.");
        } else {
            for (Compra c : compras) {
                System.out.println(c);
            }
        }

        pausar();
    }

    private void confirmarCompraPendente() {
        System.out.println("\n--- COMPRAS PENDENTES ---");

        boolean encontrou = false;

        for (Compra c : compras) {
            if (c.getStatus().equals("pendente")) {
                System.out.println(c);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma compra pendente.");
            pausar();
            return;
        }

        System.out.print("ID da compra a confirmar: ");
        int idCompra = leitura.nextInt();
        leitura.nextLine();

        if (confirmarCompra(idCompra)) {
            // Reduzir estoque do livro
            Compra compraConfirmada = encontrarCompraPorId(idCompra);
            for (Livro l : livros) {
                if (l.getId() == compraConfirmada.getIdLivro()) {
                    for (int i = 0; i < compraConfirmada.getQuantidade(); i++) {
                        l.reduzirEstoque();
                    }
                    break;
                }
            }
            System.out.println("✔ Compra confirmada com sucesso!");
        } else {
            System.out.println("Erro: compra não encontrada ou não está pendente.");
        }

        pausar();
    }

    private void cancelarCompraPendente() {
        System.out.println("\n--- COMPRAS PENDENTES ---");

        boolean encontrou = false;

        for (Compra c : compras) {
            if (c.getStatus().equals("pendente")) {
                System.out.println(c);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma compra pendente.");
            pausar();
            return;
        }

        System.out.print("ID da compra a cancelar: ");
        int idCompra = leitura.nextInt();
        leitura.nextLine();

        if (cancelarCompra(idCompra)) {
            System.out.println("✔ Compra cancelada com sucesso!");
        } else {
            System.out.println("Erro: compra não encontrada ou não está pendente.");
        }

        pausar();
    }

    // ========================
    // UTILITÁRIO
    // ========================

    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        leitura.nextLine();
    }
}