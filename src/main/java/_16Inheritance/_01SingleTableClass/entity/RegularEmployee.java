package _16Inheritance._01SingleTableClass.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "reg_emp")
public class RegularEmployee extends Employee{
	private Double basic;
	private Double da;
	private Double hra;
	private Double salary;
}
