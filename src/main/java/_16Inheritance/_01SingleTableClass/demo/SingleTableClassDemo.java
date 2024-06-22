package _16Inheritance._01SingleTableClass.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _16Inheritance._01SingleTableClass.entity.ContractualEmployee;
import _16Inheritance._01SingleTableClass.entity.Employee;
import _16Inheritance._01SingleTableClass.entity.RegularEmployee;

public class SingleTableClassDemo {

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

		Employee emp = new Employee(null, "Gangadhar Panda", "Pune");

		RegularEmployee rEmployee = new RegularEmployee(100d, 100d, 100d, 10000d);
		rEmployee.setName("Bum Bahadoor");
		rEmployee.setAddress("Phulera , UP");

		ContractualEmployee cEmployee = new ContractualEmployee(11000d);
		cEmployee.setName("Bhushan");
		cEmployee.setAddress("Phulera , UP");

		session.beginTransaction();
		session.persist(emp);
		session.persist(rEmployee);
		session.persist(cEmployee);

		session.getTransaction().commit();

		session.close();

		factory.close();

	}

}
