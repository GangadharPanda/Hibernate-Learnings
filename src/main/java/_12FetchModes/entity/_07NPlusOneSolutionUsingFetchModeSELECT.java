package _12FetchModes.entity;

import java.util.List;

import org.hibernate.annotations.BatchSize;
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
@Table(name = "eager_employee_with_office_address")
public class _07NPlusOneSolutionUsingFetchModeSELECT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@BatchSize(size = 2)
	// @formatter:off
	// After fetching the Employee, it will fetch data for 2 employees together so that
	// the number of queries gets reduced
	// for example , we have 4 employees
	// so total number of queries will be
	// 1 - To fetch all the employees
	// 2 - Fetch the addresses of two employees (as batch size is 2 ) two times
	//@formatter:on
	@JoinTable(name = "emp_address_eager", joinColumns = @JoinColumn(name = "emp_id"))
	private List<Address> listOfOffices;
}
