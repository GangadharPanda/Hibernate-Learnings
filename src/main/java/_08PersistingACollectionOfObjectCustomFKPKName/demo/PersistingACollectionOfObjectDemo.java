package _08PersistingACollectionOfObjectCustomFKPKName.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import _08PersistingACollectionOfObjectCustomFKPKName.entity.Address;
import _08PersistingACollectionOfObjectCustomFKPKName.entity.Employee;

public class PersistingACollectionOfObjectDemo {

	public static void main(String[] args) {

		// Create SessionFactory

		SessionFactory factory = new Configuration().configure("hibernate.cfg-create.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		// Create Session

		Session session = factory.openSession();

		// Create Address Object

		Address homeAddress = new Address("Sriram path", "Jharkahand", "Daltonganj", "822102");

		Address officeAddress = new Address("Giga Space IT park", "Maharashtra", "Pune", "411014");

		List<Address> officeAddresses = List.of(homeAddress, officeAddress);

		// Create Employee Object

		Employee employee = new Employee(null, "Gangadhar Panda", officeAddresses);

		// begin transaction

		session.beginTransaction();

		// persist the data into the table

		session.persist(employee);

		// commit transaction

		session.getTransaction().commit();

		// close session

		session.close();

		// close sessionFactory

		factory.close();

	}

	/*
	 * 
	 * WARN: GenerationTarget encountered exception accepting command : Error
	 * executing DDL " 
	 * 
	 * alter table emp_address drop foreign key
	 * FK7n4jr873xln58qjdmf4es0nlc" via JDBC Statement
	 * 
	 * 
	 * org.hibernate.tool.schema.spi.CommandAcceptanceException: 
	 * 
	 * Error executing DDL
	 * " alter table emp_address drop foreign key
	 * FK7n4jr873xln58qjdmf4es0nlc" via JDBC Statement
	 * 
	 * 
	 * 
	 * The issue arises because with hbm2ddl.auto=create , Hibernate tries to drop
	 * all existing tables and then recreate them based on your entity mappings. If
	 * the foreign key constraint references a table (Address) that hasn't been
	 * created yet, Hibernate might attempt to drop the foreign key first, leading
	 * to the error message you're seeing.
	 * 
	 * 
	 * 
	 * create table emp_address ( // name of the join table
       emp_id integer not null,// Name of join column
        city varchar(255),
        pin varchar(255),
        state varchar(255),
        street varchar(255)
    ) engine=InnoDB
	 * 
	 */

}
