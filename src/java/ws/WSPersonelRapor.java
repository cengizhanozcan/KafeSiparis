/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.PersonelHelper;
import helper.SiparisHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Personel;
import model.Siparis;

/**
 *
 * @author cengizhan
 */
public class WSPersonelRapor implements Serializable{
    
    PersonelHelper personelHelper;
    SiparisHelper siparisHelper;
    Personel personelLog;
    
    public WSPersonelRapor(){
    
        personelHelper = new PersonelHelper();
        siparisHelper = new SiparisHelper();
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }
    
    public List<Personel> listPersonel(){
        
        List<Personel> listPersonel = new ArrayList<>();
        
        try {
            listPersonel = personelHelper.listPersonel();
            LogInsert.insert(12, personelLog, 6, 0);
            
        } catch (Exception e) {
            LogInsert.error(12, personelLog, "WSPersonelRapor -> listPersonel() -> "+e.toString());
        }
        
        return listPersonel;
    }
    
    public List<Siparis> listPersonelRapor(Personel personel, Date startDate, Date finishDate){
    
        List<Siparis> listRapor = new ArrayList<>();
        
        try {
            listRapor = siparisHelper.listPersonelRapor(personel, startDate, finishDate);
            
            LogInsert.insert(12, personelLog, 6, 0);
            
        } catch (Exception e) {
            LogInsert.error(12, personelLog, "WSPersonelRapor -> listPersonelRapor() -> "+e.toString());
        }
        
        return listRapor;
    }
    
}
