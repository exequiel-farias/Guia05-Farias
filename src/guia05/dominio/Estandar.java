package guia05.dominio;

public class Estandar extends Servicio {
	private Double precioFijo;
	
	public Estandar(String nombre,Oficio oficio,Double precioFijo) {
		super();
		this.nombre = nombre;
		this.oficio = oficio;
		this.precioFijo = precioFijo;
	}

	@Override
	public Double costo() {
		return precioFijo;
	}

}
