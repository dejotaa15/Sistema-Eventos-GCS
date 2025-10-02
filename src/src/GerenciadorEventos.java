import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GerenciadorEventos {
    private List<Evento> listaEventos;

    public GerenciadorEventos() {
        this.listaEventos = new ArrayList<>();
    }

    public void adicionarEvento(Evento evento) {
        this.listaEventos.add(evento);
    }
    
    public boolean excluirEvento(int codigoEvento) {
        Iterator<Evento> iterator = listaEventos.iterator();
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
        if (listaEventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (Evento evento : listaEventos) {
            System.out.println(evento);
        }
    }
}