public class Participante {
    private String nomeCompleto;
    private String cpf;

    public Participante(String nomeCompleto, String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF INVALIDO");
        }

        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("NOME COMPLETO é obrigatório.");
        }

        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    public void setNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("NOME COMPLETO não pode ser vazio.");
        }
        this.nomeCompleto = nomeCompleto;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("NOVO CPF INVALIDO");
        }
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