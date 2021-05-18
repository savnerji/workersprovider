package com.WorkerProvider.DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.WorkerProvider.Entities.AddWorkerStatus;
import com.WorkerProvider.Entities.WorkDataDescription;
import com.WorkerProvider.Entities.Worker;
import com.WorkerProvider.Helper.Entities.WorkerLogIn;

@Component
public class WorkerDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	// save profile pics
	public boolean saveProfilePIc(String path, byte[] pic) {

		boolean f = false;
		try {
			FileOutputStream fos = new FileOutputStream(path);
			System.out.println(path);
			fos.write(pic);
			fos.close();
			fos.flush();

			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// save aadhar pics
	public boolean saveAadharPic(String path, byte[] pic) {

		boolean f = false;
		try {
			FileOutputStream fos = new FileOutputStream(path);
			System.out.println(path);
			fos.write(pic);
			fos.close();
			fos.flush();

			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// save worker to the db
	@Transactional
	public boolean saveWorker(Worker worker) {

		boolean f = false;
		try {

			this.hibernateTemplate.save(worker);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

//method for get workers
	public List<?> getWorker(WorkerLogIn worker) {
		String hql = "FROM Worker U WHERE U.email=:email and U.password=:pass";
		String params[] = { "email", "pass" };

		Object[] obj = { worker.getUsername(), worker.getPassword() };
		List<?> wrkr = hibernateTemplate.findByNamedParam(hql, params, obj);

		System.out.println("inside the dao =" + wrkr);

		return wrkr;

	}

//method for update worker
	@Transactional
	public boolean updateWorker(Worker worker) {

		boolean f = false;
		try {

			this.hibernateTemplate.update(worker);
			f = true;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;
	}

//method for updating worker pic
	public boolean updateWorkerPic(String path, String oldPath, byte[] pic) {

		boolean f = false;

		try {

			File oldFile = new File(oldPath);
			boolean f1 = oldFile.delete();

			if (f1 == true) {

				FileOutputStream fos = new FileOutputStream(path);
				System.out.println(path);
				fos.write(pic);
				fos.close();
				fos.flush();

				f = true;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return f;
	}

	// method for saving worker status
	@Transactional
	public boolean addStatus(AddWorkerStatus status) {

		System.out.println(status);

		boolean f = false;

		try {

			this.hibernateTemplate.save(status);

			f = true;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return f;
	}

	// method for get WorkerAvailability
	public AddWorkerStatus getWorkerStatus(Worker wrkr) {

		AddWorkerStatus addWorkerStatus = null;
		try {

			String hql = "FROM Worker_status A WHERE A.worker=:worker";
			String valueOf = "worker";
			List<?> findByNamedParam = this.hibernateTemplate.findByNamedParam(hql, valueOf, wrkr);

			if (!findByNamedParam.isEmpty()) {
				addWorkerStatus = (AddWorkerStatus) findByNamedParam.get(0);
				System.out.println(addWorkerStatus);

				return addWorkerStatus;
			} else {
				System.out.println("empty");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return addWorkerStatus;
	}

	// method for updating worker status
	@Transactional
	public boolean updateWorkerStatus(AddWorkerStatus status, Worker wrkr) {

		AddWorkerStatus addWorkerStatus = null;
		System.out.println(status);

		boolean f = false;

		try {

			String hql = "FROM Worker_status A WHERE A.worker=:worker";
			String valueOf = "worker";
			List<?> findByNamedParam = this.hibernateTemplate.findByNamedParam(hql, valueOf, wrkr);

			if (!findByNamedParam.isEmpty()) {
				addWorkerStatus = (AddWorkerStatus) findByNamedParam.get(0);
				System.out.println(addWorkerStatus);
				addWorkerStatus.setStatus(status.getStatus());
				addWorkerStatus.setTime(status.getTime());
				this.hibernateTemplate.update(addWorkerStatus);
				f = true;
			} else {
				System.out.println("empty");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return f;
	}

	public List<?> getWorkerByName(String username) {
		String hql = "FROM Worker U WHERE U.email=:emailD";
		String pera = "emailD";
		List<?> wrkr = hibernateTemplate.findByNamedParam(hql, pera, username);
		System.out.println(wrkr);
		return wrkr;
	}

	public void deletePics(String aadharPic, String profilePic) {

		try {

			File aadharFile = new File(aadharPic);
			File profileFile = new File(aadharPic);
			boolean f1 = aadharFile.delete();
			boolean f2 = profileFile.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void delete(String path) {

		try {

			File file = new File(path);

			boolean f1 = file.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*
	 * @Transactional public boolean updateWorkerPassword(String email, String
	 * password) { boolean f=false;
	 * 
	 * String hql = "FROM Worker U WHERE U.email=:email and U.password=:pass";
	 * String params[] = { "email", "pass" };
	 * 
	 * Object[] obj = {email, password }; List<?> wrkr =
	 * hibernateTemplate.findByNamedParam(hql, params, obj);
	 * 
	 * if(!wrkr.isEmpty()) { Worker worker=(Worker) wrkr.get(0);
	 * worker.setPassword(passwordEncoder.encode(password));
	 * hibernateTemplate.update(worker); f=true; }
	 * 
	 * 
	 * 
	 * return false; }
	 */

	// method for get workers
	public List<?> getWorkRequests(Worker worker, int status) {
		String hql = "FROM WorkDataDescription W WHERE W.worker=:worker and W.workstatus=:status";
		String params[] = { "worker", "status" };

		Object[] obj = { worker, status };
		List<?> requests = hibernateTemplate.findByNamedParam(hql, params, obj);

		System.out.println("requests  " + requests);
		System.out.println("id===  " + worker);
		System.out.println("statoss == " + status);

		return requests;

	}

	/*
	 * public List<?> getWorkReqssdsduests(Worker worker) {
	 * 
	 * String hql = "FROM WorkDataDescription A WHERE A.worker=:worker"; String
	 * valueOf = "worker"; List<?> requests =
	 * this.hibernateTemplate.findByNamedParam(hql, valueOf, worker);
	 * 
	 * System.out.println("requests  " + requests); //
	 * System.out.println("id=== "+worker);
	 * 
	 * // System.out.println("statoss == "+status);
	 * 
	 * return requests;
	 * 
	 * }
	 */

	// method for fetching work data dertails
	public WorkDataDescription getWorkDataDetails(int id) {

		WorkDataDescription details = this.hibernateTemplate.get(WorkDataDescription.class, id);
		return details;
	}

	// for deleting the requests
	@Transactional
	public boolean deleteREquestByWorker(WorkDataDescription data) {

		boolean f = false;

		try {

			this.hibernateTemplate.delete(data);
			f = true;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return f;
	}

	
	@Transactional
	public boolean updateChargesForWork(WorkDataDescription data) {
		boolean f = false;

		try {
			
			this.hibernateTemplate.update(data);
			f=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;

	}

}
