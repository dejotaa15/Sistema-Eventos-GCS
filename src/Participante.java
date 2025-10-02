public class Participante {
    private String nomeCompleto;
    private String cpf;

    public Participante(String nomeCompleto, String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF INVALIDO");
        }
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    public String getNomeCompleto() {

        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {

        return nomeCompleto + " (CPF: " + cpf + ")";
    }
}