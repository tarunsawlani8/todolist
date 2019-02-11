package com.deloitte.todo.model;

public abstract class CommonResponseModel {
	
	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
