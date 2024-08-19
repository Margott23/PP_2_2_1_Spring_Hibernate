package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImp implements UserService {

   private UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Override
   public void addUser(User user) {
      userDao.addUser(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUser(String username) {
      return userDao.getUser(username);
   }

   @Override
   public void deleteUser(User user) {
      userDao.deleteUser(user);
   }

   @Override
   public void addCar(Car car) {
      userDao.addCar(car);
   }

   @Override
   public User getUserByCar(String model, Integer series) {
      return userDao.getUserByCar(model, series);
   }

}
