package tn.cita.app.model.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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





