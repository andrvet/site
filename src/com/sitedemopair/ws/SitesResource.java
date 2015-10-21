package com.sitedemopair.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.spi.resource.Singleton;

@Produces({"application/xml", "application/json"})
@Path("sites")
@Singleton
public class SitesResource {
	
	   @Context
	    UriInfo uriInfo;

	// private HashMap<String, Site> sitesCollection = new HashMap<String,
	// Site>();
	// connReg hasmap of key=pair and value=distance
	// hm2 key=city value=list of connReg.Entities

	// HM of pairs and distance. each pair is unique (by key pair). a pair can
	// consist of one site
	// add pair - adds each of the sites in pair and the pair itselt (so if a
	// pair contains a and b then adding a pair creates thre enteties in HM - a,
	// b and a+b pair
	// get site - get entity from HM by key TBC...

	// set, (not HM) of pairs; each pair is inuque (with a unique key)

	// 1.each pair consists of object a, object b, distance.
	// 2.a pair may consist of one site object (a,null,null).
	// 3.each pair has it's unique key. it's uppercased site name for one-site
	// pair. it's a++b for a two-sites pair, where ++ is concatination of
	// lexicografically greater name in front
	// 4.adding AB pair results in adding three entries: add one-site pair
	// (A,null,null), add one-site pair (B,null,null), add AB pair
	// 5.getting site - get all elements from the collection by site name -
	// returns sepatae siet (one-site pair) and all the pairs where he name is
	// part of the pair key
	// 6.deleteing site - delets one-site pair and all pairs where the name is
	// part of the pair key
	// 7.update site - update site name and all pairs where the name is part of
	// the key ; update distance in a pair.

	// //
	// 8 CRUD methods, 2 collections for each 4 (4 for siets, 4 for pairs)
	// /

	// /////////////// sitesCollection
	// 1)key - pair key (or site key for one-site pair); value - pair object
	// private Map<String, SitesPair> sitesCollection = new HashMap<String,
	// SitesPair>(); //wrong ?

	// private Set<Site> sitesCollection = new HashSet<Site>();

	// private Map<String, Site> sitesCollection = new HashMap<String, Site>();

	/*
	 * --- BASIC
	 * 
	 * C POST /sites R GET /sites/{name} U PUT /sites/{name} D DELETE
	 * /sites/{name}
	 * 
	 * GET /sites
	 * 
	 * C POST /pairs R GET /pairs/{name1};{name2} U PUT /pairs/{name1};{name2} D
	 * DELETE /pairs/{name1};{name2}
	 * 
	 * GET /pairs
	 * 
	 * --- ADVANCED
	 * 
	 * GET /pairs?{name} /sites/{name}/pairs
	 */

	private Map<String, Site> sitesCollection = new HashMap<String, Site>();
	// private Set<SitesPair> sitesPairsCollection = new HashSet<SitesPair>();
	private Map<String, SitesPair> sitesPairsCollection = new HashMap<String, SitesPair>();
	/*
	 * key - key of site; value - list/set of pair objects where site-key is
	 * part of the pair-key....
	 */
	//private Map<String, Set<SitesPair>> myCollection = new HashMap<String, Set<SitesPair>>();
	// private Map<String, sitesPairsCollection> collection = new
	// HashMap<String, sitesPairsCollection>();

	// 2)key - pair key (or site key for one-site pair); value - distance
	// private Map<String, Float> sitesCollection = new HashMap<String,
	// Float>();

	// 3)key - pair obj (each pair has it's unique key), value - distance
	// private Map<SitesPair, Float> sitesCollection = new HashMap<SitesPair,
	// Float>(); //?

	// 4)set of sites objects
	// private Set<Site> sitesCollection = new HashSet<Site>();

	// /////////////// sitesPairsCollection
	// set of unique pairs. (a pair can be a one site pair)
	// private Set<SitesPair> sitesPairsCollection = new
	// LinkedHashSet<SitesPair>();

	//

	public SitesResource() {
//		the array contains duplicates. corresping collection of sites won't have  the dups
//		String[] inputSites = {"Augsburg","Batumi","Budapest","Cartago","Douala","El Paso","Envigado",
//				"Hualien","Korhogo","Liberec","Osorno","Toronto","Zagreb","Akola",
//				"Allegheny","Amarah","Augsburg","Batumi","Bonn","Budapest","El Paso","Envigado","Hualien","Osorno"};
//		
//		//create a collection of sites
//		for (String s: inputSites){
//			addSite(s);
//		}
		
		//create a collection of pairs with distances along with collection of sites
//Augsburg,28.0,Bonn
		SitesPair ausburgBonnBonnAugsburg = new SitesPair(addSite("Augsburg"), addSite("Bonn"), 28.0f);
		addPair(ausburgBonnBonnAugsburg);
//Zagreb,58.0,Envigado
		addPair(new SitesPair(addSite("Zagreb"), addSite("Envigado"), 58.0f));
//Douala,47.0,Envigado		
		addPair(new SitesPair(new Site ("Douala"), new Site("Envigado"), 47.0f));
//El Paso,58.0,Augsburg
		addPair("El Paso", "Augsburg", 58.0f);
//Batumi,61.0,Akola
		addPair(new SitesPair(addSite("Batumi"), addSite("Akola"), 61.0f));
//Envigado,34.0,Hualien
		addPair(new SitesPair(addSite("Envigado"), addSite("Hualien"), 34.0f));
//Toronto,5.0,Osorno
		addPair(new SitesPair(addSite("Toronto"), addSite("Osorno"), 5.0f));
//Cartago,58.0,Osorno
		addPair(new SitesPair(addSite("Cartago"), addSite("Osorno"), 58.0f));
		
/*		
		Toronto,6.0,El Paso
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Korhogo,7.0,Allegheny
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Cartago,18.0,Allegheny
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Korhogo,34.0,Batumi
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Hualien,69.0,Batumi
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Korhogo,30.0,Augsburg
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Zagreb,75.0,Budapest
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Liberec,15.0,Bonn
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Envigado,45.0,Bonn
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Osorno,37.0,Bonn
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Batumi,21.0,Bonn
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Douala,33.0,Amarah
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Batumi,37.0,Amarah
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
		Budapest,12.0,Amarah
		addPair(new SitesPair(addSite("Douala"), addSite("Envigado"), 47.0f));
*/	
		//one-site-pair. //TODO is it allowed?
		SitesPair paris = new SitesPair(new Site("Paris"), null, null);
		addPair(paris);
	}

	/*
	 * CRUD FOR SITE
	 * C POST /sites
	 * R GET /sites/{name}
	 * U PUT /sites/{name}
	 * D DELETE /sites/{name}
	 */
	//TODO
//	A) http://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/
	/*
	200 – OK – Eyerything is working
	201 – OK – New resource has been created
	204 – OK – The resource was successfully deleted

	304 – Not Modified – The client can use cached data

	400 – Bad Request – The request was invalid or cannot be served. The exact error should be explained in the error payload. E.g. „The JSON is not valid“
	401 – Unauthorized – The request requires an user authentication
	403 – Forbidden – The server understood the request, but is refusing it or the access is not allowed.
	404 – Not found – There is no resource behind the URI.
	422 – Unprocessable Entity – Should be used if the server cannot process the enitity, e.g. if an image cannot be formatted or mandatory fields are missing in the payload.

	500 – Internal Server Error – API developers should avoid this error. If an error occurs in the global catch blog, the stracktrace should be logged and not returned as response.
	*/
//	If a resource is related to another resource use subresources.
//	GET /cars/711/drivers/ Returns a list of drivers for car 711
//	GET /cars/711/drivers/4 Returns driver #4 for car 711
	
//	B)
	
	@POST 	@Path("{name}") @Produces({"application/xml", "application/json"}) public Site addSite(@PathParam("name") String siteName) {
		if (siteName == null) {
			//https://jersey.java.net/documentation/latest/representations.html
			//http://www.tutorialspoint.com/design_pattern/composite_pattern.htm
			System.out.println("add site. siteName required");
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		if (sitesCollection.containsKey(siteName.toUpperCase())) {
			//TODO
			System.out.println(siteName + " NOT ADDED as it already exists");
			//409 CONFLICT - http://www.restapitutorial.com/lessons/restquicktips.html
//			Whenever a resource conflict would be caused by fulfilling the request. 
//			Duplicate entries, such as trying to create two customers with the same information, 
//			and deleting root objects when cascade-delete is not supported are a couple of examples.
//			throw new EntityNotAddedException (siteName+"NOT ADDED as it already exists");//CONFLICT 409
		} 
			Site s = new Site(siteName.toUpperCase());
			sitesCollection.put(siteName.toUpperCase(), s);
//			a common RESTful pattern for the creation of a new resource is to support a POST request that returns a 201 (Created) 
//			status code and a Location header whose value is the URI to the newly created resource.
			//TODO //https://jersey.java.net/documentation/latest/representations.html
			System.out.println(siteName + " SUCCESSFULLY ADDED");
//			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
//	        URI siteUri = ub.path(s.getName()).build();
//			Response.created(siteUri).build();
			return s;
	}
	@GET 	@Path("{name}")	@Produces({"application/xml", "application/json"}) 	public Site getSite(@PathParam("name") String siteName) {
		if (siteName == null) {
			System.out.println("get site. siteName required");
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		Site s = sitesCollection.get(siteName.toUpperCase());
		if (s==null) {
			System.out.println(siteName + " not found");
			throw new EntityNotFoundException(siteName + " not found");//404
		}
		return s;
	}
	@PUT 	@Path("{name}") @Produces({"application/xml", "application/json"}) 	public Site updateSite(@PathParam("name") String currentName, @FormParam("newName") String newName) {
//		String updateMsg = "both currentName and newName required";
		if (currentName == null || newName == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		if (currentName.equalsIgnoreCase(newName)) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		Site currentSite = sitesCollection.get(currentName.toUpperCase());
		if (currentSite == null) {
			throw new EntityNotFoundException(currentName + " not found");//404
		}
		Site newSite = sitesCollection.get(newName.toUpperCase());
		if (newSite != null) {
			throw new EntityAlreadyExistsException(currentName + " already exists");//409 (conflict)
		}
		
		sitesCollection.remove(currentName.toUpperCase());
		newSite = new Site(newName.toUpperCase());
		sitesCollection.put(newName.toUpperCase(), newSite);

		//TODO
		//update all pairs whith currentName
		return newSite;
	}
	@DELETE @Path("{name}")	@Produces({"application/xml", "application/json"}) 	@Consumes({"application/xml", "application/json"}) 	public Site deleteSite(@PathParam("name") String siteName) {
		if (siteName == null) {
			//deleteMsg = "site name required in request";
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		if (!sitesCollection.containsKey(siteName.toUpperCase())) {
			//throw new WebApplicationException(Response.Status.NOT_MODIFIED);//?
			throw new EntityNotDeletedException(siteName+" not deleted as it does not exist");
		}
		Site s = sitesCollection.get(siteName.toUpperCase());
		sitesCollection.remove(siteName.toUpperCase());
		
		//TODO
		//delete all pairs with siteName
		return s; //returning already deleted site...
	}
	@GET 	@Produces({"application/xml", "application/json"}) public List<Site> getSites() {
		List<Site> sites = new ArrayList<Site>(sitesCollection.values());
		return sites;
	}

	
	/*
	 * CRUD FOR PAIRS
	 * C POST /pairs
	 * R GET /pairs/{name1};{name2}
	 * U PUT /pairs/{name1};{name2}
	 * D DELETE /pairs/{name1};{name2}
	 *
	 * GET /pairs
	 */
	public SitesPair addPair(SitesPair sitesPair) {
//		 if (sitesCollection.containsKey(sitesPair.getaSite().getName())) {
//			 System.out.println(sitesPair.getaSite().getName() +" (a) WAS NOT ADDED as it already exists");
//		 }
//		 if (sitesCollection.containsKey(sitesPair.getbSite().getName())){
//			 System.out.println( sitesPair.getbSite().getName() + " (b) WAS NOT ADDED as it already exists");
//		 }
		// add each site from a apair
		if (sitesPair != null && sitesPair.getaSite() != null) {
			addSite(sitesPair.getaSite().getName().toUpperCase());
		}
		// sitesCollection.put(sitesPair.getaSite().getName(), sitesPair.getaSite());
		if (sitesPair != null && sitesPair.getbSite() != null) {
			addSite(sitesPair.getbSite().getName().toUpperCase());
		}
		// sitesCollection.put(sitesPair.getbSite().getName(), sitesPair.getbSite());

		// then add pair itself
		if (sitesPair != null && sitesPair.getKeyPair() != null) {
			sitesPairsCollection.put(sitesPair.getKeyPair(), sitesPair);
			System.out.println(sitesPair.getKeyPair() + " pair SUCCESSFULLY ADDED");
//			UriBuilder ub = uriInfo.getAbsolutePathBuilder();
//	        URI siteUri = ub.path(sitesPair.getKeyPair()).build();
//			Response.created(siteUri).build();//201
		}
		return sitesPair;
	}
	@POST 	@Path("{name1},{name2},{distance}") @Produces({"application/xml", "application/json"})	public SitesPair addPair(@PathParam("name1") String name1, @PathParam("name2") String name2, @PathParam("distance") Float distance) {
		// add each site from a pair
		if (name1 != null) {
			addSite(name1.toUpperCase());
			// sitesCollection.put(name1, new Site(name1));
		}
		if (name2 != null) {
			addSite(name2.toUpperCase());
			// sitesCollection.put(name2, new Site(name2));
		}
		
		// then add pair itself
		SitesPair pair = new SitesPair(name1,name2,distance);
		if (pair != null && pair.getKeyPair() != null) {
			sitesPairsCollection.put(pair.getKeyPair(), pair);
			System.out.println(pair.getKeyPair() + " pair SUCCESSFULLY ADDED");
		//	UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		//   URI siteUri = ub.path(sitesPair.getKeyPair()).build();
		//	Response.created(siteUri).build();//201
		}
		return pair;
}
	@GET 	@Path("{name1},{name2}")	@Produces({"application/xml", "application/json"})	public SitesPair getPair(@PathParam("name1") String name1, @PathParam("name2") String name2) {
		String pairKey;
		if (name1==null && name2==null) {
			System.out.println("get pair. both names required");
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		//do we allow one-site pairs? if yes then
		if (name1==null && name2!=null) {
			pairKey = name2.toUpperCase();
		} else if (name1!=null && name2==null){
			pairKey = name1.toUpperCase();
		} else {
			//both names not null. create key of a pair
			if (name1.toUpperCase().compareTo(name2.toUpperCase()) > 0) {
				pairKey = name1.toUpperCase() + "+" + name2.toUpperCase();
			}
			else {
				pairKey = name2.toUpperCase() + "+" + name1.toUpperCase();
			}
		}
		return sitesPairsCollection.get(pairKey);
	}
	public SitesPair updatePair(SitesPair pair) {
		if (pair==null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		//if a pair is found by its key then just update it 
		if (sitesPairsCollection.containsKey(pair.getKeyPair())) {
			//all field required
			SitesPair p = sitesPairsCollection.get(pair.getKeyPair());
			p.setaSite(pair.getaSite());
			p.setbSite(pair.getbSite());
			p.setDistance(pair.getDistance());
			return pair;
		}
		else {
			throw new EntityNotFoundException(pair.getKeyPair() + " not found");//404
		}
	}
	@PUT 	@Path("{name1},{name2},{distance}") @Produces({"application/xml", "application/json"}) public SitesPair updateDistance(@PathParam("name1") String name1, @PathParam("name2") String name2, @FormParam("distance") Float distance) {
		return updatePair(new SitesPair(name1, name2, distance));
	}
	public SitesPair deletePair(SitesPair pair) {
		if (pair==null){
			throw new WebApplicationException(Response.Status.BAD_REQUEST);//400
		}
		//throws 404 if not fond, or 500 found but not updated {
		if (!sitesPairsCollection.containsKey(pair.getKeyPair())) {
			System.out.println(pair.getKeyPair() + " (pairName) NOT REMOVED as it does not exist");
			throw new WebApplicationException(Response.Status.NOT_FOUND);//404
		}
		sitesPairsCollection.remove(pair.getKeyPair());
		//sites a and b (from the ab/ba pair) are not deleted when the ab/ba pair is deleted; 
		//both a and b sites just become sites without direct connections and stay in collection of sites
		System.out.println(pair.getKeyPair() + " (pairName) SUCCESSFULLY REMOVED");
		return pair;//return deleted pair...
	}
	@DELETE @Path("{name1},{name2}")  public String deletePair(@PathParam("name1") String name1, @PathParam("name2") String name2) {
		SitesPair pair = new SitesPair(name1,name2,null);
		if (!sitesPairsCollection.containsKey(pair.getKeyPair())) {
			System.out.println(pair.getKeyPair() + " (pairName) NOT REMOVED as it does not exist");
			throw new EntityNotDeletedException(pair.getKeyPair()+" (pairName) NOT REMOVED as it does not exist");
		}
		sitesPairsCollection.remove(pair.getKeyPair());
		//sites a and b (from the ab/ba pair) are not deleted when the ab/ba pair is deleted; 
		//both a and b sites just become sites without direct connections and stay in collection of sites
		System.out.println(pair.getKeyPair() + " (pairName) SUCCESSFULLY REMOVED");
		return (pair.getKeyPair() + " (pairName) SUCCESSFULLY REMOVED");
	}
	@GET 	@Path("pairs") 	@Produces({"application/xml", "application/json"}) 	public List<SitesPair> getPairs() {
		List<SitesPair> sitesPairs = new ArrayList<SitesPair>(sitesPairsCollection.values());
		return sitesPairs;
	}

	// http://stackoverflow.com/questions/12883029/jersey-producing-media-type-conflict
	// resource methods can produce the same media type
	// login/{username}/{password} and ranges/{from}/{to}
	/*
	 * Consider using query params for specifying ranges - e.g. if you have some
	 * collection resource (like "myapp.com/calendar/events") you can model
	 * ranges using query parameters - e.g.
	 * "myapp.com/calendar/events?from=xxx&to=yyy
	 */
}