package controller;
import java.util.ArrayList;

import model.EnderecoIP;
import model.LinhaTabela;
import model.ProximoSalto;

public class TabelaRoteamento {

	private LinhaTabela[] linhasTabela;
	private int quantasEntradas;
	
	public TabelaRoteamento(int quantasEntradas) {
		linhasTabela = new LinhaTabela[quantasEntradas];
		for(int a=0; a < quantasEntradas; a++) {
			linhasTabela[a] = new LinhaTabela();
		}
		this.quantasEntradas = quantasEntradas;
	}
	
	public ProximoSalto verificarIP(String ipDestinoChegado) {
		EnderecoIP enderecoDestino = new EnderecoIP();
		ArrayList<LinhaTabela> possiveisEnderecos = new ArrayList();
		enderecoDestino.adicionarEndereco(ipDestinoChegado);
		ProximoSalto proxRoteador = null;
		int posicaoByte = 0;
		for(int numeroRoteador =0; numeroRoteador < quantasEntradas; numeroRoteador++) {
			for(posicaoByte=0;posicaoByte<4;posicaoByte++) {
				byte byteRoteadorDestino = linhasTabela[numeroRoteador].getByteRoteadorDestino(posicaoByte);
				byte byteMascara = linhasTabela[numeroRoteador].getByteMascaraDestino(posicaoByte);
                byte byteEnderecoDestino = enderecoDestino.getBytePosicao(posicaoByte);

                byte operacao1 = (byte) (byteRoteadorDestino & byteMascara);
                byte operacao2 = (byte) (byteEnderecoDestino & byteMascara);

                if (operacao1 != operacao2) {
                    break;
            	}
        	}
            
            if(posicaoByte == 4){
                //Significa que a mascara de certo
                possiveisEnderecos.add(linhasTabela[numeroRoteador]);
            }
        }
        
		return possiveisEnderecos.size() > 0 ? escolherRoteador(possiveisEnderecos) : null;
	}
	
	public ProximoSalto escolherRoteador(ArrayList<LinhaTabela> possiveisEnderecos) {
		int tamanhoMascara = 0;
		LinhaTabela linhaEscolhida = null;
		for(LinhaTabela linhaAtual: possiveisEnderecos) {
			if(linhaAtual.getTamanhoMascara() >= tamanhoMascara) {
				tamanhoMascara = linhaAtual.getTamanhoMascara();
				linhaEscolhida = linhaAtual;
			}
		}
		String enderecoDestino = linhaEscolhida.getProximoSalto().getEnderecoString();
		int numeroPorta = linhaEscolhida.getPortaSaidaInterface();
		return new ProximoSalto(enderecoDestino, numeroPorta);
	}
	
	public void adicionarLinhaDeComando(String parte, int numeroLinhaTabela) {
		String[] informacoes = parte.split("/");
		linhasTabela[numeroLinhaTabela].setIpRoteadorDestino(informacoes[0]);
        linhasTabela[numeroLinhaTabela].setMascara(informacoes[1]);
        linhasTabela[numeroLinhaTabela].setProximoSalto(informacoes[2]);
        linhasTabela[numeroLinhaTabela].setPortaSaidaInterface(informacoes[3]);
	}
	
	
	
}
