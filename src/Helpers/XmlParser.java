package Helpers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
	public String address;
	public String username;
	public String password;
	public String database;
	public String portnumber;
	private static String configfile = "dbconfig.xml";
	private static String path;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getPortnumber() {
		return portnumber;
	}
	public void setPortnumber(String portnumber) {
		this.portnumber = portnumber;
	}
	@Override
	public String toString() {
		return "XmlParser [address=" + address + ", username=" + username
				+ ", password=" + password +", database=" + database+", portnumber=" + portnumber+ "]";
	}
	
	/* ============ Read XML Config File ============= */
	public boolean readXML(){
		if(TahsilUtils.OsType() == "windows"){
			path = System.getProperty("user.home")+"\\config\\";
		}else{
			path = System.getProperty("user.home")+"/.config/";
		}
		Document dom;
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try{
	    	DocumentBuilder db = dbf.newDocumentBuilder();
	    	dom = db.parse(path+configfile);
	    	Element doc = dom.getDocumentElement();
	    	address = getTextValue(address, doc, "address");
	    	username = getTextValue(username, doc, "username");
	    	password = getTextValue(password, doc, "password");
	    	database = getTextValue(database, doc, "database");
	    	portnumber = getTextValue(portnumber, doc, "portnumber");
	    	if(address == null){
	    		address="127.0.0.1";
	    	}
	    	
	    	if(username == null){
	    		username="root";
	    	}
	    	
	    	if(password == null){
	    		password="";
	    	}
	    	
	    	if(database == null){
	    		database="tamarrakech";
	    	}
	    	
	    	if(portnumber == null){
	    		portnumber="3306";
	    	}
	    	

	    }catch (ParserConfigurationException pce) {
	        System.out.println(pce.getMessage());
	    } catch (SAXException se) {
	        System.out.println(se.getMessage());
	    } catch (IOException ioe) {
	        System.err.println(ioe.getMessage());
	    }
		return false;
	}
	
	/* ================= Write XML Config File =================== */
	public boolean writeXML(){
		 try {
				String filepath = path+configfile;
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
		 
				// Get the root element
				//Node dbconfig = doc.getFirstChild();
		 
				// Get the staff element , it may not working if tag has spaces, or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();
		 
				// Get the staff element by tag name directly
				doc.getElementsByTagName("address").item(0).setTextContent(this.address);
				// update staff attribute
				doc.getElementsByTagName("username").item(0).setTextContent(this.username);
		 
				// append a new node to staff
				doc.getElementsByTagName("password").item(0).setTextContent(this.password);
		 
				doc.getElementsByTagName("database").item(0).setTextContent(this.database);
				
				doc.getElementsByTagName("portnumber").item(0).setTextContent(this.portnumber);
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath));
				transformer.transform(source, result);
		 
				//System.out.println("Done");
		 
			   } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			   } catch (TransformerException tfe) {
				tfe.printStackTrace();
			   } catch (IOException ioe) {
				ioe.printStackTrace();
			   } catch (SAXException sae) {
				sae.printStackTrace();
			   }
		return true;
	}
	private String getTextValue(String def, Element doc, String tag) {
	    String value = def;
	    NodeList nl;
	    nl = doc.getElementsByTagName(tag);
	    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
	        value = nl.item(0).getFirstChild().getNodeValue();
	    }
	    return value;
	}
}
