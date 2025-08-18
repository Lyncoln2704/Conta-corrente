public class Main {
    public static void main(String[] args) {
        ContaCorrente contaComum = new ContaCorrente(1000.0);
        System.out.println("Saldo inicial Conta Comum: R$ " + contaComum.obterSaldo());

        contaComum.depositar(500.0);
        System.out.println("Saldo após depósito: R$ " + contaComum.obterSaldo());

        contaComum.sacar(200.0);
        System.out.println("Saldo após saque: R$ " + contaComum.obterSaldo());

        ContaCorrenteEspecial contaEspecial = new ContaCorrenteEspecial(2000.0);
        System.out.println("\nSaldo inicial Conta Especial: R$ " + contaEspecial.obterSaldo());

        contaEspecial.depositar(300.0);
        System.out.println("Saldo após depósito: R$ " + contaEspecial.obterSaldo());

        contaEspecial.sacar(500.0);
        System.out.println("Saldo após saque: R$ " + contaEspecial.obterSaldo());
    }
}