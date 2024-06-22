package _17Inheritance._01TablePerClass.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "employee_inhertance_tpc")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	private String name;
	private String address;

}
