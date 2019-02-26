package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola() {}
	
	public static void mostrarMenu() {
		mostrarCabecera("Gestión de reservas");
		for (Opcion opcion: Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Aula leerAula() {		
		return new Aula(leerNombreAula());
	}
	
	public static String leerNombreAula() {
		System.out.print("Introduce el nombre del aula: ");
		return Entrada.cadena();
	}
	
	public static Profesor leerProfesor() {	
		String nombre=leerNombreProfesor();
		System.out.print("Introduce correo del profesor: ");
		String correo=Entrada.cadena();
		System.out.print("Introduce teléfono del profesor (si tiene): ");
		String telefono=Entrada.cadena();
		if (telefono.trim().equals(""))
			return new Profesor(nombre, correo);
		else
			return new Profesor(nombre, correo, telefono);
	}
	
	public static String leerNombreProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		return Entrada.cadena();
	}
	
	public static Tramo leerTramo() {
		String tramo;
		do {
		System.out.print("Selecciona mañana o tarde: ");
		tramo = Entrada.cadena();
		} while (!tramo.toLowerCase().equals("mañana")|| !tramo.toLowerCase().equals("tarde"));
		
		if (tramo.equalsIgnoreCase("mañana"))
			return Tramo.MANANA;
		else
			return Tramo.TARDE;		
	}
	
	public static LocalDate leerDia() {
		int year, mes, dia;
		
		do {
			System.out.print("Indroduce año: ");
			year=Entrada.entero();
		} while (year<2019);
		
		do {
			System.out.print("Introduce mes (1-12): ");
			mes=Entrada.entero();
		} while (mes<1 || mes>12);
		
		int mayorDia=0;
		switch (mes) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				mayorDia=31;
				break;
			case 4: case 6: case 9: case 11:
				mayorDia=30;
				break;
			case 2:
				if (year%400==0 || year%4==0 && year%100!=0)
					mayorDia=29;
				else
					mayorDia=28;	
		}
		
		do {
			System.out.println("Introduce dia válido para el mes: ");
			dia=Entrada.entero();
		} while (dia<1 || dia>mayorDia);
	
		return LocalDate.of(dia, mes, year);
	}	
}
