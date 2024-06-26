package _14one2many.bi.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "car_name")
	private String carName;
	
	@Column(name = "buy_date")
	@Temporal(TemporalType.DATE)
	private Date buyDate;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
}
