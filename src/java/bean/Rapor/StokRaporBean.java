/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Rapor;

import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Stok;
import org.hibernate.mapping.Collection;
import ws.WSStokRapor;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "stokRaporBean")
@ViewScoped
public class StokRaporBean implements Serializable {

    private List<Stok> listOfStok;
    private WSStokRapor wsStokRapor;

    public StokRaporBean() {

        wsStokRapor = new WSStokRapor();
        getAllStok();
    }

    public void getAllStok() {
        List<Stok> list = new ArrayList<>();
        listOfStok = new ArrayList<>();
        list = wsStokRapor.getAllStok();
        listOfStok = removeDuplicates(list);

    }

    public List<Stok> removeDuplicates(List<Stok> list) {

        List<Stok> uniques = new ArrayList<Stok>();
        List<String> temp = new ArrayList<String>();
        
        for (Stok stok : list) {
            if (!temp.contains(stok.getUrun().getUrunAd())) {
                temp.add(stok.getUrun().getUrunAd());
                uniques.add(stok);
            }
        }

        return uniques;
    }

    /**
     * @return the listOfStok
     */
    public List<Stok> getListOfStok() {
        return listOfStok;
    }

    /**
     * @param listOfStok the listOfStok to set
     */
    public void setListOfStok(List<Stok> listOfStok) {
        this.listOfStok = listOfStok;
    }

}
