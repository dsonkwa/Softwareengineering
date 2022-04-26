package Verteiltes_System;

import java.net.Socket;

public class ModulContainerObjectAdapter implements CRUDService{

	ModulContainer container= ModulContainer.getContainer();

	public ModulContainerObjectAdapter(ModulContainer container) {
		this.container = container;

	}
	
	public ModulContainerObjectAdapter() {

	}

	@Override
	public CRUDResponse handleCRUDRequest(CRUDRequest request, Socket socket) {

		switch(request.getCommand()) {
		case CREATE:
			return createModul((Modul)request.getObject());
		case READ:
			return readModul((String)request.getObject());
		case UPDATE:
			return updateModul((Modul)request.getObject());
		case DELETE:
			return deleteModul((String)request.getObject());
		case READ_AND_LOCK_EXCLUSIVELY:
			return CRUDResponse.UNSUPPORTED;
		default:
			return CRUDResponse.FAILED;
		}
	}

	private CRUDResponse createModul(Modul modul) {
		try {
			return CRUDResponse.ok((Modul)container.create(modul.getKey(), modul.getBezeichnung()));

		}catch (Exception e) {
			return CRUDResponse.FAILED;
		}
	}

	private CRUDResponse readModul(String key) {
		try {
			return CRUDResponse.ok((Modul)container.get(key));
		}catch (Exception e) {
			return CRUDResponse.FAILED;
		}
	}

	private CRUDResponse updateModul(Modul modul) {
		try {
			return CRUDResponse.ok((Modul)container.update(modul));
		}catch (Exception e) {
			return CRUDResponse.FAILED;
		}

	}

	private CRUDResponse deleteModul(String key) {
		try {
			return CRUDResponse.ok((Modul)container.remove(key));
		}catch (Exception e) {
			return CRUDResponse.FAILED;
		}
	}

}
