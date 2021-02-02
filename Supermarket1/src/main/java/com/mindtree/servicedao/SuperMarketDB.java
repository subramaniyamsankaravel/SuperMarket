package com.mindtree.servicedao;

import java.sql.SQLException;
import java.util.Set;

import com.mindtree.entity.CustomerEntity;
import com.mindtree.entity.ItemEntity;
import com.mindtree.exception.daoException.DaoLayerException;

public interface SuperMarketDB {
	public  void customerSignUp() throws DaoLayerException;
	public void loginAndPurchase() throws DaoLayerException, SQLException;
	public void sortByMoney() throws DaoLayerException;
	public void addItems() throws DaoLayerException;
	public void updateItem() throws DaoLayerException;
	public void delete() throws DaoLayerException;
	public void sortByPrice() throws DaoLayerException;

}
