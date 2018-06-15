package model;

public class ProximoSalto {

	private String ipProxRoteador;
	private int portaProxRoteador;
	
	public ProximoSalto(String ipProxRoteador, int portaProxRoteador) {
		this.ipProxRoteador = ipProxRoteador;
		this.portaProxRoteador = portaProxRoteador;
	}
	
	public String getIpProxRoteador() {
		return ipProxRoteador;
	}
	
	public int getPortaProxRoteador() {
        return portaProxRoteador;
	}
	
}
