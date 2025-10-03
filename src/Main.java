import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Participante p1 = new Participante("Rafaella Cunha", "11112312300");
        Participante p2 = new Participante("Alo", "11444312300");
        Participante p3 = new Participante("Borga", "99912312300");

        GerenciadorEventos gerenciadorEventos = new GerenciadorEventos();

        System.out.println("=== Sistema de Gerenciamento de Eventos ===");
        System.out.println("1. Listar eventos");
        System.out.println("2. Cadastrar evento");
        System.out.println("3. Procurar evento");
        System.out.println("4. Emitir ingresso");
        System.out.println("5. Registrar presença");
        System.out.println("6. Relatório mensal");
        System.out.println("7. Sair");

        //ArrayList<Evento> eventos = new ArrayList<>();
        gerenciadorEventos.adicionarEvento(new Evento("Show de Rock", "15/12/2025", 100.0, "João", 5));
        gerenciadorEventos.adicionarEvento(new Evento("Palestra de Tecnologia", "20/12/2025", 50.0, "Maria", 200));
        gerenciadorEventos.adicionarEvento(new Evento("Feira de Ciências", "05/03/2026", 30.0, "Carlos", 150));


        Evento evento = gerenciadorEventos.procurarEvento("Show");
        if (evento != null) {
            Ingresso ing1 = evento.comprarIngresso(p1, false);
            Ingresso ing2 = evento.comprarIngresso(p2, false);
            Ingresso ing3 = evento.comprarIngresso(p2, false);
            Ingresso ing4 = evento.comprarIngresso(p2, true);
        }


        evento.registrarPresenca("101-003");

        System.out.println("\nEventos já cadastrados:");
        gerenciadorEventos.listarEventos();
        gerenciadorEventos.gerarRelatorioMensal(12, 2025);
    }
}