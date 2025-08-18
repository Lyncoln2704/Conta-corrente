public class ContaCorrente {
    private double saldo;

    public ContaCorrente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }
    public boolean sacar(double valor) {
        double taxa = calcularTaxaOperacao(valor);
        double total = valor + taxa;

        if (saldo >= total) {
            saldo -= total;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso. Taxa de operação: R$ " + taxa);
            return true;
        } else {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return false;
        }
    }
    public double calcularTaxaOperacao(double valor) {
        return valor * 0.005; 
    }

    public double obterSaldo() {
        return saldo;
    }
}
