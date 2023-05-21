package tn.cita.app.model.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_images")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UserImage extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 2231768482496554103L;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Long size;
	
	@Lob
	@Column(name = "image_lob", columnDefinition = "BLOB", nullable = true)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private byte[] imageLob;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userImage")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Employee employee;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userImage")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
}





