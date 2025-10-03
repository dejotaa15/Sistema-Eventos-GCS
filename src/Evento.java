import java.util.ArrayList;

public class Evento {
    private static int codigoBase = 100;
    private int codigoEvento;
    private String nomeEvento;
    private String dataEvento;
    private double valorIngresso;
    private String nomeResponsavel;
    private int totalIngressos;
    private int ingressosNormais;
    private int ingressosEspeciais;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Evento(String nomeEvento, String dataEvento, double valorIngresso,
                  String nomeResponsavel, int totalIngressos) {
        this.codigoEvento = ++codigoBase;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.valorIngresso = valorIngresso;
        this.nomeResponsavel = nomeResponsavel;
        this.totalIngressos = totalIngressos;
        this.ingressosEspeciais = (int) Math.round(totalIngressos * 0.15);
        this.ingressosNormais = totalIngressos - ingressosEspeciais;
        gerarIngressos();
    }

    private static String formatarNumero(int numero) {
        if (numero < 10) {
            return "00" + numero;
        } else if (numero < 100) {
            return "0" + numero;
        } else {
            return "" + numero;
        }
    }

    public void gerarIngressos() {
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

    public Ingresso comprarIngresso(Participante p, boolean ingressoEspecial) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getParticipante() == null && ingresso.isIngressoEspecial() == ingressoEspecial) {
                ingresso.setParticipante(p);
                return ingresso;
            }
        }
        return null;
    }

    public int ingressosDisponiveis() {
        int count = 0;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getParticipante() == null) {
                count++;
            }
        }
        return count;
    }

    public boolean registrarPresenca(String codigoIngresso) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getCodigoIngresso().equals(codigoIngresso) &&
                    ingresso.getParticipante() != null) {
                ingresso.registrarPresenca();
                return true;
            }
        }
        return false;
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

    public double getPercentualOcupacao() {
        int vendidos = 0;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getParticipante() != null) {
                vendidos++;
            }
        }
        if (totalIngressos == 0) return 0;
        return (vendidos * 100.0) / totalIngressos;
    }

    @Override
    public String toString() {
        String resultado = codigoEvento + " - " + nomeEvento + " | Data: " + dataEvento +
                " | R$" + valorIngresso + " | Resp: " + nomeResponsavel +
                " | Ingressos: " + totalIngressos +
                " | Ingressos Disponiveis: " + ingressosDisponiveis() + "\n";

        for (Ingresso ingresso : ingressos) {
            if (ingresso.getParticipante() != null) {
                resultado += ingresso.toString() + "; ";
            }
        }

        return resultado;
    }
}
