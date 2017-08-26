package persistencia.dao.interfaz;

import java.util.List;

import dto.DomicilioDTO;

public interface DomicilioDAO {
	
	public int insert(DomicilioDTO domicilio);
	
	public boolean update(DomicilioDTO domicilio);
		
	public boolean delete(DomicilioDTO domicilio); 
	
	public List<DomicilioDTO> readAll();

}
