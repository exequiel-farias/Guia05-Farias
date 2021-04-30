package guia05.dominio;

import java.time.Duration;
import java.time.Instant;

public class Trabajo implements Contratable {
	private Trabajador trabajador;
	private Servicio servicio;
	private Boolean urgente;
	private Instant fechaInicio;
	private Instant fechaFin;
	
	public Trabajo(Servicio servicio, Boolean urgente, Instant fechaInicio, Instant fechaFin) {
		super();
		this.servicio = servicio;
		this.urgente = urgente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Trabajo(Servicio servicio, Boolean urgente, Instant fechaIncio) {
		super();
		this.servicio = servicio;
		this.urgente = urgente;
		this.fechaInicio = fechaInicio;
	}

	@Override
	public Double precio() {
		if(urgente) {
			if(servicio instanceof Estandar) {
				long horasTrabajadas = Duration.between(fechaInicio, fechaFin).toHours();
				return (servicio.costo() + trabajador.getCostoHora()*horasTrabajadas) * 1.5;
			}
			return servicio.costo() * 1.5;
		}
		if(servicio instanceof Estandar) {
			long horasTrabajadas = Duration.between(fechaInicio, fechaFin).toHours();
			return servicio.costo() + trabajador.getCostoHora()*horasTrabajadas;
		}
		return servicio.costo();
	}
	
	@Override
	public Boolean finalizado() {
		if(fechaFin != null) {
			return fechaFin.isBefore(Instant.now());
		}
		return false;
	}
	
	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}
	
	public void fechaFin(Instant fechaFinTrabajo) {
		this.fechaFin = fechaFinTrabajo;
	}
	
	public Instant getFechaInicio() {
		return fechaInicio;
	}
	
	public Servicio getServicio() {
		return servicio;
	}
	
}
