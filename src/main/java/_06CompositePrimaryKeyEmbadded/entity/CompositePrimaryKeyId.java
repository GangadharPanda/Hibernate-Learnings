package _06CompositePrimaryKeyEmbadded.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class CompositePrimaryKeyId {
	private Integer id;
	private String department;
}
