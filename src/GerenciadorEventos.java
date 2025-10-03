import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Evento buscarEventoPorCodigo(int codigoEvento) {
        for (Evento evento : listaEventos) {
            if (evento.getCodigoEvento() == codigoEvento) {
                return evento;
            }
        }
        return null;
    }

    public void cadastrarNovoEvento(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE NOVO EVENTO (Item 2) ---");

        System.out.print("Nome do Evento (Obrigatório): ");
        String nome = scanner.nextLine();

        System.out.print("Data (Formato dd/MM/yyyy - Obrigatório): ");
        String dataStr = scanner.nextLine();

        System.out.print("Valor do Ingresso (Obrigatório, > 0): R$ ");
        String valorStr = scanner.nextLine();

        System.out.print("Nome do Responsável (Obrigatório): ");
        String responsavel = scanner.nextLine();

        System.out.print("Lotação Máxima (Obrigatório, > 0): ");
        String lotacaoStr = scanner.nextLine();

        if (nome.trim().isEmpty() || dataStr.trim().isEmpty() ||
                valorStr.trim().isEmpty() || responsavel.trim().isEmpty() || lotacaoStr.trim().isEmpty()) {
            System.out.println("ERRO: Todos os campos são obrigatórios. Cadastro cancelado.");
            return;
        }

        double valor;
        int lotacao;
        try {
            valor = Double.parseDouble(valorStr);
            lotacao = Integer.parseInt(lotacaoStr);
            if (valor <= 0 || lotacao <= 0) {
                System.out.println("ERRO: Valor do ingresso e Lotação devem ser positivos. Cadastro cancelado.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Valor ou Lotação fornecidos não são números válidos. Cadastro cancelado.");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataEvento = LocalDate.parse(dataStr, formatter);
            LocalDate hoje = LocalDate.now();

            if (dataEvento.isBefore(hoje) || dataEvento.isEqual(hoje)) {
                System.out.println("ERRO: A data de realização deve ser FUTURA. Cadastro cancelado.");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERRO: Formato de data inválido. Use o padrão dd/MM/yyyy. Cadastro cancelado.");
            return;
        }

        Evento novoEvento = new Evento(nome, dataStr, valor, responsavel, lotacao);
        this.listaEventos.add(novoEvento);

        System.out.println("SUCESSO! Evento cadastrado. Código: " + novoEvento.getCodigoEvento());
    }

    public void gerarRelatorioMensal(int mes, int ano) {
        boolean encontrou = false;
        for (Evento evento : listaEventos) {
            if (evento.isNoMesEAno(mes, ano)) {
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

    public void atualizarDadosParticipante(Scanner scanner) {
        System.out.println("\n--- ATUALIZAR DADOS DO PARTICIPANTE (Item 9) ---");
        System.out.print("Digite o código do ingresso (ex: 101-003) para editar: ");
        String codigoIngresso = scanner.nextLine();

        try {
            int codEvento = Integer.parseInt(codigoIngresso.split("-")[0]);
            Evento evento = buscarEventoPorCodigo(codEvento);

            if (evento == null) {
                System.out.println("ERRO: Evento não encontrado.");
                return;
            }

            Ingresso ingresso = evento.buscarIngressoPorCodigo(codigoIngresso);

            if (ingresso == null || ingresso.getParticipante() == null) {
                System.out.println("ERRO: Ingresso não encontrado ou não foi emitido.");
                return;
            }

            if (ingresso.isPresente()) {
                System.out.println("ERRO: Não é possível editar. O participante JÁ REGISTROU PRESENÇA.");
                return;
            }

            Participante p = ingresso.getParticipante();
            System.out.println("Participante atual: " + p.getNomeCompleto() + ", CPF: " + p.getCpf());

            System.out.print("Novo Nome Completo (Obrigatório, ou ENTER para manter): ");
            String novoNome = scanner.nextLine();

            System.out.print("Novo CPF (11 dígitos, ou ENTER para manter): ");
            String novoCpf = scanner.nextLine();

            String nomeFinal = novoNome.trim().isEmpty() ? p.getNomeCompleto() : novoNome;
            String cpfFinal = novoCpf.trim().isEmpty() ? p.getCpf() : novoCpf;

            try {
                p.setCpf(cpfFinal);
                p.setNomeCompleto(nomeFinal);
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO: " + e.getMessage() + ". Edição não realizada.");
                return;
            }

            System.out.println("SUCESSO! Dados do ingresso " + codigoIngresso + " atualizados.");
            System.out.println("Novo Nome: " + p.getNomeCompleto());

        } catch (Exception e) {
            System.out.println("ERRO: Ocorreu um erro ao processar a edição. Verifique o código do ingresso.");
        }
    }
}