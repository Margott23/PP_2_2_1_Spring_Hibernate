package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addUser(new User("Tom", "Catson", "tom@mail.ru"));
        userService.addUser(new User("Jery", "Mousen", "jery@mail.ru"));
        userService.addUser(new User("Willy", "Wonka", "ww@mail.ru"));
        userService.addUser(new User("Scrooge", "McDuck", "money@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        userService.addCar(new Car(userService.listUsers().get(0), "AUDI", 100));
        userService.addCar(new Car(userService.listUsers().get(1), "BMW", 3));
        userService.addCar(new Car(userService.listUsers().get(2), "Lada", 14));
        userService.addCar(new Car(userService.listUsers().get(3), "Mirach", 3));

        User mark = new User("Mark", "Thompson", "tompson@gmail.com");
        Car car5 = new Car(mark, "Mark II", 3);
        mark.setCar(car5);
        userService.addUser(mark);

        User bonnie = new User("Bonnie", "Shot", "bang@gmail.com");
        Car car6 = new Car(bonnie, "Ford model T", 1);
        bonnie.setCar(car6);
        userService.addUser(bonnie);

        userService.deleteUser(mark);
        userService.deleteUser(bonnie);

        User user = userService.getUserByCar("LADA", 14);
        System.out.println("Who have car LADA: " + user);

        for (User anyUser : userService.listUsers()) {
            System.out.println(anyUser.getFirstName() + " have " + anyUser.getCar());
        }

        context.close();
    }
}
