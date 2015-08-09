/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.MasaHelper;
import helper.SiparisHelper;
import java.io.Serializable;
import javax.ws.rs.Path;
import model.Masa;
import model.Personel;
import model.Siparis;

/**
 *
 * @author cengizhan
 */
@Path("/WSSatis")
public class WSSatis implements Serializable {

    SiparisHelper siparisHelper;
    MasaHelper masaHelper;
    Personel personelLog;

    public WSSatis() {

        siparisHelper = new SiparisHelper();
        masaHelper = new MasaHelper();
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }

    public void siparisOdeme(Siparis siparis) {

        try {
            
            siparisHelper.update(siparis);
            LogInsert.insert(13, personelLog, 2, siparis.getSiparisId());
        } catch (Exception e) {
            LogInsert.error(13, personelLog, "WSSatis -> siparisOdeme() -> "+e.toString());
        }
    }
    
    public void masaOdeme(Masa masa){
    
        try {
            masaHelper.update(masa);
            
            LogInsert.insert(10, personelLog, 2, masa.getMasaId());
        } catch (Exception e) {
            
            LogInsert.error(10, personelLog, "WSSatis -> masaOdeme() -> "+e.toString());
        }
    }
}
