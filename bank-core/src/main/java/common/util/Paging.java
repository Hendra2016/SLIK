package common.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Paging {
	public Pageable getPageable(int page, int size, String direction, String orderBy) {
		Sort.Direction sortDirection = Sort.Direction.fromString(direction);
		return PageRequest.of(
				page,
				size,
				Sort.by(sortDirection, orderBy)
		);
	}

}
