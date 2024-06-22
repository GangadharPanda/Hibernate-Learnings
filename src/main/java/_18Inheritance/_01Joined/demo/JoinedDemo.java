package _18Inheritance._01Joined.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _18Inheritance._01Joined.entity.ContractualEmployee;
import _18Inheritance._01Joined.entity.Employee;
import _18Inheritance._01Joined.entity.RegularEmployee;
public class JoinedDemo {

	public static void main(String[] args) {

		// @formatter:off
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(RegularEmployee.class)
				.addAnnotatedClass(ContractualEmployee.class)
				.buildSessionFactory();
		// @formatter:on

		Session session = factory.openSession();

		session.beginTransaction();
		
		Employee emp = new Employee(null, "Gangadhar Panda", "Pune");
		session.persist(emp);
		RegularEmployee rEmployee = new RegularEmployee(null,100d, 100d, 100d, 10000d);
		rEmployee.setName("Bum Bahadoor (Kabootar Wala)");
		rEmployee.setAddress("Phulera , UP");
		session.persist(rEmployee);

		ContractualEmployee cEmployee = new ContractualEmployee(null, 11000d);
		cEmployee.setName("Banrakas");
		cEmployee.setAddress("Phulera , UP");
		session.persist(cEmployee);

		session.getTransaction().commit();

		session.close();

		factory.close();

	}

}
