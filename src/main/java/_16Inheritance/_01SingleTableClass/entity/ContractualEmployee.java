package _16Inheritance._01SingleTableClass.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "con_emp")
public class ContractualEmployee extends Employee {

	private Double consolidatedPay;
}
