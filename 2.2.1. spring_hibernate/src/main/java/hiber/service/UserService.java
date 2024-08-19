package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> listUsers();

    User getUser(String username);

    void deleteUser(User user);

    void addCar(Car car);

    User getUserByCar(String model, Integer series);
}
