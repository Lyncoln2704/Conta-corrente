public class ContaCorrenteEspecial extends ContaCorrente {

    public ContaCorrenteEspecial(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public double calcularTaxaOperacao(double valor) {
        return valor * 0.001; 
    }
}



