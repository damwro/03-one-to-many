package sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sdacademy.demo.entity.BusLine;
import sdacademy.demo.entity.Driver;
import sdacademy.demo.entity.DriverDetail;

public class CreateBusLineDemo {

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
            //rozpocznij transakcję żeby zapisać
            session.beginTransaction();

            //pobieranie kierowcy
            Long id = 1L;
            Driver driver = session.get(Driver.class, id);

            //tworzenie linii autobusowych
            BusLine busLine1 = new BusLine("Linia 6");
            BusLine busLine5 = new BusLine("Linia 10");

            //dodawanie lini do kierowcy
            driver.addBusLine(busLine1);
            driver.addBusLine(busLine5);

            //zapisywanie linii
            session.save(busLine1);
            session.save(busLine5);

            //zakomituj transakcję
            session.getTransaction().commit();

        } finally {
            //posprzątaj po otwartej sesji
            session.close();
            factory.close();
        }
    }

}
