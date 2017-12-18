package org.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.demo.controller.criteria.AuthorCriteria;
import org.demo.entity.Author;
import org.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Page<Author> search(Pageable pageable, AuthorCriteria criteria) {
		return authorRepository.findAll(new CustomerSpecification(criteria), pageable);
	}

	@Override
	public Author get(Integer id) {
		return authorRepository.findOne(id);
	}

	@Override
	public Boolean delete(Integer id) {
		if (authorRepository.findOne(id) != null) {
			authorRepository.delete(id);
			return true;
		}
		return false;
	}

	@Override
	public Author create(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Boolean save(Author author) {
		final Integer pk = author.getId();
		if (authorRepository.findOne(pk) != null) {
			authorRepository.save(author);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Author author) {
		return authorRepository.exists(author.getId());
	}

	class CustomerSpecification implements Specification<Author> {

		private AuthorCriteria criteria;

		public CustomerSpecification(AuthorCriteria criteria) {
			this.criteria = criteria;
		}

		@Override
		public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			final List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getFirstName()))
				predicates.add(builder.like(builder.lower(root.get("firstName")),
						criteria.getFirstName().toLowerCase() + "%"));
			if (StringUtils.isNotBlank(criteria.getLastName()))
				predicates.add(
						builder.like(builder.lower(root.get("lastName")), criteria.getLastName().toLowerCase() + "%"));
			return andTogether(predicates, builder);
		}

		private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder builder) {
			return builder.and(predicates.toArray(new Predicate[0]));
		}
	}

}
