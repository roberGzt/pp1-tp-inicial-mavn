package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

public class PersonaDAOImpl implements PersonaDAO
{
	private static final Conexion conexion = Conexion.getConexion();
	
	private static final String insertPersona = "INSERT INTO personas(nombre, telefono, mail, cumpleanios, idTipo, idDomicilio) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String personasJoinDomicilios = "SELECT * FROM personas p "
														+ "inner join domicilios d "
														+ "on d.idDomicilio = p.idDomicilio "
														+ "inner join localidades l "
														+ "on d.idLocalidad = l.idLocalidad "
														+ "inner join TiposDeContacto t "
														+ "on t.idTipo = p.idTipo";
	
	private static final String update = "UPDATE personas " + 
						"SET Nombre = ?, telefono = ?, mail = ?, cumpleanios = ?, idTipo = ?, idDomicilio = ? " + 
						"WHERE idPersona = ?";
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insertPersona);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getMail());
			statement.setDate(4, (Date) persona.getFechaCumpleaños());
			statement.setInt(5, persona.getTipoDeContacto().getId());
			statement.setInt(6, persona.getDomicilio().getIdDomicilio());
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
		
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(personasJoinDomicilios);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono"),
						resultSet.getString("Mail"), resultSet.getDate("Cumpleanios"), new TipoDeContactoDTO(resultSet.getInt("idTipo"), resultSet.getString("nombreTipo")), 
						new DomicilioDTO(resultSet.getInt("idDomicilio"), resultSet.getString("calle"), 
								resultSet.getInt("altura"), resultSet.getInt("piso"), resultSet.getString("departamento"), new LocalidadDTO(resultSet.getInt("idLocalidad"), 
										resultSet.getString("nombreLocalidad"))) ) );
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}

	@Override
	public boolean update(PersonaDTO persona) 
	{
		PreparedStatement statement;
		try 
 		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getMail());
			statement.setDate(4, (Date) persona.getFechaCumpleaños());
			statement.setInt(5, persona.getTipoDeContacto().getId());
			statement.setInt(6, persona.getDomicilio().getIdDomicilio());
			statement.setInt(7, persona.getIdPersona());
						
			if(statement.executeUpdate() > 0) //Si se ejecut� devuelvo true
				 				return true;
 		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

}
