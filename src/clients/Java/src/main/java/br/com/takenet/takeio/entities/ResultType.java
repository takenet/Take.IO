package br.com.takenet.takeio.entities;

import java.util.List;

public class ResultType<T> {
	private Integer totalResults;
	private Integer startIndex;
	private Integer itemsPerPage;
	private List<T> entry;
	
	public ResultType() {
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public List<T> getEntry() {
		return entry;
	}

	public void setEntry(List<T> entry) {
		this.entry = entry;
	}
}
