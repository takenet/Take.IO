package br.com.takenet.takeio.client;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import br.com.takenet.takeio.client.ParameterInterface;

public class MessageParameters implements ParameterInterface {

	private Integer count;
	private Integer startIndex;
	private List<String> filterBy;
	private List<String> filterValue;
	private List<String> filterOp;
	private List<String> orderBy;
	private List<String> orderOp;

	{
		filterBy = new ArrayList<>();
		filterValue = new ArrayList<>();
		filterOp = new ArrayList<>();
		orderBy = new ArrayList<>();
		orderOp = new ArrayList<>();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<String> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<String> filterBy) {
		this.filterBy = filterBy;
	}

	public List<String> getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(List<String> filterValue) {
		this.filterValue = filterValue;
	}

	public List<String> getFilterOp() {
		return filterOp;
	}

	public void setFilterOp(List<String> filterOp) {
		this.filterOp = filterOp;
	}

	public List<String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(List<String> orderBy) {
		this.orderBy = orderBy;
	}

	public List<String> getOrderOp() {
		return orderOp;
	}

	public void setOrderOp(List<String> orderOp) {
		this.orderOp = orderOp;
	}

	public void addFilter(String by, String value) {
		addFilter(by, value, null);
	}

	public void addFilter(String by, String value, String op) {
		by = by.startsWith("@") ? by : "@" + by;
		filterBy.add(by);
		filterValue.add(value);

		if (op == null) {
			filterOp.add("equal");
		} else {
			filterOp.add(op);
		}
	}

	public void addOrder(String by, String op) {
		by = by.startsWith("@") ? by : "@" + by;
		orderBy.add(by);

		if (op == null) {
			filterOp.add("ASC");
		} else {
			filterOp.add(op);
		}
	}

	@Override
	public ArrayList<String> getParametersList() {
		ArrayList<String> paramList = new ArrayList<>();

		if (getCount() != null) {
			paramList.add(String.format("count=%d", getCount()));
		}

		if (getStartIndex() != null) {
			paramList.add(String.format("startIndex=%d", getStartIndex()));
		}

		if (!getFilterBy().isEmpty()) {
			paramList.add(String.format("filterBy=[%s]", StringUtils.join(getFilterBy(), ",")));
		}

		if (!getFilterValue().isEmpty()) {
			paramList.add(String.format("filterValue=[%s]", StringUtils.join(getFilterValue(), ",")));
		}

		if (!getFilterOp().isEmpty()) {
			paramList.add(String.format("filterOp=[%s]", StringUtils.join(getFilterOp(), ",")));
		}

		if (!getOrderBy().isEmpty()) {
			paramList.add(String.format("orderBy=[%s]", StringUtils.join(getOrderBy(), ",")));
		}

		if (!getOrderOp().isEmpty()) {
			paramList.add(String.format("orderOp=[%s]", StringUtils.join(getOrderOp(), ",")));
		}

		return paramList;
	}
}
