package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

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




