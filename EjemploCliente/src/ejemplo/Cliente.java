package ejemplo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private Socket socketCliente;
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	public Cliente(){
		try {
			socketCliente = new Socket("127.0.0.1", 1234);
			System.out.println("Se conecto con un servidor");
			salida = new DataOutputStream(socketCliente.getOutputStream());
			salida.writeUTF("Hola Servidor");
			
			entrada = new DataInputStream(socketCliente.getInputStream());
			System.out.println("Servidor Dice: " + entrada.readUTF());
			
			socketCliente.close();
			salida.close();
			entrada.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Cliente();
	}

}
