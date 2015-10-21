package com.sitedemopair.ws;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class EntityNotAddedException  extends WebApplicationException {
	
//	409 CONFLICT - http://www.restapitutorial.com/lessons/restquicktips.html
	
//	Whenever a resource conflict would be caused by fulfilling the request. 
//	Duplicate entries, such as trying to create two customers with the same information, 
//	and deleting root objects when cascade-delete is not supported are a couple of examples.
		 /**
		  * Create a HTTP 409 (Conflict) exception.
		  */
		  public EntityNotAddedException() {
		    super(Responses.conflict().build());
		  }
		 
		  /**
		  * Create a HTTP 409 (Conflict) exception.
		  * @param message the String that is the entity of the 409 response.
		  */
		  public EntityNotAddedException(String message) {
		    super(Response.status(Responses.CONFLICT).entity(message).type("text/plain").build());
		  }
		}