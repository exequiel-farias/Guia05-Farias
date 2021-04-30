package guia05.dominio;

import java.time.Duration;
import java.time.Instant;

public class Alquiler implements Contratable {
	
	private Herramienta herramienta;
	private Instant fechaInicio;
	private Instant fechaEntrega;
	private Instant fechaEntregaReal;

/*	public Alquiler(Herramienta herramienta, Instant fechaInicio) {
		super();
		this.herramienta = herramienta;
		this.fechaInicio = fechaInicio;
	}*/

	public Alquiler(Herramienta herramienta, Instant fechaInicio, Instant fechaEntrega) {
		super();
		this.herramienta = herramienta;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
		this.fechaEntregaReal = null;
	}

	@Override
	public Double precio() {
		if(fechaEntregaReal != null) {
			Duration diasAlquilado = Duration.between(fechaInicio, fechaEntregaReal);
			if(diasAlquilado.toDays() == 0) {//si lo alquila por 1 dia solamente
				return herramienta.costoDia();
			}
			return herramienta.costoDia() * diasAlquilado.toDays();
		}
		//si no devolvio todavia la herramienta
		if(Duration.between(fechaInicio, Instant.now()).toDays() == 0) {//si esta dentro del dia
			return herramienta.costoDia();
		}
		else {
			return herramienta.costoDia() * Duration.between(fechaInicio, Instant.now()).toDays();
		}
	}

	public Boolean enMora() {
		if(fechaEntregaReal != null) {
			return Duration.between(fechaInicio, fechaEntregaReal).toDays() > Duration.between(fechaInicio, fechaEntrega).toDays();
		}
		return Duration.between(fechaInicio, Instant.now()).toDays() > Duration.between(fechaInicio, fechaEntrega).toDays();
	}
	
	public void setFechaEntregaReal(Instant fecha) {
		fechaEntregaReal = fecha;
	}
	
	@Override
	public Boolean finalizado() {
		return fechaEntregaReal != null;
	}

}
