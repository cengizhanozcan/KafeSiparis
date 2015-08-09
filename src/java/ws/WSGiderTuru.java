/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.GiderTuruHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.GiderTuru;
import model.Personel;

/**
 *
 * @author cengizhan
 */
public class WSGiderTuru implements Serializable{
    
    private GiderTuruHelper giderTuruHelper;
    private Personel personelLog;
    
    public WSGiderTuru(){
    
        giderTuruHelper = new GiderTuruHelper();
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }
    
    public void insert(GiderTuru giderTuru){
    
        try {
            giderTuruHelper.insert(giderTuru);
            LogInsert.insert(6, personelLog, 2, giderTuru.getGiderTuruId());
            
        } catch (Exception e) {
            
            LogInsert.error(6, personelLog, "WSGiderTuru -> insert() ->  " +e.toString());
        }
    }
    
    
    public List<GiderTuru> list(){
    
        List <GiderTuru> list = new ArrayList<>();
        
        try {
            
            list = giderTuruHelper.list();
            
            LogInsert.insert(6, personelLog, 6, 0);
            
        } catch (Exception e) {
            LogInsert.error(6, personelLog, "WSGiderTuru -> list() ->  " +e.toString());
        }
        
        return list;
    }
    
}
