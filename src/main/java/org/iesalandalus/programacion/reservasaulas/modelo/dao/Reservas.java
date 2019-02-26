package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {
	private static final int MAX_RESERVAS=20;
	private Reserva[] reservas;
	private int numReservas;
	
	public Reservas() {
		reservas=new Reserva[MAX_RESERVAS];
		numReservas=0;
	}
	
	public Reservas(Reservas otrasReservas) {
		setReservas(otrasReservas);
	}
	
	private void setReservas(Reservas reservas) {
		if (reservas==null)
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		
		this.reservas=copiaProfundaReservas(reservas.reservas);			
		
	}
	
	private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
		Reserva[] otrasReservas=new Reserva[MAX_RESERVAS];
		for (int i=0;i<MAX_RESERVAS && reservas[i]!=null ;i++) {
			otrasReservas[i]=new Reserva(reservas[i]);
		}
		return otrasReservas;
	}

	public Reserva[] getReservas() {
		return copiaProfundaReservas(reservas);
	}

	public int getNumReservas() {
		return numReservas;
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva==null)
			throw new IllegalArgumentException ("No se puede realizar una reserva nula.");
		
		int indice = buscarIndiceReserva(reserva);
		if (!indiceNoSuperaTamano(indice)) {
			reservas[indice] = reserva;
			numReservas++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("La reserva ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más reservas.");
			}
		}
	}	
	
	private int buscarIndiceReserva(Reserva reserva) {
		int indice=0;
		boolean reservaEncontrada = false;
		while (indiceNoSuperaTamano(indice) && !reservaEncontrada) {
			if (reservas[indice].equals(reserva)) {
				reservaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		return indice<numReservas;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice<MAX_RESERVAS;
	}

	
	public Reserva buscar(Reserva reserva) {
		int indice = 0;
		indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			return reservas[indice];
		} else {
			return null;
		}
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva==null)
			throw new IllegalArgumentException ("No se puede anular una reserva nula.");
		
		int indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
		else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquierda (int indice) {
		for (int i = indice; i < numReservas - 1; i++) {
			reservas[i] = reservas[i+1];
		}
		reservas[numReservas] = null;
		numReservas--;
	}
	
	public String[] representar() {
		String[] representacion=new String[numReservas];
		for (int i=0;i<numReservas;i++) {
			representacion[i]=reservas[i].toString();			
		}
		return representacion;
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) {
		Reserva[] reservasProfesor=new Reserva[MAX_RESERVAS];
		for (int i=0; i<MAX_RESERVAS && reservas[i]!=null; i++) {
			if (profesor.equals(reservas[i].getProfesor()))
				reservasProfesor[i]=reservas[i];
		}
		return reservasProfesor;
	}
	
	public Reserva[] getReservasAula(Aula aula) {
		Reserva[] reservasAula=new Reserva[MAX_RESERVAS];
		for (int i=0; i<MAX_RESERVAS && reservas[i]!=null; i++) {
			if (aula.equals(reservas[i].getAula()))
				reservasAula[i]=reservas[i];
		}
		return reservasAula;
	}
	
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reservasPermanencia=new Reserva[MAX_RESERVAS];
		int k=0;
		for (int i=0; i<MAX_RESERVAS && reservas[i]!=null; i++) {
			if (permanencia.equals(reservas[i].getPermanencia())) {
				reservasPermanencia[k]=reservas[i];
				k++;
			}
		}
		return reservasPermanencia;
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula==null)
			throw new IllegalArgumentException ("No se puede consultar la disponibilidad de un aula nula.");
		if (permanencia==null)
			throw new IllegalArgumentException ("No se puede consultar la disponibilidad de una permanencia nula.");
		for (int i=0; i<MAX_RESERVAS && reservas[i]!=null; i++)
			if (reservas[i].getAula().equals(aula) && reservas[i].getPermanencia().equals(permanencia))
				return false;
		return true;
	}
	
	
}
