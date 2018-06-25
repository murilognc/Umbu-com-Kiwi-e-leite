package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Mensagem;
import model.ProximoSalto;

public class Roteador {
	
	private int portaExecucao;
	private byte[] msg;
	private DatagramSocket socketReceptor = null;
	private TabelaRoteamento tabela;
        int ttl = 5;
        String log = "";
	public Roteador(String comando) {
		String[] partes = comando.split(" ");
		portaExecucao = Integer.parseInt(partes[0]);
		tabela = new TabelaRoteamento(partes.length -1);
		for(int a = 1; a< partes.length;a++) {
			tabela.adicionarLinhaDeComando(partes[a], a-1);
		}
		executar();
	}
	
	public static void main (String[]args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String comando;
		comando = buffer.readLine();
		comando = comando.replaceAll("   ", " ");
		comando = comando.replaceAll("  ", " ");
		Roteador novoRoteador = new Roteador(comando);
	}
	
	public void executar() {
		byte[] mensagemTransformada = new byte[256];
		DatagramPacket pacote = new DatagramPacket(mensagemTransformada, mensagemTransformada.length);
		
        try {
            socketReceptor = new DatagramSocket(portaExecucao);
        } catch (SocketException ex) {

            Logger.getLogger(Roteador.class.getName()).log(Level.SEVERE, null, ex);
        }
		
        try {
            socketReceptor.receive(pacote);
        } catch (IOException ex) {
            socketReceptor.close();
            Logger.getLogger(Roteador.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] informacoesMensagem = lerPacote(pacote);
        String ipDestinoFinal = informacoesMensagem[0];
        String ipProxRoteador;
        String ipOrigem = informacoesMensagem[2];
        String mensagem = informacoesMensagem[3];
        if(informacoesMensagem[4] != null){
        ttl = Integer.valueOf(informacoesMensagem[4]);
        ttl--;
        }
        ProximoSalto proximoRoteador = tabela.verificarIP(informacoesMensagem[0]);
        if(ttl > 0){
        if (proximoRoteador == null) {
            Mensagem.destinoInexistente(ipDestinoFinal);
            log += "destination" + ipDestinoFinal + " not found in routing table, dropping packet \n";
        } else if (proximoRoteador.getIpProxRoteador().equals("0.0.0.0")) {
            Mensagem.encaminharDiretamente(informacoesMensagem);
            log += "destination " + informacoesMensagem + " not found in routing table, dropping packet \n" ;
        } else {
            ipProxRoteador = proximoRoteador.getIpProxRoteador();
            int portaProxRoteador = proximoRoteador.getPortaProxRoteador();
            mensagem = ipDestinoFinal + " " + ipProxRoteador + " " + ipOrigem + " " + mensagem + ttl;
            enviarPacote(mensagem, ipProxRoteador, portaProxRoteador);
            Mensagem.encaminharPacote(ipDestinoFinal, ipProxRoteador, portaProxRoteador);
            log += "destination reached. From " + ipOrigem + " to " +
                ipDestinoFinal + " : " + mensagem + "\n";
        }
        }else{
            Mensagem.ttlExpirado(ipDestinoFinal);
            log += "Time to Live exceeded in Transit, dropping packet for: " + ipDestinoFinal + "\n";
        }
        socketReceptor.close();    
	}
	
	private void enviarPacote(String mensagem, String ipRoteador, int portaProxRoteador) {
        InetAddress enderecoRoteador = null;
        byte[] mensagemByteficada;
        DatagramPacket pacote;
        DatagramSocket socketEnviador = null;
        try {
            enderecoRoteador = InetAddress.getByName(ipRoteador);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Roteador.class.getName()).log(Level.SEVERE, null, ex);
        }
        mensagemByteficada = mensagem.getBytes();
        pacote = new DatagramPacket(mensagemByteficada, mensagemByteficada.length, enderecoRoteador, portaProxRoteador);
        try {
            socketEnviador = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Roteador.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socketEnviador.send(pacote);
        } catch (IOException ex) {
            Logger.getLogger(Roteador.class.getName()).log(Level.SEVERE, null, ex);
        }
        socketEnviador.close();
	}
	
	private String[] lerPacote(DatagramPacket pacote) {
        String informacoesPacote = new String(pacote.getData()).trim();
        String[] informacoesQuebradas = informacoesPacote.split(" ", 4);
        return informacoesQuebradas;
	}

}
