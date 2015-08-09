/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.StokHelper;
import java.io.Serializable;
import java.util.List;
import model.Personel;
import model.Stok;

/**
 *
 * @author cengizhan
 */
public class WSStokRapor implements Serializable{
    
    StokHelper stokHelper;
    Personel personelLog;
    
    public WSStokRapor(){
    
        stokHelper = new StokHelper();
        
        
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }
    
    public List<Stok> getAllStok(){
        
        try {
            return stokHelper.getAllStok();
        
        } catch (Exception e) {
            LogInsert.error(14, personelLog, "WSStokRapor -> getAllStok() "+e.toString());
        }
        
        return null;
    }
    
}
