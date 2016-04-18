package ejemplo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private ServerSocket socketServidor;
	private DataInputStream entrada;
	private DataOutputStream salida;
	public Servidor(){
		try {
			socketServidor = new ServerSocket(1234);
			System.out.println("Esperando clientes");
			Socket socketCliente = socketServidor.accept();
			System.out.println("Se conecto un cliente" + socketCliente.getInetAddress().toString());
			
			salida =new DataOutputStream(socketCliente.getOutputStream());
			salida.writeUTF("Hola Cristhian, que ondas");
			
			entrada = new DataInputStream(socketCliente.getInputStream());
			System.out.println("Cristhian dice: " + entrada.readUTF());
			socketServidor.close();
			socketCliente.close();
			entrada.close();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Servidor();
	}

}
