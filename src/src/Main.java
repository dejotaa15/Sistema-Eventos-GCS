import java.util.ArrayList;
import java.util.Iterator;
public class Main {
   
    public static void main(String[] args) {
         GerenciadorEventos eventos = new GerenciadorEventos();

        System.out.println("=== Sistema de Gerenciamento de Eventos ===");
        System.out.println("1. Listar eventos");
        System.out.println("2. Cadastrar evento");
        System.out.println("3. Procurar evento");
        System.out.println("4. Emitir ingresso");
        System.out.println("5. Registrar presença");
        System.out.println("6. Relatório mensal");
        System.out.println("7. Sair");

        eventos.carregarDados();
        

        System.out.println("\nEventos já cadastrados:");
        eventos.listarEventos();
    }
}