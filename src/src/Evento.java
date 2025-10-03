public class Evento {
    private static int codigoBase = 100;
    private int codigoEvento;
    private String nomeEvento;
    private String dataEvento;
    private double valorIngresso;
    private String nomeResponsavel;
    private int totalIngressos;
    private String statusEvento;
    private int ingressosVendidos;

    public Evento(String nomeEvento, String dataEvento, double valorIngresso, String nomeResponsavel, int totalIngressos) {
        this.codigoEvento = ++codigoBase;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.valorIngresso = valorIngresso;
        this.nomeResponsavel = nomeResponsavel;
        this.totalIngressos = totalIngressos;
        this.statusEvento = "Rascunho";
        this.ingressosVendidos = 0;
    }
    public int getIngressosDisponiveis() {
        return totalIngressos - ingressosVendidos;
    }
    public boolean venderIngressos(int quantidade) {

        if (quantidade > 0 && getIngressosDisponiveis() >= quantidade) {
            this.ingressosVendidos += quantidade;


            if (getIngressosDisponiveis() == 0) {
                this.statusEvento = "Esgotado";
            }

            return true; 
        } else {

            System.out.println(" Venda não realizada! Ingressos insuficientes ou quantidade inválida.");
            return false;
        }
    }

    public String getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(String novoStatus) {
        this.statusEvento = novoStatus;

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

    @Override
    public String toString() {
        return codigoEvento + " - " + nomeEvento + " | Data: " + dataEvento + " | R$" + valorIngresso + " | Resp: " + nomeResponsavel + " | Ingressos: " + totalIngressos;
    }
}