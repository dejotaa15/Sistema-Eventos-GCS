import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Participante p1 = new Participante("Rafaella Cunha", "11112312300");
        System.out.println("=== Sistema de Gerenciamento de Eventos ===");
        System.out.println("1. Listar eventos");
        System.out.println("2. Cadastrar evento");
        System.out.println("3. Procurar evento");
        System.out.println("4. Emitir ingresso");
        System.out.println("5. Registrar presença");
        System.out.println("6. Relatório mensal");
        System.out.println("7. Sair");

        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("Show de Rock", "15/12/2025", 100.0, "João", 100));
        eventos.add(new Evento("Palestra de Tecnologia", "20/01/2026", 50.0, "Maria", 200));
        eventos.add(new Evento("Feira de Ciências", "05/03/2026", 30.0, "Carlos", 150));



        Ingresso ing1 = eventos.get(0).comprarIngresso(p1);

        System.out.println("\nEventos já cadastrados:");
        for (Evento e : eventos) {
            System.out.println(e);
        }
    }
}