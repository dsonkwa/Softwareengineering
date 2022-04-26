package Verteiltes_System;
import java.net.Socket;

/**
 * 
 * @author Christian Weidauer, Hochschule Bochum
 *
 */
public interface CRUDService {
	public CRUDResponse handleCRUDRequest(CRUDRequest request, Socket socket);
}
