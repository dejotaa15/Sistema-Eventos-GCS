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
    
  
    ArrayList<Ingresso> ingressos = new ArrayList<>();
    private String statusEvento;

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
        this.statusEvento = "Rascunho";
    }
    public String getStatusEvento() {
        return statusEvento;
    }
    public void setStatusEvento(String novoStatus) {
        this.statusEvento = novoStatus;
    }

    private static String formatarNumero(int numero) {
        if (numero < 10) {
            return "00" + numero;
        } else if (numero < 100) {
            return "0" + numero;
        }
        else {
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

    public int ingressosVendidos() {
        return totalIngressos - ingressosDisponiveis();
    }

    public int getIngressosVendidos(boolean especial) {
        int count = 0;
        for(Ingresso i : ingressos) {
            if(i.getParticipante() != null && i.isIngressoEspecial() == especial) {
                count++;
            }
        }
        return count;
    }

    public Ingresso buscarIngressoPorCodigo(String codigo) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getCodigoIngresso().equals(codigo)) {
                return ingresso;
            }
        }
        return null;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public boolean isNoMesEAno(int mes, int ano) {
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate data = java.time.LocalDate.parse(dataEvento, formatter);
            return data.getMonthValue() == mes && data.getYear() == ano;
        } catch (Exception e) {
            return false;
        }
    }

    public int getCodigoEvento() { return codigoEvento; }
    public String getNomeEvento() { return nomeEvento; }
    public String getDataEvento() { return dataEvento; }
    public double getValorIngresso() { return valorIngresso; }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public int getTotalIngressos() { return totalIngressos; }

  
    public int getIngressosVendidosNormais() {
        int count = 0;
        for (Ingresso ingresso : ingressos) {
            if (!ingresso.isIngressoEspecial() && ingresso.getParticipante() != null) {
                count++;
            }
        }
        return count;
    }

    public int getIngressosVendidosEspeciais() {
        int count = 0;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.isIngressoEspecial() && ingresso.getParticipante() != null) {
                count++;
            }
        }
        return count;
    }

    public double getPercentualIngressosVendidosNormais() {
        if (ingressosNormais == 0) return 0;
        return (getIngressosVendidosNormais() * 100.0) / ingressosNormais;
    }

    public double getPercentualIngressosVendidosEspeciais() {
        if (ingressosEspeciais == 0) return 0;
        return (getIngressosVendidosEspeciais() * 100.0) / ingressosEspeciais;
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
