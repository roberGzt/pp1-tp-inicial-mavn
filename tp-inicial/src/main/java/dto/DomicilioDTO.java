package dto;

public class DomicilioDTO {
	private int idDomicilio;
	private String calle;
	private int altura;
	private int piso;
	private String departamento;
	private LocalidadDTO localidad;
	
	public DomicilioDTO(int idDomicilio, String calle, int altura, int piso, String departamento, LocalidadDTO localidad) {
		super();
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.localidad = localidad;
	}
	
	public DomicilioDTO(String calle, int altura, int piso, String departamento, LocalidadDTO localidad) {
		super();
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.localidad = localidad;
	}
	
	public DomicilioDTO(int idDomicilio) {
		super();
		this.idDomicilio = idDomicilio;
	}

	public DomicilioDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}
	
	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomicilioDTO other = (DomicilioDTO) obj;
		if (altura != other.altura)
			return false;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (piso != other.piso)
			return false;
		return true;
	}
	

}
