/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.SiparisHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Personel;
import model.Siparis;
import org.json.simple.JSONArray;

/**
 *
 * @author cengizhan
 */
@Path("/WSSiparis")
public class WSSiparis implements Serializable{

    SiparisHelper siparisHelper;
    Personel personelLog;

    public WSSiparis() {

        siparisHelper = new SiparisHelper();
        
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }

    public List<Siparis> siparisleriListele(int masaNoId) {

        List<Siparis> list = new ArrayList<>();

        try {
            list = siparisHelper.listele(masaNoId);
            
            LogInsert.insert(13, personelLog, 6, 0);

        } catch (Exception e) {
            LogInsert.error(13, personelLog, "WSSiparis -> siparisListele() -> "+e.toString());
        }

        return joining(list);
    }

    @Path("/ListeleSiparis/{MasaNoId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray siparisListele(@PathParam("MasaNoId") String masaNoId) {

        List<Siparis> list = new ArrayList<>();
        JSONArray jsonList = new JSONArray();

        try {
            list = joining(siparisHelper.listele(Integer.parseInt(masaNoId)));
            //LogInsert.insert(13, personelLog, 6, 0);
        } catch (Exception e) {
            
            //LogInsert.error(13, personelLog, "WSSiparis -> siparisListeleMobil() -> "+e.toString());

        }

        for (Siparis siparis : list) {
            Map map = new LinkedHashMap();
            map.put("MasaNo", siparis.getMasa().getMasaNo().getNumara());
            map.put("UrunAdeti", siparis.getSatilanAdet());
            map.put("UrunSatisFiyati", siparis.getSatisFiyati());
            map.put("UrunAdi", siparis.getStok().getUrun().getUrunAd());
            map.put("ToplamTutar", siparis.getMasa().getToplamTutar());
            jsonList.add(map);
        }
        return jsonList;
    }

    //Aynı üründen birden farklı zamanlarda verildiyse birden fazla göstermek yerine üstüne bindirmeyi sağlıyacak
    public List<Siparis> joining(List<Siparis> list) {

        List<Siparis> tempList = new ArrayList<>();

        boolean isFirst = true;
        for (Siparis siparis : list) {
            
            boolean isDuplicate = false;
            int i = 0;
            for (Siparis tempSiparis : tempList) {
                if (siparis.getStok().getUrun().getUrunId().equals(tempSiparis.getStok().getUrun().getUrunId())) {

                    tempSiparis.setSatilanAdet(tempSiparis.getSatilanAdet() + siparis.getSatilanAdet());
                    tempSiparis.setSatisFiyati(tempSiparis.getSatisFiyati() + siparis.getSatisFiyati());
                    tempList.set(i, tempSiparis);
                    isDuplicate = true;
                    break;
                }
                i++; 
            }
          
            if (!isDuplicate) {
                tempList.add(siparis);
            }
        }

        return tempList;
    }

}
