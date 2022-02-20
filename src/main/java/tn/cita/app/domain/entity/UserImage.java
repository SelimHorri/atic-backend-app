package tn.cita.app.domain.entity;

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
	
	@Lob
	@Column(name = "image_lob", columnDefinition = "BLOB", nullable = true)
	private String imageLob;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userImage")
	private Employee employee;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userImage")
	private Customer customer;
	
}











