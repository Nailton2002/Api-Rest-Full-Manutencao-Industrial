package com.manutencao.industrial.infra.validation;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id){
		super("Tarefa finalizada " + id);
	}
}
