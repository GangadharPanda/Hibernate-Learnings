package PreventingPersistanceOfSpecificProperties.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Integer id;

	@Column
	private String name;

	@Column
	private Integer age;
	
	/***
	 * 
	 * I don't want to persist this martialStatus field in the database 
	 * 
	 * What if I don't give @Column annotation , will this still have column in the db?
	 * 
	 * Yes, 
	 * 
	 * 		CREATE TABLE teacher
		  (
		     teacher_id    INTEGER NOT NULL auto_increment,
		     age           INTEGER,
		     martialstatus VARCHAR(255),
		     name          VARCHAR(255),
		     PRIMARY KEY (teacher_id)
		  )
		engine=innodb 
	 *  The @Column /@Table annotations are OTIONAL,
	 *  
	 *  So to restrict hibernate from creating a column for the martialStatus properties 
	 *  we will need to add an annotation @Transient
	 *  
		  CREATE TABLE teacher
		  (
		     teacher_id INTEGER NOT NULL auto_increment,
		     age        INTEGER,
		     name       VARCHAR(255),
		     PRIMARY KEY (teacher_id)
		  ) 
	 */
	@Transient
	private String martialStatus;
}
