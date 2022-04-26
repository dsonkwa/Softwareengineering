package Verteiltes_System;

import java.util.Scanner;

import Verteiltes_System.CRUDRequest.CRUD_COMMAND;

public class ModulManagementServer {

	public static void main(String[] args)  throws Exception{

		ModulContainer modulcontainer= ModulContainer.getContainer();


		@SuppressWarnings("resource") Scanner sc= new Scanner(System.in); System.out.
		println("Möchten sie alte Module laden (ja), oder neue speichern (nein)? ");
		String str= sc.nextLine();


		
		  if(args[0].equals(str)) { modulcontainer.load();
		  modulcontainer.setIdentity(modul -> modul.getKey()); }
		  
		  else { modulcontainer= ModulContainer.getContainer();
		  //System.out.println("leer Container: " + modulcontainer.getAll().toString()); 
		  }
		 

		CRUDService service= new ModulContainerObjectAdapter(); 

		CRUDResponse response= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.CREATE, new Modul("GROB", "Grundlagen der Robotik")), null);
		//System.out.println("Response0: "+ response);

		CRUDResponse response1= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.CREATE, new Modul("STW2", "Softwaretechnik2")), null);
		//System.out.println("Response1: "+ response1);

		CRUDResponse response2= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.CREATE, new Modul("GAT",
						"Grundlagen der Automatisierungstechnik")), null);
		//System.out.println("Response2: "+ response2);

		CRUDResponse response3= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.CREATE, new Modul("STW1", "Softwaretechnik")), null);
		//System.out.println("Response3: "+ response3);

		CRUDResponse response4= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.CREATE, new Modul("ML", "Machinelles Lernen")), null);
		//System.out.println("Response4: "+ response4);

		CRUDResponse response5= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.CREATE, new Modul("STW2", "Softwaretechnik")), null);
		//System.out.println("Response5: "+ response5 +"\n");

		CRUDResponse response6= service.handleCRUDRequest(new CRUDRequest
				(CRUD_COMMAND.READ, "ML"), null); 
		//System.out.println("Response6: "+ response6+"\n");

				CRUDResponse response7= service.handleCRUDRequest(new CRUDRequest
						(CRUD_COMMAND.DELETE, "vvv"), null); 
				//System.out.println("Response7: "+ response7 +"\n");


				CRUDResponse response8= service.handleCRUDRequest(new CRUDRequest
						(CRUD_COMMAND.UPDATE, new Modul("STW1", "Softwaretechnik1")), null);
				//System.out.println("Response8: "+ response8);


				CRUDResponse response9= service.handleCRUDRequest(new CRUDRequest
						(CRUD_COMMAND.CREATE, new Modul("Phy1", "Physik1")), null);
				//System.out.println("Response9: "+ response9 +"\n");


				modulcontainer.store();

				//System.out.println(modulcontainer.getAll());
				CRUDMultiClientTCPServer crudServer= new CRUDMultiClientTCPServer();
				crudServer.start();

	}

}
