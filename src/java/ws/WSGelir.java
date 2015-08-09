/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.GelirHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Path;
import model.Gelir;
import model.Personel;
import model.Siparis;

/**
 *
 * @author cengizhan
 */
@Path("/Gelir")
public class WSGelir implements Serializable{

    GelirHelper gelirHelper;
    Personel personelLog;
    
    public WSGelir(){
        
        gelirHelper = new GelirHelper();
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    } 
    
    public void insertGelir(Gelir gelir){
    
        try {
            
            gelirHelper.insert(gelir);
            
            LogInsert.insert(4, personelLog, 2, gelir.getGelirId());
        } catch (Exception e) {
            
            LogInsert.error(4, personelLog, "WSGelir -> insertGelir() ->  " + e.toString());
        }
    }
    
    public List<Siparis> listGelirRapor(Date startDate, Date endDate){
    
        List<Siparis> list = new ArrayList<>();
        try {
            
            list = gelirHelper.listGelirRapor(startDate, endDate);
            
            LogInsert.insert(4, personelLog, 6, 0);
            
        } catch (Exception e) {
            
            LogInsert.error(4, personelLog, "WSGelir -> listGelirRapor() ->  " + e.toString());
        }
        
        return list;
    }
}
