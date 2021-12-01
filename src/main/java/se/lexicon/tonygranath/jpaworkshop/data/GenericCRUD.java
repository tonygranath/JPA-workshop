package se.lexicon.tonygranath.jpaworkshop.data;

import java.util.Collection;

public interface GenericCRUD<T, ID> {
	T findById(ID id);
	Collection<T> findAll();
	T create(T t);
	T update(T t);
	void remove(ID id);
}
