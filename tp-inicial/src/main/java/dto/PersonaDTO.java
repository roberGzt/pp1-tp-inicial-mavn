package dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String mail;
	private Date fechaCumpleaños;
	private TipoDeContactoDTO tipoDeContacto;
	private DomicilioDTO domicilio;

	public PersonaDTO(int idPersona, String nombre, String telefono)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public PersonaDTO(int idPersona, String nombre, String telefono, String mail, Date fechaCumpleaños,
			TipoDeContactoDTO tipoDeContacto, DomicilioDTO domicilio) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.fechaCumpleaños = fechaCumpleaños;
		this.tipoDeContacto = tipoDeContacto;
		this.domicilio = domicilio;
	}

	public PersonaDTO(String nombre, String telefono, String mail, Date fechaCumpleaños,
			TipoDeContactoDTO tipoDeContacto, DomicilioDTO domicilio) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.mail = mail;
		this.fechaCumpleaños = fechaCumpleaños;
		this.tipoDeContacto = tipoDeContacto;
		this.domicilio = domicilio;
	}
	
	public PersonaDTO(int idPersona) {
		super();
		this.idPersona = idPersona;
	}

	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getFechaCumpleaños() {
		return fechaCumpleaños;
	}

	public void setFechaCumpleaños(Date fechaCumpleaños) {
		this.fechaCumpleaños = fechaCumpleaños;
	}

	public TipoDeContactoDTO getTipoDeContacto() {
		return tipoDeContacto;
	}

	public void setTipoDeContacto(TipoDeContactoDTO tipoDeContacto) {
		this.tipoDeContacto = tipoDeContacto;
	}

	public DomicilioDTO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getFechaOrdenada() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String formatString= format.format(this.fechaCumpleaños);
		return formatString;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaDTO other = (PersonaDTO) obj;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (fechaCumpleaños == null) {
			if (other.fechaCumpleaños != null)
				return false;
		} else if (!fechaCumpleaños.equals(other.fechaCumpleaños))
			return false;
		if (idPersona != other.idPersona)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipoDeContacto == null) {
			if (other.tipoDeContacto != null)
				return false;
		} else if (!tipoDeContacto.equals(other.tipoDeContacto))
			return false;
		return true;
	}

	
		
}
