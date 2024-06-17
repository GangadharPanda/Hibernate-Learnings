package _13one2one.entity.bidirectional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Table(name = "instructor_details")
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDetails {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	@Column(name = "youtube_channel_url")
	private String youtubeChannelURL;

	@Column(name = "linkedin_profile_url")
	private String linkedInProfileURL;

	@OneToOne(mappedBy = "instructorDetails", cascade = CascadeType.ALL)
	private Instructor instructor;

}
