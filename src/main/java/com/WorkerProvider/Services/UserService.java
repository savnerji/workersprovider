package com.WorkerProvider.Services;

import java.util.List;

import com.WorkerProvider.Entities.User;
import com.WorkerProvider.Entities.WorkDataDescription;
import com.WorkerProvider.Entities.Worker;
import com.WorkerProvider.Helper.Entities.UserLogIn;

public interface UserService {

	boolean saveUser(User user);
	List<?> getUser(String name, String password);
	List<?> getUser(UserLogIn user);
	
	List<?> getAvailableWorker(String service);
	Worker getWorker(int id);
	
	List<?> getAvailableWorkerByCity(String city,String Service);
	List<?>  getUserByEmail(String username);
	boolean updateForgotPassword(String email,String password);
	
	boolean updateUser(User user);
	
	boolean sendWorkServiceData(WorkDataDescription workDetails);
	
	List<?>  getUserSentRequest(User user,int id);
	
	boolean completeRequest(WorkDataDescription data);

	WorkDataDescription getWorkDataDescription(int id);
	
	
}
