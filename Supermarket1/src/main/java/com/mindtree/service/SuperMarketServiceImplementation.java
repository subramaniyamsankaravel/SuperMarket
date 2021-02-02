package com.mindtree.service;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.mindtree.servicedao.SuperMarketDBImp;
import com.mindtree.entity.CustomerEntity;
import com.mindtree.entity.ItemEntity;
import com.mindtree.exception.daoException.DaoLayerException;
import com.mindtree.exception.serviceException.SuperMarketServiceException;

public class SuperMarketServiceImplementation implements SuperMarketService {
	private static SuperMarketDBImp obj = new SuperMarketDBImp();

	public void customerSignUp() throws SuperMarketServiceException {
		try {
			 obj.customerSignUp();

		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
	}

	public void loginAndPurchase() throws SuperMarketServiceException, SQLException {
		try {
			obj.loginAndPurchase();

		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
	}

	public void sortByMoney() throws SuperMarketServiceException {
		try {
			obj.sortByMoney();

		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
	}

	public void addItems() throws SuperMarketServiceException {
		try {
			obj.addItems();

		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
	}

	public void updateItem() throws SuperMarketServiceException {
		try {
			obj.updateItem();

		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
	}

	public void delete() throws SuperMarketServiceException {
		try {
			obj.delete();

		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
	}

	public void sortByPrice() throws SuperMarketServiceException {
		try {
			obj.sortByPrice();
		} catch (DaoLayerException e) {
			e.getStackTrace();
		}
		
	}

}
