package com.accolite.beans;

import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name = "INSTITUTE")
@XmlRootElement
public class Institute {
	@Id @GeneratedValue
	@Column(name = "instid")
	int instid;
	@Column(name = "title")
	String title;
	@Column(name = "instdesc")
	String instdesc;
	@Column(name = "location")
	String location;
	@Column(name = "branches")
	String branches;
	@Column(name = "imageurl")
	String imageurl;
	@OneToMany(mappedBy="inst")
    private Set<Course> courses;
	
	public int getInstid() {
		return instid;
	}
	public void setInstid(int instid) {
		this.instid = instid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return instdesc;
	}
	public void setDesc(String desc) {
		this.instdesc = desc;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBranches() {
		return branches;
	}
	public void setBranches(String branches) {
		this.branches = branches;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	
	
	

}
