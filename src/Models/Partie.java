package Models;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Helpers.DBConnect;

public class Partie {
	
	DBConnect dbc = new DBConnect();
	private int id;
	private String type;
	private String nom;
	private String prenom;
	private String ville;
	private String adresse;
	private String role_partie;
	private int debiteur;
	private int dossier_id;
	private boolean physiqueIsSelected;
	private boolean isDebiteur;
	
	
	/**
	 * @param id
	 * @param type
	 * @param nom
	 * @param prenom
	 * @param ville
	 * @param adresse
	 * @param role_partie
	 * @param debiteur
	 * @param dossier_id
	 */
	public Partie(){
		super();
	}
	public Partie(int id, String type, String nom, String prenom, String ville, String adresse, String role_partie, int debiteur,
			int dossier_id) {
		super();
		this.id = id;
		this.type = type;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.adresse = adresse;
		this.role_partie = role_partie;
		this.debiteur = debiteur;
		this.dossier_id = dossier_id;
	}
	
	//Constructor with property "physiqueIsSelected"
	public Partie(int id, boolean physiqueIsSelected, String nom, String prenom, String ville, String adresse, String role_partie, boolean isDebiteur,
			int dossier_id) {
		super();
		this.id = id;
		this.physiqueIsSelected = physiqueIsSelected;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.adresse = adresse;
		this.role_partie = role_partie;
		this.isDebiteur = isDebiteur;
		this.dossier_id = dossier_id;
		if (physiqueIsSelected) {
			this.type="physique";
		}else{
			this.type="moral";
		}
	}
	
	//Add Partie to Database
	public boolean addPartie(List<Object> params){
		String AddPartieQuery ="INSERT INTO parties(type,nom,prenom,ville,adresse,role_partie,debiteur,dossier_id) VALUES (?,?,?,?,?,?,?,?)";
		try {
			dbc.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dbc.query(AddPartieQuery, params, 'u') == null){
			dbc.close();
			return true;
		}else{
			dbc.close();
			return false;
		}
	}
	
	//REMOVE Partie with Condition
	public boolean removePartie(String row, int value){
		List<Object> params = new LinkedList<Object>();
		params.add(value);
		String removePartieQuery ="DELETE FROM parties WHERE "+row+" = ?";
		try {
			dbc.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dbc.query(removePartieQuery, params, 'u') == null){
			dbc.close();
			return true;
		}else{
			dbc.close();
			return false;
		}
	}
	//UPDATE Partie Information
	public boolean updatePartie(Map<String, String> params, int user_id, int dossier_id){
		String updateQuery = "UPDATE parties SET ";

		for (Map.Entry<String, String> entry : params.entrySet()){
			updateQuery+=entry.getKey()+"='"+entry.getValue()+"',";
    	}
		updateQuery = updateQuery.substring(0, updateQuery.length()-1);
		updateQuery+=" WHERE id="+user_id;
		
		try {
			dbc.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//IF Current User is "Debiteur" Then Change the other to NON Debiteur
		int OtherIsDebiteur = 0;
		if(params.get("debiteur").equals("0")){
			OtherIsDebiteur = 1;
		}
		if(dbc.query(updateQuery, null, 'u') == null){
			SetOtherAsDebiteur(dossier_id,OtherIsDebiteur, dbc, user_id);
			dbc.close();
			return true;
		}else{
			dbc.close();
			return false;
		}
	
	}
	
	//UPDATE Debiteur Or NON Debiteur
	public void SetOtherAsDebiteur(int dossier_id,int OtherIsDebiteur, DBConnect dbc, int updated_user_id){
		String Query = "UPDATE parties SET debiteur = "+OtherIsDebiteur+" WHERE id !="+updated_user_id+" AND dossier_id="+dossier_id;
		dbc.query(Query, null, 'u');
	}
	
	//GET List of Parties By Dossier ID
	public List<Partie> getPartiesByDossId(int dosid){
		List<Partie> lstParties = new LinkedList<Partie>();
		String getParties = "SELECT*FROM parties WHERE dossier_id="+dosid;
		try {
			dbc.connect();
		
		ResultSet partiesRS = dbc.query(getParties, null, 'g');
			while (partiesRS.next()) {
				lstParties.add(new Partie(
							partiesRS.getInt("id"),
							partiesRS.getString("type"),
							partiesRS.getString("nom"),
							partiesRS.getString("prenom"),
							partiesRS.getString("ville"),
							partiesRS.getString("adresse"),
							partiesRS.getString("role_partie"),
							partiesRS.getInt("debiteur"),
							partiesRS.getInt("dossier_id")
						));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstParties;
	}
	
	// SETTERS and GETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getRole_partie() {
		return role_partie;
	}
	public void setRole_partie(String role_partie) {
		this.role_partie = role_partie;
	}
	public int getDebiteur() {
		return debiteur;
	}
	public void setDebiteur(int debiteur) {
		this.debiteur = debiteur;
	}
	public int getDossierId() {
		return dossier_id;
	}
	public void setDossierId(int dossier_id) {
		this.dossier_id = dossier_id;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public int getDossier_id() {
		return dossier_id;
	}
	public void setDossier_id(int dossier_id) {
		this.dossier_id = dossier_id;
	}
	
	
	public boolean isPhysiqueIsSelected() {
		return physiqueIsSelected;
	}
	public void setPhysiqueIsSelected(boolean physiqueIsSelected) {
		this.physiqueIsSelected = physiqueIsSelected;
	}
	public boolean isDebiteur() {
		return isDebiteur;
	}
	public void setDebiteur(boolean isDebiteur) {
		this.isDebiteur = isDebiteur;
	}
	
	@Override
	public String toString() {
		return "Partie [id=" + id + ", type=" + type + ", nom=" + nom + ", prenom=" + prenom + ", ville=" + ville
				+ ", adresse=" + adresse + ", role_partie=" + role_partie + ", debiteur=" + debiteur + ", dossier_id="
				+ dossier_id + "]";
	}
	
}
