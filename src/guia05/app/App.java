package guia05.app;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import guia05.dominio.Alquiler;
import guia05.dominio.Estandar;
import guia05.dominio.Herramienta;
import guia05.dominio.Oficio;
import guia05.dominio.Trabajador;
import guia05.dominio.Trabajo;
import guia05.dominio.Usuario;
import guia05.excepciones.AgendaOcupadaException;
import guia05.excepciones.AlquilerNoEntregadoException;
import guia05.excepciones.OficioNoCoincideException;

public class App {

	public static void main(String[] args) {
		Usuario u1 = new Usuario("exequiel-farias@outlook.com");
		
		Herramienta escalera = new Herramienta("escalera", 30.0);
		Herramienta sierra = new Herramienta("sierra", 45.5);
		Herramienta compresor = new Herramienta("compresor", 74.3);
		
		Oficio o1 = new Oficio("carpinteria");
		Oficio o3 = new Oficio("albañileria");
		
		Trabajador t1 = new Trabajador("Juan","juan-perez@hotmail.com",55.0,0.25,o1);
		Trabajador t3 = new Trabajador("Jose","jose-perez@hotmail.com",63.5,0.25,o1);
		
		Estandar instalarPuerta = new Estandar("instalar puerta",o1,300.0);
		Estandar hacerTecho = new Estandar("hacer techo",o1,700.0);
		
		//Ejemplo de que el usuario contrata un servicio:
		//Elije que quiere un servicio estandar, de carpinteria, para instalar una puerta de madera,que no es urgente,que empieze
		//mañana y lo termine pasado mañana
		//Y elige un servicio estandar, de carpinteria, para hacer un techo de madera, que es urgente,que empieza el mismo dia, y lo termina en 10
		Trabajo tr1 = new Trabajo(instalarPuerta,false,Instant.now().plusSeconds(86400),Instant.now().plusSeconds(86400*2));
		Trabajo tr2 = new Trabajo(hacerTecho,true,Instant.now(),Instant.now().plusSeconds(86400*10));
		try{
			t1.agregarTrabajo(tr1);
			t3.agregarTrabajo(tr2);
			u1.contratar(tr1);
			u1.contratar(tr2);
		}
		catch(OficioNoCoincideException e1){
			System.out.println(e1.getMessage());
		}
		catch(AgendaOcupadaException e2) {
			System.out.println(e2.getMessage());
		}
		catch(AlquilerNoEntregadoException e3) {
			System.out.println(e3.getMessage());
		}
		System.out.println("El costo del servicio de instalar una puerta es: " + tr1.precio());
		System.out.println("El costo del servicio de hacer un techo es: " + tr2.precio());
		
		//Ejemplo de que el usuario alquila una herramienta:
		//Elije una escalera, que lo necesita el mismo dia y por 4 dias
		//Elije tambien una sierra, que lo necesita el mismo dia y por 5 dias
		Alquiler a1 = new Alquiler(escalera,Instant.now(),Instant.now().plus(4, ChronoUnit.DAYS));
		Alquiler a2 = new Alquiler(sierra,Instant.now(),Instant.now().plus(5, ChronoUnit.DAYS));
		try {
			u1.contratar(a1);
			u1.contratar(a2);
		}
		catch(AlquilerNoEntregadoException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("El costo del alquiler de la escalera es: " + a1.precio());//muestra eso por que no paso un dia todavia
		System.out.println("El costo del alquiler de la sierra es: " + a2.precio());//muestra eso por que no paso un dia todavia
		u1.devolver(a1, Instant.now().plusSeconds(86400*7));
		System.out.println("El usuario esta en mora? : "+a1.enMora());//devuelve true porque lo devolvio tarde
		System.out.println("El costo del alquiler de la escalera es: " + a1.precio());
		System.out.println("El alquiler de la escalera finalizo?: "+a1.finalizado());//devuelve true porq devolvio la escalera
		System.out.println("El alquiler de la sierra finalizo?: "+a2.finalizado());//devuelve false porq no lo devolvio
		System.out.println("El trabajo de instalar una puerta finalizo?:"+ tr1.finalizado());//despues false porq todavia no es mayor a la fecha de fin del trabajo
		
		//Para probar las excepciones
		//Creo un trabajo cualquiera para probar pero que no contrata ningun usuario
		Oficio o2 = new Oficio("cerrajeria");
		Trabajador t2 = new Trabajador("Martin","martin-perez@hotmail.com",40.0,0.25,o2);
		Trabajo tr3 = new Trabajo(hacerTecho,false,Instant.now().plusSeconds(86400),Instant.now().plusSeconds(86400*15));
		Estandar reparacionCerradura = new Estandar("reparacion cerradura",o2,630.0);
		Trabajo tr4 = new Trabajo(reparacionCerradura,false,Instant.now(),Instant.now().plusSeconds(86400*13));
		
		try {
			t2.agregarTrabajo(tr3);//lanza una excepcion de que no coincide el oficio
		}
		catch(OficioNoCoincideException e1){
			System.out.println(e1.getMessage());
		}
		catch(AgendaOcupadaException e2) {
			System.out.println(e2.getMessage());
		}
		
		try {
			t1.agregarTrabajo(tr3);//lanza una excepcion de que ya tiene otro trabajo agendado
		}
		catch(OficioNoCoincideException e1){
			System.out.println(e1.getMessage());
		}
		catch(AgendaOcupadaException e2) {
			System.out.println(e2.getMessage());
		}
		
		//Para probar la excepcion de que no puede tener mas de dos alquileres
		Alquiler a3 = new Alquiler(compresor,Instant.now(),Instant.now().plus(5, ChronoUnit.DAYS));
		try {
			u1.contratar(a3);//con este alquiler, el usuario tiene 2 alquileres en la lista de contratables
			u1.contratar(a2);//lanza una excepcion porque el usuario ya tiene 2 alquileres 
		}
		catch(AlquilerNoEntregadoException e1) {
			System.out.println(e1.getMessage());
		}
		
	}

}
