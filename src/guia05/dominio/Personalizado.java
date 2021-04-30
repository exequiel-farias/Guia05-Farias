package guia05.dominio;

public class Personalizado extends Servicio {
	private Double presupuesto;
	private Double costoMateriales;
	private Double costoTransporte;
	
	public Personalizado(String nombre,Oficio oficio,Double presupuesto, Double materiales, Double transporte) {
		super();
		this.nombre = nombre;
		this.oficio = oficio;
		this.presupuesto = presupuesto;
		this.costoMateriales = materiales;
		this.costoTransporte = transporte;
	}

	@Override
	public Double costo() {
		return presupuesto + costoMateriales + costoTransporte;
	}

}
