import java.util.ArrayList;
import java.util.List;

public class Evento {
    private static int codigoBase = 100;
    private int codigoEvento;
    private String nomeEvento;
    private String dataEvento;
    private double valorIngresso;
    private String nomeResponsavel;
    private int totalIngressos;
 private List<Ingresso> ingressosVendidos = new ArrayList<>();
    public Evento(String nomeEvento, String dataEvento, double valorIngresso, String nomeResponsavel, int totalIngressos) {
        this.codigoEvento = ++codigoBase;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.valorIngresso = valorIngresso;
        this.nomeResponsavel = nomeResponsavel;
        this.totalIngressos = totalIngressos;
    }

    public int getCodigoEvento() {
        return codigoEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public int getTotalIngressos() {
        return totalIngressos;
    }
    public int getProximaSequencia() {
    // A próxima sequência é o tamanho atual da lista + 1
    return this.ingressosVendidos.size() + 1;
}

// Para ajudar na lógica de validação:
public long getIngressosNormaisVendidos() {
    // Filtra ingressos que NÃO terminam com 'E'
    return this.ingressosVendidos.stream()
               .filter(i -> !i.getCodigoIngresso().endsWith("E"))
               .count();
}

public long getIngressosEspeciaisVendidos() {
    // Filtra ingressos que terminam com 'E'
    return this.ingressosVendidos.stream()
               .filter(i -> i.getCodigoIngresso().endsWith("E"))
               .count();
}
public void adicionarIngresso(Ingresso ingresso) {
    this.ingressosVendidos.add(ingresso);
}

    @Override
    public String toString() {
        return codigoEvento + " - " + nomeEvento + " | Data: " + dataEvento + " | R$" + valorIngresso + " | Resp: " + nomeResponsavel + " | Ingressos: " + totalIngressos;
    }
}