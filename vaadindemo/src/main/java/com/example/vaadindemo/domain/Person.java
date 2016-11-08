package com.example.vaadindemo.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class Person {
	
	private UUID id;
	
	private String firstName;
	
	private int yob;
	
	private String title;
	
	private String email;
	
	private Date data_wyp;
	
	private Date data_zwr;
	
	public Person(String firstName, int yob, String title, Date data,String email) {
		super();
		this.firstName = firstName;
		this.yob = yob;
		this.title = title;
		this.data_wyp = data;
		this.email = email;
	}

	public Person() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}
	
	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", yob=" + yob
				+ ", title=" + title + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getData_wyp() {
		return data_wyp;
	}

	public void setData_wyp(Date data_wyp) {
		this.data_wyp = data_wyp;
	}

	public Date getData_zwr() {
		return data_zwr;
	}

	public void setData_zwr(Date data_zwr) {
		this.data_zwr = data_zwr;
	}
	
	public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(days >= 0)
        {
        	if(days < 5)
        	{
        		cal.add(Calendar.DATE,days*7);
        	}else
        	{
        		cal.add(Calendar.MONTH,1);
        	}
        }
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MINUTE,59);
        return cal.getTime();
    }
	
	/*@SuppressWarnings("deprecation")
	public void setData_zwr(int x){
		this.setData_zwr(this.getData_wyp());
		int days = this.getData_zwr().getDay();
		days = days + x;
		this.data_zwr.setDate(days);
		
		Calendar cal = Calendar.getInstance();
		Date data = this.getData_wyp();
		data.setDate(data.getDate() + x);
		this.setData_zwr(data);
		
		
		
	}*/
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean W_email()
	{
		return this.email.contains("@");
	}
	
	public boolean W_yob()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if(this.yob > 1900 && this.yob<(year-6))
		{
			return true;
		}
		return false;
	}
	public boolean W_name()
	{
		if(this.firstName.contains("0") || this.firstName.contains("1") || this.firstName.contains("2") || this.firstName.contains("3") || 
				this.firstName.contains("4") || this.firstName.contains("5") || this.firstName.contains("6") || this.firstName.contains("7") || 
				this.firstName.contains("8") || this.firstName.contains("9"))
		{
			return false;
		}else	{return true;}
	}
}

