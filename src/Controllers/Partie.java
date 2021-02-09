/**
 * 
 */
package Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yassine El Khanboubi
 *
 */
public class Partie {
	//ADD Partie to Database
	public boolean addPartie(List<Object> params){
		Models.Partie P = new Models.Partie();
		boolean stat = true;
		if (params.isEmpty()) {
			stat = false;
		}else{
			for (int i = 0; i < params.size(); i++) {
				if (params.get(i) == "" || params.get(i) == null) {
					stat = false;
					break;
				}
			}
		}
		
		if (stat) {
			if (P.addPartie(params)) {
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//UPDATE Partie
	public boolean updatePartie(Models.Partie P){
		Map<String, String> params =new HashMap<String, String>();
		params.put("type", P.getType());
		params.put("nom", P.getNom());
		params.put("prenom", P.getPrenom());
		params.put("ville", P.getVille());
		params.put("adresse", P.getAdresse());
		params.put("role_partie", P.getRole_partie());
		//IF Partie is Debiteur, SET value to 1 else to 0
		if (P.isDebiteur()) {
			params.put("debiteur", "1");
		}else{
			params.put("debiteur", "0");
		}
		
		if(P.updatePartie(params, P.getId(),P.getDossier_id())){
			return true;
		}else{
			return false;
		}
		
	}
}


 
	 
		 
	 
		 
	 

