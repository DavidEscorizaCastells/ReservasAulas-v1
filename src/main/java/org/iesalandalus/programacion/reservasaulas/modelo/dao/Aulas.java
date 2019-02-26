package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

public class Aulas {
	private List<Aula>aulas;
	
	public Aulas() {
		aulas=new ArrayList<>();
	}
	
	public Aulas(Aulas otrasAulas) {
		setAulas(otrasAulas);
	}
	
	private void setAulas(Aulas aulas) {
		if (aulas==null)
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		
		this.aulas=copiaProfundaAulas(aulas.aulas);			
		
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> aulas) {
		List<Aula> otrasAulas = new ArrayList<>();
		for (Aula aula: aulas) {
			otrasAulas.add(new Aula(aula));
		}
		return otrasAulas;
	}
	


	public List<Aula> getAulas() {
		return copiaProfundaAulas(aulas);
	}

	public int getNumAulas() {
		return aulas.size();
	}
	
	public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula==null)
			throw new IllegalArgumentException ("No se puede insertar un aula nula.");
		if (aulas.contains(aula)) {
			throw new OperationNotSupportedException("El aula ya existe.");
		}
		aulas.add(new Aula(aula));
	}
	
	public Aula buscar(Aula aula) { 
		int indice = aulas.indexOf(aula);
		if (indice != -1) {
			return new Aula(aulas.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
		if (!aulas.remove(aula)) {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}
	
	public List<String> representar() {
		List <String> representacion=new ArrayList<>();
		for (Aula aula : aulas) {
			representacion.add(aula.toString());
		}
		return representacion;
	}
}
