/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.Personel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Personel;
import model.Yetki;
import ws.WSPersonel;
import ws.WSYetki;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "personelUpdateBean")
@ViewScoped
public class PersonelUpdateBean implements Serializable{

    
    private Personel personel;
    private Yetki yetki;
    
    private WSPersonel personelWS;
    private WSYetki yetkiWS;
    
    private List<Yetki> listYetki;
    
    public PersonelUpdateBean()  {
        
        personel = new Personel();
        yetki = new Yetki();
        
        personelWS = new WSPersonel();
        yetkiWS = new WSYetki();
        
        personel = (Personel) FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("login");
        
        listeleYetki();
    }

    
    
    public void listeleYetki(){
        
        listYetki = new ArrayList<>();
        listYetki = yetkiWS.listYetki();
    }
    
    
    public String update(){
    
        personel.setIsAktif(true);    
        personel.setYetki(getYetki());
        
        personelWS.update(personel);
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", personel);
        return "PersonelUpdate?faces-redirect=true";
    }
    
    /**
     * @return the personel
     */
    public Personel getPersonel() {
        return personel;
    }

    /**
     * @param personel the personel to set
     */
    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    /**
     * @return the yetki
     */
    public Yetki getYetki() {
        return yetki;
    }

    /**
     * @param yetki the yetki to set
     */
    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    /**
     * @return the listYetki
     */
    public List<Yetki> getListYetki() {
        return listYetki;
    }

    /**
     * @param listYetki the listYetki to set
     */
    public void setListYetki(List<Yetki> listYetki) {
        this.listYetki = listYetki;
    }
    
}
