package com.demo.crudrestful.cars;

public class CarNotFoundException extends RuntimeException  {

	public CarNotFoundException(String exception) {
		super(exception);
	}
}
