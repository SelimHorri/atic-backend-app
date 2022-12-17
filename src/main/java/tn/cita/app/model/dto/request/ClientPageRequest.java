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
	
	private ClientPageRequest(final int offset) {
		this.offset = offset;
		this.size = AppConstants.PAGE_SIZE;
	}
	
	private ClientPageRequest(final Map<String, String> params) {
		
		this(Integer.parseInt(params.get("offset")));
		
		final Integer sizeParam = Integer.parseInt(params.get("size"));
		final String sortByParam = params.get("sortBy");
		final String sortDirectionParam = params.get("sortDirection");
		
		this.size = (sizeParam != null) ? sizeParam : AppConstants.PAGE_SIZE;
		this.sortBy = (sortByParam != null && !sortByParam.isBlank()) ? sortByParam.split(",") : new String[] {"createdAt"};
		this.sortDirection = (sortDirectionParam != null && !sortDirectionParam.isBlank()) ? 
				(sortDirectionParam.equalsIgnoreCase("desc")) ? 
						Sort.Direction.DESC : Sort.Direction.ASC 
				: Sort.Direction.DESC;
	}
	
	public static ClientPageRequest of(final Map<String, String> params) {
		return new ClientPageRequest(params);
	}
	
	
	
}















