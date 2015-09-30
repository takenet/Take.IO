package br.com.takenet.takeio.entities;

public class Collection<T> {		
	private ResultType<T> result;
	
	public Collection() {
	}

	public ResultType<T> getResult() {
		return result;
	}

	public void setResult(ResultType<T> result) {
		this.result = result;
	}
	
}
