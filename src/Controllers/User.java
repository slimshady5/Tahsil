package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Helpers.DBConnect;
import Helpers.TahsilUtils;


public class User {
	DBConnect dbc = new DBConnect();
	private File tempSessionFile;
	private static String TmpDirPath = System.getProperty("java.io.tmpdir");
	private static String TmpFilePath;
	private static String tmpFileName = "userSession.tmp";
	boolean exists;
	

	public boolean login(String username, String password){
		List<Object> params = new LinkedList<Object>();
		params.add(username);
		params.add(password);
		String sql = "SELECT users.id AS user_id ,users.firstname, users.lastname, users.username, role.id AS role_id,role.rolename FROM users,role,userrole WHERE username = ? AND password = ? AND userrole.user_id=users.id AND userrole.role_id=role.id";
		
		try {
			dbc.connect();
			ResultSet rs = dbc.query(sql, params,'g');
			if(!rs.next()){
				exists = false;
			}else{
				exists = true;
				usersession(rs);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}
	
	public void usersession(ResultSet rs){
		List<Object> params = new LinkedList<Object>();

		try {
			//assign values to the variables retrived from database
			int userid= rs.getInt("user_id");
			String username = rs.getString("username");
			String userfirstname = rs.getString("firstname");
			String userlastname = rs.getString("lastname");
			int roleid= rs.getInt("role_id");
			String rolename = rs.getString("rolename");
			Timestamp logintime = new Timestamp(new Date().getTime());
			//SQL query to insert the user session data in the temporary table
			String addtotmptable = "INSERT INTO usersession VALUES(? ,? , ?, ?, ?, ?, ?)";
			
			//add the values to the list of parameters that will be sent to the "query()" function
			params.add(userid);
			params.add(username);
			params.add(userfirstname);
			params.add(userlastname);
			params.add(roleid);
			params.add(rolename);
			params.add(logintime);
			//execute the Query - insert session data in the database tmp table
			dbc.query(addtotmptable,params,'m');
			
			if(TahsilUtils.OsType() == "windows"){
				TmpDirPath = TmpDirPath+"\\tahsilTmp\\";
			}else{
				TmpDirPath = TmpDirPath+"/tahsilTmp/";
			}
			if(TahsilUtils.CreateDirIfNotExist(TmpDirPath)){
				TmpFilePath = TmpDirPath+tmpFileName;
				setTempSessionFile(new File(TmpFilePath));
			}
			if(!tempSessionFile.exists()){
				tempSessionFile.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(tempSessionFile));
		    	bw.write(username);
		    	bw.close();
			}
			
	    	    
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void logout(){
		String username = "";
		try {
		BufferedReader br;
		if(TahsilUtils.OsType() == "windows"){
			br = new BufferedReader(new FileReader(new File(System.getProperty("java.io.tmpdir")+"\\tahsilTmp\\userSession.tmp"))); 
		}else{
			br = new BufferedReader(new FileReader(new File(System.getProperty("java.io.tmpdir")+"/tahsilTmp/userSession.tmp"))); 
		}
		
			dbc.connect();
            
            username = br.readLine();
            br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String cleartmptable = "DELETE FROM usersession WHERE username = '"+username+"'";
		dbc.query(cleartmptable,null,'m');
		dbc.close();
		
	}

	public File getTempSessionFile() {
		return tempSessionFile;
	}

	public void setTempSessionFile(File tempSessionFile) {
		this.tempSessionFile = tempSessionFile;
	}
	
}
