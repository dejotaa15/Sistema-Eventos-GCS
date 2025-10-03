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