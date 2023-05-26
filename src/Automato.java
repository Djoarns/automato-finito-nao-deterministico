import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Automato {
    private String sentenca;
    private final String estadoInicial;
    private final List<String> estadosFinais;
    private final List<Transicao> listaTransicao;

    public Automato(String sentenca, String estadoInicial, List<String> estadosFinais, List<Transicao> listaTransicao) {
        this.sentenca = sentenca;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.listaTransicao = listaTransicao;
    }

    public String getSentenca() {
        return sentenca;
    }

    public void setSentenca(String sentenca) {
        this.sentenca = sentenca;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public List<String> getEstadosFinais() {
        return estadosFinais;
    }

    public List<Transicao> getListaTransicao() {
        return listaTransicao;
    }

    public Boolean verificaSetenca() {
        Set<String> estadosAtuais = new HashSet<>();
        estadosAtuais.add(estadoInicial);

        for (int i = 0; i < sentenca.length(); i++) {
            char letra = sentenca.charAt(i);

            Set<String> proximosEstados = new HashSet<>();

            for (String estadoAtual : estadosAtuais) {
                List<String> estadosDestino = retornaEstadosDestino(estadoAtual, letra);
                proximosEstados.addAll(estadosDestino);
            }

            estadosAtuais = proximosEstados;

            if (estadosAtuais.isEmpty()) {
                return false;
            }
        }

        for (String estadoAtual : estadosAtuais) {
            if (isFinal(estadoAtual)) {
                return true;
            }
        }

        return false;
    }

    private List<String> retornaEstadosDestino(String origem, char letraEntrada) {
        List<String> estadosDestino = new ArrayList<>();

        for (Transicao transicao : listaTransicao) {
            if (transicao.estadoOrigem().equals(origem) && transicao.letraSetenca() == letraEntrada) {
                estadosDestino.addAll(transicao.estadosDestino());
            }
        }

        return estadosDestino;
    }

    private Boolean isFinal(String estado) {
        return estadosFinais.contains(estado);
    }

    public static Automato criarAutomato() {
        // Defina os estados, transições e estados finais do autômato
        String estadoInicial = "q1";
        List<String> estadosFinais = List.of("q4");

        List<Transicao> listaTransicao = new ArrayList<>();
        listaTransicao.add(new Transicao("q1", '0', List.of("q1")));
        listaTransicao.add(new Transicao("q1", '1', List.of("q1", "q2")));
        listaTransicao.add(new Transicao("q2", '0', List.of("q3")));
        listaTransicao.add(new Transicao("q3", '1', List.of("q4")));
        listaTransicao.add(new Transicao("q4", '0', List.of("q4")));
        listaTransicao.add(new Transicao("q4", '1', List.of("q4")));

//        listaTransicao.add(new Transicao("q0", '0', List.of("q1", "q2")));
//        listaTransicao.add(new Transicao("q0", '1', List.of("q0")));
//        listaTransicao.add(new Transicao("q1", '0', List.of("q1")));
//        listaTransicao.add(new Transicao("q1", '1', List.of("q2")));

        // Crie e retorne o autômato
        return new Automato(null, estadoInicial, estadosFinais, listaTransicao);
    }
}