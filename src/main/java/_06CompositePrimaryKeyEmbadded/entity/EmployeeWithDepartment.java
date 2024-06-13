package _06CompositePrimaryKeyEmbadded.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

	@EmbeddedId
	private CompositePrimaryKeyId primaryKey;

	@Column
	private String name;
}
