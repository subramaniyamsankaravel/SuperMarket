package com.mindtree.servicedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Scanner;
import com.mindtree.entity.ItemEntity;
import com.mindtree.exception.daoException.DaoLayerException;
import com.mindtree.exception.daoException.databaseEmptyException;
import com.mindtree.utility.DatabaseConnection;
import com.mysql.cj.protocol.Resultset;
import com.sun.java_cup.internal.runtime.Symbol;
public class SuperMarketDBImp implements SuperMarketDB {
	private static Scanner sc = new Scanner(System.in);

	public Symbol next_token() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void customerSignUp() throws DaoLayerException {
		System.out.println("Enter the customer Id");
		int id = sc.nextInt();
		System.out.println("Enter the customer name");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter the phoneNumber");
		String pNum = sc.nextLine();
		System.out.println("Enter the billamount");
		int date=sc.nextInt();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.connect();
			String sql = "insert into customer(id,name,contact,billAmount)values(?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, pNum);
			pst.setInt(4, date);
			pst.executeUpdate();
		} catch (SQLException s) {
			s.getStackTrace();
		} finally {
			DatabaseConnection.close(con, pst);
		}
	}

	public void loginAndPurchase() throws DaoLayerException, SQLException {
		Scanner sc=new Scanner(System.in);
		int id=0;
		int quantity=0;
		int userId=0;
		int billAmount=0;
		boolean cond=true;
		int total=0;
		try {
		Connection con = DatabaseConnection.connect();
		System.out.println("Enter id");
		userId=sc.nextInt();
		String abc="Select billAmount from customer where id=?";
		java.sql.PreparedStatement sta=con.prepareStatement(abc);
		sta.setInt(1, userId);
		ResultSet r=sta.executeQuery();
		if(!(r.next())) {
			cond=false;
			throw new Exception("id not found");
		
		}
		billAmount=r.getInt(1);
		r.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		while(cond) {
			System.out.println("1.Add item to cart");
			System.out.println("2.Finish purchase");
			System.out.println("Enter choice");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:
				try{
					Connection con = DatabaseConnection.connect();
					System.out.println("Enter item id to purchase");
			id=sc.nextInt();
			String sql="Select * from item where id=?";
			java.sql.PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			rs.next();
				
			System.out.println("id-"+rs.getInt(1)+" name-"+rs.getString(2)+" price-"+rs.getInt(3));
			System.out.println("Enter quantity to purchase");
			quantity=sc.nextInt();
			total=total+(rs.getInt(3)*quantity);
			System.out.println(total);
			quantity=rs.getInt(4)-quantity;
			st.close();
			rs.close();
				}catch(Exception e) {
					throw new SQLException("id not found");
				}
			Connection con = DatabaseConnection.connect();
			String ql="update item set quantity=? where id=?";
			java.sql.PreparedStatement pst=con.prepareStatement(ql);
			pst.setInt(2, id);
			pst.setInt(1, quantity);
			pst.executeUpdate();
			System.out.println("Item purchased");
			pst.close();
					break;
			case 2:cond=false;
					System.out.println("Thank you for shopping");
					break;
			default:System.out.println("Enter proper choice");
			}
		}
		Connection con = DatabaseConnection.connect();
		billAmount+=total;
		System.out.println(total);
		String ql="update customer set billAmount=? where id=?";
		java.sql.PreparedStatement pst=con.prepareStatement(ql);
		pst.setInt(2, userId);
		pst.setInt(1, billAmount);
		pst.executeUpdate();
		pst.close();
		}
	public void sortByMoney() throws DaoLayerException {
		boolean val = false;
		Connection con = null;
		PreparedStatement pst = null;
		System.out.println("Enter the date");
		String date = sc.next();
		try {
			con = DatabaseConnection.connect();
			String sql = "select * from  customer  order by billAmount ";
			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, date);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == false) {
				System.out.println("no records");
			} else {
				System.out.println("sorted based on total ammount");
				rs.previous();
				while (rs.next()) {
					System.out.println(
							"cust id : " + rs.getInt(1) + "  cust namename : " + rs.getString(2) + "  phone number : "
									+ rs.getString(3) + "  date :" + rs.getDate(4) + "  total bill" + rs.getDouble(5));
				}

			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	public void addItems() throws DaoLayerException {
		boolean val = false;
		while (true) {
			System.out.println("Enter the item Id");
			int id = sc.nextInt();
			System.out.println("Enter the item name");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.println("Enter the item quantity");
			int quantity = sc.nextInt();
			System.out.println("item price");
			double price = sc.nextDouble();
			Connection con = null;
			PreparedStatement pst = null;
			try {
				con = DatabaseConnection.connect();
				String sql = "insert into item values(?,?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				pst.setString(2, name);
				pst.setDouble(3, price);
				pst.setInt(4, quantity);
				int i = pst.executeUpdate();
				if (i > 0) {
					System.out.println("Item added");
					val = true;
				} else {
					System.out.println("Item not added");

				}
				System.out.println("do you want to add more items\n1>yes\n2>no ");
				int n = sc.nextInt();
				if (n == 1) {

				} else {
					break;
				}

			}

			catch (SQLException s) {
				s.getStackTrace();
			}

			finally {
				DatabaseConnection.close(con, pst);
			}
		}
	}

	public void updateItem() throws DaoLayerException {
		boolean val = true;
		System.out.println("Enter the item Id");
		int id = sc.nextInt();
		System.out.println("Enter the item quantity");
		int quantity = sc.nextInt();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.connect();
			String sql = "update item set quantity=? where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(2, id);
			pst.setInt(1, quantity);
			int i = pst.executeUpdate();
			if (i > 0) {
				val = true;
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	public void delete() throws DaoLayerException {
		boolean val = true;
		System.out.println("Enter the item Id");
		int id = sc.nextInt();
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.connect();
			String sql = "delete from item  where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			int i = pst.executeUpdate();
			if (i > 0) {
				val = true;
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
		if(val=true) {
			System.out.println("Book deleted successfully");
		}
	}

	public void sortByPrice() throws DaoLayerException {
		String sql = "select * from item order by  quantity";
		Connection con = null;
		PreparedStatement pst = null;
		Set<ItemEntity> ls = new LinkedHashSet<ItemEntity>();

		try
		{
			
			con = DatabaseConnection.connect();
			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == false) {
				System.out.println("no records");
			} else {
				System.out.println("sorted based on items quantity");
				rs.previous();
				while (rs.next()) {
					ls.add(new ItemEntity(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)
							));
				}
  rs.close();
			}
			System.out.println("================================================================");
			sql = "select * from item order by  price";
			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 ResultSet rs1 = pst.executeQuery();
			if (rs1.next() == false) {
				System.out.println("no records");
			} else {
				System.out.println("sorted based on items  price ");
				while(rs1.next()) {
				System.out.println("Itemid : " + rs1.getInt(1) + "  Item name : " + rs1.getString(2) + "  cost : "
						+ rs1.getDouble(3) + " quantity :" + rs1.getInt(4));
				}

			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(con, pst);

		}
	}

	public void display() {
		String sql = "select * from item";
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.connect();
			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery();
			if (rs.next() == false) {
				System.out.println("no records");
			} else {
				System.out.println("available items are");
				rs.previous();
				while (rs.next()) {
					System.out.println("Itemid : " + rs.getInt(1) + "  Item name : " + rs.getString(2) + "  cost : "
							+ rs.getDouble(3) + " quantity :" + rs.getInt(4));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(con, pst);

		}

	}

	public boolean dec(int quantity, int itemId) {
		boolean val = false;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.connect();
			String sql = "select quantity from item  where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, itemId);
			ResultSet rs = pst.executeQuery();
			rs.next();
			int getquantity = rs.getInt(1);
			if (update(quantity, itemId, getquantity)) {
				val = true;
			} else {
				val = false;
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			DatabaseConnection.close(con, pst);
		}
		return val;

	}

	public boolean update(int getquantity, int itemId, int quantity) {
		boolean val = false;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			if (quantity < getquantity && getquantity != 0) {
				System.out.println("Item not available");
			} else {

				int qua = quantity - getquantity;
				System.out.println(qua);
				con = DatabaseConnection.connect();
				String sql = "update item set quantity =? where id=?";
				pst = con.prepareStatement(sql);
				pst.setInt(2, itemId);
				pst.setInt(1, qua);
				pst.executeUpdate();
				val = true;

			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			DatabaseConnection.close(con, pst);
		}
		System.out.println(val);
		return val;
	}
}
