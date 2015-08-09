/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.PersonelHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Personel;

/**
 *
 * @author cengizhan
 */
@Path("/WSPersonel")
public class WSPersonel implements Serializable{

    PersonelHelper personelHelper;
    Personel personelLog;

    public WSPersonel() {
        personelHelper = new PersonelHelper();
        
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }

    @Path("/Insert")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void insert(Personel personel) {

        try {
            personelHelper.insert(personel);
            
            LogInsert.insert(12, personelLog, 1, personelLog.getPersonelId());

        } catch (Exception e) {
            LogInsert.error(12, personelLog, "WSPersonel -> insert() -> "+ e.toString());
            
        }
    }
    
    @Path("/Delete")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public void delete(Personel personel){
    
        try { 
            personelHelper.delete(personel);
            LogInsert.insert(12, personelLog, 4, personelLog.getPersonelId());
        } catch (Exception e) {
            LogInsert.error(12, personelLog, "WSPersonel -> delete() -> "+ e.toString());
        }
    }
    
    @Path("/Update")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public void update(Personel personel){
    
        try {
            personelHelper.update(personel);
            
            LogInsert.insert(12, personelLog, 3, personelLog.getPersonelId());
        } catch (Exception e) {
            LogInsert.error(12, personelLog, "WSPersonel -> update() -> "+ e.toString());
        }
    }
    
    public List<Personel> listPersonel(){
        
        List<Personel> list = new ArrayList<>();
        
        try {
            list = personelHelper.listPersonel();
            LogInsert.insert(12, personelLog, 6, 0);
        
        } catch (Exception e) {
            LogInsert.error(12, personelLog, "WSPersonel -> list() -> "+ e.toString());
        
        }
        return list;
    }
}
