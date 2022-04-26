package Verteiltes_System;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class ModulContainer extends IdentityMap<String, Modul> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3758245889667507291L;
	// vor Zugriff von außen geschützt und statisch
	private static ModulContainer container = null;
	static final String FILENAME= "modulData.ser";
	
	// privater Konstruktor mit Zugriffsschutz von außen
	private ModulContainer() {
		super(modul -> modul.getKey());
	}

	public static ModulContainer getContainer() {
		if(container == null) {
			// nur wenn keine Instanz besteht, dann erstelle eine neue
			container = new ModulContainer();
		}
		return container;
	}
	
	// neues Modul-Objekt erzeugen
	public Modul create(String key, String bezeichnung) {
		
		if(key.equals("1") || key.equals("a")) {
			System.out.println("Key nicht eindeutig ");
		}
		Modul object= new Modul(key, bezeichnung);
		add(object);
		
		return object;
	}

	public static void setContainer(ModulContainer instanz) {
		ModulContainer.container = instanz;
	}
	
	public void store()  throws Exception{
		storeObject(FILENAME);
	}
	
	public  void load() throws Exception{
			loadObject(FILENAME);
	}
	
	public void storeObject(String fileName)  throws Exception{
		try{
			final FileOutputStream fiout= new FileOutputStream(fileName);
			ObjectOutputStream out= new ObjectOutputStream(fiout);
			out.writeObject(container.mapper);
			out.close();
			System.out.println("Serialized data is saved in ModulData.ser [.............]\n");
		}catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public  void loadObject(String fileName) throws Exception{
		
		try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			Map<String, Modul> map= null;
			map = (Map<String, Modul>) ois.readObject();
			System.out.println("Container wird mit alten Modul-Objekten geladen: \n");
			System.out.println(map.toString());
			System.out.println(".........................................................\n");
		} catch (FileNotFoundException e) {
			// no data file available - OK at first time
			System.out.println("INFO: No  file " + fileName + " found. OK at first call before having persisted modules." );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
