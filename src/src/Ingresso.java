public class Ingresso {
    private String codigoIngresso;
    private Participante participante;
    private boolean presente;

    public Ingresso(String codigoIngresso, Participante participante) {
        this.codigoIngresso = codigoIngresso;
        this.participante = participante;
        this.presente = false;
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

    @Override
    public String toString() {
        return "Ingresso: " + codigoIngresso + " | " + participante + " | Presente: " + presente;
    }
}