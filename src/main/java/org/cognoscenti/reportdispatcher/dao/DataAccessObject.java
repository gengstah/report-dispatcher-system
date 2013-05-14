package org.cognoscenti.reportdispatcher.dao;

import java.util.List;

public interface DataAccessObject {
	Object add(Object o);
	Object update(Object o);
	void remove(Object o);
	<T> T get(Object id, Class<T> clazz);
	<T> List<T> list(Class<T> clazz);
}