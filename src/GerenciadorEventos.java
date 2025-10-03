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

    public Evento procurarEvento(String termoBusca) {
        if (listaEventos.isEmpty()) {
            return null;
        }

        List<Evento> eventosEncontrados = new ArrayList<>();
        String termoLowerCase = termoBusca.toLowerCase();

        for (Evento evento : listaEventos) {
            if (evento.getNomeEvento().toLowerCase().contains(termoLowerCase)) {
                eventosEncontrados.add(evento);
            }
        }

        if (eventosEncontrados.size() == 1) {
            return eventosEncontrados.get(0);
        } else if (eventosEncontrados.size() > 1) {
            System.out.println("Muitos eventos encontrados:");
            for (int i = 0; i < eventosEncontrados.size(); i++) {
                System.out.println(" [" + (i + 1) + "] " + eventosEncontrados.get(i));
            }
            return null;
        } else {
            return null;
        }


    }

    

}