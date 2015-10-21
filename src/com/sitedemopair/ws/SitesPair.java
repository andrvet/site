package com.sitedemopair.ws;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/*
 * each pair of two sites is unique with it unique distance
 * key of a pair is concationation of upper-cased site names with the lexigrafically greater name in front
 * (example: pair of a and b sites : A>B => so the key of both AB pair and BA pair is "AB")
 * 
 * resource is a set of sites; each site is unique in the set
 * show all sites - show all pairs; 
 * show a site - show all pairs where the site is part of the key of those pairs
 * delete a site - delete all pairs where the site is part of the key
 * update a site - update all pairs where the site is part of the key
 * 
 * resourse is a set of pairs, each pair in the set is unique (AB pair and BA pair is one pair with unique key)
 * to show all sites - show all pairs
 * to show a site - get all pairs from the set where a site is part of the key 
 */

@XmlRootElement
public class SitesPair {
	
	public SitesPair() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Site aSite;

	private Site bSite;
	private Float distance;
	private String keyPair;
	
	public SitesPair(Site aSite, Site bSite, Float distance) {
		super();
		this.aSite = aSite;
		this.bSite = bSite;
		this.distance = distance;
		this.keyPair = createKeyPair();
	}
	
	public SitesPair(String name1, String name2, Float distance) {
		this.aSite=new Site(name1);
		this.bSite=new Site(name2);
		this.distance=distance;
		this.keyPair=createKeyPair();
	}

	private String createKeyPair() {
		String kp=null;
		if (aSite==null && bSite==null) {
			return null;
		}
		if (aSite==null && bSite!=null) {
			kp = bSite.getSiteKey();
			return kp;
		}
		if (bSite==null && aSite!=null) {
			kp = aSite.getSiteKey();
			return kp;
		}
		
		if (aSite.getSiteKey().compareTo(bSite.getSiteKey()) > 0) {
			kp = aSite.getSiteKey() + "+" + bSite.getSiteKey();
		}
		else {
			kp = bSite.getSiteKey() + "+" + aSite.getSiteKey();
		}
		System.out.println("key of pair="+kp);
		return kp;
//		return (aSite.getSiteKey().compareTo(bSite.getSiteKey()) > 0 ? 
//				aSite.getSiteKey() + bSite.getSiteKey() : 
//					bSite.getSiteKey() + aSite.getSiteKey());
	}

	//@XmlTransient
	public String getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(String keyPair) {
		this.keyPair = keyPair;
	}
	@XmlTransient
	public Site getaSite() {
		return aSite;
	}
	public void setaSite(Site aSite) {
		this.aSite = aSite;
	}
	@XmlTransient
	public Site getbSite() {
		return bSite;
	}
	public void setbSite(Site bSite) {
		this.bSite = bSite;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	
}
