package com.GroupOn.servlet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONObject;


public class DataBaseHelper {
	private Connection con = dbConnection.getConnection();
	
	public JSONObject createJsonForLogin(String st,String typeofuser)
	{
		JSONObject jObj = new JSONObject();
		jObj.put("status", st);
        jObj.put("type", typeofuser);
        return jObj;
		
	}
	
	public JSONArray createJsonForOffer() throws SQLException
	{
		PreparedStatement insertUser = con
				.prepareStatement("Select offername,description,peoplerequired,offerstartdate,offerenddate,offercategory,price,corporateid from offer"); 
		ResultSet r1= insertUser.executeQuery();
		JSONArray jArray = new JSONArray();
         while (r1.next())
         {
            try {
				String  offerName=r1.getString("offername");
				String desc=r1.getString("description");
				int pplreq = r1.getInt("peoplerequired");
				Date sdate=r1.getDate("offerstartdate");
				Date edate = r1.getDate("offerenddate");
	            String cat=r1.getString("offercategory");
	            double cost = r1.getDouble("price");
	            int id_corp = r1.getInt("corporateid");
	            PreparedStatement getNameOfcorp = con.prepareStatement("select firstname,lastname,email from userdetails where userid=? and typeofuser=?");
	            getNameOfcorp.setInt(1, id_corp);
	            getNameOfcorp.setInt(2, 1);
	            ResultSet r2 = getNameOfcorp.executeQuery();
	            String Name_of_corp=null;
	            String email_corp=null;
	            if(r2.next())
	            {
	            	Name_of_corp= r2.getString("firstname")+ r2.getString("lastname");
	            	email_corp = r2.getString("email");
	            }
	            JSONObject jObj = new JSONObject();
	            jObj.put("name", offerName);
	            jObj.put("desc", desc);
	            jObj.put("num_ppl_req", pplreq);
	            jObj.put("start_date", sdate);
	            jObj.put("end_date", edate);
	            jObj.put("category", cat);
	            jObj.put("cost_per_person", cost);
	            jObj.put("Name_of_corp_created", Name_of_corp);
	            jObj.put("email_of_corp_created", email_corp);
	            jArray.put(jObj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            

         }
		return jArray;
		
	}

	public void insertOffer(String offerName, String offerDesc, String offerCat,
			String offerstart,String offerend, String nbrusers, String costofusers,int userid) throws SQLException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
		java.util.Date sdateindateformat = null;
		java.util.Date edateindateformat = null;
        try {
        	sdateindateformat =  dateFormat.parse(offerstart);
        	edateindateformat = dateFormat.parse(offerend);

        } catch (ParseException e) {
            e.printStackTrace();
		int nmbrusers=Integer.parseInt(nbrusers);
		int cost = Integer.parseInt(costofusers);
			try {
				PreparedStatement insertUser = con
						.prepareStatement("INSERT INTO offer (offername,description,peoplerequired,offerstartdate,offerenddate,offercategory,price,corporateid) VALUES (?, ?, ?, ?, ?,?,?,?)");
				con.setAutoCommit(false);
				insertUser.setString(1, offerName);
				insertUser.setString(2, offerDesc);
				insertUser.setInt(3, nmbrusers);
				insertUser.setDate(4, (Date) sdateindateformat);
				insertUser.setDate(5, (Date) edateindateformat);
				insertUser.setString(6, offerCat);
				insertUser.setInt(7, cost);
				insertUser.setInt(8, userid);
				insertUser.execute();
				con.commit();
			} catch (SQLException e1) {
				if (con != null) {
					System.err.print("Transaction is being rolled back");
					con.rollback();
				}
				e1.printStackTrace();
			} finally {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			}
        }
	}
	public void insertUser(String fName, String lName, String typeOfUser,
			String email, String pass, String address) throws SQLException {
		int typeUser=0;
		try {
			if(typeOfUser.equals("Regular User"))
				typeUser=0;
			else
				typeUser=1;
			PreparedStatement insertUser = con
					.prepareStatement("INSERT INTO userdetails (firstName, LastName,email,typeofuser,upassword,address) VALUES (?, ?, ?, ?, ?,?)");
			con.setAutoCommit(false);
			insertUser.setString(1, fName);
			insertUser.setString(2, lName);
			insertUser.setString(3, email);
			insertUser.setInt(4, typeUser);
			insertUser.setString(5, pass);
			insertUser.setString(6, address);
			insertUser.execute();
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				System.err.print("Transaction is being rolled back");
				con.rollback();
			}
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.setAutoCommit(true);
				con.close();
			}
		}
	}

	public boolean checkUser(String username) throws SQLException {
		boolean userNameExists = false;
		PreparedStatement checkUserCred = con
				.prepareStatement("select email from userdetails where email = ?");

		try {
			checkUserCred.setString(1, username);
			ResultSet r1 = checkUserCred.executeQuery();
			if (r1.next()) {
				userNameExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occurred");
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return userNameExists;
	}

	public JSONObject verify_Credentials(String username, String password)
			throws SQLException {
		boolean verified = false;
		PreparedStatement checkUserCred = con
				.prepareStatement("select email,typeofuser from userdetails where email = ? and upassword=?");
		JSONObject jsobj =null;

		try {
			checkUserCred.setString(1, username);
			checkUserCred.setString(2, password);
			ResultSet r1 = checkUserCred.executeQuery();
			
			if (r1.next()) {
				jsobj =createJsonForLogin("success",r1.getString("typeofuser"));
				verified = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception occurred");
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return jsobj;
	}

}
