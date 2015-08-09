/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.PersonelHelper;
import helper.YetkiHelper;
import java.io.IOException;
import java.io.Serializable;  
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Personel;
import model.Yetki; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author cengizhan
 */
@Path("/WSLogin")
public class WSLogin implements Serializable {

    PersonelHelper personelHelper;
    YetkiHelper yetkiHelper; 

    public WSLogin() {

        personelHelper = new PersonelHelper(); 
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Personel login(String kullanciAdi, String sifre) {

        Personel loginPersonel = new Personel();
        Personel personel = new Personel();
        personel.setKullaniciAd(kullanciAdi);
        personel.setSifre(sifre);

        try {
            loginPersonel = personelHelper.login(personel);
            LogInsert.insert(12, loginPersonel, 1, loginPersonel.getPersonelId());
            
        } catch (Exception e) {
            
            LogInsert.error(12, loginPersonel, "WSLogin -> login() -> " + e.toString());
        }
        return loginPersonel;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{user}/{password}")
    public String loginPhone(@PathParam("user") String user, @PathParam("password") String password){
    
        Personel personel= new Personel();
        personel.setKullaniciAd(user);
        personel.setSifre(password);
        Personel loginPersonel = new Personel();
        
        JSONObject obj =new JSONObject();
        StringWriter out = new StringWriter();
        
        try {
            loginPersonel = personelHelper.login(personel);
            if (loginPersonel==null) {
                return "personel yok";
            }
            
            obj.put("userName", loginPersonel.getPersonelAd());
            obj.put("userSurname", loginPersonel.getPersonelSoyad());
            obj.put("userId", loginPersonel.getPersonelId());
            obj.writeJSONString(out);
            
            //LogInsert.insert(12, loginPersonel, 1, loginPersonel.getPersonelId());
            return out.toString();
            
        } catch (Exception e) {
            
            //LogInsert.error(12, loginPersonel, "WSLogin -> loginPhone() -> " + e.toString());
        }
        return null;
    }
}
