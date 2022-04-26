package Verteiltes_System;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import Verteiltes_System.CRUDRequest.CRUD_COMMAND;
import Verteiltes_System.CRUDResponse.Status;

public class ModulManagementClient {

	@SuppressWarnings("unused")
	private static CRUDResponse sendRequest(CRUDRequest request, ObjectOutputStream out, ObjectInputStream in) 
			throws IOException, ClassNotFoundException {
		out.writeObject(request);
		out.reset();

		return (CRUDResponse) in.readObject();
	}

	public static void checkResponse(CRUDResponse response, CRUDResponse.Status expectedStatus) {
		System.out.println("Expected status: " + expectedStatus + " expectedStatus " + "Received status: "
				+ response.getStatus());
	}

	public static void testCRUDService(ObjectOutputStream out, ObjectInputStream in) {
		try {

			CRUDRequest request= new CRUDRequest(CRUD_COMMAND.READ, "test_m1");
			checkResponse(sendRequest(request, out, in), Status.FAILED);//1
			request= new CRUDRequest(CRUD_COMMAND.DELETE, "test_m1");
			checkResponse(sendRequest(request, out, in), Status.FAILED);//2
			request= new CRUDRequest(CRUD_COMMAND.CREATE, new Modul("test_m1", "test_m1_name "));

			checkResponse(sendRequest(request, out, in), Status.FAILED);//3
			request= new CRUDRequest(CRUD_COMMAND.CREATE, new Modul("test_m2", "test_m2_name "));

			checkResponse(sendRequest(request, out, in), Status.OK);//4
			request= new CRUDRequest(CRUD_COMMAND.READ, "test_m1");
			checkResponse(sendRequest(request, out, in), Status.OK);//5
			request= new CRUDRequest(CRUD_COMMAND.READ_AND_LOCK_EXCLUSIVELY, "test_m1");
			checkResponse(sendRequest(request, out, in), Status.UNSUPPORTED);//6
			request= new CRUDRequest(CRUD_COMMAND.CREATE, new Modul("test_m1", "test_m1_name "));

			checkResponse(sendRequest(request, out, in), Status.FAILED);//7
			request= new CRUDRequest(CRUD_COMMAND.CREATE, new Modul("test_m1", "test_m1_name2 "));

			checkResponse(sendRequest(request, out, in), Status.OK);//8
			request= new CRUDRequest(CRUD_COMMAND.READ, "test_m1");
			CRUDResponse response= sendRequest(request, out, in);
			checkResponse(response, Status.OK);//9

			/*
			 * if(!((Modul)response.getObject()).getBezeichnung().equals("test_m1_name_2"))
			 * { throw new IllegalStateException("Update failed "); }
			 */
			request = new CRUDRequest(CRUD_COMMAND.DELETE, "test_m1");
			checkResponse(sendRequest(request, out, in), Status.OK);
			request= new CRUDRequest(CRUD_COMMAND.UPDATE, new Modul("test_m1", "test_m1_name_3"));

			checkResponse(sendRequest(request, out, in), Status.FAILED);
			request = new CRUDRequest(CRUD_COMMAND.READ, "test_m2");
			checkResponse(sendRequest(request, out, in), Status.OK);

		}catch (Exception e) {
			System.out.println("Server test failed");

			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		try(Socket socket = new Socket("localhost", 4080)){
			ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			SocketAddress socketadress= socket.getRemoteSocketAddress();
			System.out.println("Verbindung hergestellt mit: " + socketadress);
			socket.setSoTimeout(300);
			testCRUDService(out, in);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
