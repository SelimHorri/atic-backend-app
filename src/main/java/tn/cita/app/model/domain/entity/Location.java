package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Location extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = -1729873323870938738L;
	private String zipcode;
	private String city;
	private String state;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "location")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Saloon> saloons;
	
}




