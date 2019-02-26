package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

public class Profesores {
	private static final int MAX_PROFESORES=20;
	private Profesor[] profesores;
	private int numProfesores;

	public Profesores() {
		profesores=new Profesor[MAX_PROFESORES];
		numProfesores=0;
	}

	public Profesores(Profesores otrosProfesores) {
		setProfesores(otrosProfesores);
	}
	
	private void setProfesores(Profesores profesores) {
		if (profesores==null)
			throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
		
		this.profesores=copiaProfundaProfesores(profesores.profesores);	
	}
	
	private Profesor[] copiaProfundaProfesores (Profesor[] profesores) {
		Profesor[] otrosProfesores=new Profesor[MAX_PROFESORES];
		for (int i=0;i<MAX_PROFESORES && profesores[i]!=null ;i++) {
			otrosProfesores[i]=new Profesor(profesores[i]);
		}
		return otrosProfesores;
	}
	
	public Profesor[] getProfesores() {
		return copiaProfundaProfesores(profesores);
	}
	
	public int getNumProfesores() {
		return numProfesores;
	}
	
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor==null)
			throw new IllegalArgumentException ("No se puede insertar un profesor nulo.");
		
		int indice = buscarIndiceProfesor(profesor);
		if (!indiceNoSuperaTamano(indice)) {
			profesores[indice] = profesor;
			numProfesores++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("El profesor ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más profesores.");
			}
		}
	}
	
	private int buscarIndiceProfesor(Profesor profesor) {
		int indice=0;
		boolean profesorEncontrado = false;
		while (indiceNoSuperaTamano(indice) && !profesorEncontrado) {
			if (profesores[indice].equals(profesor)) {
				profesorEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		return indice<numProfesores;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice<MAX_PROFESORES;
	}
	
	public Profesor buscar(Profesor profesor) {
		int indice = 0;
		indice = buscarIndiceProfesor(profesor);
		if (indiceNoSuperaTamano(indice)) {
			return profesores[indice];
		} else {
			return null;
		}	
	}
	
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor==null)
			throw new IllegalArgumentException ("No se puede borrar un profesor nulo.");
		
		int indice = buscarIndiceProfesor(profesor);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
		else {
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquierda (int indice) {
		for (int i = indice; i < numProfesores - 1; i++) {
			profesores[i] = profesores[i+1];
		}
		profesores[numProfesores] = null;
		numProfesores--;
	}
	
	public String[] representar() {
		String[] representacion=new String[numProfesores];
		for (int i=0;i<numProfesores;i++) {
			representacion[i]=profesores[i].toString();			
		}
		return representacion;
	}
	
}