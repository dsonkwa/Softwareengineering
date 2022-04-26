package Verteiltes_System;

import java.util.Scanner;

public class ModulManagement {

	public static void main(String[] args) throws Exception{
		ModulContainer modulcontainer= ModulContainer.getContainer();


		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in); System.out.
		println("Möchten sie alte Module laden (ja), oder neue speichern (nein)? ");
		String str= sc.nextLine();


		if(args[0].equals(str)) { 
			modulcontainer.load();
			modulcontainer.setIdentity(modul -> modul.getKey()); 
		}

		else {
			modulcontainer= ModulContainer.getContainer();
			System.out.println("leer Container: " + modulcontainer.getAll().toString());
		}



		// Modul-Objekte erzeugen modulcontainer.create("STW2", "Softwaretechnik2");
		modulcontainer.create("VROB", "Vertiefung Robotik");
		modulcontainer.create("GAT","Grundlagen der Automatisierung");
		modulcontainer.create("GROB", "Grundlagen der Robotik");
		modulcontainer.create("STW1", "Softwaretechnik"); 
		modulcontainer.create("ML","Machinelles Lernen");
		modulcontainer.create("IT", "Industrial IT");
		modulcontainer.create("IN", "Intelligente Netze");
		modulcontainer.create("Math1", "Mathematik1");

		modulcontainer.store();


		// Test-Verwaltungsoperationen

		System.out.println(ModulContainer.getContainer().get("GROB"));
		System.out.println(ModulContainer.getContainer().remove("Math1"));
		//System.out.println(ModulContainer.getContainer().getAll());
		ModulContainer.getContainer().add(new Modul("Math1", "Mathematik1"));
		//System.out.println(ModulContainer.getContainer().getAll());

	}

}
