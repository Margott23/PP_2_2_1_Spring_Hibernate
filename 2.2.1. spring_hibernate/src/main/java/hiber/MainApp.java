package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.addUser(new User("Tom", "Catson", "tom@mail.ru"));
        userService.addUser(new User("Jerry", "Mousen", "jery@mail.ru"));
        userService.addUser(new User("Willy", "Wonka", "ww@mail.ru"));
        userService.addUser(new User("Scrooge", "McDuck", "money@mail.ru"));

        carService.addCar(new Car("AUDI", 100));
        carService.addCar(new Car("BMW", 3));
        carService.addCar(new Car("Lada", 14));
        carService.addCar(new Car("Mirach", 3));

        User user1 = userService.getUser("Tom");
        User user2 = userService.getUser("Jerry");
        User user3 = userService.getUser("Willy");
        User user4 = userService.getUser("Scrooge");

        Car car1 = carService.getCar("AUDI");
        Car car2 = carService.getCar("BMW");
        Car car3 = carService.getCar("Lada");
        Car car4 = carService.getCar("Mirach");

        userService.setCarByUser(user1, car1);
        userService.setCarByUser(user2, car2);
        userService.setCarByUser(user3, car3);
        userService.setCarByUser(user4, car4);

        System.out.println(userService.getUserByCar("AUDI", 100));
    }
}
