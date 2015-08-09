/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.MasaNoHelper;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.MasaNo;
import model.Personel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author cengizhan
 */
@Path("/WSMasaNo")
public class WSMasaNo implements Serializable{

    MasaNoHelper masaNoHelper;
    Personel personelLog;

    public WSMasaNo() {

        masaNoHelper = new MasaNoHelper();
         personelLog = new Personel();
        personelLog.setPersonelId(1);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMasaNo")
    public JSONArray listeleMasaNo() {

        List<MasaNo> list = new ArrayList<>();

        JSONArray jSonArray = new JSONArray();  

        try {
            list = masaNoHelper.list();
            
            //LogInsert.insert(11, personelLog, 6, 0);

        } catch (Exception e) {
            //LogInsert.error(11, personelLog, "WSMasaNo -> listeleMasaNo() -> " + e.toString());
            return null;
        }

        for (MasaNo masaNo : list) {

            Map map = new HashMap();
            map.put("MasaNoId", masaNo.getMasaNoId());
            map.put("MasaNo", masaNo.getNumara());

            jSonArray.add(map);
        }

        return jSonArray;
    }

    public List<MasaNo> list() {

        List<MasaNo> list = new ArrayList<>();
        
        try {

            list = masaNoHelper.list();
            LogInsert.insert(11, personelLog, 6, 0);
        } catch (Exception e) {
            LogInsert.error(11, personelLog, "WSMasaNo -> list() -> " + e.toString());
        }
        
        return list;
    }

    public void insert(MasaNo masaNo){
    
        try {
            masaNoHelper.insert(masaNo);
            
            LogInsert.insert(11, personelLog, 2, masaNo.getMasaNoId());
        } catch (Exception e) {
            LogInsert.error(11, personelLog, "WSMasaNo -> insert() -> " + e.toString());
        }
        
    }
    
    public void update(MasaNo masaNo){
    
        try {
            
            masaNoHelper.update(masaNo);
            LogInsert.insert(11, personelLog, 3, masaNo.getMasaNoId());
        } catch (Exception e) {
            LogInsert.error(11, personelLog, "WSMasaNo -> update() -> " + e.toString());
        }
    }
    
}
