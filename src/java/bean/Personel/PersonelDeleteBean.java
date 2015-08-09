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
import model.Personel;
import ws.WSPersonel;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "personelDeleteBean")
@ViewScoped
public class PersonelDeleteBean implements Serializable {

    private List<Personel> listPersonel;
    private WSPersonel personelWS;
    private Personel personel;

    public PersonelDeleteBean() {

        personel = new Personel();

        personelWS = new WSPersonel();

        listelePersonel();  
    }

    public void listelePersonel() {

        listPersonel = new ArrayList<>();
        listPersonel = personelWS.listPersonel();
    }

    public void deletePersonel() {

        try {
            personelWS.delete(personel);
            
            listPersonel.remove(personel);
            
        } catch (Exception e) {
        }
    }

    /**
     * @return the listPersonel
     */
    public List<Personel> getListPersonel() {
        return listPersonel;
    }

    /**
     * @param listPersonel the listPersonel to set
     */
    public void setListPersonel(List<Personel> listPersonel) {
        this.listPersonel = listPersonel;
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

}
