
class ContaCorrente {
    protected String titular;
    protected double saldo;

    public ContaCorrente(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public double calcularTaxaOperacao(double valor) 
    {
        return valor * 0.002;
    }
}

public class ContaCorrenteEspecial extends ContaCorrente {

    
    private static final double TAXA_ESPECIAL = 0.001;

    public ContaCorrenteEspecial(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public double calcularTaxaOperacao(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }
        return valor * TAXA_ESPECIAL;
    }
}   

