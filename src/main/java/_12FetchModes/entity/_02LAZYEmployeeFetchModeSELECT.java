package _12FetchModes.entity;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import _04EmbadeAClass.entity.Address;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "lazy_employee_with_office_address")
public class _02LAZYEmployeeFetchModeSELECT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String name;

	@ElementCollection(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	// When FetchMode is SELECT , it respects the fetchType - EAGER : 2 select Queries
	// When FetchMode is SELECT , it respects the fetchType - LAZY : 1 select Queries
	@JoinTable(name = "emp_address_lazy", joinColumns = @JoinColumn(name = "emp_id"))
	private List<Address> listOfOffices;
}
