package model;
public class Mensagem {

    public static void encaminharPacote(String ipFinal, String roteadorDestino, int portaDestino) {

        System.out.println("forwarding packet for " + ipFinal + " to next hop "
                + roteadorDestino + " over interface " + portaDestino);

    }
    
    public static void destinoInexistente(String ipFinal) {
        System.out.println("destination " + ipFinal + " not found in routing table, dropping packet ");
    }
    
    public static void encaminharDiretamente(String[] informacoesPacote) {
        String ipOrigem = informacoesPacote[2];
        String ipDestino = informacoesPacote[0];
        String mensagem = informacoesPacote[3];
        
        System.out.println("destination reached. From " + ipOrigem + " to " +
                ipDestino + " : " + mensagem);
    }
}