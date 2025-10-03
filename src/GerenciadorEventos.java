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
    public void gerarRelatorioMensal(int mes, int ano) {
        boolean encontrou = false;
        for (Evento evento : listaEventos) {
            String[] partesData = evento.getDataEvento().split("/");
            int eventoMes = Integer.parseInt(partesData[1]);
            int eventoAno = Integer.parseInt(partesData[2]);
            if (eventoMes == mes && eventoAno == ano) {
                encontrou = true;
                System.out.println("Evento: " + evento.getNomeEvento());
                System.out.println("Data: " + evento.getDataEvento());
                System.out.println("Ingressos totais: " + evento.getTotalIngressos());
                System.out.println("Ingressos vendidos: " + (evento.getTotalIngressos() - evento.ingressosDisponiveis()));
                System.out.println("Ingressos disponíveis: " + evento.ingressosDisponiveis());
                System.out.println("-----------------------------");
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum evento encontrado para o mês/ano informado.");
        }
    }



}