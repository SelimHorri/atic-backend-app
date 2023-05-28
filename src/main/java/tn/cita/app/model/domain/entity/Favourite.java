package tn.cita.app.model.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.model.domain.id.FavouriteId;
import tn.cita.app.model.domain.listener.FavouriteEntityListener;

@Entity
@Table(name = "favourites")
@EntityListeners(FavouriteEntityListener.class)
@IdClass(FavouriteId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Favourite extends AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 7405143971226663517L;
	
	@Id
	@Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
	private Integer customerId;
	
	@Id
	@Column(name = "saloon_id", nullable = false, insertable = false, updatable = false)
	private Integer saloonId;
	
	@Column(name = "favourite_date", nullable = false)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	private LocalDateTime favouriteDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Customer customer;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "saloon_id", referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Saloon saloon;
	
}





