package com.codenotfound.primefaces.model.customer;

import java.util.List;
import java.util.function.Function;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class SelectableListDataModel<T> extends ListDataModel<T> implements SelectableDataModel<T> {

	Function<T, String> keyExtractor;
	
	public SelectableListDataModel(List<T> values, Function<T, String> keyExtractor) {
		super(values);
		this.keyExtractor = keyExtractor;
	}

	@Override
	public String getRowKey(T object) {
		return keyExtractor.apply(object);
	}

	@Override
	public T getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<T> values = (List<T>)getWrappedData();
		for (T t : values) {
			String currentKey = keyExtractor.apply(t);
			if (currentKey != null && currentKey.equals(rowKey)) {
				return t;
			}
		}
		return null;
	}

}
