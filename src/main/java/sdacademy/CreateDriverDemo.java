package sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sdacademy.demo.entity.BusLine;
import sdacademy.demo.entity.Driver;
import sdacademy.demo.entity.DriverDetail;

public class CreateDriverDemo {

    public static void main(String[] args) {
        //tworzenie fabryki
        // dodanie konfiguracji, dodanie klas
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DriverDetail.class)
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(BusLine.class)
                .buildSessionFactory();
        //tworzenie sesji na potrzeby naszej pracy
        Session session = factory.getCurrentSession();

        try {

            Driver driver = new Driver("Klaudia", "Nowacka");
            DriverDetail driverDetail = new DriverDetail("T", "999888777");

            driver.setDriverDetail(driverDetail);
            //rozpocznij transakcję żeby zapisać
            session.beginTransaction();

            session.save(driver);

            //zakomituj transakcję
            session.getTransaction().commit();

        } finally {
            //posprzątaj po otwartej sesji
            session.close();
            factory.close();
        }
    }

}
