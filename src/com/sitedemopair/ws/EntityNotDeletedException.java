package com.sitedemopair.ws;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class EntityNotDeletedException extends WebApplicationException {

	//TODO or not modified?
		 /**
		  * Create a HTTP 404 (not found) exception.
		  */
		  public EntityNotDeletedException() {
		    super(Responses.notModified().build());
		  }
		 
		  /**
		  * Create a HTTP 404 (not found) exception.
		  * @param message the String that is the entity of the 404 response.
		  */
		  public EntityNotDeletedException(String message) {
		    super(Response.status(Responses.NOT_FOUND).entity(message).type("text/plain").build());
		  }
		}