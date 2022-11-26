package tn.cita.app.model.dto.request;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.cita.app.constant.AppConstants;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class ClientPageRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Builder.Default
	private int offset = 1;
	
	@Builder.Default
	private int size = AppConstants.PAGE_SIZE;
	
	private String[] sortBy;
	
	@Builder.Default
	private Sort.Direction sortDirection = Sort.Direction.DESC;
	
	private ClientPageRequest(final int offset, final int size) {
		this.offset = offset;
		this.size = size;
	}
	
	private ClientPageRequest(final Map<String, String> params) {
		this(Integer.parseInt(params.get("offset")), Integer.parseInt(params.get("size")));
		
		if (params.get("sortBy") != null && !params.get("sortBy").isBlank())
			this.sortBy = params.get("sortBy").split(",");
		else
			this.sortBy = new String[] {};
		if (params.get("sortDirection") != null && !params.get("sortDirection").isBlank())
			this.sortDirection = (params.get("sortDirection").equalsIgnoreCase("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;
		else
			this.sortDirection = Sort.Direction.DESC;
	}
	
	public static ClientPageRequest of(final Map<String, String> params) {
		return new ClientPageRequest(params);
	}
	
	
	
}















