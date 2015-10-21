package com.sitedemopair.ws;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class EntityAlreadyExistsException extends WebApplicationException {
	 /**
	  * Create a HTTP 409 (CONFLICT) exception.
	  */
	  public EntityAlreadyExistsException() {
	    super(Responses.conflict().build());
	  }
	 
	  /**
	  * Create a HTTP 409 (CONFLICT) exception.
	  * @param message the String that is the entity of the 404 response.
	  */
	  public EntityAlreadyExistsException(String message) {
	    super(Response.status(Responses.CONFLICT).entity(message).type("text/plain").build());
	  }
}
