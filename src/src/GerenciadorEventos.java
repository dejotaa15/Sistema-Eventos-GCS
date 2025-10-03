import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GerenciadorEventos {
    private List<Evento> eventos;

    public GerenciadorEventos() {
        this.eventos = new ArrayList<>();
    }

    public void adicionarEvento(Evento evento) {
        this.eventos.add(evento);
    }
    
    public boolean excluirEvento(int codigoEvento) {
        Iterator<Evento> iterator = eventos.iterator();
        while (iterator.hasNext()) {
            Evento evento = iterator.next();
            
            if (evento.getCodigoEvento() == codigoEvento) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
      public Evento buscarEventoPorCodigo(int codigoEvento) {
        for (Evento evento : eventos) {
            if (evento.getCodigoEvento() == codigoEvento) {
                return evento;
            }
        }
        return null;
    }
    public Ingresso emitirIngresso(int codigoEvento, String nomeParticipante, String cpfParticipante, boolean isEspecial) {
    
    // 1. Encontra o evento
    Evento evento = buscarEventoPorCodigo(codigoEvento); // Você precisará criar este método 'buscarEventoPorCodigo'
    if (evento == null) {
        System.err.println("ERRO: Evento com código " + codigoEvento + " não encontrado.");
        return null;
    }
      if (evento.getProximaSequencia() > evento.getTotalIngressos()) {
            System.err.println("ERRO: Limite total de ingressos esgotado (" + evento.getTotalIngressos() + ").");
            return null;
        }

    // 2. Valida o Limite de Ingressos (Requisito 5c)
     int limiteEspeciais = (int)(evento.getTotalIngressos() * 0.1); // 10% do total
        if (isEspecial && evento.getIngressosEspeciaisVendidos() >= limiteEspeciais) {
            System.err.println("ERRO: Limite de ingressos especiais esgotado (" + limiteEspeciais + ").");
            return null;
        }

    Participante participante = new Participante(nomeParticipante, cpfParticipante);
    
 
    int sequencia = evento.getProximaSequencia();
    String sufixo = isEspecial ? "E" : "";

    String seqFormatada = String.format("%03d", sequencia);
    
    String codigoIngresso = evento.getCodigoEvento() + "-" + seqFormatada + sufixo;

    // 5. Finaliza e Adiciona ao Evento
    Ingresso novoIngresso = new Ingresso(codigoIngresso, participante);
    evento.adicionarIngresso(novoIngresso); // Adiciona na lista interna do Evento
    
    System.out.println("Ingresso Emitido com sucesso!");
    System.out.println("Código: " + codigoIngresso + " | Comprador: " + nomeParticipante);
    return novoIngresso;
}
    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }
     public void carregarDados(){
    System.out.println("Carregando dados iniciais do evento..");
        eventos.add(new Evento("Show de Rock", "15/12/2025", 100.0, "João", 100));
        eventos.add(new Evento("Palestra de Tecnologia", "20/01/2026", 50.0, "Maria", 200));
        eventos.add(new Evento("Feira de Ciências", "05/03/2026", 30.0, "Carlos", 150));

    }
}