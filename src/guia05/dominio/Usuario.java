package guia05.dominio;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import guia05.excepciones.AlquilerNoEntregadoException;

public class Usuario {
	private String email;
	private List<Contratable> itemsContratados;
	private int cantAlquileres;
	
	public Usuario(String email) {
		super();
		this.email = email;
		this.cantAlquileres = 0;
		this.itemsContratados = new ArrayList<Contratable>();
	}
	
/*	public void contratar(Servicio servicio,Boolean urgente,Instant fechaInicio,Instant fechaFin) {
		Trabajo trabajo = new Trabajo(servicio,urgente,fechaInicio,fechaFin);
		itemsContratados.add(trabajo);
	}*/
	
	public void contratar(Contratable c) throws AlquilerNoEntregadoException {
		if(c instanceof Trabajo) {
			itemsContratados.add(c);
		}
		if(cantAlquileres < 2) {
			itemsContratados.add(c);
			cantAlquileres++;
		}
		else {
			throw new AlquilerNoEntregadoException("Debe devolver "+(cantAlquileres-1)+" alquileres como minimo");
		}
	}
	
	public void devolver(Contratable c,Instant fechaDevolucion) {
		itemsContratados.remove(c);
		cantAlquileres--;
		((Alquiler) c).setFechaEntregaReal(fechaDevolucion);//es para hacer pruebas
	}
}
