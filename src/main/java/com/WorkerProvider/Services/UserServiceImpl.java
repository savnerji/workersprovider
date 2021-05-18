package com.WorkerProvider.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkerProvider.DAO.UserDao;
import com.WorkerProvider.Entities.User;
import com.WorkerProvider.Entities.WorkDataDescription;
import com.WorkerProvider.Entities.Worker;
import com.WorkerProvider.Helper.Entities.UserLogIn;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public boolean saveUser(User user) {
		
		boolean f=false;
		
		f=userDao.saveUser(user);
		return f;
	}

	public List<?> getUser(String name, String password) {
		List<?> usr=userDao.getUser(name,password);
		return usr;
	}

	public List<?> getUser(UserLogIn user) {
		List<?> usr=userDao.getUser(user);
		return usr;
		
	}


	public List<?> getAvailableWorker(String service ) {
		List<?> availableWorkers = userDao.getAvailableWorker(service);
		return availableWorkers;
	}

	public Worker getWorker(int id) {
		
		Worker wrkr=userDao.getWorker(id);
		return wrkr;
	}

	
	
	public List<?> getAvailableWorkerByCity(String city,String service) {
		List<?> availableWorkersByCity = userDao.getWorkersByCity(city,service);
		return availableWorkersByCity;
	}

	
	public List<?> getUserByEmail(String username) {
		List<?> user=userDao.getUSerByEmail(username);
		return user;
	}

	@Override
	public boolean updateForgotPassword(String email,String password) {
		
		boolean f=false;
		f=userDao.updateForgotPassword(email,password);
		return false;
	}

	@Override
	public boolean updateUser(User user) { 
		boolean f= false;
		
		f=userDao.updateUser(user);
		return f;
	}

	@Override
	public boolean sendWorkServiceData(WorkDataDescription workDetails) {
		
		boolean status = userDao.saveWorkDetails(workDetails);
		
		return status;
	}

	@Override
	public List<?> getUserSentRequest(User user,int id) {
		
		List<?> userSentRequest = userDao.getUserSentRequest(user,id);
		return userSentRequest;
	}

	@Override
	public boolean completeRequest(WorkDataDescription data) {
		
		boolean status = userDao.completeRequest(data);
		return status;
	}

	@Override
	public WorkDataDescription getWorkDataDescription(int id) {
	
		
		return userDao.getWorkDataDescription(id);
	}




	

}
