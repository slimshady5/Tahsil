package Models;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import Helpers.DBConnect;

public class Dossier {
	DBConnect dbc = new DBConnect();
	private int id;
	private int numDossier;
	private int year;
	private String numDossierOrig;
	private Timestamp dateDossier;
	private int numVerdict;
	private Date dateVerdict;
	private double montantVerdict;
	private double montantIndemnisation;
	private double droitPlaidoirie;
	private double autreCharge;
	private double total;
	private boolean isDroitPlaidoirie;
	private String num_terrain;
	private String num_marsoum;
	private String num_jarida;
	private Date date_jarida;
	private Date date_isdar;
	private Partie partie;
	private List<Partie> parties = new LinkedList<Partie>();
	private List<String[]> statistics = new ArrayList<String[]>();

	public Dossier(){
		super();
	}
	
	public Dossier(int numDossier, int year, String numDossierOrig, Timestamp dateDossier, int numVerdict,
			Date dateVerdict, double montantVerdict, double montantIndemnisation, boolean isDroitPlaidoirie,
			double autreCharge, double total, String num_terrain, String num_marsoum, String num_jarida, Date date_jarida, Date date_isdar) {
		super();
		this.numDossier = numDossier;
		this.year = year;
		this.numDossierOrig = numDossierOrig;
		this.dateDossier = dateDossier;
		this.numVerdict = numVerdict;
		this.dateVerdict = dateVerdict;
		this.montantVerdict = montantVerdict;
		this.montantIndemnisation = montantIndemnisation;
		this.isDroitPlaidoirie = isDroitPlaidoirie;
		this.autreCharge = autreCharge;
		this.total = total;
		this.num_terrain = num_terrain;
		this.num_marsoum = num_marsoum;
		this.num_jarida = num_jarida;
		this.date_jarida = date_jarida;
		this.date_isdar = date_isdar;
	}

	public int getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(int numDossier) {
		this.numDossier = numDossier;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Timestamp getDateDossier() {
		return dateDossier;
	}

	public void setDateDossier(Timestamp dateDossier) {
		this.dateDossier = dateDossier;
	}
	public String getNumDossierOrig() {
		return numDossierOrig;
	}

	public void setNumDossierOrig(String numDossierOrig) {
		this.numDossierOrig = numDossierOrig;
	}
	public int getNumVerdict() {
		return numVerdict;
	}

	public void setNumVerdict(int numVerdict) {
		this.numVerdict = numVerdict;
	}

	public Date getDateVerdict() {
		return dateVerdict;
	}

	public void setDateVerdict(Date dateVerdict) {
		this.dateVerdict = dateVerdict;
	}

	public double getMontantIndemnisation() {
		return montantIndemnisation;
	}

	public void setMontantIndemnisation(float montantIndemnisation) {
		this.montantIndemnisation = montantIndemnisation;
	}

	public double getDroitPlaidoirie() {
		return droitPlaidoirie;
	}

	public void setDroitPlaidoirie(float droitPlaidoirie) {
		this.droitPlaidoirie = droitPlaidoirie;
	}

	public double getAutreCharge() {
		return autreCharge;
	}

	public void setAutreCharge(float autreCharge) {
		this.autreCharge = autreCharge;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}


	public double getMontantVerdict() {
		return montantVerdict;
	}

	public void setMontantVerdict(float montantVerdict) {
		this.montantVerdict = montantVerdict;
	}
	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public List<Partie> getParties() {
		return parties;
	}

	public void setParties(List<Partie> parties) {
		this.parties = parties;
	}

	public boolean isDroitPlaidoirie() {
		return isDroitPlaidoirie;
	}

	public void setDroitPlaidoirie(boolean isDroitPlaidoirie) {
		this.isDroitPlaidoirie = isDroitPlaidoirie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNumTerrain() {
		return num_terrain;
	}

	public void setNumTerrain(String num_terrain) {
		this.num_terrain = num_terrain;
	}

	public String getNumMarsoum() {
		return num_marsoum;
	}

	public void setNumMarsoum(String num_marsoum) {
		this.num_marsoum = num_marsoum;
	}

	public String getNumJarida() {
		return num_jarida;
	}

	public void setNumJarida(String num_jarida) {
		this.num_jarida = num_jarida;
	}

	public Date getDateJarida() {
		return date_jarida;
	}

	public void setDateJarida(Date date_jarida) {
		this.date_jarida = date_jarida;
	}
	
	public Date getDateIsdar() {
		return date_isdar;
	}

	public void setDateIsdar(Date date_isdar) {
		this.date_isdar = date_isdar;
	}
	
	public ResultSet getLast(){
		String query = "SELECT * FROM dossiers ORDER BY id DESC LIMIT 1";
		try {
			dbc.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet rs = dbc.query(query,null,'g');
		return rs;
	}
	
	public boolean addDossier(List<Object> params, JDialog dialog){
		String AddDossQuery ="INSERT INTO dossiers(num_dossier,annee_dossier,date_dossier,dossier_original,num_verdict,date_verdict,montant_verdict,montant_indemnisation,droit_plaidoirie,autre_charge,totale,num_terrain, num_marsoum,date_marsoum, num_jarida, date_jarida, date_isdar) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			dbc.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dbc.query(AddDossQuery, params, 'u') == null){
			dbc.close();
			return true;
		}else{
			partie.removePartie("", Integer.parseInt(params.get(0).toString()));
			removeDossier("id", Integer.parseInt(params.get(0).toString()));
			dbc.close();
			return false;
		}
	}
	
	public boolean removeDossier(String row, int value){
		List<Object> params = new LinkedList<Object>();
		params.add(value);
		String removeDossQuery ="DELETE FROM dossiers WHERE "+row+" = ?";
		try {
			dbc.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dbc.query(removeDossQuery, params, 'u') == null){
			dbc.close();
			return true;
		}else{
			dbc.close();
			return false;
		}
	}
	
	public List<Dossier> getDossiers(List<List<String>> conditions, int limit, boolean isDebiteur){
		List<Object> params = new LinkedList<Object>();
		List<Dossier> lst = new LinkedList<Dossier>();
		String query = "SELECT * FROM dossiers";
		String PartiesQuery = "SELECT * FROM parties WHERE dossier_id=";
		try {
			dbc.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (conditions != null) {
			query += " WHERE ";
			for (int i = 0; i < conditions.size(); i++) {
				if (i>0) {
					query += " AND ";
				}
				query += conditions.get(i).get(0).toString()+" = "+conditions.get(i).get(1).toString();
			}
		}
		
		query += " ORDER BY id DESC";
		
		if (limit > 0 ) {
			query += " Limit "+limit;
		}
		
		ResultSet dossiers = dbc.query(query, params, 'g');
			try {
				while(dossiers.next()){
					Dossier d = new Dossier();
					/////////////////////////////////////////////////// ------> 
					d.setId(dossiers.getInt("id"));
					d.setNumDossier(dossiers.getInt("num_dossier"));
					d.setYear(dossiers.getInt("annee_dossier"));
					d.setDateDossier(dossiers.getTimestamp("date_dossier"));
					d.setNumVerdict(dossiers.getInt("num_verdict"));
					d.setDateVerdict(dossiers.getDate("date_verdict"));
					d.setMontantVerdict(dossiers.getFloat("montant_verdict"));
					d.setMontantIndemnisation(dossiers.getFloat("montant_indemnisation"));
					d.setDroitPlaidoirie(dossiers.getInt("droit_plaidoirie"));
					d.setTotal(dossiers.getFloat("totale"));
					d.setNumDossierOrig(dossiers.getString("dossier_original"));
					d.setAutreCharge(dossiers.getFloat("autre_charge"));
					d.setNumTerrain(dossiers.getString("num_terrain"));
					d.setNumMarsoum(dossiers.getString("num_marsoum"));
					d.setNumJarida(dossiers.getString("num_jarida"));
					d.setDateJarida(dossiers.getDate("date_jarida"));
					d.setDateIsdar(dossiers.getDate("date_isdar"));
					
						String tmpPartiesQuery = PartiesQuery+dossiers.getInt("id");
						if (isDebiteur) {
							tmpPartiesQuery += " AND debiteur=1";
						}
						ResultSet partiesRS = dbc.query(tmpPartiesQuery, null, 'g');
						Partie tmpPartie = new Partie();
						List<Partie> tmpParties = new LinkedList<Partie>();
						while (partiesRS.next()) {
							tmpPartie.setId(partiesRS.getInt("id"));
							tmpPartie.setType(partiesRS.getString("type"));
							tmpPartie.setNom(partiesRS.getString("nom"));
							tmpPartie.setPrenom(partiesRS.getString("prenom"));
							tmpPartie.setVille(partiesRS.getString("ville"));
							tmpPartie.setAdresse(partiesRS.getString("adresse"));
							tmpPartie.setRole_partie(partiesRS.getString("role_partie"));
							tmpPartie.setDebiteur(partiesRS.getInt("debiteur"));
							tmpPartie.setDossierId(partiesRS.getInt("dossier_id"));
							tmpParties.add(new Partie(
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
							partiesRS.close();
							d.setParties(tmpParties);
					lst.add(d);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				dossiers.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbc.close();
		return lst;
	}
	
	public Dossier getDossier(List<Object> params){
		
		Dossier d = new Dossier();
		String req = "SELECT * FROM dossiers WHERE num_dossier=? AND annee_dossier=?";
		try {
			dbc.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet dossierRS = dbc.query(req, params, 'g');
		
		try {
			while (dossierRS.next()){
				////////////////////////////////////// ----------->
				d.setId(dossierRS.getInt("id"));
				d.setNumDossier(dossierRS.getInt("num_dossier"));
				d.setYear(dossierRS.getInt("annee_dossier"));
				d.setNumDossierOrig(dossierRS.getString("dossier_original"));
				d.setDateDossier(dossierRS.getTimestamp("date_dossier"));
				d.setNumVerdict(dossierRS.getInt("num_verdict"));
				d.setDateVerdict(dossierRS.getDate("date_verdict"));
				d.setMontantVerdict(dossierRS.getFloat("montant_verdict"));
				d.setMontantIndemnisation(dossierRS.getFloat("montant_indemnisation"));
				d.setDroitPlaidoirie(dossierRS.getFloat("droit_plaidoirie"));
				d.setAutreCharge(dossierRS.getFloat("autre_charge"));
				d.setTotal(dossierRS.getFloat("totale"));
				d.setNumTerrain(dossierRS.getString("num_terrain"));
				d.setNumMarsoum(dossierRS.getString("num_marsoum"));
				d.setNumJarida(dossierRS.getString("num_jarida"));
				d.setDateJarida(dossierRS.getDate("date_jarida"));
				d.setDateIsdar(dossierRS.getDate("date_isdar"));
			}
			dossierRS.close();

			String reqParties = "SELECT*FROM parties WHERE dossier_id = "+d.getId();
			ResultSet partiesRS = dbc.query(reqParties, null, 'g');
			List<Partie> lstParties = new LinkedList<Partie>();
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
			d.setParties(lstParties);
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public boolean deleteDossier(int id){
		String query = "DELETE FROM dossiers WHERE id="+id;
		try {
			dbc.connect();
			dbc.query(query, null, 'u');
			dbc.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	public boolean updateDossier(List<Object> params){
		boolean stat = true;
		String query = "UPDATE dossiers SET "
				+ "num_dossier=?,"
				+ "annee_dossier=?,"
				+ "date_dossier=?,"
				+ "dossier_original=?,"
				+ "num_verdict=?,"
				+ "date_verdict=?,"
				+ "montant_verdict=?,"
				+ "montant_indemnisation=?,"
				+ "droit_plaidoirie=?,"
				+ "autre_charge=?,"
				+ "totale=?,"
				+ "num_terrain=?,"
				+ "num_marsoum=?,"
				+ "num_jarida=?,"
				+ "date_jarida=?,"
				+ "date_isdar=?"
				+ "WHERE id=?";
		try {
			dbc.connect();
			dbc.query(query, params, 'u');
			dbc.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			stat = false;
		}
		return stat;
	}
	
	public boolean exists(String num, String year){
		boolean stat = false;
		String query = "SELECT*FROM dossiers WHERE num_dossier = "+num+" AND annee_dossier = "+year;
		try {
			dbc.connect();
			ResultSet rs = dbc.query(query, null, 'g');
			if (rs.next()) {
				stat = true;
			}
			rs.close();
			dbc.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return stat;
	}

	public List<String[]> statisticsByNum(int fromNum, int toNum, int FromYear, int toYear){

		String[] header = {"العنوان","المدينة","المدعى عليه","تاريخ الاصدار","تاريخ الجريدة الرسمية","رقم الجريدة الرسمية","تاريخ المرسوم","رقم المرسوم","رقم القطعة","المجموع","مصاريف أخرى","حقوق المرافعة","مبلغ التعويض","مبلغ الحكم","تاريخ الحكم","رقم الحكم","ملف الموضوع","ملف التحصيل"};
		statistics.add(header);
			
		if(FromYear == toYear){
			String query = "select*from dossiers left join parties on parties.dossier_id = dossiers.id where parties.role_partie LIKE '%عليه%' AND annee_dossier="+toYear+" AND num_dossier between "+fromNum+" and "+toNum+" order by num_dossier DESC";
			this.addStatisticsToList(query);
		}else{
			for(int i = toYear; i >= FromYear; i--){
				String query = "";
				if(i == FromYear){
					query = "select*from dossiers left join parties on parties.dossier_id = dossiers.id where parties.role_partie LIKE '%عليه%' AND annee_dossier="+i+" AND num_dossier > "+fromNum+" order by num_dossier DESC";
				}else if(i == toYear){
					query = "select*from dossiers left join parties on parties.dossier_id = dossiers.id where parties.role_partie LIKE '%عليه%' AND annee_dossier="+i+" AND num_dossier < "+toNum+" order by num_dossier DESC";
				}else{
					query = "select*from dossiers left join parties on parties.dossier_id = dossiers.id where parties.role_partie LIKE '%عليه%' AND annee_dossier="+i+" order by num_dossier DESC";
				}
				this.addStatisticsToList(query);
			}
		}
		
		return statistics;
	}
	public void addStatisticsToList(String query){
		try{
			dbc.connect();
			ResultSet RS = dbc.query(query, null, 'g');
			while(RS.next()){
				String[] row = {
									RS.getString("adresse"),
									RS.getString("ville"),
									RS.getString("nom"),
									RS.getString("date_isdar"),
									RS.getString("date_jarida"),
									RS.getString("num_jarida"),
									RS.getString("date_marsoum"),
									RS.getString("num_marsoum"),
									RS.getString("num_terrain"),
									Double.toString(RS.getDouble("totale")),
									Double.toString(RS.getDouble("autre_charge")),
									Double.toString(RS.getDouble("droit_plaidoirie")),
									Double.toString(RS.getDouble("montant_indemnisation")),
									Double.toString(RS.getDouble("montant_verdict")),
									RS.getString("date_verdict"),
									Integer.toString(RS.getInt("num_verdict")),
									RS.getString("dossier_original"),
									RS.getInt("annee_dossier")+"/"+RS.getInt("num_dossier"),
							   };
				statistics.add(row);
			}
			RS.close();
			dbc.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Dossier [id=" + id + ", numDossier=" + numDossier + ", year=" + year + ", numDossierOrig="
				+ numDossierOrig + ", dateDossier=" + dateDossier + ", numVerdict=" + numVerdict + ", dateVerdict="
				+ dateVerdict + ", montantVerdict=" + montantVerdict + ", montantIndemnisation=" + montantIndemnisation
				+ ", droitPlaidoirie=" + droitPlaidoirie + ", autreCharge=" + autreCharge + ", total=" + total
				+ ", isDroitPlaidoirie=" + isDroitPlaidoirie + ", num_terrain=" + num_terrain + ", num_marsoum="
				+ num_marsoum + ", num_jarida=" + num_jarida + ", date_jarida=" + date_jarida + ", date_isdar="+date_isdar+", parties=" + parties
				+ "]";
	}
}
