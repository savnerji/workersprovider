package com.WorkerProvider.Services;

import java.util.List;

import com.WorkerProvider.Entities.AddWorkerStatus;
import com.WorkerProvider.Entities.WorkDataDescription;
import com.WorkerProvider.Entities.Worker;
import com.WorkerProvider.Helper.Entities.WorkerLogIn;


public interface WorkerService {

	boolean saveProfilePic(String path,byte[] pic);
	boolean saveAadharPic(String path,byte[] pic);
	boolean saveWorker(Worker worker);
	List<?> getWorker(WorkerLogIn user);
	
	boolean updateWorker(Worker worker);
	boolean updateWorkerPic(String path,String oldPath,byte[] pic);
	
	boolean addWorkerStatus(AddWorkerStatus status);
	
	boolean updateWorkerStatus(AddWorkerStatus status,Worker wrkr);
	
	AddWorkerStatus getWorkerStatus(Worker wrkr);
	List<?> getWorker(String username);
	boolean updateWorkerPassword(String email, String password);
	void deletePics(String aadharPic,String profilePic);
	
	void deletePics(String path);

	List<?> getWorkRequests(Worker worker,int Status);

	boolean deleteRequestByWorker(WorkDataDescription data);
	
	WorkDataDescription getWorkData(int id);
	
	boolean updateChargesForWork(WorkDataDescription data);
}
