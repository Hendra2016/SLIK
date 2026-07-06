package common.spring.data;

import java.io.Serializable;

public class Paging implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Object data;
	private long recordsTotal;
	private long recordsFiltered;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

}
