package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import dto.LocalidadDTO;
import dto.TipoDeContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoDeContactoDAO;

public class TipoDeContactoDAOImpl implements TipoDeContactoDAO {

	private static final Conexion conexion = Conexion.getConexion();
	
	private static final String insert="INSERT INTO tiposDeContacto (nombreTipo) VALUES(?)";
	private static final String delete = "DELETE FROM tiposDeContacto WHERE idTipo = ?";
	private static final String update = "UPDATE tiposDeContacto " + 
			"SET nombreTipo = ? " + 
			"WHERE idTipo = ?";
	private static final String readAll="select * from tiposDeContacto";
	
	@Override
	public int insert(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		ResultSet rs;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, tipoDeContacto.getNombre());
			
			
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
	public boolean update(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, tipoDeContacto.getNombre());
			statement.setInt(2, tipoDeContacto.getId());
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
	public boolean delete(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoDeContacto.getId()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public List<TipoDeContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoDeContactoDTO> localidades = new ArrayList<TipoDeContactoDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				localidades.add(new TipoDeContactoDTO(resultSet.getInt(1), resultSet.getString(2)));
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
