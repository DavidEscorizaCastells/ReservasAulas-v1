package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;

public class IUTextual {
	private static final String ERROR="Error: ";
	private static final String NOMBRE_VALIDO="David Escoriza Castells";
	private static final String CORREO_VALIDO="lDavi14@hotmail.com";
	private ModeloReservasAulas modelo;
	
	public IUTextual() {
		modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}
	
	public void comenzar(){
		Consola.mostrarCabecera("Programa para gestion de reservas.");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		System.out.println("Adios.");
	}
	
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {			
			Aula aula = Consola.leerAula();
			modelo.insertarAula(aula);
			System.out.println("Aula insertada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.borrarAula(aula);
			System.out.println("Aula borrada correctamente");
			} catch (OperationNotSupportedException|IllegalArgumentException e) {
				System.out.println(ERROR + e.getMessage());	
			}
	}
	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = modelo.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscada es: " + aula.toString());
			} else {
				System.out.println("No existe ningún aula con ese nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		String[] listaAulas = modelo.representarAulas();
		if (listaAulas[0]!=null) {
			for (String aula : listaAulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}
	
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {			
			Profesor profesor = Consola.leerProfesor();
			modelo.insertarProfesor(profesor);
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			modelo.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente");
			} catch (OperationNotSupportedException|IllegalArgumentException e) {
				System.out.println(ERROR + e.getMessage());	
			}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = modelo.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El profesor buscada es: " + profesor.toString());
			} else {
				System.out.println("No existe ningún profesor con ese nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		String[] listaProfesores = modelo.representarProfesores();
		if (listaProfesores[0]!=null) {
			for (String profesor : listaProfesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesores que listar.");
		}
	}
	
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar reserva");
		
		try {		
			LocalDate dia=Consola.leerDia();
			Tramo tramo=Consola.leerTramo();
			Permanencia permanencia=new Permanencia(dia, tramo);
			Aula aula=Consola.leerAula();
			Profesor profesor=Consola.leerProfesor();
			Reserva reserva=new Reserva(modelo.buscarProfesor(profesor),modelo.buscarAula(aula), permanencia);
			modelo.realizarReserva(reserva);
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	/*private Reserva leerReserva(Profesor profesor) {
		
	}*/
	
	public void aunlaReserva() {
		Consola.mostrarCabecera("Anular reserva");
		try {
			LocalDate dia=Consola.leerDia();
			Tramo tramo=Consola.leerTramo();
			Permanencia permanencia=new Permanencia(dia, tramo);
			Aula aula=Consola.leerAula();
			Profesor profesor=Consola.leerProfesor();
			Reserva reserva=new Reserva(modelo.buscarProfesor(profesor),modelo.buscarAula(aula), permanencia);
			if (modelo.buscarReserva(reserva)!=null)
				modelo.anularReserva(reserva);
						
			} catch (OperationNotSupportedException|IllegalArgumentException e) {
				System.out.println(ERROR + e.getMessage());	
			}
	}
	
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		String[] listaReservas = modelo.representarReservas();
		if (listaReservas[0]!=null) {
			for (String reserva : listaReservas) {
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}
	
	public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas por aula");		
		Reserva[] listaReservas = modelo.getReservasAula(Consola.leerAula());
		if (listaReservas[0]!=null) {
			for (Reserva reserva : listaReservas) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas que listar para ese aula.");
		}
	}
	
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listar reservas por profesor");		
		Reserva[] listaReservas = modelo.getReservaProfesor(Consola.leerProfesor());
		if (listaReservas[0]!=null) {
			for (Reserva reserva : listaReservas) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas que listar para ese profesor.");
		}
	}
	
	public void listarReservasPermanencia () {
		Consola.mostrarCabecera("Listar reservas por permanencia");
		LocalDate dia=Consola.leerDia();
		Tramo tramo=Consola.leerTramo();
		Permanencia permanencia=new Permanencia(dia, tramo);
		Reserva[] listaReservas = modelo.getReservaPermanencia(permanencia);
		if (listaReservas[0]!=null) {
			for (Reserva reserva : listaReservas) {
				System.out.println(reserva.toString());
			}
		} else {
			System.out.println("No hay reservas que listar para esa permanencia.");
		}
	}
	
	public void consultarDisponibilidad() {
		Aula aula=Consola.leerAula();
		LocalDate dia=Consola.leerDia();
		Tramo tramo=Consola.leerTramo();
		Permanencia permanencia=new Permanencia(dia, tramo);
		modelo.consultarDisponibilidad(modelo.buscarAula(aula), permanencia);
	}
	
}
