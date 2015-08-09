/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.SiparisHelper;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import model.Personel;
import model.Siparis;

/**
 *
 * @author cengizhan
 */
public class WSSatilanUrunRapor implements Serializable{
    
    SiparisHelper siparisHelper;
    Personel personelLog;
    
    public WSSatilanUrunRapor(){
        
        siparisHelper = new SiparisHelper();
        
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }
    
    public List<Siparis> listReport(Date pStartDate, Date pFinishDate){         //Satilan ürünlerin raporları getiriliyor.
    
        try {
            
            return siparisHelper.listReport(pStartDate, pFinishDate);
            
        } catch (Exception e) {
            LogInsert.error(13, personelLog, "WSSatilanUrunRapor -> listReport() -> "+e.toString());
        }
        
        return null;
    }
    
}
