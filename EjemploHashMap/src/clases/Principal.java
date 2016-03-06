package clases;

import java.util.HashMap;

public class Principal {
	//<Indice,TipoDatoAlmacenar>
	private HashMap<String, Producto> personas;
	public Principal(){
		personas = new HashMap<String, Producto>();
		personas.put("Productos", new Producto(5,"Mentolina"));
		personas.put("Producto X", new Producto(5,"Aspirina"));
		personas.put("Producto Y", new Producto(5,"Vick Vaporub"));
		System.out.println(personas.get("Producto X"));
	}
	
	public static void main(String[] args) {
		new Principal();
	}

}
