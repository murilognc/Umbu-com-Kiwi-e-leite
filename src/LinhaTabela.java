
public class LinhaTabela {
	private EnderecoIP ipRoteadorDestino;
	private MascaraRede mascara;
	private EnderecoIP proximoSalto;
	private int portaSaidaInterface;
	
	public LinhaTabela() {
		ipRoteadorDestino = new EnderecoIP();
		mascara = new MascaraRede();
		proximoSalto = new EnderecoIP();
	    portaSaidaInterface = 0;
	}

	public EnderecoIP getIpRoteadorDestino() {
		return ipRoteadorDestino;
	}

	public MascaraRede getMascara() {
		return mascara;
	}

	public EnderecoIP getProximoSalto() {
		return proximoSalto;
	}

	public int getPortaSaidaInterface() {
		return portaSaidaInterface;
	}
	
	public int getTamanhoMascara() {
        return mascara.getTamanhoMascara();
	}
	
	public byte getByteRoteadorDestino(int posicao) {
        return ipRoteadorDestino.getBytePosicao(posicao);
    }
    
    public byte getByteMascaraDestino(int posicao) {
        return mascara.getBytePosicao(posicao);
    }

	public void setIpRoteadorDestino(String ipRoteadorDestino) {
        this.ipRoteadorDestino.adicionarEndereco(ipRoteadorDestino);;
    }

    public void setMascara(String mascara) {
        this.mascara.adicionarEndereco(mascara);
    }

    public void setProximoSalto(String proximoSalto) {
        this.proximoSalto.adicionarEndereco(proximoSalto);
    }

    public void setPortaSaidaInterface(String portaSaidaInterface) {
        this.portaSaidaInterface = Integer.parseInt(portaSaidaInterface);
    }
	
	
	
}
