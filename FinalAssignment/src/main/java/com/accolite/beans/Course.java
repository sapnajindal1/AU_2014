package com.accolite.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "COURSE")
public class Course {
	@Id @GeneratedValue
	   @Column(name = "courseid")
	int courseid;
	@Column(name = "coursename")
	String coursename;
	@Column(name = "coursedesc")
	String coursedesc;
	@Column(name = "duration")
	int duration;
	@Column(name = "eligibilitycriteria")
	String eligibilitycriteria;
	@Column(name = "admissionproc")
	String admissionproc;
	@ManyToOne
    @JoinColumn(name="instid")
	Institute inst;
	
	public Institute getInst() {
		return inst;
	}
	public void setInst(Institute inst) {
		this.inst = inst;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCoursedesc() {
		return coursedesc;
	}
	public void setCoursedesc(String coursedesc) {
		this.coursedesc = coursedesc;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getEligibilitycriteria() {
		return eligibilitycriteria;
	}
	public void setEligibilitycriteria(String eligibilitycriteria) {
		this.eligibilitycriteria = eligibilitycriteria;
	}
	public String getAdmissionproc() {
		return admissionproc;
	}
	public void setAdmissionproc(String admissionproc) {
		this.admissionproc = admissionproc;
	}
	

}
