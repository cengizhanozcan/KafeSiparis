/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Masa;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import model.MasaNo;
import ws.WSMasaNo;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "masaBean")
@ViewScoped
public class MasaBean implements Serializable {

    private List<MasaNo> listMasaNo;
    private WSMasaNo masaNoWS;
    private String masaId;
    private String masaNo;
    
    public MasaBean() {

        listMasaNo = new ArrayList<>();
        masaNoWS = new WSMasaNo();

        listeleMasaNo();
    }

    public void listeleMasaNo() {

        listMasaNo = new ArrayList<>();
        listMasaNo = masaNoWS.list();
    }

    
    //bu Method kullanılmıyor suan direk url den yönlendiriyoruz jsf sayfasından
    public void navigation() {
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect("Satis.xhtml?MasaId=" + masaId);
        } catch (Exception e) {
        }
    }

    /**
     * @return the listMasaNo
     */
    public List<MasaNo> getListMasaNo() {
        return listMasaNo;
    }

    /**
     * @param listMasaNo the listMasaNo to set
     */
    public void setListMasaNo(List<MasaNo> listMasaNo) {
        this.listMasaNo = listMasaNo;
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
     * @return the masaNo
     */
    public String getMasaNo() {
        return masaNo;
    }

    /**
     * @param masaNo the masaNo to set
     */
    public void setMasaNo(String masaNo) {
        this.masaNo = masaNo;
    }

}
