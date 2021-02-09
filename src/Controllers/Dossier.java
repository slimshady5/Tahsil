package Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Helpers.TahsilUtils;

public class Dossier {
	private Models.Dossier dossierModel = new Models.Dossier();
	private Models.Partie partie = new Models.Partie();
	
	
	public int getLastId(){
		int numDoss=0;
		try {
			ResultSet rs= dossierModel.getLast();
			if(rs.next()){
				numDoss = rs.getInt(2);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception"+e.getMessage());
		}
		
		return numDoss;
	}
	
	public Models.Dossier getDossier(String numDoss){
		Models.Dossier md= new Models.Dossier();  
		List<Object> params = new LinkedList<Object>();
		String [] numdoss = numDoss.split("/");
		params.add(Integer.parseInt(numdoss[1]));
		params.add(Integer.parseInt(numdoss[0]));
		return md.getDossier(params);
		 
	}
	//ADD Dossier
	public boolean addDossier(List<List<Object>> params, JDialog dialog){
		TahsilUtils util = new TahsilUtils();
		boolean stat = false;
		try{
			List <Object> dossValues = params.get(0);
			List <Object> Partie1Values = params.get(1);
			List <Object> Partie2Values = params.get(2);
			
			if (dossierModel.exists(dossValues.get(0).toString(), dossValues.get(1).toString())) {
				JOptionPane.showMessageDialog(dialog, "هذا الملف يوجد مسبقاً في قاعدة البيانات","",JOptionPane.ERROR_MESSAGE);
			}else{
				//Numero Dossier (0)
				dossValues.set(0,util.VarParseInt(dossValues.get(0).toString()));
				//Annee ouverture dossier (1)
				dossValues.set(1,util.VarParseInt(dossValues.get(1).toString()));
				//Numero verdict (4)
				dossValues.set(4,util.VarParseDouble(dossValues.get(4).toString()));
				//Montant Verdict(6)
				dossValues.set(6,util.VarParseDouble(dossValues.get(6).toString()));
				//Montant Indemnisation (7)
				double mntVerdict = (Double) dossValues.get(6);
				double mntIndem = 0;
				
				//Si le montant du verdict entre 1000 et 5000, minimum 50 
				if (mntVerdict >= 1000 && mntVerdict <= 5000) {
					mntIndem = (mntVerdict*4)/100;
					if(mntIndem < 50){
						dossValues.set(7, 50);
					}else{
						mntIndem = Math.ceil(mntIndem);
						dossValues.set(7, mntIndem);
					}
					//Si le montant du verdict entre 5000 et 20000, minimum 200
				}else if(mntVerdict > 5000 && mntVerdict <= 20000){
					
					mntIndem = (mntVerdict*2.5)/100;
					if(mntIndem < 200){
						dossValues.set(7, 200);
					}else{
						mntIndem = Math.ceil(mntIndem);
						dossValues.set(7, mntIndem);
					}
					
				}else if(mntVerdict > 20000){
					mntIndem = (mntVerdict/100)+300;
					mntIndem = Math.ceil(mntIndem);
					dossValues.set(7, mntIndem);
				}
				//Autre (9)
				dossValues.set(9,util.VarParseDouble(dossValues.get(9).toString()));
				
				//Total (10)
				double total = util.VarParseDouble(dossValues.get(7).toString())
						+util.VarParseDouble(dossValues.get(8).toString())
						+util.VarParseDouble(dossValues.get(9).toString());
				dossValues.set(10, total);
				
				
				try{
					if (dossierModel.addDossier(dossValues, dialog)) {
						List<Object> lst = new LinkedList<Object>();
						lst.add(dossValues.get(0));
						lst.add(dossValues.get(1));
						Models.Dossier dosForPartie = dossierModel.getDossier(lst);
						Partie1Values.add(dosForPartie.getId());
						Partie2Values.add(dosForPartie.getId());
						if (!partie.addPartie(Partie1Values) || !partie.addPartie(Partie2Values)) {
							JOptionPane.showMessageDialog(dialog, "حدث خطأ أثناء اضافة أحد الطرفين، المرجو حذف و اعادة ادخال الملف","خطأ أثناء اضافة أحد الطرفين",JOptionPane.ERROR_MESSAGE);
							stat=false;
						}else{
							JOptionPane.showMessageDialog(dialog, "تمت اضافة الملف بنجاح","تمت اضافة الملف بنجاح",JOptionPane.INFORMATION_MESSAGE);
							stat=true;
						}
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(dialog, "حدث خطأ أثناء اضافة الملف، المرجو اعادة ادخال الملف", " خطأ أثناء اضافة الملف", JOptionPane.ERROR_MESSAGE);
					stat=false;
				}
				
			}
	 }catch(NumberFormatException e){
		JOptionPane.showMessageDialog(dialog, "المرجو التحقق من نوعية المعطيات المدخلة","خطأ في نوعية المعطيات المدخلة",JOptionPane.ERROR_MESSAGE);
	 }
		return stat;
	}
	
	public List<Models.Dossier> getDossiers(List<List<String>> conditions, int limit, boolean isDebiteur){
		return dossierModel.getDossiers(conditions, limit, isDebiteur);
	}
	
	public boolean deleteDossier(int id){
		if (dossierModel.deleteDossier(id)) {
			return true;
		}else{
			return false;
		}

		
	}
	//UPDATE Dossier
	public boolean update(Models.Dossier dos, JDialog dialog, String dossierOrig, int dossier_id){
		List<Object> params = new LinkedList<Object>();
		String s = Integer.toString(dos.getYear())+"/"+Integer.toString(dos.getNumDossier());
		boolean stat = false;
		
		if (dossierModel.exists(Integer.toString(dos.getNumDossier()), Integer.toString(dos.getYear()))) {
			
			if (dossierOrig.equals(s)) {

				//Numero du Dossier (0)
				params.add(dos.getNumDossier());
				//Annee ouverture dossier (1)
				params.add(dos.getYear());
				//Date ouverture Dossier (2)
				params.add(dos.getDateDossier());
				//Dossier Original (3)
				params.add(dos.getNumDossierOrig());
				//Numero verdict (4)
				params.add(dos.getNumVerdict());
				//Date Verdict (5)
				params.add(dos.getDateVerdict());
				//Montant Verdict(6)
				params.add(dos.getMontantVerdict());
				//Montant Indemnisation (7)
				double mntVerdict = dos.getMontantVerdict();
				double mntIndem = 0;
				
				//Si le montant du verdict entre 1000 et 5000, minimum 50 
				if (mntVerdict >= 1000 && mntVerdict <= 5000) {
					mntIndem = (mntVerdict*4)/100;
					if(mntIndem < 50){
						params.add(50);
					}else{
						mntIndem = Math.ceil(mntIndem);
						params.add(mntIndem);
					}
					//Si le montant du verdict entre 5000 et 20000, minimum 200
				}else if(mntVerdict > 5000 && mntVerdict <= 20000){
					
					mntIndem = (mntVerdict*2.5)/100;
					if(mntIndem < 200){
						params.add(200);
					}else{
						mntIndem = Math.ceil(mntIndem);
						params.add(mntIndem);
					}
					
				}else if(mntVerdict > 20000){
					mntIndem = (mntVerdict/100)+300;
					mntIndem = Math.ceil(mntIndem);
					params.add(7, mntIndem);
				}
				
				//Droit Plaidoirie (8)
				if(dos.isDroitPlaidoirie()){
					//ttl = 10.00;
					params.add(10.00);
				}else{
					//ttl = 0.00;
					params.add(0.00);
				}
				//Autre (9)
				params.add(dos.getAutreCharge());

				//Total (10)
				double total = mntIndem + Double.parseDouble(params.get(8).toString())+ Double.parseDouble(params.get(9).toString());
				params.add(total);
				
				// (11)
				params.add(dos.getNumTerrain());
				// (12)
				params.add(dos.getNumMarsoum());
				// (13)
				params.add(dos.getNumJarida());
				// (14)
				params.add(dos.getDateJarida());
				// (15)
				params.add(dos.getDateIsdar());
				//Id
				params.add(dossier_id);
				

				if (dossierModel.updateDossier(params)) {
					JOptionPane.showMessageDialog(dialog, "ثم تغير معلومات الملف بنجاح","نجاح",JOptionPane.INFORMATION_MESSAGE);
					stat = true;
				}else{
					JOptionPane.showMessageDialog(dialog, "حدث خطأ أثناء عملية التغيير، المرجو إعادة المحاولة","خطأ",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(dialog, "هذا الملف يوجد مسبقاً في قاعدة البيانات","",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			//params.
			params.add(0,dos.getNumDossier());
			//Annee ouverture dossier (1)
			params.add(1,dos.getYear());
			//Date ouverture Dossier
			params.add(2, dos.getDateDossier());
			//Dossier Original
			params.add(3, dos.getNumDossierOrig());
			//Numero verdict (4)
			params.add(4,dos.getNumVerdict());
			//Date Verdict
			params.add(5, dos.getDateVerdict());
			//Montant Verdict(6)
			params.add(6,dos.getMontantVerdict());
			//Montant Indemnisation (7)
			double mntVerdict = dos.getMontantVerdict();
			double mntIndem = 0;
			
			//Si le montant du verdict entre 1000 et 5000, minimum 50 
			if (mntVerdict >= 1000 && mntVerdict <= 5000) {
				mntIndem = (mntVerdict*4)/100;
				if(mntIndem < 50){
					params.set(7, 50);
				}else{
					mntIndem = Math.ceil(mntIndem);
					params.set(7, mntIndem);
				}
				//Si le montant du verdict entre 5000 et 20000, minimum 200
			}else if(mntVerdict > 5000 && mntVerdict <= 20000){
				
				mntIndem = (mntVerdict*2.5)/100;
				if(mntIndem < 200){
					params.add(7, 200);
				}else{
					mntIndem = Math.ceil(mntIndem);
					params.add(7, mntIndem);
				}
				
			}else if(mntVerdict > 20000){
				mntIndem = (mntVerdict/100)+300;
				mntIndem = Math.ceil(mntIndem);
				params.add(7, mntIndem);
			}
			
			//Droit Plaidoirie
			if(dos.isDroitPlaidoirie()){
				//ttl = 10.00;
				params.add(8, 10.00);
			}else{
				//ttl = 0.00;
				params.add(8, 0.00);
			}
			//Autre (9)
			//ttl += dos.getAutreCharge();
			params.add(9,dos.getAutreCharge());
			
			//Total (10)
			double total = mntIndem + Double.parseDouble(params.get(8).toString())+ Double.parseDouble(params.get(9).toString());
			params.add(10,total);
			
			// (11)
			params.add(dos.getNumTerrain());
			// (12)
			params.add(dos.getNumMarsoum());
			// (13)
			params.add(dos.getNumJarida());
			// (14)
			params.add(dos.getDateJarida());
			// (15)
			params.add(dos.getDateIsdar());
			
			//Id
			params.add(11,dossier_id);
			if (dossierModel.updateDossier(params)) {
				JOptionPane.showMessageDialog(dialog, "ثم تغير معلومات الملف بنجاح","نجاح",JOptionPane.INFORMATION_MESSAGE);
				stat = true;
			}else{
				JOptionPane.showMessageDialog(dialog, "حدث خطأ أثناء عملية التغيير، المرجو إعادة المحاولة","خطأ",JOptionPane.ERROR_MESSAGE);
			}
		}
		return stat;
	}
	
	public void statisticsByNum(int fromNum, int toNum, int fromYear, int toYear, JDialog dialog){
		//System.out.println("Download Controller");
		if(toYear < fromYear){
			JOptionPane.showMessageDialog(dialog, "سنة النهاية يجب أن تكون أكبر أو تساوي سنة البداية","خطأ",JOptionPane.ERROR_MESSAGE);
		}else if(fromNum == 0 || toNum == 0){
			JOptionPane.showMessageDialog(dialog, "المرجو ملأ الخانات بأعداد صحيحة","خطأ",JOptionPane.ERROR_MESSAGE);
		}else{
			List<String[]> statistics = dossierModel.statisticsByNum(fromNum, toNum, fromYear, toYear);
			TahsilUtils.toCSV(statistics, dialog);
		}
	}
}