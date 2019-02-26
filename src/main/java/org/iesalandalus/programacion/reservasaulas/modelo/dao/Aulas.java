package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

public class Aulas {
	private static final int MAX_AULAS=20;
	private Aula[] aulas;
	private int numAulas;
	
	public Aulas() {
		aulas=new Aula[MAX_AULAS];
		numAulas=0;
	}
	
	public Aulas(Aulas otrasAulas) {
		setAulas(otrasAulas);
	}
	
	private void setAulas(Aulas aulas) {
		if (aulas==null)
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		
		this.aulas=copiaProfundaAulas(aulas.aulas);			
		
	}
	
	private Aula[] copiaProfundaAulas(Aula[] aulas) {
		Aula[] otrasAulas=new Aula[MAX_AULAS];
		for (int i=0;i<MAX_AULAS && aulas[i]!=null ;i++) {
			otrasAulas[i]=new Aula(aulas[i]);
		}
		return otrasAulas;
	}

	public Aula[] getAulas() {
		return copiaProfundaAulas(aulas);
	}

	public int getNumAulas() {
		return numAulas;
	}
	
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula==null)
			throw new IllegalArgumentException ("No se puede insertar un aula nula.");
		int indice = buscarIndiceAula(aula);
		if (!indiceNoSuperaTamano(indice)) {
			aulas[indice] = aula;
			numAulas++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("El aula ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más aulas.");
			}
		}
	}
		
	
	private int buscarIndiceAula(Aula aula) {
		int indice=0;
		boolean aulaEncontrada = false;
		while (indiceNoSuperaTamano(indice) && !aulaEncontrada) {
			if (aulas[indice].equals(aula)) {
				aulaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		return indice<numAulas;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) throws OperationNotSupportedException {
		return indice<MAX_AULAS;
	}

	
	public Aula buscar(Aula aula) {
		int indice = 0;
		indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			return aulas[indice];
		} else {
			return null;
		}			
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula==null)
			throw new IllegalArgumentException ("No se puede borrar un aula nula.");
		
		int indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
		else {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numAulas - 1; i++) {
			aulas[i] = aulas[i+1];
		}
		aulas[numAulas] = null;
		numAulas--;
	}
	
	public String[] representar() {
		String[] representacion=new String[numAulas];
		for (int i=0;indiceNoSuperaTamano(i);i++) {
			representacion[i]=aulas[i].toString();			
		}
		return representacion;
	}
	

}
