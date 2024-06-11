package _01MappingDateAndTime.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@ToString
@NoArgsConstructor
@Setter
@Getter
public class Employee {

	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	/**
	 * GeneratedValue , without strategy or GenerationType.SEQUENCE, means it will create a separate sequence table 
	 * 
	 * 'employee_SEQ' and the next value will be decided using this table 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private Integer id;

	@Column(name = "emp_name")
	private String name;

	@Temporal(TemporalType.DATE) 
	// By default all the date are saved as Timestamp , we can use TemporalType.DATE to save it as Date
	@Column(name = "date_of_joining")
	private Date dateOfJoining;

	@Temporal(TemporalType.TIMESTAMP) //Anyways, TIMESTAMP is the default value as well, column is DATETIME
	@Column(name = "last_logged_in")
	private Date lastLoggedIn;
	
	

}
