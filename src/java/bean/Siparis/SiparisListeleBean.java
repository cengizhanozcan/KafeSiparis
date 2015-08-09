/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Siparis;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Gelir;
import model.Masa;
import model.Siparis;
import controls.Time;
import java.io.Serializable;
import ws.WSGelir;
import ws.WSMasa;
import ws.WSSatis;
import ws.WSSiparis;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "siparisListeleBean")
@ViewScoped
public class SiparisListeleBean implements Serializable {

    WSSiparis siparisWS;
    WSSatis satisWS;
    WSMasa masaWS;
    WSGelir gelirWS;

    private List<Siparis> listSiparis;
    private String masaId;
    private double toplamTutar;
    private String masaNumarası;

    public SiparisListeleBean() {

        siparisWS = new WSSiparis();
        satisWS = new WSSatis();
        masaWS = new WSMasa();
        gelirWS = new WSGelir();

        listSiparis = new ArrayList<>();

        masaId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("masaId");
        masaNumarası = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("masaNo");
        listeleSiparisleri();

    }

    public void listeleSiparisleri() {

        listSiparis = new ArrayList<>();
        toplamTutar = 0;
        try {

            listSiparis = siparisWS.siparisleriListele(Integer.parseInt(masaId));
            if (listSiparis.size() > 0) {
                toplamTutar = listSiparis.get(0).getMasa().getToplamTutar();
            }

            //Hatalı toplam tutar hesapladığı için teker teker kontrol ediliyor eğer size büyükse   
            double tempToplamTutar = 0;
            if (listSiparis.size() > 1) {
                tempToplamTutar = 0;
                for (Siparis siparis : listSiparis) {
                    tempToplamTutar = tempToplamTutar + siparis.getSatisFiyati();
                }

                if (tempToplamTutar != toplamTutar) {
                    toplamTutar = tempToplamTutar;
                    listSiparis.get(0).getMasa().setToplamTutar(Float.valueOf(
                            String.valueOf(tempToplamTutar)));
                }
            }

        } catch (Exception e) {
        }
    }

    //Tüm hesabı ödüyor. Daha sonradan parçalı ödeticez.
    public String siparisiOde() {

        try {
            for (Siparis siparis : listSiparis) {

                siparis.setIsOdendi(true);
                siparis.setIsIptal(false);
                siparis.setIsAktif(true);
                satisWS.siparisOdeme(siparis);
            }

            Masa masa = listSiparis.get(0).getMasa();
            masa.setIsOdendi(true);
            masa.setIsKampanya(false);
            masa.setIsAktif(true);

            Gelir gelir = new Gelir();
            gelir.setMiktar(masa.getToplamTutar());
            gelir.setTarih(Time.getTimeOfToday());
            gelir.setIsAktif(true);
            gelirWS.insertGelir(gelir);

            masa.setGelir(gelir);
            satisWS.masaOdeme(masa);
            infoSatisBasarili();

        } catch (Exception e) {
            infoSatisBasarisiz();
            System.out.println("Ödeme Sırasında Hata gerçekleşti.");
        }
        return "Satis?faces-redirect";
    }

    //---------------------------------         Info            --------------------------------------------------
    public void infoSatisBasarili() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                "Satış Başarıyla Gerçekleştirilmiştir."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void infoSatisBasarisiz() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata",
                "Hata Satış Gerçekleştirilemedi."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    /**
     * @return the listSiparis
     */
    public List<Siparis> getListSiparis() {
        return listSiparis;
    }

    /**
     * @param listSiparis the listSiparis to set
     */
    public void setListSiparis(List<Siparis> listSiparis) {
        this.listSiparis = listSiparis;
    }

    /**
     * @return the masaId
     */
    public String getMasaId() {
        return masaId;
    }

    /**
     * @param masaId the masaId to set
     */
    public void setMasaId(String masaId) {
        this.masaId = masaId;
    }

    /**
     * @return the toplamTutar
     */
    public double getToplamTutar() {
        return toplamTutar;
    }

    /**
     * @param toplamTutar the toplamTutar to set
     */
    public void setToplamTutar(double toplamTutar) {
        this.toplamTutar = toplamTutar;
    }

    /**
     * @return the masaNumarası
     */
    public String getMasaNumarası() {
        return masaNumarası;
    }

    /**
     * @param masaNumarası the masaNumarası to set
     */
    public void setMasaNumarası(String masaNumarası) {
        this.masaNumarası = masaNumarası;
    }

    /**
     * @return the masaNumarası
     */
}
