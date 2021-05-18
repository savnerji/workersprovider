package com.WorkerProvider.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkerProvider.DAO.WorkerDao;
import com.WorkerProvider.Entities.AddWorkerStatus;
import com.WorkerProvider.Entities.WorkDataDescription;
import com.WorkerProvider.Entities.Worker;
import com.WorkerProvider.Helper.Entities.WorkerLogIn;

@Service
public class WorkerServiceImpl implements WorkerService {
	@Autowired
	WorkerDao workerDao;

	// save worker profile pic to folder
	public boolean saveProfilePic(String path, byte[] pic) {
		boolean f = workerDao.saveProfilePIc(path, pic);
		return f;
	}

	// save worker aadhar pic to folder
	public boolean saveAadharPic(String path, byte[] pic) {

		boolean f = workerDao.saveAadharPic(path, pic);
		return f;
	}

	// save worker
	public boolean saveWorker(Worker worker) {
		boolean f = workerDao.saveWorker(worker);
		return f;
	}

	public List<?> getWorker(WorkerLogIn worker) {

		List<?> list = workerDao.getWorker(worker);
		return list;
	}

	// update worker
	public boolean updateWorker(Worker worker) {

		boolean f = workerDao.updateWorker(worker);
		return f;
	}

	// update worker pic
	public boolean updateWorkerPic(String path, String oldPath, byte[] pic) {

		boolean f = workerDao.updateWorkerPic(path, oldPath, pic);
		return f;
	}

	// add worker status
	public boolean addWorkerStatus(AddWorkerStatus status) {

		boolean f = workerDao.addStatus(status);
		return f;
	}

	// update Worker status
	public boolean updateWorkerStatus(AddWorkerStatus status, Worker wrkr) {
		boolean f = workerDao.updateWorkerStatus(status, wrkr);
		return f;
	}

	// get Worker status
	public AddWorkerStatus getWorkerStatus(Worker wrkr) {

		return workerDao.getWorkerStatus(wrkr);
	}

	@Override
	public List<?> getWorker(String username) {
		List<?> lst = workerDao.getWorkerByName(username);
		return lst;
	}

	@Override
	public boolean updateWorkerPassword(String email, String password) {

		return false;
	}

	@Override
	public void deletePics(String aadharPic, String profilePic) {
		workerDao.deletePics(aadharPic, profilePic);

	}

	@Override
	public void deletePics(String path) {
		workerDao.delete(path);
	}

	@Override
	public List<?> getWorkRequests(Worker worker, int Status) {
		List<?> lst = workerDao.getWorkRequests(worker, Status);
		return lst;
	}

	@Override
	public WorkDataDescription getWorkData(int id) {
		
		WorkDataDescription details = workerDao.getWorkDataDetails(id);
		
		return details;
	}

	@Override
	public boolean deleteRequestByWorker(WorkDataDescription data) {
	
		return workerDao.deleteREquestByWorker(data);
	}

	@Override
	public boolean updateChargesForWork(WorkDataDescription data) {
		
		
		
		return workerDao.updateChargesForWork(data);
	}



}
