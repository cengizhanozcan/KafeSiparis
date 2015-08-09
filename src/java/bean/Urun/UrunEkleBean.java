/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Urun;

import controls.Time;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Firma;
import model.Gider;
import model.GiderTuru;
import model.Personel;
import model.Stok;
import model.Urun;
import model.UrunTur;
import ws.WSFirma;
import ws.WSGider;
import ws.WSGiderTuru;
import ws.WSUrun;
import ws.WSUrunTuru;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "urunEkleBean")
@ViewScoped
public class UrunEkleBean implements Serializable {

    private Personel loginPersonel;
    private Urun urun;
    private Stok stok;
    private UrunTur urunTuru;
    private Firma firma;

    private String aciklama;
    private String hazirlanmaSuresi;
    private Date urunAlisTarihi;
    private int alinanUrunAdedi;
    private float alisFiyati;
    private float satisFiyati;

    private WSUrun urunWS;
    private WSUrunTuru urunTuruWS;
    private WSFirma firmaWS;
    private WSGider giderWS;
    private WSGiderTuru giderTuruWS;

    private List<Stok> list;
    private List<UrunTur> listUrunTuru;
    private List<Firma> listFirma;

    public UrunEkleBean() {

        loginPersonel = new Personel();
        urun = new Urun();
        stok = new Stok();
        firma = new Firma();
        urunTuru = new UrunTur();
        urunAlisTarihi = new Date();

        urunWS = new WSUrun();
        urunTuruWS = new WSUrunTuru();
        firmaWS = new WSFirma();
        giderWS = new WSGider();
        giderTuruWS = new WSGiderTuru();

        listUrunTuru = new ArrayList<>();

        try {
            loginPersonel = (Personel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");

        } catch (Exception e) {
        }

        listeleUrunTuru();
        firmaListele();;
    }

    public void listeleUrunTuru() {

        listUrunTuru = new ArrayList<>();
        listUrunTuru = urunTuruWS.listele();
    }

    public void firmaListele() {

        listFirma = new ArrayList<>();
        listFirma = firmaWS.listeleFirma();
    }

    //Ürün ekle kısımında girilen string ile otomatik olarak did you mean kısmını getirir :D
    public List<String> listUrun() {

        list = new ArrayList<>();
        List<String> listUrunAd = new ArrayList<>();
        list = urunWS.listeleUrun();

        for (Stok stok : list) {
            listUrunAd.add(stok.getUrun().getUrunAd());
        }

        return listUrunAd;
    }

    public void itemSelectUrunAd() {
        stok = new Stok();

        for (Stok stok : list) {
            if (stok.getUrun().getUrunAd().equals(urun.getUrunAd())) {
                this.urun = stok.getUrun();
                this.stok = stok;
                this.stok.setStokId(0);                    //üzerine güncelleme yapmasın diye.

                //Alt satırda ki atamalar ürünlerin ekranda dinamik olarak getirilmesini sağlıyan atamalar.
                satisFiyati = urun.getSatisFiyati();
                alisFiyati = stok.getAlisFiyati();
                aciklama = urun.getAciklama();
                hazirlanmaSuresi = urun.getHazirlanmaSuresi();

                break;
            }
        }
    }

    public String insert() {

        Stok yeniStok = new Stok();
        try {

            // stok a yeni bi ürün ekleyip urunleri güncellicez. Eğer ürün yoksa önce ürün eklicez.
            if (urun.getUrunId() == null) {
                Urun yeniUrun = new Urun();

                yeniUrun.setUrunTur(urunTuru);
                yeniUrun.setUrunAd(urun.getUrunAd());
                yeniUrun.setToplamAdet(alinanUrunAdedi);
                yeniUrun.setAciklama(aciklama);
                yeniUrun.setHazirlanmaSuresi(hazirlanmaSuresi);
                yeniUrun.setSatisFiyati(satisFiyati);
                yeniUrun.setIsSatis(true);
                yeniUrun.setIsAktif(true);

                urunWS.insertUrun(yeniUrun);
                yeniStok.setUrun(yeniUrun);
            } else {

                urun.setUrunTur(urunTuru);
                urun.setToplamAdet(urun.getToplamAdet() + alinanUrunAdedi);
                urun.setSatisFiyati(satisFiyati);
                urun.setAciklama(aciklama);
                urun.setHazirlanmaSuresi(hazirlanmaSuresi);
                urun.setIsSatis(true);
                urun.setIsAktif(true);

                urunWS.updateUrun(urun);
                yeniStok.setUrun(urun);
            }

            yeniStok.setAlinanAdet(alinanUrunAdedi);
            yeniStok.setKalanAdet(alinanUrunAdedi);
            yeniStok.setAlisFiyati(alisFiyati);
            yeniStok.setAlisTarihi(Time.getTimeOfSaved(urunAlisTarihi.getTime()));
            yeniStok.setFirma(firma);
            yeniStok.setIsAktif(true);
            yeniStok.setPersonel(loginPersonel);

            urunWS.insertStok(yeniStok);
            infoUrunEklemeBasarili();
            
            insertGider(yeniStok);
            
        } catch (Exception e) {
            infoUrunEklemeBasarisiz();
        }

        return "UrunEkle?faces-redirect=true";
    }
    
    public void insertGider(Stok stok){             //Gider tablosuna Yeni ürün ekliyor
        
        List<GiderTuru> listGiderTuru = new ArrayList<>();
        listGiderTuru = giderTuruWS.list();
        GiderTuru giderTuru = listGiderTuru.get(0);
        
        Gider gider = new Gider();
        gider.setAlisTarihi(stok.getAlisTarihi());
        gider.setGiderTuru(giderTuru);
        gider.setIsAktif(true);
        gider.setMiktar(stok.getAlinanAdet() * stok.getAlisFiyati());
        gider.setStok(stok);
        
        try {
            
            giderWS.insert(gider);
        
        } catch (Exception e) {
        }
        
    }
    

    //-------------------------------------     Info        -------------------------------------------
    public void infoUrunEklemeBasarili() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                "Ürün Başarıyla Eklenmiştir."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void infoUrunEklemeBasarisiz() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info",
                "Ürün Ekleme Sırasında Hata Gerçekleşti."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    /**
     * @return the urun
     */
    public Urun getUrun() {
        return urun;
    }

    /**
     * @param urun the urun to set
     */
    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    /**
     * @return the stok
     */
    public Stok getStok() {
        return stok;
    }

    /**
     * @param stok the stok to set
     */
    public void setStok(Stok stok) {
        this.stok = stok;
    }

    /**
     * @return the listUrunTuru
     */
    public List<UrunTur> getListUrunTuru() {
        return listUrunTuru;
    }

    /**
     * @param listUrunTuru the listUrunTuru to set
     */
    public void setListUrunTuru(List<UrunTur> listUrunTuru) {
        this.listUrunTuru = listUrunTuru;
    }

    /**
     * @return the urunTuru
     */
    public UrunTur getUrunTuru() {
        return urunTuru;
    }

    /**
     * @param urunTuru the urunTuru to set
     */
    public void setUrunTuru(UrunTur urunTuru) {
        this.urunTuru = urunTuru;
    }

    /**
     * @return the urunAlisTarihi
     */
    public Date getUrunAlisTarihi() {
        return urunAlisTarihi;
    }

    /**
     * @param urunAlisTarihi the urunAlisTarihi to set
     */
    public void setUrunAlisTarihi(Date urunAlisTarihi) {
        this.urunAlisTarihi = urunAlisTarihi;
    }

    /**
     * @return the urunAdedi
     */
    public int getAlinanUrunAdedi() {
        return alinanUrunAdedi;
    }

    /**
     * @param urunAdedi the urunAdedi to set
     */
    public void setAlinanUrunAdedi(int alinanUrunAdedi) {
        this.alinanUrunAdedi = alinanUrunAdedi;
    }

    /**
     * @return the alisFiyati
     */
    public float getAlisFiyati() {
        return alisFiyati;
    }

    /**
     * @param alisFiyati the alisFiyati to set
     */
    public void setAlisFiyati(float alisFiyati) {
        this.alisFiyati = alisFiyati;
    }

    /**
     * @return the satisFiyati
     */
    public float getSatisFiyati() {
        return satisFiyati;
    }

    /**
     * @param satisFiyati the satisFiyati to set
     */
    public void setSatisFiyati(float satisFiyati) {
        this.satisFiyati = satisFiyati;
    }

    /**
     * @return the listFirma
     */
    public List<Firma> getListFirma() {
        return listFirma;
    }

    /**
     * @param listFirma the listFirma to set
     */
    public void setListFirma(List<Firma> listFirma) {
        this.listFirma = listFirma;
    }

    /**
     * @return the firma
     */
    public Firma getFirma() {
        return firma;
    }

    /**
     * @param firma the firma to set
     */
    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    /**
     * @return the aciklama
     */
    public String getAciklama() {
        return aciklama;
    }

    /**
     * @param aciklama the aciklama to set
     */
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    /**
     * @return the hazirlanmaSuresi
     */
    public String getHazirlanmaSuresi() {
        return hazirlanmaSuresi;
    }

    /**
     * @param hazirlanmaSuresi the hazirlanmaSuresi to set
     */
    public void setHazirlanmaSuresi(String hazirlanmaSuresi) {
        this.hazirlanmaSuresi = hazirlanmaSuresi;
    }
}
