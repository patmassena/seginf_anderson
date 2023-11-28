package teste;

import dto.TriglicerideoDto;
import modelo.Paciente;
import seginf_av3.ChaveSecretaDao;
import seginf_av3.DeCrypt;
import seginf_av3.ExameDao;

public class TestaDeCrypt {

	public static void main(String[] args) throws Exception {
//		1 - ir no banco buscar a chave
		int chaveSecretaId = 1;
		ChaveSecretaDao chaveSecretaDao = new ChaveSecretaDao();
		String chaveSecreta = chaveSecretaDao.recuperaSenhaSecreta(chaveSecretaId);
		
		if(chaveSecreta == null) {
			throw new RuntimeException("Sem chave secreta no BD!");
		}
		
		DeCrypt decrypt = new DeCrypt(chaveSecreta);
		
		ExameDao exameDao = new ExameDao(decrypt);
		
		Paciente p = new Paciente();
		p.setId(1);
		
//		Teste insercao
//		TriglicerideoDto triglicerideoDto = new TriglicerideoDto("mg/dL", "100");
//		exameDao.insereTriglicerídeo(triglicerideoDto, p);
		
		int exameId = 1;
		TriglicerideoDto triglicerideoDto = exameDao.recuperaExame(exameId);
		System.out.println("resultado do exame: " + triglicerideoDto.getResultado());
		System.out.println("Metrica: " + triglicerideoDto.getTipoMetrica());
		
	}

}
