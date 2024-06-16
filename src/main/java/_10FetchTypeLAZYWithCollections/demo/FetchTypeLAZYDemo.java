package _10FetchTypeLAZYWithCollections.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _04EmbadeAClass.entity.Address;
import _10FetchTypeLAZYWithCollections.entity.EmployeeFetchTypeLAZY;

public class FetchTypeLAZYDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg-create.xml")
				.addAnnotatedClass(EmployeeFetchTypeLAZY.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Create Address Object

		Address homeAddress = new Address("Sriram path", "Jharkahand", "Daltonganj", "822102");

		Address officeAddress = new Address("Giga Space IT park", "Maharashtra", "Pune", "411014");

		List<Address> officeAddresses = List.of(homeAddress, officeAddress);

		// Create Employee Object

		EmployeeFetchTypeLAZY employee = new EmployeeFetchTypeLAZY(null, "Gangadhar Panda",
				officeAddresses);

		// begin transaction

		session.beginTransaction();

		// persist the data into the table

		session.persist(employee);

		// commit transaction

		session.getTransaction().commit();

		// close session

		session.close();

		// ------------- Get the saved Element ----------

		// Open session

		session = factory.openSession();

		session.beginTransaction();

		// Get the Employee object

		EmployeeFetchTypeLAZY employeeHaveCollectionOfAddress = session
				.get(EmployeeFetchTypeLAZY.class, 1);

		/*
		 * By default fetch Type is LAZY so only one Query will be there to fetch the
		 * employee select e1_0.id, e1_0.name from employee_with_office_address e1_0
		 * where e1_0.id=?
		 * 
		 */

		session.getTransaction().commit();

		session.close();
		
		//ERROR HERE 
		
		/*
		 * 
		 * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: 
		 * _10FetchTypeWithCollections.entity.EmployeeHaveCollectionOfAddress.listOfOffices: 
		 * could not initialize proxy - no Session
		 * 
		 * Because earlier to to lazy fetch type , only Employee object was fetched , so when we close the session 
		 * and try to print addresses , it fails 
		 * because addresses were not fetched at that time.
		 */

		for (Address address : employeeHaveCollectionOfAddress.getListOfOffices()) {
			System.out.println(address);
		}

		// close sessionFactory

		factory.close();

	}
}
