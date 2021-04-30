package guia05.dominio;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import guia05.excepciones.AgendaOcupadaException;
import guia05.excepciones.OficioNoCoincideException;

public class Trabajador {
	private String nombre;
	private String email;
	private Double costoHora;
	private Double porcentComision;
	private Oficio oficio;
	private List<Trabajo> trabajosAsignados;
	
	public Trabajador(String nombre, String email, Double costoHora, Double porcentComision, Oficio oficio) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.costoHora = costoHora;
		this.porcentComision = porcentComision;
		this.oficio = oficio;
		this.trabajosAsignados = new ArrayList<Trabajo>();
	}
	
	public Double getCostoHora() {
		return costoHora;
	}
	
	public void agregarTrabajo(Trabajo trabajo) throws OficioNoCoincideException,AgendaOcupadaException {
		if(oficio.getNombre() == trabajo.getServicio().getOficio().getNombre()) {
			if(trabajosAsignados.size() == 0) {
				trabajosAsignados.add(trabajo);
				trabajo.setTrabajador(this);
			}
			else {
				for(Trabajo unTrabajo : trabajosAsignados) {
					if(Duration.between(unTrabajo.getFechaInicio(), trabajo.getFechaInicio()).toDays() == 0) {
						throw new AgendaOcupadaException("El trabajador ya tiene agendado otro trabajo esa fecha");
					}
				}
				trabajosAsignados.add(trabajo);
				trabajo.setTrabajador(this);
			}
		}
		else {
			throw new OficioNoCoincideException("El oficio no coincide con el oficio del trabajador");
		}
	}
	
	public Oficio getOficio() {
		return oficio;
	}
}
