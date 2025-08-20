import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {

    private static final double TAXA_OPERACAO = 0.005;

    private double saldo;
    private String numeroConta;
    private String titular;
    private final List<Transacao> historicoTransacoes = new ArrayList<>();

    public ContaCorrente(String numeroConta, String titular, double saldoInicial) {

        if (numeroConta == null || numeroConta.isEmpty()) {
            throw new IllegalArgumentException("O número da conta não pode ser nulo ou vazio.");
        }
        if (titular == null || titular.isEmpty()) {
            throw new IllegalArgumentException("O titular não pode ser nulo ou vazio.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");
        }
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public ContaCorrente(String titular, double saldoInicial) 
    {

        this("0000-0", titular, saldoInicial);
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        if (titular == null || titular.isEmpty()) {
            throw new IllegalArgumentException("O titular não pode ser nulo ou vazio.");
        }
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("O saldo não pode ser negativo.");
        }
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            historicoTransacoes.add(new Transacao("Depósito", valor));
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("O valor do saque deve ser positivo.");
            return false;
        }

        double total = valor + calcularTaxaOperacao(valor);

        if (saldo >= total) {
            this.saldo -= total;
            historicoTransacoes.add(new Transacao("Saque", valor, TAXA_OPERACAO * valor));
            System.out.println("Saque de R$ " + valor + " realizado com sucesso. Taxa de operação: R$ " + calcularTaxaOperacao(valor));
            return true;
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return false;
        }
    }

    public void transferir(ContaCorrente contaDestino, double valor) {
        if (valor <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
            return;
        }
        if (contaDestino == null) {
            System.out.println("Conta de destino inválida.");
            return;
        }
        if (this.numeroConta.equals(contaDestino.getNumeroConta())) {
            System.out.println("Não é possível transferir para a mesma conta.");
            return;
        }
        if (this.sacar(valor)) {
            contaDestino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada para a conta " + contaDestino.getNumeroConta() + ".");
        } else {
            System.out.println("Não foi possível realizar a transferência. Saldo insuficiente na conta de origem.");
        }
    }

    public void imprimirExtrato() {
        System.out.println("\n--- Extrato da Conta: " + this.numeroConta + " | Titular: " + this.titular + " ---");
        System.out.println("Saldo atual: R$ " + String.format("%.2f", this.saldo));
        System.out.println("\n--- Histórico de Transações ---");
        if (historicoTransacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            for (Transacao transacao : historicoTransacoes) {
                System.out.println(transacao);
            }
        }
        System.out.println("-------------------------------------------------");
    }

    private double calcularTaxaOperacao(double valor) {
        return valor * TAXA_OPERACAO;
    }
    
    public static void main(String[] args) {
        ContaCorrente conta1 = new ContaCorrente("12345-6", "João Silva", 1000.0);
        ContaCorrente conta2 = new ContaCorrente("65432-1", "Maria Oliveira", 500.0);

        System.out.println("--- Extratos Iniciais ---");
        conta1.imprimirExtrato();
        conta2.imprimirExtrato();

        System.out.println("\n--- Realizando Operações ---");
    
        conta1.depositar(250.0);
        conta1.sacar(100.0);
        conta1.transferir(conta2, 300.0);
        conta2.depositar(50.0);
        conta2.sacar(200.0);

        System.out.println("\n--- Extratos Finais ---");
        conta1.imprimirExtrato();
        conta2.imprimirExtrato();
    }
}
class Transacao {
    private String tipo;
    private double valor;
    private double taxa;
    private LocalDateTime dataHora;

    public Transacao(String tipo, double valor) {
        this(tipo, valor, 0.0);
    }

    public Transacao(String tipo, double valor, double taxa) {
        this.tipo = tipo;
        this.valor = valor;
        this.taxa = taxa;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f (Taxa: R$ %.2f) em %s",
                             tipo, valor, taxa, dataHora.toString().substring(0, 19).replace('T', ' '));
    }
}