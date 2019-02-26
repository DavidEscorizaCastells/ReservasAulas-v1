package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {
	private List<Reserva> reservas;
	
	public Reservas() {
		reservas=new ArrayList<>();
	}
	
	public Reservas(Reservas otrasReservas) {
		setReservas(otrasReservas);
	}
	
	private void setReservas(Reservas reservas) {
		if (reservas==null)
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		
		this.reservas=copiaProfundaReservas(reservas.reservas);			
		
	}
	
	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> otrasReservas = new ArrayList<>();
		for (Reserva reserva: reservas) {
			otrasReservas.add(new Reserva(reserva));
		}
		return otrasReservas;
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(reservas);
	}

	public int getNumReservas() {
		return reservas.size();
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva==null)
			throw new IllegalArgumentException ("No se puede realizar una reserva nula.");
		if (reservas.contains(reserva)) {
			throw new OperationNotSupportedException("La reserva ya existe.");
		}
		reservas.add(new Reserva(reserva));
	}
	
	public Reserva buscar(Reserva reserva) {
		int indice = reservas.indexOf(reserva);
		if (indice != -1) {
			return new Reserva(reservas.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva==null)
			throw new IllegalArgumentException ("No se puede anular una reserva nula.");
		
		if (!reservas.remove(reserva)) {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}
	
	public List<String> representar() {
		List <String> representacion=new ArrayList<>();
		for (Reserva reserva : reservas) {
			representacion.add(reserva.toString());
		}
		return representacion;
	}
	
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		List<Reserva> reservasProfesor=new ArrayList<>();
		for (Reserva reserva:reservas) {
			if (profesor.equals(reserva.getProfesor()))
				reservasProfesor.add(reserva);
		}
		return reservasProfesor;
	}
	
	public List<Reserva> getReservasAula(Aula aula) {
		List<Reserva> reservasAula=new ArrayList<>();
		for (Reserva reserva:reservas) {
			if (aula.equals(reserva.getAula()))
				reservasAula.add(reserva);
		}
		return reservasAula;
	}
	
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		List <Reserva> reservasPermanencia=new ArrayList<>();
		for (Reserva reserva:reservas) {
			if (permanencia.equals(reserva.getPermanencia())) {
				reservasPermanencia.add(reserva);
			}
		}
		return reservasPermanencia;
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula==null)
			throw new IllegalArgumentException ("No se puede consultar la disponibilidad de un aula nula.");
		if (permanencia==null)
			throw new IllegalArgumentException ("No se puede consultar la disponibilidad de una permanencia nula.");
		for (Reserva reserva:reservas)
			if (reserva.getAula().equals(aula) && reserva.getPermanencia().equals(permanencia))
				return false;
		return true;
	}	
	
}
