package pti.sb_rentacar_mvc.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_rentacar_mvc.model.Car;
import pti.sb_rentacar_mvc.model.Reservation;

@Repository
public class Database {

	private SessionFactory sessionFactory;

	public Database() {

		Configuration cfg = new Configuration();
		cfg.configure();

		this.sessionFactory = cfg.buildSessionFactory();
	}

	public List<Car> getAvaibleCars(LocalDate startDate, LocalDate endDate) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		List <Car> allCarList = this.getAllCar();
		
		List<Integer> bannedCarIdList = this.getRentedCarsId(startDate, endDate);
		
		
		System.out.println("bannedIds: " + bannedCarIdList);
		System.out.println("allcars: " + allCarList);
		
		for (int index = 0; index < allCarList.size(); index++) {
			
			Car currentCar = allCarList.get(index);
			
			if(bannedCarIdList.contains(currentCar.getId())) {
				
				allCarList.remove(index);
				index--;
				
			}
			
		}
		
		System.out.println();
		
		tx.commit();
		session.close();
		return allCarList;
	}
	
	public List<Car> getAllCar(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Car> query = session.createSelectionQuery("SELECT c FROM Car c WHERE c.active = true",Car.class);
		List<Car> cars = query.getResultList();
		
		tx.commit();
		session.close();
		return cars;
	}
	
	private List<Integer> getRentedCarsId(LocalDate startDate, LocalDate endDate){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createNativeQuery("SELECT car_id FROM reservations WHERE "
				
				+ "(start_date >= ?1 AND end_date <= ?2)OR"
				+ "(start_date < ?1 AND end_date > ?1)"
				+ " OR (start_date < ?2 AND end_date > ?2)", Object.class);
		
		query.setParameter(1, startDate);
		query.setParameter(2, endDate);
		List<Object> rows = query.getResultList();
		List<Integer> integerList = new ArrayList<>();
		for(int index = 0; index < rows.size(); index++) {
			
			
			
			Integer currentInt = Integer.parseInt(rows.get(index).toString());
			integerList.add(currentInt);
			
			
		}
		
		
		
		tx.commit();
		session.close();
		
		return integerList;
	}

	public Car getCarById(int carId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Car car = session.get(Car.class, carId);
		
		tx.commit();
		session.close();
		return car;
	}

	public void saveReservation(Reservation reservation) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(reservation);
		
		tx.commit();
		session.close();
		
	}

	public List<Reservation> getAllReservation() {
		

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Reservation> query = session.createSelectionQuery("SELECT r FROM Reservation r",Reservation.class);
		
		
		List<Reservation> reservations = query.getResultList();
		
		tx.commit();
		session.close();
		return reservations;
		
	
	}

	public void updateCar(Car car) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(car);
		
		tx.commit();
		session.close();
		
	}

	public void inserNewCar(Car car) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(car);
		
		tx.commit();
		session.close();
		
	}
}
