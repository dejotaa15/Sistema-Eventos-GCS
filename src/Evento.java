import java.util.ArrayList;

public class Evento {
    private static int codigoBase = 100;
    private int codigoEvento;
    private String nomeEvento;
    private String dataEvento;
    private double valorIngresso;
    private String nomeResponsavel;
    private int totalIngressos;
    private double ingressosNormais;
    private double ingressosEspeciais;
    ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Evento(String nomeEvento, String dataEvento, double valorIngresso, String nomeResponsavel, int totalIngressos) {
        this.codigoEvento = ++codigoBase;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.valorIngresso = valorIngresso;
        this.nomeResponsavel = nomeResponsavel;
        this.totalIngressos = totalIngressos;
        ingressosEspeciais = totalIngressos * 0.15;
        ingressosNormais = totalIngressos - ingressosEspeciais;
    }
    private static String formatarNumero(int numero) {
        if (numero < 10) {
            return "00" + numero;
        }
        else if (numero < 100){
            return "0" + numero;
        }
        else  {
            return "" + numero;
        }
    }

    private void gerarIngressos() {
        int contador = 1;
        for (int i = 0; i < ingressosNormais; i++) {
            String codigo = codigoEvento + "-" + formatarNumero(contador);
            ingressos.add(new Ingresso(codigo, null, false));
            contador++;
        }
        for (int i = 0; i < ingressosEspeciais; i++) {
            String codigo = codigoEvento + "-" + formatarNumero(contador) + "E";
            ingressos.add(new Ingresso(codigo, null, true));
            contador++;
        }
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