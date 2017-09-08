package pruebas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import modelo.Agenda;

public class GeneradorDeDatos {
	private static String[] nombres = { "Elliott", "Albertha", "Wilburn", "Marquita", "Merrilee", "Rosy", "Williemae",
			"Loma", "Raymond", "Ardis", "Patrice", "Julie", "Maryjane", "Giselle", "Irena", "Hang", "Margarita",
			"Raymundo", "Zachariah", "Stephenie", "Freddy", "Natividad", "Tequila", "Ron", "Sunni", "Verlie", "Dennis",
			"Nathan", "Tania", "Lory", "Eladia", "Kitty", "Elyse", "Kandice", "Charlene", "Isobel", "Morris",
			"Rosalina", "Narcisa", "Eun", "Alda", "Marilou", "Beatrice", "Marcy", "Margery", "Bradley			",
			"Raeann", "Katheryn", "Brandy", "Hulda" };
	private static String[] calles = { "lima", "buenos aires", "gaspar campos", "ecuador", "colombia", "san martín",
			"quito", "calle 1", "calle2", "calle 3" };
	private static String[] localidades = { "17 de Agosto", "25 de Mayo", "9 de Julio / La Ni�a", "Acassuso",
			"Aguas Verdes", "Alberti", "Arenas Verdes", "Arrecifes", "Avellaneda", "Ayacucho", "Azul", "Bah�a Blanca",
			"Bah�a San Blas", "Balcarce", "Balneario Marisol", "Balneario Orense", "Balneario Reta",
			"Balneario San Cayetano", "Baradero", "Bella Vista", "Benito Ju�rez", "Berazategui", "Berisso", "Boulogne",
			"Bragado", "Brandsen", "Campana", "Capilla del Se�or", "Capital Federal", "Capit�n Sarmiento", "Carapachay",
			"Carhue", "Carhu�", "Carlos Keen", "Carmen de Areco", "Carmen de Patagones", "Caseros", "Castelar",
			"Castelli", "Chacabuco", "Chascom�s", "Chivilcoy", "City Bell", "Ciudadela", "Claromec�", "Col�n",
			"Coronel Dorrego", "Coronel Pringles", "Coronel Su�rez", "Darregueira", "Dunamar", "Escobar", "Ezeiza",
			"Florencio Varela", "Florida", "Fort�n Mercedes", "Garin", "General Arenales", "General Belgrano",
			"General Madariaga", "General Villegas", "Gral. Daniel Cerri", "Gran Buenos Aires", "Guamin�", "Haedo",
			"Huanguelen", "Hurlingham", "Isla Mart�n Garc�a", "Ituzaingo", "Jun�n", "La Plata", "La Tablada",
			"Laferrere", "Lanus", "Laprida", "Las Flores", "Las Gaviotas", "Las Toninas", "Lima", "Lisandro Olmos",
			"Llavallol", "Lobos", "Lomas de Zamora", "Los Toldos - Gral. Viamonte", "Los Polvorines", "Lucila del Mar",
			"Luis Guill�n", "Luj�n", "Magdalena", "Maip�", "Mar Azul", "Mar Chiquita", "Mar de Aj�", "Mar de Cobo",
			"Mar del Plata", "Mar del Sud", "Mar del Tuy�", "Martinez", "M�danos / Laguna Chasic�", "Mercedes", "Merlo",
			"Miramar", "Monte Hermoso", "Moreno", "Mor�n", "Munro", "Navarro", "Necochea", "Nueva Atlantis",
			"Olavarr�a", "Olivos", "Open Door", "Ostende", "Pedro Luro", "Pehuaj�", "Pehuen C�", "Pergamino", "Pig��",
			"Pilar", "Pinamar", "Provincia de Buenos Aires", "Puan", "Punta Alta", "Punta Indio", "Punta Lara",
			"Quequ�n", "Quilmes", "Ramallo", "Ramos Mej�a", "Ranchos", "Rauch", "Rivadavia", "Rojas", "Roque P�rez",
			"Saenz Pe�a", "Saladillo", "Salto", "San Antonio de Areco", "San Bernardo", "San Cayetano",
			"San Clemente del Tuy�", "San Fernando", "San Isidro", "San Justo", "San Martin", "San Miguel del Monte",
			"San Nicol�s", "San Pedro", "San Vicente", "Santa Clara del Mar", "Santa Teresita", "Sarand�",
			"Sierra de la Ventana", "Sierra de los Padres", "Sierras de los Padres", "Tandil", "Tapalqu�", "Temperley",
			"Tigre", "Tornquist / Ruta Prov. 76", "Trenque Lauquen", "Tres Arroyos", "Turdera", "Valentin Alsina",
			"Vicente Lopez", "Victoria", "Villa Ballester", "Villa Gesell", "Villa Lynch", "Villa Serrana La Gruta",
			"Villa Ventana", "Villalonga", "Wilde", "Z�rate" };
	private static Random random = new Random();
	private static Agenda agenda = Agenda.getInstance();

	private static String getTelefeno() {
		return Integer.toString(
				random.nextInt(10) * 1000 + random.nextInt(10) + 100 + random.nextInt(10) * 10 + random.nextInt(10))
				+ " " + Integer.toString(random.nextInt(10) * 1000 + random.nextInt(10) + 100 + random.nextInt(10) * 10
						+ random.nextInt(10));

	}

	private static int getAltura() {
		return random.nextInt(3000);
	}

	private static int getPiso() {
		return random.nextInt(16);
	}

	private static String getDepto() {
		return Character.toString((char) (65 + random.nextInt(11)));
	}

	private static String getMail() {
		return nombres[random.nextInt(nombres.length)] + "@" + "mail.com";
	}

	private static Date getFechaNac() {
		return Date.valueOf(Integer.toString(1970 + random.nextInt(48)) + "-" + Integer.toString(1 + random.nextInt(12))
				+ "-" + Integer.toString(1 + random.nextInt(28)));
	}

	public static void generarDatos() {
		for (int i = 0; i < localidades.length; i++) {
			agenda.agregarLocalidad(new LocalidadDTO(localidades[i]));
		}

		List<LocalidadDTO> listaLocalidades = agenda.getLocalidades();
		List<TipoDeContactoDTO> listaContactos = agenda.getTiposDeContacto();

		for (int i = 0; i < 50; i++) {
			DomicilioDTO d = new DomicilioDTO(calles[random.nextInt(calles.length)], getAltura(), getPiso(), getDepto(),listaLocalidades.get(random.nextInt(listaLocalidades.size())));
			int idDomicilio= agenda.agregarDomicilio(d);
			d.setIdDomicilio(idDomicilio);
			
			PersonaDTO p = new PersonaDTO(nombres[random.nextInt(nombres.length)], getTelefeno(), getMail(),
					getFechaNac(), listaContactos.get(random.nextInt(listaContactos.size())),d);
			
			agenda.agregarPersona(p);
		}
	}

}