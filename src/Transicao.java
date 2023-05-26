import java.util.List;

public record Transicao(String estadoOrigem, char letraSetenca, List<String> estadosDestino) {
}