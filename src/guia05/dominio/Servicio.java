package guia05.dominio;

public abstract class Servicio {
	protected String nombre;
	protected Oficio oficio;
	
	//public static Oficio oficio;
	public abstract Double costo();
	
	public Oficio getOficio() {
		return oficio;
	}
}
