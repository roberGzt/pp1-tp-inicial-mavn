package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOImpl implements LocalidadDAO{
	
	private static final Conexion conexion = Conexion.getConexion();
	
	private static final String insertDomicilio="INSERT INTO localidades(nombreLocalidad) VALUES(?)";
	private static final String delete = "DELETE FROM localidades WHERE idLocalidad = ?";
	private static final String update = "UPDATE localidades " + 
			"SET nombreLocalidad = ? " + 
			"WHERE idLocalidad = ?";
	private static final String readAll="select * from localidades";

	@Override
	public int insert(LocalidadDTO localidad) {
		PreparedStatement statement;
		ResultSet rs;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insertDomicilio, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, localidad.getNombre());
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{	
				//con esto obtengo el numero autogenerado por mysql, luego lo uso para setearselo a la persona
				rs=statement.getGeneratedKeys();
				if(rs.next())
					return rs.getInt(1);
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
		return -1;
	}

	@Override
	public boolean delete(LocalidadDTO localidad) {
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad.getId()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			//throw e;
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public boolean update(LocalidadDTO localidad) {
		PreparedStatement statement;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, localidad.getNombre());
			statement.setInt(2, localidad.getId());
			if(statement.executeUpdate() > 0)
				return true;
		} 
		catch (Exception e)
		{
			// TODO: handle exception
		}
		finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				localidades.add(new LocalidadDTO(resultSet.getInt(1), resultSet.getString(2)));
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
		return localidades;
	}

}
