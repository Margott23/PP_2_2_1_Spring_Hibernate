package hiber.service;

import hiber.model.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    List<Car> listCars();

    Car getCar(String model);

    void deleteCar(Car car);
}
