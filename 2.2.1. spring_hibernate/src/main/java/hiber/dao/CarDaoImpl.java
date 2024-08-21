package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car", Car.class);
        return query.getResultList();
    }

    @Override
    public Car getCar(String model) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("select c from Car c where c.model = :model", Car.class);
        query.setParameter("model", model);
        return query.getSingleResult();
    }

    @Override
    public void deleteCar(Car car) {
        sessionFactory.getCurrentSession().delete(car.getUser());
        sessionFactory.getCurrentSession().delete(car);
    }
}
