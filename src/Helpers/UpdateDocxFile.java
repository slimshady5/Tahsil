package Helpers;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class UpdateDocxFile {

    private String INPUT_ZIP_FILE = "template.docx";
    private static final String OUTPUT_FOLDER = "extract";
    private String RESULT_FILE = "result.docx";
    private static String path = System.getProperty("java.io.tmpdir");
    private static InputStream  is = null;
    private static OutputStream  os = null;
    private Map<String, String> params;
	
	public void ExtractAllFiles() {
			try {
				// Initiate ZipFile object with the path/name of the zip file.
				ZipFile zipFile = new ZipFile(path+INPUT_ZIP_FILE);
				
				// Extracts all files to the path specified
				zipFile.extractAll(path+OUTPUT_FOLDER );
				
			} catch (ZipException e) {
				e.printStackTrace();
			}
		}
	
	public UpdateDocxFile(String izf, String rslt, Map<String, String> params) {
		super();
		this.INPUT_ZIP_FILE = izf;
		this.RESULT_FILE = rslt;
		this.params = params;
		System.out.println(this.params.toString());
	}

	public void Compress() {
		
		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(path+RESULT_FILE);
			
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			
			// set compression method to store compression
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			
			// Set the compression level
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			// DO NOT INCLUDE ROOT DIRECTORY
			parameters.setIncludeRootFolder(false);
			
			// Add folder to the zip file
			zipFile.addFolder(path+OUTPUT_FOLDER , parameters);
			
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	//Open The Document 
	public void LunchDoc(){
		try {
			Desktop.getDesktop().open(new File(path+RESULT_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean replaceVars(){
		  List<String> lines = new ArrayList<String>();
		    String line = null;

		        try {
		            File f1 = new File(path+OUTPUT_FOLDER+"/word/document.xml");
		            FileReader fr = new FileReader(f1);
		            BufferedReader br = new BufferedReader( new InputStreamReader(
		                               new FileInputStream(f1), "UTF8"));
		            
		            	while ((line = br.readLine()) != null) {
				            	line = line.replace("\n","");
				            	for (Map.Entry<String, String> entry : params.entrySet()){
				            		line = line.replace(entry.getKey(), entry.getValue());
				            	}
				            	lines.add(line);
				            }
		            
		            fr.close();
		            br.close();

		            OutputStream ops = new FileOutputStream(f1);
		            Writer opsw = new OutputStreamWriter(ops, "UTF-8");
		            for(String s : lines)
		            	opsw.write(s);
		            	opsw.flush();
		            	opsw.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		return true;
	}
	
	private boolean InputStreamToFile(){
		try{
		 is = UpdateDocxFile.class.getClassLoader().getResourceAsStream(INPUT_ZIP_FILE);		
		 os = new FileOutputStream(new File(path+INPUT_ZIP_FILE));
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
		return true;
	}
	
	public boolean CreateDirIfNotExist(){
		File theDir = new File(path+OUTPUT_FOLDER);
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
		}
		return result;
	}
	
	public void UpdateFile(){
		if(TahsilUtils.OsType() == "windows"){
			path = path+"\\tahsilTmp\\";
		}else{
			path = path+"/tahsilTmp/";
		}
	    
	    	CreateDirIfNotExist();
	    	InputStreamToFile();
	    	ExtractAllFiles();
	    	replaceVars();
	    	Compress();
	    	LunchDoc();
	}
}
