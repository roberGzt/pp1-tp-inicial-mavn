package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;

public class Conexion 
{
	public static Conexion instancia;
	private Connection conexion;
	private static String setUTF8= "SET NAMES 'utf8'";
	
	private Conexion()
	{
		try
		{
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpi_g2","root","root");
			System.out.println("Conexion exitosa");
			PreparedStatement statement = this.getSQLConexion().prepareStatement(setUTF8);
			statement.execute();

		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida");
		}
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return conexion;
	}
	
	public void cerrarConexion()
	{
		instancia = null;
	}
}
