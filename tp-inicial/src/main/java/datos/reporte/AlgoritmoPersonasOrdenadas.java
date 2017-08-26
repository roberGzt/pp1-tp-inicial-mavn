package datos.reporte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.PersonaDTO;
import persistencia.dao.mysql.PersonaDAOImpl;

public class AlgoritmoPersonasOrdenadas {

	PersonaDAOImpl personaDAO = new PersonaDAOImpl();
	List<PersonaDTO> lista = new ArrayList<>();
	ArrayList<PersonaDTOJasper> listaPersonas = new ArrayList<>();
	
	public AlgoritmoPersonasOrdenadas (){
		
	}
	
	public void llenarListaPersonasDTO(){
		lista = personaDAO.readAll();
	}
	
	
	public void llenarListaPersonas(List <PersonaDTO> listaPersonasDTO){
		for (PersonaDTO p : listaPersonasDTO){
			listaPersonas.add(new PersonaDTOJasper (p.getNombre(),p.getTelefono(),p.getMail()
					,p.getFechaCumpleaños(),p.getTipoDeContacto(),p.getDomicilio(),null));
		}
	}
	
	public ArrayList<PersonaDTOJasper> getListaPersonasOrdenadasPorDia(){
		listaPersonas.clear();
		llenarListaPersonasDTO();
		llenarListaPersonas(lista);
		asignarSignoAPersonas(listaPersonas);
		
		Collections.sort(listaPersonas, new OrdenarPorDia());
		
		
		return listaPersonas;
		
		/*
		//XXX No olvidar borrar
		for (int i=0; i < listaPersonas.size(); i++){
			System.out.println(listaPersonas.get(i).getFechaCumpleaños()); 
			System.out.println(listaPersonas.get(i).getSigno()); 
		}
		*/
			
		}
	
	public void asignarSignoAPersonas(ArrayList<PersonaDTOJasper> listaPersonas){
		for (PersonaDTOJasper p : listaPersonas){
			SignoZodiaco sig = null;
			sig = p.calcularYAsignarSigno(p.getFechaCumpleaños());
			p.setSigno(sig);
		}
	}

	}

