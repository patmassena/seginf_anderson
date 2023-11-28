package dto;

public class TriglicerideoDto {
	private String tipoMetrica;
	private String resultado;
	public TriglicerideoDto(String tipoMetrica, String resultado) {
		this.tipoMetrica = tipoMetrica;
		this.resultado = resultado;
	}
	public String getTipoMetrica() {
		return tipoMetrica;
	}
	public String getResultado() {
		return resultado;
	}
}
