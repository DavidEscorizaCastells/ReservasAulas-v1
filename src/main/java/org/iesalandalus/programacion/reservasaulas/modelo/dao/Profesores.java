package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

public class Profesores {
	private List<Profesor> profesores;

	public Profesores() {
		profesores=new ArrayList<>();
	}

	public Profesores(Profesores otrosProfesores) {
		setProfesores(otrosProfesores);
	}
	
	private void setProfesores(Profesores profesores) {
		if (profesores==null)
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		
		this.profesores=copiaProfundaProfesores(profesores.profesores);	
	}
	
	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {
		List<Profesor> otrosProfesores = new ArrayList<>();
		for (Profesor profesor: profesores) {
			otrosProfesores.add(new Profesor(profesor));
		}
		return otrosProfesores;
	}
	
	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(profesores);
	}
	
	public int getNumProfesores() {
		return profesores.size();
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor==null)
			throw new IllegalArgumentException ("No se puede insertar un profesor nulo.");
		
		if (profesores.contains(profesor)) {
			throw new OperationNotSupportedException("El profesor ya existe.");
		}
		profesores.add(new Profesor(profesor));
	}
	
	public Profesor buscar(Profesor profesor) {
		int indice = profesores.indexOf(profesor);
		if (indice != -1) {
			return new Profesor(profesores.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
		}
		if (!profesores.remove(profesor)) {
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		}
	}
	
	
	public List<String> representar() {
		List <String> representacion=new ArrayList<>();
		for (Profesor profesor: profesores) {
			representacion.add(profesor.toString());		
		}
		return representacion;
	}
	
}