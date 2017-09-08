package main;

import modelo.Agenda;
import presentacion.controlador.ControladorPrincipal;
import presentacion.vista.Principal;
import pruebas.GeneradorDeDatos;


public class Main 
{

	public static void main(String[] args) 
	{
		GeneradorDeDatos.generarDatos();
		Principal vista = new Principal();
		Agenda modelo = Agenda.getInstance();
		ControladorPrincipal controlador = new ControladorPrincipal(vista, modelo);
		controlador.inicializar();
		
		
	}
}
