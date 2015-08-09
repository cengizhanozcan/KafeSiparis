/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.GiderHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Gider;
import model.Personel;

/**
 *
 * @author cengizhan
 */
public class WSGider implements Serializable{
    
    private GiderHelper giderHelper;
    private Personel personelLog;
    
    public WSGider(){
        
        giderHelper = new GiderHelper();
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }
    
    public void insert(Gider gider){
    
        try {
            giderHelper.insert(gider);
            
            LogInsert.insert(5, personelLog, 2, gider.getGiderId());
        } catch (Exception e) {
            LogInsert.error(5, personelLog, "WSGider -> insert() ->  "+ e.toString());
        }
    }
    
    public List<Gider> listStok(Date startDate, Date endDate){
    
        List<Gider> list = new ArrayList<>();
        try {
            
            list = giderHelper.listStok(startDate, endDate);
            
            LogInsert.insert(5, personelLog, 6, 0);
        } catch (Exception e) {
            LogInsert.error(5, personelLog, "WSGider -> listStok() ->  "+ e.toString());
        }
        
        return list;
    }
    
}
