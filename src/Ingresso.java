public class Ingresso {
    private String codigoIngresso;
    private Participante participante;
    private boolean presente;
    private boolean ingressoEspecial;

    public Ingresso(String codigoIngresso, Participante participante, boolean ingressoEspecial) {
        this.codigoIngresso = codigoIngresso;
        this.participante = participante;
        this.presente = false;
        this.ingressoEspecial = ingressoEspecial;
    }

    public String getCodigoIngresso() {
        return codigoIngresso;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public boolean isPresente() {
        return presente;
    }

    public void registrarPresenca() {
        this.presente = true;
    }

    public boolean isIngressoEspecial() {
        return ingressoEspecial;
    }

    @Override
    public String toString() {
        return "Ingresso: " + codigoIngresso + " | " +
                (participante != null ? participante.toString() : "Disponível") +
                " | Presente: " + presente;
    }
}
