/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import controls.LogInsert;
import helper.MasaHelper;
import helper.SiparisHelper;
import helper.StokHelper;
import helper.UrunHelper;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 
import model.Masa;
import model.MasaNo;
import model.Personel;
import model.Siparis;
import model.Stok;
import model.Urun;
import model.UrunTur;
import model.Yetki;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import controls.Time;
import java.util.HashSet;

/**
 *
 * @author cengizhan
 */
@Path("/WSUrun")
public class WSUrun implements Serializable {

    Masa masa;

    UrunHelper urunHelper;
    StokHelper stokHelper;
    MasaHelper masaHelper;
    SiparisHelper siparisHelper;
    Personel personelLog;

    public WSUrun() {

        urunHelper = new UrunHelper();
        stokHelper = new StokHelper();
        masaHelper = new MasaHelper();
        siparisHelper = new SiparisHelper();
        
        personelLog = new Personel();
        personelLog.setPersonelId(1);
    }

    public List<Stok> listeleUrun() {
        try {

            return duplicateClear(urunHelper.listUrun());
        } catch (Exception e) {
            LogInsert.error(16, personelLog, "WSUrun -> listeleUrun() -> "+e.toString());
        }
        return null;
    }

    @Path("/Listele")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray listeleUrunPhone() throws ParseException {

        List<Stok> tempList = new ArrayList<>();
        JSONArray array = new JSONArray();

        try {
            tempList = urunHelper.listUrun();
            
            //LogInsert.insert(16, personelLog, 6, 0);
        } catch (Exception e) {
            //LogInsert.error(16, personelLog, "WSUrun -> ListeleUrunPhone() -> "+e.toString());

            return null;
        }
        List<Stok> list = duplicateClear(tempList);

        for (Stok stok : list) {
            Map map = new LinkedHashMap();
            map.put("UrunId", stok.getUrun().getUrunId());
            map.put("UrunAd", stok.getUrun().getUrunAd());
            map.put("SatisFiyati", stok.getUrun().getSatisFiyati());
            map.put("Icindekiler", stok.getUrun().getAciklama());
            map.put("HazirlanmaSuresi", stok.getUrun().getHazirlanmaSuresi());
            map.put("IsSatis", stok.getUrun().isIsSatis());
            map.put("UrunTuruAd", stok.getUrun().getUrunTur().getTurAdi());
            map.put("UrunTuruId", stok.getUrun().getUrunTur().getUrunTurId());
            map.put("UrunTuruUstId", stok.getUrun().getUrunTur().getUstTur());
            map.put("UrunTuruIsUst", stok.getUrun().getUrunTur().getIsAlt());
            array.add(map);
        }

        return array;
    }

    public List<Stok> duplicateClear(List<Stok> listStok) {

        List<Stok> listTempStok = new ArrayList<>();
        for(Stok stok: listStok){
            
            boolean duplicate = false;
            if (listTempStok.size()==0) {
                listTempStok.add(stok);
            }
            for(Stok tempStok : listTempStok){
                if(tempStok.getUrun().getUrunId() == stok.getUrun().getUrunId()){
                    duplicate=true;
                    break;
                }
            }
            if (!duplicate) {
                listTempStok.add(stok);
            }
        }
        
        
        return listTempStok;
    }

    @Path("/UrunSiparis/{UrunId}/{Adet}/{MasaNoId}/{PersonelId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)   
    public void urunSiparisPhone(@PathParam("UrunId") String urunId, @PathParam("Adet") String adet,
            @PathParam("MasaNoId") String masaNoId, @PathParam("PersonelId") String personelId) {

        Personel personel = new Personel();
        personel.setPersonelId(Integer.parseInt(personelId));
        try {
            Masa tempMasa = checkMasa(Integer.parseInt(masaNoId));
            if (tempMasa == null) {         //Eğer masa nesnesi daha önce oluşturulmadıysa.
                masa = new Masa();
                createMasa(Integer.parseInt(masaNoId));
            } else {
                masa = tempMasa;
            }

            Urun urun = urunHelper.findUrun(Integer.parseInt(urunId));
            //LogInsert.insert(13, personelLog, 7, urun.getUrunId());
            if (urun != null) {

                if (urun.getToplamAdet() > 0 && urun.getToplamAdet() >= Integer.parseInt(adet)) {
                    if (urun.isIsSatis()) {

                        urun.setToplamAdet(urun.getToplamAdet() - Integer.parseInt(adet));
                        urunHelper.update(urun);

                        //Geçici kısım Daha sonradan dahil edilekecek bölüm
                        stokKontrolveStokdanDusum(urun, personel, Integer.parseInt(adet));
                        //Geçici kısım Daha sonradan dahil edilekecek bölüm Bitişşşşş
                    } else {               //Eğer stokta isSatisi kapalıysa
                        System.out.println("İs satiş = false Durumunda");
                    }

                } else {               //eğer stokta yeteri kadar ürün yoksa

                    System.out.println("Yeteri kadar Ürün yok");
                }

            } else {
                System.out.println("Sistemde ürün yook");
            }

        } catch (Exception e) {
            //LogInsert.error(13, personelLog, "WSUrun -> urunSiparisPhone() -> "+e.toString());
        }

    }

    public Masa checkMasa(int masaNoId) {

        Masa masa = null;
        try {

            masa = siparisHelper.checkMasa(masaNoId);
            
            //LogInsert.insert(10, personelLog, 2, masa.getMasaId());
        } catch (Exception e) {
            
            //LogInsert.error(10, personelLog, "WSUrun -> checkMasa() -> "+e.toString());
        }
        return masa;
    }

    public void createMasa(int masaNoId) {

        MasaNo masaNo = new MasaNo();
        masaNo.setMasaNoId(masaNoId);

        masa.setToplamTutar(0.0f);
        masa.setMasaNo(masaNo);
        masa.setIsOdendi(false);
        masa.setIsKampanya(false);
        masa.setIsAktif(true);

        try {

            masaHelper.insert(masa);
            
            //LogInsert.insert(10, personelLog, 2, masa.getMasaId());
        } catch (Exception e) {
            
            //LogInsert.error(10, personelLog, "WSUrun -> createMasa() -> "+e.toString());
        }
    }

    // Alttaki metod sepete ürün eklediğimiz an oluşturulur ve masaID ile masa ya bağlanır.
    // Siparis eklendikden sonra masa güncellenir.
    public void createSiparisForEveryProduct(Personel personel, Stok stok, int satilanAdet, float satisFiyati) {

        float urunSatisFiyati = satisFiyati * satilanAdet;
        Siparis siparis = new Siparis();

        siparis.setMasa(masa);
        siparis.setStok(stok);
        siparis.setPersonel(personel);
        siparis.setSatilanAdet(satilanAdet);
        siparis.setSatisFiyati(urunSatisFiyati);
        siparis.setSatisTarihi(Time.getTimeOfToday());
        siparis.setIsIptal(false);
        siparis.setIsOdendi(false);
        siparis.setIsHazir(false);
        siparis.setIsAktif(true);

        try {
            siparisHelper.insert(siparis);

            updateMasa(siparis);
            
            //LogInsert.insert(13, personelLog, 2, siparis.getSiparisId());
        } catch (Exception e) {
            
            //LogInsert.error(13, personelLog, "WSUrun -> createSiparisForEveryProduct() -> "+e.toString());
        }
    }

    //Masaya yeni siparis (ürün) eklendiği an fiyat güncellemesi için kullanılcak.
    public void updateMasa(Siparis siparis) {

        float urunCost = siparis.getSatisFiyati();
        masa.setToplamTutar(masa.getToplamTutar() + urunCost);
        masa.setIsOdendi(false);
        masa.setIsKampanya(false);
        masa.setIsAktif(true);

        try {
            masaHelper.update(masa);
            
            //LogInsert.insert(10, personelLog, 3, masa.getMasaId());
        } catch (Exception e) {
            
            //LogInsert.error(10, personelLog, "WSUrun -> updateMasa() -> "+e.toString());
        }
    }

    public void stokKontrolveStokdanDusum(Urun pUrun, Personel personel, int adet) {

        List<Stok> listStok = new ArrayList<>();

        if (pUrun != null && pUrun.getUrunId() != 0) {
            try {

                listStok = stokHelper.findStok(pUrun);

                //LogInsert.insert(14, personelLog, 7, 0);
                /* Stok da 1 4 dagılmış bir ürün için 3 adet istek gelirse önce en eski eklenen ürünü (1 olanı) 
                 bulunur ve sistemden düşürülür daha sonra  4 adet olan stok bulunur ve ondan 2 tane düşürülür.
                 */
                int kalanAdet = adet;
                for (Stok stok : listStok) {

                    if (kalanAdet < stok.getKalanAdet()) {

                        stok.setKalanAdet(stok.getKalanAdet() - kalanAdet);
                        stokHelper.update(stok);

                        createSiparisForEveryProduct(personel, stok, kalanAdet, pUrun.getSatisFiyati());
                        break;
                    } else {

                        stok.setKalanAdet(0);
                        stok.setIsAktif(false);
                        int stokKalanAdet = stok.getKalanAdet();
                        kalanAdet = kalanAdet - stokKalanAdet;
                        kalanAdet = kalanAdet * -1;

                        stokHelper.update(stok);

                        createSiparisForEveryProduct(personel, stok, stokKalanAdet, pUrun.getSatisFiyati());
                    }
                }

            } catch (Exception e) {
                //LogInsert.error(14, personelLog, "WSUrun -> stokKontrolveStokdanDusum() -> "+e.toString());
            }
        }
    }

    // ---------------------------------    Ürün Ekleme Kısmının İnsert Updateleri Başlar        ---------------------------------
    public void insertStok(Stok stok) {
        try {

            stokHelper.insert(stok);
            LogInsert.insert(14, personelLog, 2, stok.getStokId());
            
        } catch (Exception e) {
            
            LogInsert.error(14, personelLog, "WSUrun -> insertStok() -> "+e.toString());
        }
    }

    public void insertUrun(Urun urun) {
        try {

            urunHelper.insert(urun);
            
            LogInsert.insert(16, personelLog, 2, urun.getUrunId());
        } catch (Exception e) {
            
            LogInsert.error(16, personelLog, "WSUrun -> insertUrun() -> "+e.toString());
        }
    }

    public void updateUrun(Urun urun) {
        try {

            urunHelper.update(urun);
            
            LogInsert.insert(16, personelLog, 3, urun.getUrunId());
        }catch(Exception e){
            
            LogInsert.error(16, personelLog, "WSUrun -> updateUrun() -> "+e.toString());
        }
    }

}
