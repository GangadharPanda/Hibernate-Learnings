package _11FetchTypeEAGERWithCollections.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _04EmbadeAClass.entity.Address;
import _11FetchTypeEAGERWithCollections.entity.EmployeeFetchTypeEAGER;

public class FetchTypeEAGERDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg-create.xml")
				.addAnnotatedClass(EmployeeFetchTypeEAGER.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Create Address Object

		Address homeAddress = new Address("Sriram path", "Jharkahand", "Daltonganj", "822102");

		Address officeAddress = new Address("Giga Space IT park", "Maharashtra", "Pune", "411014");

		List<Address> officeAddresses = List.of(homeAddress, officeAddress);

		// Create Employee Object

		EmployeeFetchTypeEAGER employee = new EmployeeFetchTypeEAGER(null, "Gangadhar Panda",
				officeAddresses);

		// Begin transaction

		session.beginTransaction();

		// Persist the data into the table

		session.persist(employee);

		// Commit transaction

		session.getTransaction().commit();

		// Close session

		session.close();

		// ------------- Get the saved Element ----------

		// Open session

		session = factory.openSession();

		session.beginTransaction();

		// Get the Employee object

		EmployeeFetchTypeEAGER employeeHaveCollectionOfAddress = session
				.get(EmployeeFetchTypeEAGER.class, 1);

		/*
		 * select
		        e1_0.id,
		        l1_0.emp_id,
		        l1_0.city,
		        l1_0.pin,
		        l1_0.state,
		        l1_0.street,
		        e1_0.name 
		    from
        		employee_with_office_address e1_0 
		    left join
		        emp_address l1_0 
		            on e1_0.id=l1_0.emp_id 
		    where
		        e1_0.id=?
		 * 
		 */

		session.getTransaction().commit();

		session.close();

		for (Address address : employeeHaveCollectionOfAddress.getListOfOffices()) {
			System.out.println(address);
		}

		// Close sessionFactory

		factory.close();

	}
}
