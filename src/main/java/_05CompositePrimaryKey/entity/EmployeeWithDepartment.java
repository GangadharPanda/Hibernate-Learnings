package _05CompositePrimaryKey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "employee_with_department")
public class EmployeeWithDepartment {

	@Id
	@Column(name = "id")
	private Integer id;

	@Id
	@Column(name = "dept_id")
	private Integer departmentId;

	@Column
	private String name;
}
