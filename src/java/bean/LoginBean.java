/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.Personel;
import ws.WSLogin;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    private Personel personel;
    private Personel loginPersonel;
    private WSLogin loginWS;

    public LoginBean() {

        personel = new Personel();
        loginPersonel = new Personel();
        loginWS = new WSLogin();
    }

    public String login() {

        if (!personel.getKullaniciAd().equals("") && !personel.getSifre().equals("")) {
            
            loginPersonel = loginWS.login(personel.getKullaniciAd(), personel.getSifre());

            if (loginPersonel == null) {
                return "login?faces-redirect=true";
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", loginPersonel);
            return "index?faces-redirect=true";

        }
        return "login?faces-redirect=true";
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
     * @return the loginPersonel
     */
    public Personel getLoginPersonel() {
        return loginPersonel;
    }

    /**
     * @param loginPersonel the loginPersonel to set
     */
    public void setLoginPersonel(Personel loginPersonel) {
        this.loginPersonel = loginPersonel;
    }

}
