/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.controller.mapper;

import java.util.List;

import org.demo.controller.resource.WorkgroupResource;
import org.demo.entity.Workgroup;

import fr.xebia.extras.selma.Mapper;

/**
 * WorkgroupResource's Mapper declaration using selma.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Mapper(withIgnoreFields = { "id", "links" })
public interface WorkgroupMapper {

	// This will build a fresh new WorkgroupResource with in infos
	WorkgroupResource map(Workgroup in);

	Workgroup map(WorkgroupResource in);

	// This will update the given Author
	Workgroup map(WorkgroupResource in, Workgroup out);

	// this will build a fresh list of WorkgroupResource with in infos
	List<WorkgroupResource> map(List<Workgroup> in);
}
