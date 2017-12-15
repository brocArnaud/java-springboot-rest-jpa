package org.demo.service;

import org.demo.controller.criteria.AuthorCriteria;
import org.demo.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

	public void init();

	public Page<Author> search(Pageable pageable, AuthorCriteria criteria);

	public Author get(Integer id);

	public Boolean delete(Integer id);

	public Author create(Author customer);

	public Boolean save(Author customer);

	public Boolean exist(Author customer);
}
