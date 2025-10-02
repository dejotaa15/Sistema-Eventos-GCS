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

    public boolean isPresente() {
        return presente;
    }

    public void registrarPresenca() {
        this.presente = true;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Override
    public String toString() {
        return "Ingresso: " + codigoIngresso + " | " + participante + " | Presente: " + presente;
    }


}