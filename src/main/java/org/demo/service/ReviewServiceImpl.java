/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.demo.entity.Review;
import org.demo.entity.ReviewKey;
import org.demo.repository.ReviewRepository;
import org.demo.service.criteria.ReviewCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Service implementation for Review.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Page<Review> search(Pageable pageable, ReviewCriteria criteria) {
		return reviewRepository.findAll(new ReviewSpecification(criteria), pageable);
	}

	@Override
	public Review get(String customerCode, Integer bookId) {
		// Build the composite key
		ReviewKey key = new ReviewKey(customerCode, bookId);
		return reviewRepository.findOne(key);
	}

	@Override
	public Boolean delete(String customerCode, Integer bookId) {
		// Build the composite key
		ReviewKey key = new ReviewKey(customerCode, bookId);
		if (reviewRepository.findOne(key) != null) {
			reviewRepository.delete(key);
			return true;
		}
		return false;
	}

	@Override
	public Review create(Review review) {
		return reviewRepository.save(review);
	}

	@Override
	public Boolean save(Review review) {
		// Build the composite key
		ReviewKey pk = new ReviewKey(review.getCustomerCode(), review.getBookId());
		if (reviewRepository.findOne(pk) != null) {
			reviewRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(Review review) {
		// Build the composite key
		ReviewKey pk = new ReviewKey(review.getCustomerCode(), review.getBookId());
		return reviewRepository.exists(pk);
	}

	class ReviewSpecification implements Specification<Review> {

		private ReviewCriteria criteria;

		public ReviewSpecification(ReviewCriteria criteria) {
			this.criteria = criteria;
		}

		@Override
		public Predicate toPredicate(Root<Review> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			final List<Predicate> predicates = new ArrayList<>();
			if (StringUtils.isNotBlank(criteria.getReviewText())) {
				predicates.add(builder.like(builder.lower(root.get("reviewText")),
						criteria.getReviewText().toLowerCase() + "%"));
			}
			return andTogether(predicates, builder);
		}

		private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder builder) {
			return builder.and(predicates.toArray(new Predicate[0]));
		}
	}

}
