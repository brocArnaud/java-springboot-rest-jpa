/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.mapper;

import java.util.List;

import org.demo.controller.resource.SynopsisResource;
import org.demo.entity.Synopsis;

import fr.xebia.extras.selma.Mapper;

/**
 * SynopsisResource's Mapper declaration using selma.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Mapper(withIgnoreFields = { "id", "links" })
public interface SynopsisMapper {

	// This will build a fresh new SynopsisResource with in infos
	SynopsisResource map(Synopsis in);

	Synopsis map(SynopsisResource in);

	// This will update the given Author
	Synopsis map(SynopsisResource in, Synopsis out);

	// this will build a fresh list of SynopsisResource with in infos
	List<SynopsisResource> map(List<Synopsis> in);
}