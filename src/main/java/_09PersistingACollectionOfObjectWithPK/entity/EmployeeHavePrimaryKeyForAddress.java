package _09PersistingACollectionOfObjectWithPK.entity;

import java.util.List;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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
@Table(name = "employee_with_office_address")
public class EmployeeHavePrimaryKeyForAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column
	private String name;

	@ElementCollection
	@JoinTable(name = "emp_address", joinColumns = @JoinColumn(name = "emp_id"))
	@GenericGenerator(name = "MyGenerator", strategy = "increment")
	@CollectionId(column = @Column(name = "address_id", columnDefinition = "INT"))
	@GeneratedValue(generator = "MyGenerator")
	private List<Address> listOfOffices;
}
