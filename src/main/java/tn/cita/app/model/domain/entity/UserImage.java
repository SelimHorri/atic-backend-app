package tn.cita.app.model.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	private static final long serialVersionUID = 1L;
	
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











