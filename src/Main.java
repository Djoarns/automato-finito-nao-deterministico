import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Defina o autômato não determinístico
        Automato automato = Automato.criarAutomato();

        // Solicita a entrada da sentença
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe uma palavra:");
        String palavra = scanner.next();

        // Configura a sentença no autômato
        automato.setSentenca(palavra);

        // Verifica se a sentença é aceita pelo autômato
        boolean aceita = automato.verificaSetenca();

        // Imprime o resultado
        if (aceita) {
            System.out.println("A sentença foi aceita pelo autômato!");
        } else {
            System.out.println("A sentença não foi aceita pelo autômato!");
        }
    }
}