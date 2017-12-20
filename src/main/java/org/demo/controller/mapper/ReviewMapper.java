/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.mapper;

import java.util.List;

import org.demo.controller.resource.ReviewResource;
import org.demo.entity.Review;

import fr.xebia.extras.selma.Mapper;

/**
 * ReviewResource's Mapper declaration using selma.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Mapper(withIgnoreFields = { "id", "links" })
public interface ReviewMapper {

	// This will build a fresh new ReviewResource with in infos
	ReviewResource map(Review in);

	Review map(ReviewResource in);

	// This will update the given Author
	Review map(ReviewResource in, Review out);

	// this will build a fresh list of ReviewResource with in infos
	List<ReviewResource> map(List<Review> in);
}
