package tn.cita.app.domain.entity;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstant;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@SuperBuilder
public abstract class AbstractAuditingMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@CreatedDate
	@JsonFormat(pattern = AppConstant.INSTANT_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstant.INSTANT_FORMAT)
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;
	
	@LastModifiedDate
	@JsonFormat(pattern = AppConstant.INSTANT_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstant.INSTANT_FORMAT)
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
	
}











