package com.WorkerProvider.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.WorkerProvider.Entities.Contact;

@Component
public class HelperDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	public boolean isEmailExist(String email) {
		
		boolean f= false;
		
		try {
			
			String hql = "FROM User U WHERE U.Email=:email";
			String pera = "email";
			List<?> usr = hibernateTemplate.findByNamedParam(hql, pera, email);
			
			if(usr.isEmpty())
			{
				String W_hql = "FROM Worker W WHERE W.email=:email";
				String W_pera = "email";
				List<?> worker = hibernateTemplate.findByNamedParam(W_hql, W_pera, email);
				
				if(worker.isEmpty())
				{
					return f;
				}
				else
				{
					f= true;
					return f;
				}
			}
			else
			{
				f= true;
				return f;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	//save contact us details
	@Transactional
	public boolean saveContactData(Contact data) {
		
		boolean f = false;
		try {

			this.hibernateTemplate.save(data);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	

	

}
