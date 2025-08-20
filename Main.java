import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Transacao {
    private final String tipo;
    private final double valor;
    private final  double taxa;
    private final LocalDateTime dataHora;

    public Transacao(String tipo, double valor, double taxa) {
        this.tipo = tipo;
        this.valor = valor;
        this.taxa = taxa;
        this.dataHora = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if (taxa > 0) {
            return String.format("%s de R$ %.2f (Taxa: R$ %.2f) em %s", tipo, valor, taxa, dataHora.format(formatter));
        } else {
            return String.format("%s de R$ %.2f em %s", tipo, valor, dataHora.format(formatter));
        }
    }
}   
