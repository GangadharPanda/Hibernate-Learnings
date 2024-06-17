package _13one2one.entity.bidirectional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "instructor")
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Instructor {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@OneToOne
	@JoinColumn(name = "instructor_details_id") // Which column in this table will be join with pkey of
												// instructorDetails table
	@Cascade(CascadeType.ALL) // insert ,update,delete everything should happen together
	private InstructorDetails instructorDetails;

}
