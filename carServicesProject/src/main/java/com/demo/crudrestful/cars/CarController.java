package com.demo.crudrestful.cars;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class CarController {

	@Autowired
	private CarRepository carRepository;

	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@GetMapping("/cars")
	public List<Car> retrieveAllCars() {
		return carRepository.findAll();
	}
	
	@GetMapping("/cars/{id}")
	public Car retrieveCar(@PathVariable long id) {
		Optional<Car> car = carRepository.findById(id);

		if (!car.isPresent())
			throw new CarNotFoundException("car with id-" + id + " not found");

		return car.get();
	}

	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathVariable long id) {
		carRepository.deleteById(id);
	}

	@PostMapping("/cars")
	public ResponseEntity<Object> createCar(@RequestBody Car car) {
		Car savedCar = carRepository.save(car);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCar.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/cars/{id}")
	public ResponseEntity<Object> updateCar(@RequestBody Car car, @PathVariable long id) {

		Optional<Car> carOptional = carRepository.findById(id);

		if (!carOptional.isPresent())
			return ResponseEntity.notFound().build();

		car.setId(id);
		
		carRepository.save(car);

		return ResponseEntity.noContent().build();
	}
}
