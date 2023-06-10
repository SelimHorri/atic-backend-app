package tn.cita.app.model.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "saloon_images")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SaloonImage extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 4616883902905139667L;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private Long size;
	
	@Lob
	@Column(name = "image_lob", columnDefinition = "BLOB", nullable = true)
	private byte[] imageLob;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Saloon saloon;
	
}




