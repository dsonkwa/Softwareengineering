package Verteiltes_System;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class CRUDMultiClientTCPServer {

	//public static void main(String[] args) {
	public void start(){
		int cnt =0;
		try(ServerSocket ss =new ServerSocket(4080)){
			SocketAddress socketAddress =ss.getLocalSocketAddress();
			System.out.println("Warte auf Verbindungen auf: "+ socketAddress);
			while(true){
				Socket socket =ss.accept();
				(new TCPClientThread(++cnt,socket)).start();
			}
		}catch(IOException e){
			System.err.println(e.toString());
			System.exit(1);
		}
		finally{
			System.out.println("Verbindungen auf Port 7 ist abgebaut ");
		}
	}

}

//}

class TCPClientThread extends Thread{
	private int name;
	private Socket socket;
	public TCPClientThread(int name,Socket socket){
		this.name= name;
		this.socket=socket;
	}

	public void run(){
		CRUDService service= new ModulContainerObjectAdapter(ModulContainer.getContainer());

		//SocketAddress socketAddress =socket.getRemoteSocketAddress(); 
		//String msg= "CRUDServer: Verbindung " +" name " + " (" + socketAddress + ") ";

		String info ="CRUDServer: Verbindung " + name + "";

		System.out.println(info +" hergestellt ");
		try(
				ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream())){

			while(true){

				out.writeObject(service.handleCRUDRequest((CRUDRequest)in.readObject(), socket));
			}
		}catch(IOException e){

			System.err.println(e.toString());

		}catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		finally{
			try{
				socket.close();
				System.out.println("Verbindung: "+ name + " wird beendet");

			}catch(IOException e){

				System.err.println(e.toString());
			}
		}}}
