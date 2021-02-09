package Helpers;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Session {
	public static DBConnect dbc = new DBConnect();
	public static int userid;
	public static String username;
	public static String userfirstname;
	public static String userlastname;
	public static int roleid;
	public static String rolename;
	public static Timestamp logintime;
	private static String tmpDirPath=System.getProperty("java.io.tmpdir");
	public static String tmpFileName = "userSession.tmp";
	public static String tmpFilePath;

	static{
		
		if(TahsilUtils.OsType() == "windows"){
			tmpFilePath = tmpDirPath+"\\tahsilTmp\\"+tmpFileName;
		}else{
			tmpFilePath = tmpDirPath+"/tahsilTmp/"+tmpFileName;
		}
		File tmpSessionFile = new File(tmpFilePath);
		
		if (tmpSessionFile.exists() && tmpSessionFile.isFile()) {
			try {
				if(dbc.connect()){
				ResultSet rs = dbc.query("SELECT*FROM usersession", null, 'g');
				try {
					if(rs.next()){
						rs.first();
						userid=rs.getInt("userid");
						username=rs.getString("username");
						userfirstname=rs.getString("userfirstname");
						userlastname=rs.getString("userlastname");
						roleid=rs.getInt("roleid");
						rolename=rs.getString("rolename");
						logintime=rs.getTimestamp("logintimestamp");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//dbc.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void read(){
		try {
			dbc.connect();
			ResultSet rs = dbc.query("SELECT*FROM usersession", null, 'g');
				rs.first();
				userid=rs.getInt("userid");
				username=rs.getString("username");
				userfirstname=rs.getString("userfirstname");
				userlastname=rs.getString("userlastname");
				roleid=rs.getInt("roleid");
				rolename=rs.getString("rolename");
				logintime=rs.getTimestamp("logintimestamp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isValide(){
		long sessionvalidity = 3600000; // validité de la session d'une heur
		Timestamp ts = new Timestamp(new Date().getTime());
		long diff = ts.getTime() - getLogintime().getTime();
		if(diff < sessionvalidity){
			Timestamp logintime = new Timestamp(new Date().getTime());
			List<Object> params = new LinkedList<Object>();
			params.add(logintime.toString());
			String updateSession = "UPDATE usersession SET logintimestamp=?";
			try {
				dbc.connect();
				dbc.query(updateSession,params,'u');
				//dbc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}else{
			return false;
		}		
	}
	
	public static int getUserid() {
		return userid;
	}
	public static void setUserid(int userid) {
		Session.userid = userid;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		Session.username = username;
	}
	public static String getUserfirstname() {
		return userfirstname;
	}
	public static void setUserfirstname(String userfirstname) {
		Session.userfirstname = userfirstname;
	}
	public static String getUserlastname() {
		return userlastname;
	}
	public static void setUserlastname(String userlastname) {
		Session.userlastname = userlastname;
	}
	public static int getRoleid() {
		return roleid;
	}
	public static void setRoleid(int roleid) {
		Session.roleid = roleid;
	}
	public static String getRolename() {
		return rolename;
	}
	public static void setRolename(String rolename) {
		Session.rolename = rolename;
	}
	public static Timestamp getLogintime() {
		return logintime;
	}
	public static void setLogintime(Timestamp logintime) {
		Session.logintime = logintime;
	}
}
