package com.WorkerProvider.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.WorkerProvider.Entities.User;
import com.WorkerProvider.Entities.WorkDataDescription;
import com.WorkerProvider.Entities.Worker;
import com.WorkerProvider.Helper.Entities.UserLogIn;

@Component
public class UserDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// method for saving worker

	@Transactional
	public boolean saveUser(User user) {
		boolean f = false;
		try {

			this.hibernateTemplate.save(user);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// method for getting a user
	public List<?> getUser(String name, String password) {

		String hql = "FROM user U WHERE U.Name=:name and U.Password=:pass";
		String params[] = { "name", "pass" };

		Object[] obj = { name, password };
		List<?> user = hibernateTemplate.findByNamedParam(hql, params, obj);

		return user;
	}

	// method for authenticate user for log in
	public List<?> getUser(UserLogIn user) {

		String hql = "FROM User U WHERE U.Email=:email and U.Password=:pass";
		String params[] = { "email", "pass" };

		Object[] obj = { user.getUsername(), user.getPassword() };
		List<?> usr = hibernateTemplate.findByNamedParam(hql, params, obj);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println("inside the dao =" + usr);

		return usr;

	}

	// method for fetching user by email
	public List<?> getUSerByEmail(String username) {

		String hql = "FROM User U WHERE U.Email=:email";
		String pera = "email";
		List<?> usr = hibernateTemplate.findByNamedParam(hql, pera, username);
		return usr;
	}

	// method for fetching Available workers
	public List<?> getAvailableWorker(String service) {

		List<? extends Object> AvailableWorkers = null;

		System.out.println("ser" + service);

		String hql = "FROM Worker_status w INNER JOIN FETCH w.worker WHERE w.worker.work_Type=:wrkr";
		String pera = "wrkr";

		AvailableWorkers = this.hibernateTemplate.findByNamedParam(hql, pera, service);

		if (!AvailableWorkers.isEmpty()) {
			System.out.println(AvailableWorkers);
		}
		return AvailableWorkers;
	}

	// method for fetching worker
	public Worker getWorker(int id) {

		Worker wrkr = this.hibernateTemplate.get(Worker.class, id);
		return wrkr;
	}

	// method for fetching Workers list by city
	public List<?> getWorkersByCity(String city, String service) {

		List<? extends Object> AvailableWorkers = null;
		try {

			String hql = "FROM Worker_status w  INNER JOIN FETCH w.worker WHERE w.status=:status AND w.worker.city=:city And w.worker.work_Type=:service";
			String pera[] = { "status", "city", "service" };

			Object obj[] = { "Available", city, service };

			AvailableWorkers = this.hibernateTemplate.findByNamedParam(hql, pera, obj);
			// AvailableWorkers = this.hibernateTemplate.find(hql);

			System.out.println(AvailableWorkers);

			if (!AvailableWorkers.isEmpty()) {
				System.out.println(AvailableWorkers);
			}
			return AvailableWorkers;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public boolean updateForgotPassword(String email, String password) {
		boolean f = false;

		try {
			List<?> userList = getUSerByEmail(email);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean updateUser(User user) {

		boolean f = false;
		try {
			hibernateTemplate.update(user);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

	@Transactional
	public boolean saveWorkDetails(WorkDataDescription workDetails) {

		boolean f = false;
		try {
			hibernateTemplate.save(workDetails);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}

	public List<?> getUserSentRequest(User user,int status) {

		String hql = "FROM WorkDataDescription A WHERE A.user=:user and A.workstatus=:status";
		
		String pera[] = { "user", "status" };

		Object obj[] = { user, status};
		
		
		List<?> requests = this.hibernateTemplate.findByNamedParam(hql, pera, obj);

		System.out.println("requests  " + requests);
		// System.out.println("id=== "+worker);

		// System.out.println("statoss == "+status);

		return requests;

	}
	
	
	/*
	 * public WorkDataDescription getUserSentRequest(int id) { WorkDataDescription
	 * workDataDescription = hibernateTemplate.get(WorkDataDescription.class, id);
	 * 
	 * return workDataDescription; }
	 */

	
	@Transactional
	public boolean completeRequest(WorkDataDescription data) {
		boolean f = false;

		try {

			this.hibernateTemplate.update(data);
			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	
	
	public WorkDataDescription getWorkDataDescription(int id) {

		WorkDataDescription data = null;

		try {
			data = hibernateTemplate.get(WorkDataDescription.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

}
