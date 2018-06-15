
public class MascaraRede extends EnderecoIP{
	private int tamanhoMascara = 0;
	
	@Override
	public void adicionarEndereco(String endereco) {
		if(endereco.contains(".")) {
			String[] enderecoQuebrado = endereco.split("\\.");
			for(int a=0; a<bytes.length;a++) {
				int byteConvertido = Integer.valueOf(enderecoQuebrado[a]);
				bytes[a] = (byte) byteConvertido;
			}
			enderecoString = endereco;
		} else {
			criarMascara(endereco);
		}
	}
	
	private void adicionarTamanhoMascara(int byteConvertido) {
        switch (byteConvertido) {
            case 128:
                tamanhoMascara += 1;
                break;
            case 192:
                tamanhoMascara += 2;
                break;
            case 224:
                tamanhoMascara += 3;
                break;
            case 240:
                tamanhoMascara += 4;
                break;
            case 248:
                tamanhoMascara += 5;
                break;
            case 252:
                tamanhoMascara += 6;
                break;
            case 254:
                tamanhoMascara += 7;
                break;
            case 255:
                tamanhoMascara += 8;
                break;
            default:
                break;
        }

	}
	
	public int getTamanhoMascara() {
		return tamanhoMascara;
	}
	
	private void criarMascara(String endereco) {
		int valorMascara = Integer.parseInt(endereco);
		tamanhoMascara = valorMascara;
		String mascaraBinaria = criarMascaraBinaria(valorMascara);
		String[] mascaraBinariaRepartida = quebrarMascaraBinaria(mascaraBinaria);
		adicionarEndereco(mascaraBinariaRepartida);
	}
	
	private String criarMascaraBinaria(int valorMascara) {
		int indice = 0;
		String mascaraBinaria ="";
		for(indice = 0; indice<valorMascara; indice++) {
			mascaraBinaria+= "1";
		}
		while(indice < 32) {
			mascaraBinaria += "0";
			indice++;
		}
		return mascaraBinaria;
	}
	
	private String[] quebrarMascaraBinaria(String mascaraBinaria) {
		String[] mascaraBinariaRepartida = new String[4];
		int indice = 0;
		int posicaoInicial = 0;
		int posicaoFinal = 8;
		for(indice = 0; indice<4; indice++) {
			mascaraBinariaRepartida[indice] = mascaraBinaria.substring(posicaoInicial, posicaoFinal);
			posicaoInicial = posicaoFinal;
			posicaoFinal +=8;
		}
		return mascaraBinariaRepartida;
	}
	
	
}
