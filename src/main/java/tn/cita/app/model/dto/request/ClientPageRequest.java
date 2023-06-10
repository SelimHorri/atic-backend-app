package tn.cita.app.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Sort;
import tn.cita.app.constant.AppConstants;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@Data
public final class ClientPageRequest implements Serializable {
	
	private static final long serialVersionUID = 6428616552006298688L;
	
	private int offset = 1;
	private int size = AppConstants.PAGE_SIZE;
	private String[] sortBy;
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
	
	public static ClientPageRequest from(final Map<String, String> params) {
		return new ClientPageRequest(params);
	}
	
}




