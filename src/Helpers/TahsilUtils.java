package Helpers;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileDeleteStrategy;

public class TahsilUtils {
	private static InputStream  is = null;
    private static OutputStream  os = null;
    private static String path;
    
	public int VarParseInt(String t){
		return Integer.parseInt(t);
	}
	
	public Double VarParseDouble(String t){
		return Double.parseDouble(t);
	}
	
	public void configToHome(){
		String configfile = "dbconfig.xml";
		if(OsType() == "windows"){
			path = System.getProperty("user.home")+"\\config\\";
		}else{
			path = System.getProperty("user.home")+"/.config/";
		}
		CreateDirIfNotExist(path);
		File f = new File(path+configfile);
		if(!f.exists()) { 
			try{
				 is = TahsilUtils.class.getClassLoader().getResourceAsStream("config/"+configfile);		
				 os = new FileOutputStream(new File(path+configfile));
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				}catch(Exception e){
					e.getMessage();
				}finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (os != null) {
						try {
							// outputStream.flush();
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
		}
	}
	
	public static boolean CreateDirIfNotExist(String dirpath){
		File theDir = new File(dirpath);
		boolean result = false;
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    try{
		        if(theDir.mkdirs()){
		        	result = true;
		        }
		        
		    } catch(SecurityException se){
		        //handle it
		    	se.getStackTrace();
		    }        
		}else{
			result = true;
		}
		return result;
	}

	public static void DeleteTmps(){
		String tmpDirPath="";
		if(OsType() == "windows"){
			tmpDirPath = System.getProperty("java.io.tmpdir")+"\\tahsilTmp";
		}else{
			tmpDirPath = System.getProperty("java.io.tmpdir")+"/tahsilTmp";
		}
		
		File tmpDir = new File(tmpDirPath);
		if(tmpDir.exists() && tmpDir.isDirectory()){
			try {
				FileDeleteStrategy.FORCE.delete(tmpDir);
			} catch (IOException e) {
				System.out.println("impossible de supprimer le dossier temporaire");
				e.printStackTrace();
			}
		}
	}
	
	public static String OsType(){
		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.indexOf("nux") >= 0){
			return "linux";
		}else{
			return "windows";
		}
	}
	
	public static void toCSV(List<String[]> list, JDialog jdialog){
		// writer
		BufferedWriter writer;
		try {
			final JFileChooser fc = new JFileChooser();
			fc.setSelectedFile(new File("statistics.csv"));
			int returnVal = fc.showSaveDialog(jdialog); //parent component to JFileChooser
			if (returnVal == JFileChooser.APPROVE_OPTION) { //OK button pressed by user
				File file = fc.getSelectedFile();
				String path = file.getPath();
				
				//result = 0 means OptionPane.YES_OPTION is pressed
				int result = 0;
				
				if(file.exists() && fc.getDialogType() == fc.SAVE_DIALOG){
					result = JOptionPane.showConfirmDialog(jdialog,"المستند موجود مسبقا، هل تريد فعلا استبداله؟","مستند موجود",JOptionPane.YES_NO_OPTION);
				}
				
				//cases of a choice, if the file doesn't exist, the file will be created
				switch(result){
	                case JOptionPane.YES_OPTION:
	                	//System.out.println(path);
	    				//writer = new FileWriter(path);
	                	writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
	    			    // data
	    			    for(String[] arr: list) {
	    			        String appender = "";
	    			        for(String s : arr){
	    			            writer.write(appender + "\""+s+"\"");
	    			            appender = ";";
	    			        }
	    			        writer.write("\n");
	    			        writer.flush();
	    			    }
	    			    writer.close();
	    			    return;
	                case JOptionPane.NO_OPTION:
	                    return;
	                case JOptionPane.CLOSED_OPTION:
	                    return;
	            }
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(jdialog, "حدث خطأ أثناء حفض المستند، المرجو التأكد من أذونات المستعمل","خطأ",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 

	}

}
