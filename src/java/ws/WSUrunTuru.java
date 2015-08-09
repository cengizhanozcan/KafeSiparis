/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.UrunTuruHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 
import model.Personel;
import model.UrunTur;
import org.json.simple.JSONArray;

/**
 *
 * @author cengizhan
 */
@Path("/WSUrunTuru")
public class WSUrunTuru implements Serializable{

    private UrunTuruHelper urunTuruHelper;
    Personel personelLog;

    public WSUrunTuru() {

        urunTuruHelper = new UrunTuruHelper();
        
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }

    public void insert(UrunTur urunTuru) {

        try {

            urunTuruHelper.insert(urunTuru);
            LogInsert.insert(17, personelLog, 2, urunTuru.getUrunTurId());
        } catch (Exception e) {
            LogInsert.error(17, personelLog, "WSUrunTuru -> insert() -> "+e.toString());
        }
    }

    @Path("/Listele")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray listeleUrunTuru() {

        List<UrunTur> list = new ArrayList<>();
        List<UrunTur> listUstUrunTuru = new ArrayList<>();
        List<UrunTur> listAltUrunTuru = new ArrayList<>();
        List<UrunTur> listTempUrunTuru = new ArrayList<>();
        JSONArray array = new JSONArray();
        try {
            list = urunTuruHelper.listele();
            //LogInsert.insert(17, personelLog, 6, 0);
        } catch (Exception e) {
            //LogInsert.error(17, personelLog, "WSUrunTuru -> listeleUrunTuru() -> "+e.toString());

            return null;
        }

        for (UrunTur urunTuru : list) {
            if (urunTuru.getIsAlt()) {
                listAltUrunTuru.add(urunTuru);
            } else {
                listUstUrunTuru.add(urunTuru);
            }
        }
        
        for(UrunTur urunUstTuru : listUstUrunTuru){
            listTempUrunTuru.add(urunUstTuru);
            for(UrunTur urunAltTuru : listAltUrunTuru){
                if (urunUstTuru.getUrunTurId() == urunAltTuru.getUstTur()) {
                    listTempUrunTuru.add(urunAltTuru);
                }
            }
        }
        
        for (UrunTur urunTuru : listTempUrunTuru) {
            Map map = new LinkedHashMap();
            map.put("UrunTurAdi", urunTuru.getTurAdi());
            map.put("UrunTurId", urunTuru.getUrunTurId());
            map.put("UrunTurUst", urunTuru.getUstTur());
            map.put("UrunTurIsAlt", urunTuru.getIsAlt());
            array.add(map);
        }
        return array;
    }

    public List<UrunTur> listele() {
        List<UrunTur> list = new ArrayList<>();

        try {

            list = urunTuruHelper.listele();            
            LogInsert.insert(17, personelLog, 6, 0);

        } catch (Exception e) {
            LogInsert.error(17, personelLog, "WSUrunTuru -> list() -> "+e.toString());
        }

        return list;
    }

    public void update(UrunTur urunTuru) {
        try {
            urunTuruHelper.update(urunTuru);
                
            LogInsert.insert(17, personelLog, 3, urunTuru.getUrunTurId());
        } catch (Exception e) {
            
            LogInsert.error(17, personelLog, "WSUrunTuru -> update() -> "+e.toString());
        }

    }
}
