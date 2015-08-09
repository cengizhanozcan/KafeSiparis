/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.MasaNo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.MasaNo;
import ws.WSMasaNo;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "masaNoBean")
@ViewScoped
public class MasaNoBean implements Serializable {

    WSMasaNo masaNoWs = null;
    private List<MasaNo> masaNolist;
    private int masaNoSize = 0;
    private int newMasaNoSize = 0;

    public MasaNoBean() {

        masaNoWs = new WSMasaNo();
        masaNolist = new ArrayList<>();
        listMasaNo();
    }

    public void listMasaNo() {

        masaNolist = new ArrayList<>();
        try {

            masaNolist = masaNoWs.list();
            masaNoSize = masaNolist.size();
            newMasaNoSize = masaNoSize;
        } catch (Exception e) {
        }
    }

    public String updateMasaNo() {

        if (newMasaNoSize != masaNoSize) {

            if (masaNoSize < newMasaNoSize) // Yeni masa numaralı eklenecek ise
            { 
                for (int i = masaNoSize + 1; i <= newMasaNoSize; i++) {
                    MasaNo tempMasaNo = new MasaNo();
                    tempMasaNo.setNumara(i);
                    tempMasaNo.setIsAktif(true);

                    masaNoWs.insert(tempMasaNo);
                }
            } else {                                   // Masa numaralı silinecek ise

                for (int i = masaNoSize - 1; i >= newMasaNoSize; i--) {

                    MasaNo tempMasaNo = masaNolist.get(i);
                    tempMasaNo.setIsAktif(false);

                    masaNoWs.update(tempMasaNo); 
                }
            }
        }
        listMasaNo();
        
        return "MasaNo?faces-redirect=true";
    }

    /**
     * @return the masaNolist
     */
    public List<MasaNo> getMasaNolist() {
        return masaNolist;
    }

    /**
     * @param masaNolist the masaNolist to set
     */
    public void setMasaNolist(List<MasaNo> masaNolist) {
        this.masaNolist = masaNolist;
    }

    /**
     * @return the masaNoSize
     */
    public int getMasaNoSize() {
        return masaNoSize;
    }

    /**
     * @param masaNoSize the masaNoSize to set
     */
    public void setMasaNoSize(int masaNoSize) {
        this.masaNoSize = masaNoSize;
    }

    /**
     * @return the newMasaNoSize
     */
    public int getNewMasaNoSize() {
        return newMasaNoSize;
    }

    /**
     * @param newMasaNoSize the newMasaNoSize to set
     */
    public void setNewMasaNoSize(int newMasaNoSize) {
        this.newMasaNoSize = newMasaNoSize;
    }

}
