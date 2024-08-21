package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(String username) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select u from User u where u.firstName=:username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user.getCar());
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getUserByCar(String model, Integer series) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Query<Car> query = session.createQuery("select c from Car c where c.model = :model " +
                    "and c.series = :series", Car.class);
            query.setParameter("model", model);
            query.setParameter("series", series);
            Car car = query.uniqueResult();
            return car.getUser();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void setCarByUser(User user, Car car) {
        user.setCar(car);
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
}
