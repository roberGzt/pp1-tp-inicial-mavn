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
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOImpl implements DomicilioDAO {

	private static final Conexion conexion = Conexion.getConexion();

	private static final String insertLocalidad = "INSERT INTO domicilios(calle, altura, piso, Departamento, idlocalidad) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM domicilios WHERE idDomicilio = ?";
	private static final String update = "UPDATE domicilios "
			+ "SET calle = ?, altura = ?, piso = ?, departamento = ?, idLocalidad = ? " + "WHERE idDomicilio = ?";
	private static final String readAll = "select * from domicilios d inner join localidades l on d.idLocalidad = l.idLocalidad";

	@Override
	public int insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		ResultSet rs;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insertLocalidad, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, domicilio.getCalle());
			statement.setInt(2, domicilio.getAltura());
			statement.setInt(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepartamento());
			statement.setInt(5, domicilio.getLocalidad().getId());

			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
			{
				// con esto obtengo el numero autogenerado por mysql, luego lo uso para
				// setearselo a la persona
				rs = statement.getGeneratedKeys();
				if (rs.next())
					return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return -1;
	}

	@Override
	public boolean update(DomicilioDTO domicilio) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, domicilio.getCalle());
			statement.setInt(2, domicilio.getAltura());
			statement.setInt(3, domicilio.getPiso());
			statement.setString(4, domicilio.getDepartamento());
			statement.setInt(5, domicilio.getLocalidad().getId());
			statement.setInt(6, domicilio.getIdDomicilio());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public boolean delete(DomicilioDTO domicilio) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(domicilio.getIdDomicilio()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	@Override
	public List<DomicilioDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				domicilios.add(new DomicilioDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getString(5),
						new LocalidadDTO(resultSet.getInt("idLocalidad"), resultSet.getString("nombreLocalidad"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return domicilios;
	}

}
