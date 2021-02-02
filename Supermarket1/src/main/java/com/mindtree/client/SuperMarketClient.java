package com.mindtree.client;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import com.mindtree.entity.ItemEntity;
import com.mindtree.exception.serviceException.SuperMarketServiceException;
import com.mindtree.exception.superMarketException.SuperMarketException;
import com.mindtree.service.SuperMarketServiceImplementation;


public class SuperMarketClient {
	private static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws SuperMarketException, SQLException{
		SuperMarketServiceImplementation emp=new SuperMarketServiceImplementation();
		boolean cond=true;
		while(cond) {
			System.out.println("-------------------------");
			System.out.println("1.Signup");
			System.out.println("2.Shop");
			System.out.println("3.Bill");
			System.out.println("4.Add item");
			System.out.println("5.Update item quantity");
			System.out.println("6.Delete item");
			System.out.println("7.Sort item by price and quantity");
			System.out.println("8.Exit");
			System.out.println("Enter choice");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:emp.customerSignUp();
				break;
			case 2:emp.loginAndPurchase();
				break;
			case 3:emp.sortByMoney();
				break;
			case 4:emp.addItems();
				break;
			case 5:emp.updateItem();
				break;
			case 6:emp.delete();
				break;
			case 7:emp.sortByPrice();
				break;
			case 8:cond=false;
			default:System.out.println("Enter proper choice");
			}
		}
	}	
	}
		