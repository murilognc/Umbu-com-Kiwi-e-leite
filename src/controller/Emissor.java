package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Emissor {
    
    public Emissor(String comando)throws UnknownHostException, SocketException, IOException{
        comando = comando.replaceAll("   ", " ");
        comando = comando.replaceAll("  ", " ");
        String[] entradas = comando.split(" ", 5);  
        String ipProxRoteador = entradas[0];
        int portaRoteador = Integer.parseInt(entradas[1]);
        String ipOrigem = entradas[2];
        String ipDestinoFinal = entradas[3];
        String mensagem = entradas[4]; 
        InetAddress enderecoRoteador;
        byte[] mensagemByteficada;
        DatagramPacket pacote;
        DatagramSocket socketEnviador;   
        enderecoRoteador = InetAddress.getByName(ipProxRoteador);
        mensagem = ipDestinoFinal + " " + ipProxRoteador + " " + ipOrigem + " " + mensagem;
        mensagemByteficada = mensagem.getBytes();
        pacote = new DatagramPacket(mensagemByteficada, mensagemByteficada.length, enderecoRoteador, portaRoteador);
        socketEnviador = new DatagramSocket();
        socketEnviador.send(pacote);
        socketEnviador.close();
    }
    
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entradaLida = br.readLine();
        entradaLida = entradaLida.replaceAll("   ", " ");
        entradaLida = entradaLida.replaceAll("  ", " ");
        String[] entradas = entradaLida.split(" ", 5);  
        String ipProxRoteador = entradas[0];
        int portaRoteador = Integer.parseInt(entradas[1]);
        String ipOrigem = entradas[2];
        String ipDestinoFinal = entradas[3];
        String mensagem = entradas[4]; 
        InetAddress enderecoRoteador;
        byte[] mensagemByteficada;
        DatagramPacket pacote;
        DatagramSocket socketEnviador;   
        enderecoRoteador = InetAddress.getByName(ipProxRoteador);
        mensagem = ipDestinoFinal + " " + ipProxRoteador + " " + ipOrigem + " " + mensagem;
        mensagemByteficada = mensagem.getBytes();
        pacote = new DatagramPacket(mensagemByteficada, mensagemByteficada.length, enderecoRoteador, portaRoteador);
        socketEnviador = new DatagramSocket();
        socketEnviador.send(pacote);
        socketEnviador.close();
    }
}