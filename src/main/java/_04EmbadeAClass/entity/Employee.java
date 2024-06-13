package _04EmbadeAClass.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "employee_with_office_address")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String name;

	// Don't want a separate column for officeAddress attribute , but a list of
	// columns instead

	// We have to add this AttributeOverride because we want to use the same class
	// Address two times
	// If we don't give use Attribute Override , it will try to create same column
	// for city , address , pin and state
	// two times and it's not possible to have two duplicate columns in the same
	// table
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "office_street")),
			@AttributeOverride(name = "state", column = @Column(name = "office_state")),
			@AttributeOverride(name = "city", column = @Column(name = "office_city")),
			@AttributeOverride(name = "pin", column = @Column(name = "office_pin")) })
	private Address officeAddress;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "home_street")),
			@AttributeOverride(name = "state", column = @Column(name = "home_state")),
			@AttributeOverride(name = "city", column = @Column(name = "home_city")),
			@AttributeOverride(name = "pin", column = @Column(name = "home_pin")) })
	private Address homeAddress;
	
	
}
