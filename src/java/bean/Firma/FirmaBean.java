/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Firma;

import controls.StringDuzenle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Firma;
import ws.WSFirma;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "firmaBean")
@ViewScoped
public class FirmaBean implements Serializable{

    WSFirma firmaWS;
    private List<Firma> firmaList;
    private Firma firma;
    private Firma deletedFirma;
    private String phone;
    private String fax;
    
    
    public FirmaBean() {
        firmaWS = new WSFirma();
        firma = new Firma();
        deletedFirma = new Firma();
        list();
    }

    public String insert(){
    
        try {
            phone = StringDuzenle.editPhoneNumber(phone);
            fax = StringDuzenle.editPhoneNumber(fax);
            
            firma.setTelefon(phone);
            firma.setFax(fax);
            firma.setIsAktif(true);
            firmaWS.insert(firma);
            list();
        } catch (Exception e) {
        }
        
        return "FirmaDuzenle.xhtml?faces-redirect=true";
    }
    
    public void list(){
    
        firmaList = new ArrayList<>();
        try {
            
            firmaList = firmaWS.listeleFirma();
        } catch (Exception e) {
        }
    }
    
    public void delete(){
    
        firmaList.remove(deletedFirma);
        deletedFirma.setIsAktif(false);
        try {
            
            firmaWS.delete(deletedFirma);
         } catch (Exception e) {
        }
    }
    
    /**
     * @return the firmaList
     */
    public List<Firma> getFirmaList() {
        return firmaList;
    }

    /**
     * @param firmaList the firmaList to set
     */
    public void setFirmaList(List<Firma> firmaList) {
        this.firmaList = firmaList;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the deletedFirma
     */
    public Firma getDeletedFirma() {
        return deletedFirma;
    }

    /**
     * @param deletedFirma the deletedFirma to set
     */
    public void setDeletedFirma(Firma deletedFirma) {
        this.deletedFirma = deletedFirma;
    }
    
}
