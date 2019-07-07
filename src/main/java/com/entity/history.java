package com.entity;

public class history<T> {

	private Operation operation;
	
	private T history;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public T getHistory() {
		return history;
	}

	public void setHistory(T history) {
		this.history = history;
	}
	
}
