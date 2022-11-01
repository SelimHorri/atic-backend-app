package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "service_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ServiceDetail extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(name = "is_available", nullable = false)
	private Boolean isAvailable;
	
	@Column(columnDefinition = "DECIMAL")
	private Double duration;
	
	@Column(name = "price_unit", columnDefinition = "DECIMAL")
	private Double priceUnit;
	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Category category;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "serviceDetail")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<OrderedDetail> orderedDetails;
	
}














