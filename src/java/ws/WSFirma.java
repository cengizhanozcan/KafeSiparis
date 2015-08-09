/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.FirmaHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import model.Firma;
import model.Personel;

/**
 *
 * @author cengizhan
 */
@Path("/Firma")
public class WSFirma implements Serializable{

    FirmaHelper firmaHelper;
    Personel personelLog; 
    
    public WSFirma() {

        firmaHelper = new FirmaHelper();
        personelLog = new Personel();
        personelLog.setPersonelId(1);
        
    }

    public List<Firma> listeleFirma() {

        List<Firma> list = new ArrayList<>();
        try {
            
            list = firmaHelper.list();
            
            LogInsert.insert(3, personelLog, 6, 0);
        } catch (Exception e) {
            LogInsert.error(3, personelLog, "WSFİrma -> listeleFirma() ->  " +e.toString());
        }
        return list;
    }
    
    public void insert(Firma firma){
    
        try {
            firmaHelper.insert(firma);
            
            LogInsert.insert(3, personelLog, 6, 0);
         } catch (Exception e) {
            LogInsert.error(3, personelLog, "WSFİrma -> insert() ->  " +e.toString());
        }
    }
    
    public void update(Firma firma){
    
        try {
            firmaHelper.update(firma);
            
            LogInsert.insert(3, personelLog, 3, firma.getFirmaId());
        } catch (Exception e) {
            LogInsert.error(3, personelLog, "WSFirma -> update() -> " + e.toString());
        }
    }
    
    public void delete(Firma firma){
    
        try {
            firma.setIsAktif(false);
            firmaHelper.update(firma);
            
            LogInsert.insert(3, personelLog, 4, firma.getFirmaId());
        } catch (Exception e) {
            LogInsert.error(3, personelLog, "WSFirma -> delete() -> " + e.toString());
        }
        
    }
}
