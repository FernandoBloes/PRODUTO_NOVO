package exercicio_04_poo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Produto {
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;

    // Construtor 1: Recebe todos os atributos por parâmetro
    public Produto(String nome, double precoCusto, double precoVenda, LocalDate dataFabricacao, LocalDate dataValidade) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
    }

    // Construtor 2: Recebe nome, preço de custo, preço de venda e data de fabricação
    // Data de validade será 1 mês após a data de fabricação
    public Produto(String nome, double precoCusto, double precoVenda, LocalDate dataFabricacao) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataFabricacao.plusMonths(1); // Adiciona 1 mês à data de fabricação
    }

    // Construtor 3: Recebe apenas o nome e o preço de custo
    // Preço de venda será 10% a mais do que o preço de custo e a validade será 1 mês após a fabricação
    public Produto(String nome, double precoCusto) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoCusto * 1.10; // Preço de venda 10% a mais do que o custo
        this.dataFabricacao = LocalDate.now(); // Data de fabricação atual
        this.dataValidade = dataFabricacao.plusMonths(1); // Data de validade 1 mês após a fabricação
    }

    // Método para exibir os detalhes do produto
    public void exibirDetalhes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nome: " + nome);
        System.out.println("Preço de Custo: R$ " + String.format("%.2f", precoCusto));
        System.out.println("Preço de Venda: R$ " + String.format("%.2f", precoVenda));
        System.out.println("Data de Fabricação: " + dataFabricacao.format(formatter));
        System.out.println("Data de Validade: " + dataValidade.format(formatter));
    }

    // Método main para capturar as informações do usuário e testar os construtores
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Informe o nome do produto:");
        String nome = scanner.nextLine();

        System.out.println("Informe o preço de custo do produto:");
        double precoCusto = scanner.nextDouble();

        System.out.println("Escolha uma das opções:");
        System.out.println("1 - Inserir preço de venda e data de fabricação");
        System.out.println("2 - Inserir preço de venda e calcular data de validade");
        System.out.println("3 - Apenas inserir nome e preço de custo (venda + 10% e validade + 1 mês)");

        int opcao = scanner.nextInt();
        scanner.nextLine();  // Para limpar o buffer de entrada

        Produto produto = null;

        switch (opcao) {
            case 1:
                System.out.println("Informe o preço de venda do produto:");
                double precoVenda = scanner.nextDouble();

                System.out.println("Informe a data de fabricação (dd/MM/yyyy):");
                String dataFabricacaoString = scanner.next();
                LocalDate dataFabricacao = LocalDate.parse(dataFabricacaoString, formatter);

                System.out.println("Informe a data de validade (dd/MM/yyyy):");
                String dataValidadeString = scanner.next();
                LocalDate dataValidade = LocalDate.parse(dataValidadeString, formatter);

                produto = new Produto(nome, precoCusto, precoVenda, dataFabricacao, dataValidade);
                break;

            case 2:
                System.out.println("Informe o preço de venda do produto:");
                precoVenda = scanner.nextDouble();

                System.out.println("Informe a data de fabricação (dd/MM/yyyy):");
                dataFabricacaoString = scanner.next();
                dataFabricacao = LocalDate.parse(dataFabricacaoString, formatter);

                produto = new Produto(nome, precoCusto, precoVenda, dataFabricacao);
                break;

            case 3:
                produto = new Produto(nome, precoCusto);
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }

        if (produto != null) {
            System.out.println("\nDetalhes do produto:");
            produto.exibirDetalhes();
        }

        scanner.close();
    }
}
