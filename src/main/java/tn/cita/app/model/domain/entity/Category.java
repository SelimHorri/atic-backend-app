package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Category extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 388322734477460176L;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "parent_category_id", referencedColumnName = "id", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Category parentCategory;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parentCategory")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Category> subCategories;
	
	@ManyToOne
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Saloon saloon;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<ServiceDetail> serviceDetails;
	
}






