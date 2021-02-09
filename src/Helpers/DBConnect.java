package Helpers;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import Helpers.XmlParser;



public class DBConnect {
	XmlParser xmlparser = new XmlParser();
	Connection cnx;
	PreparedStatement prestmnt;
	private String url;
	private String username;
	private String password;
	
	public boolean connect() throws Exception{
		boolean stat = true;
		xmlparser.readXML();
		url = "jdbc:mysql://"+xmlparser.getAddress()+":"+xmlparser.getPortnumber()+"/"+xmlparser.getDatabase()+"?useUnicode=yes&characterEncoding=UTF-8";
		username = xmlparser.getUsername();
		password = xmlparser.getPassword();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, username, password);
		}catch(CommunicationsException ce){
			JOptionPane.showMessageDialog(null, "هناك خطأ في الربط مع قاعدة البيانات، المرجو التحقق من الاعدادات","خطأ",JOptionPane.ERROR_MESSAGE);
			System.out.println("Communication Error");
			stat = false;
		}catch (ClassNotFoundException ex) {
            System.err.println("Ne peut pas trouver les classes du Driver de la base de données.");
            System.err.println(ex);
            stat = false;
        } catch (SQLException ex) {
            System.err.println("pas de connection à la base de de donnee.");
            System.err.println(ex);
            stat = false;
        }
		
		return stat;
	}
	
	public void close(){
		try {
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String query, List<Object> params, char type){
		ResultSet rs = null;

		try {
			prestmnt = cnx.prepareStatement(query);
			if(params != null){
				
				for (int i = 0; i < params.size(); i++) {
					int x=i+1;
					if (params.get(i) instanceof String) {
						if (params.get(i) == "") {
							prestmnt.setNull(x, Types.NULL);
						}else{
							prestmnt.setString(x, (String) params.get(i));
						}
						
					}else if(params.get(i) instanceof Integer){
						prestmnt.setInt(x, (Integer) params.get(i));
					}else if(params.get(i) instanceof Timestamp){
						prestmnt.setTimestamp(x,(Timestamp) params.get(i));
					}else if(params.get(i) instanceof Double){
						prestmnt.setDouble(x,(Double) params.get(i));
					}else if(params.get(i) instanceof Date){
						Date utildate = (Date) params.get(i); 
						prestmnt.setDate(x, new java.sql.Date(utildate.getTime()));
					}
				}
			}
			// SELECT statement ++++++++++++++++++++
			if(type == 'g'){
				rs = prestmnt.executeQuery();
			//Manipulate TablesStatement +++++++++++
			}else if(type == 'm'){
				prestmnt.execute();
			//INSERT and UPDATE Statements +++++++++
			}else if(type == 'u'){
				prestmnt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
