package guia05.dominio;

public class Herramienta{
	private String nombre;
	private Double costoDia;

	public Herramienta(String nombre, Double costoDia) {
		super();
		this.nombre = nombre;
		this.costoDia = costoDia;
	}

	public Double costoDia() {
		return costoDia;
	}


}
