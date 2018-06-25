package model;

public class ProximoSalto {

	private String ipProxRoteador;
	private int portaProxRoteador;
        private int ttl = 5;
	
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
        
        public int getTtl(){
            return ttl;
        }
        
        public void setTtl(int ttl){
            this.ttl = ttl;
        }
	
}
