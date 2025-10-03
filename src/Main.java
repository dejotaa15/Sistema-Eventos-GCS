import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Participante p1 = new Participante("Rafaella Cunha", "11112312300");
        Participante p2 = new Participante("Alo", "11444312300");
        Participante p3 = new Participante("Borga", "99912312300");

        GerenciadorEventos gerenciadorEventos = new GerenciadorEventos();
        Scanner scanner = new Scanner(System.in);

        gerenciadorEventos.adicionarEvento(new Evento("Show de Rock", "15/12/2025", 100.0, "João", 5));
        gerenciadorEventos.adicionarEvento(new Evento("Palestra de Tecnologia", "20/12/2025", 50.0, "Maria", 200));
        gerenciadorEventos.adicionarEvento(new Evento("Feira de Ciências", "05/03/2026", 30.0, "Carlos", 150));


        Evento evento = gerenciadorEventos.procurarEvento("Show");
        if (evento != null) {
            Ingresso ing1 = evento.comprarIngresso(p1, false);
            Ingresso ing2 = evento.comprarIngresso(p2, false);
            Ingresso ing3 = evento.comprarIngresso(p3, false);
            Ingresso ing4 = evento.comprarIngresso(p2, true);
        }

        if (evento != null) {
            evento.registrarPresenca("101-003");
        }

        int opcao = -1;
        do {
            System.out.println("\n=== Sistema de Gerenciamento de Eventos ===");
            System.out.println("1. Listar eventos");
            System.out.println("2. Cadastrar evento");
            System.out.println("3. Procurar evento");
            System.out.println("4. Emitir ingresso");
            System.out.println("5. Registrar presença");
            System.out.println("6. Relatório mensal");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    gerenciadorEventos.listarEventos();
                    break;
                case 2:
                    gerenciadorEventos.cadastrarNovoEvento(scanner);
                    break;
                case 3:
                    System.out.print("Digite o termo de busca: ");
                    String termo = scanner.nextLine();
                    gerenciadorEventos.procurarEvento(termo);
                    break;
                case 6:
                    System.out.print("Digite o mês (MM): ");
                    int mes = scanner.nextInt();
                    System.out.print("Digite o ano (AAAA): ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    gerenciadorEventos.gerarRelatorioMensal(mes, ano);
                    break;
                case 7:
                    System.out.println("Sistema encerrado.");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção não reconhecida.");
            }

        } while (opcao != 7);
    }
}