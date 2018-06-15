public class EnderecoIP {
	
	protected byte[] bytes = new byte[4];
	protected String enderecoString = "";
	
	public void adicionarEndereco(String endereco) {
		if(endereco.contains(".")) {
			String[] enderecoQuebrado = endereco.split("\\.");
			for(int a = 0; a<bytes.length; a++) {
				int byteConvertido = Integer.valueOf(enderecoQuebrado[a]);
				bytes[a] = (byte) byteConvertido;
			}
			enderecoString = endereco;
		}
	}

	public byte[] getBytes() {
		return bytes;
	}

	public String getEnderecoString() {
		return enderecoString;
	}

	protected void adicionarEndereco(String[] mascaraBinariaRepartida) {
		for(int a=0; a<4; a++) {
			bytes[a] = Byte.parseByte(mascaraBinariaRepartida[a], 2);
		}
	}

	public byte getBytePosicao(int posicao) {
        return bytes[posicao];
	}
	
	
}
