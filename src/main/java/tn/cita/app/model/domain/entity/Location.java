package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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





