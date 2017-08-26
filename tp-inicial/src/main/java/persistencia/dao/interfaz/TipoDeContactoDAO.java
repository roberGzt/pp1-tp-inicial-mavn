package persistencia.dao.interfaz;

import java.util.List;

import dto.DomicilioDTO;
import dto.TipoDeContactoDTO;

public interface TipoDeContactoDAO {

	public int insert(TipoDeContactoDTO tipoDeContacto);
	
	public boolean update(TipoDeContactoDTO tipoDeContacto);
		
	public boolean delete(TipoDeContactoDTO tipoDeContacto); 
	
	public List<TipoDeContactoDTO> readAll();
}
