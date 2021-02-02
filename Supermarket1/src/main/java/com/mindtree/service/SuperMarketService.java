package com.mindtree.service;

import java.sql.SQLException;
import java.util.Set;

import com.mindtree.entity.CustomerEntity;
import com.mindtree.entity.ItemEntity;
import com.mindtree.exception.serviceException.SuperMarketServiceException;

public interface SuperMarketService {

	public  void customerSignUp() throws SuperMarketServiceException;
	public void loginAndPurchase() throws SuperMarketServiceException, SQLException;
	public  void sortByMoney() throws SuperMarketServiceException;
	public void addItems() throws SuperMarketServiceException;
	public void updateItem() throws SuperMarketServiceException;
	public void delete() throws SuperMarketServiceException;
	public void sortByPrice() throws SuperMarketServiceException;
	
	
	
}
