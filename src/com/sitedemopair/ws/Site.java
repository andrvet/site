package com.sitedemopair.ws;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//JAXB annotation that allows JAXB to convert this entity from Java to XML and back
@XmlRootElement
// @XmlAccessorType(XmlAccessType.FIELD)
public class Site {

	private String name;

	private String siteKey;
	
	public Site() {
		super();
	}

	public Site(String name) {
		this.name = name;
		createSiteKey();
	}

	// Mapped code fragment
	// public class Foo {
	// @XmlElements({@XmlElement(name="A",
	// type=Integer.class),@XmlElement(name="B", type=Float.class)})
	// public List items;
	// }
	//
	// <!-- XML Representation for a List of {1,2.5}
	// XML output is not wrapped using another element -->
	// ...
	// <A> 1 </A>
	// <B> 2.5 </B>
	// ...

	// @XmlElements({@XmlElement(name="A", type=Site.class)})
	// @XmlElement
	// @XmlTransient
	// @XmlElement

//	@XMLTransient
//	public B getB() {
//	    return b;
//	}
//
//	@XMLElement(name="b")
//	public Long getBForREST() {
//	    return b.getId();
//	}
	@XmlTransient
	public String getSiteKey() {
		return siteKey;
	}

	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}

	public void createSiteKey() {
		this.siteKey=this.name.toUpperCase();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SitesPair> getRelatedSites() {
		// TODO Auto-generated method stub
		return null;
	}

//	public String printSite() {
//		return name+" with related sites:["+printDirectConnectionsWithDistance()+"]";
//	}
//	public String printDirectConnectionsWithDistance(){
//		StringBuffer s = new StringBuffer();
//		if (null!=relatedSites) {
//			Iterator<Site> itr = relatedSites.iterator();
//			while(itr.hasNext()) {
//	    	  Site site = itr.next();
//		      s.append(site.getName()+"("+site.getDistance()+"km)-");
//			}
//		}
//	    return s.toString();
//	}


}
