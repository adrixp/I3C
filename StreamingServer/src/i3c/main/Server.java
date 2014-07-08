package i3c.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	
	private DatagramSocket aSocket;
	
	private static int usage(String[] args, int port) {
		if(args.length == 1){
			try{
				return Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				System.err.println("No has introducido un numero valido");
				System.exit(1);
			}
		}
		return port;
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public void start(int port){
		
       try {

           // Crea un socket para datagramas y le asocia al puerto.
           aSocket = new DatagramSocket(port);

           // Creamos un buffer para entrada paquetes de un fotograma.
//           byte [] buffer = new byte[1000];
           byte [] buffer = hexStringToByteArray("ff");
//           // Genera un datagrama para mensages de longitud indicada.
//           DatagramPacket request = new DatagramPacket(buffer, buffer.length);
//
//           while (true){
//
//               // Esperamos a recibir algun paquete de un cliente.
//               aSocket.receive(request);
//
//              //mostramos la imagen
           		System.out.print("bytes: ");
               
           		System.out.println(Byte.valueOf(buffer[0]));

//           }

       } catch (SocketException ex) {

               Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

       } catch (IOException ex) {

               Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

       }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 14000;

		port = usage(args, port);
		
		Server s = new Server();		
		s.start(port);
	}

}
