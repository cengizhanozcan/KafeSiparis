/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Rapor;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Siparis;
import ws.WSGelir;
import ws.WSGider;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "gelirRaporBean")
@ViewScoped
public class GelirRaporBean implements Serializable {

    WSGelir gelirWS;
    
    private Date startDate;
    private Date endDate;
    private float toplamTutar;
    
    private List<Siparis> listGelir;
    
    public GelirRaporBean() {
        
        gelirWS = new WSGelir();
    }
    
    
    public void listeleGelirRapor(){
    
        listGelir = new ArrayList<>();
        
        listGelir = gelirWS.listGelirRapor(startDate, endDate);
        toplamTutarHesapla();
    }
    
    public void toplamTutarHesapla(){
    
        toplamTutar = 0;
        for(Siparis siparis : listGelir){
        
            toplamTutar = toplamTutar + siparis.getSatisFiyati();
        }
        
        DecimalFormat df = new DecimalFormat("#.##"); 
        df.setMaximumFractionDigits(2);
        toplamTutar = Float.valueOf(df.format(toplamTutar)); 
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the toplamTutar
     */
    public float getToplamTutar() {
        return toplamTutar;
    }

    /**
     * @param toplamTutar the toplamTutar to set
     */
    public void setToplamTutar(float toplamTutar) {
        this.toplamTutar = toplamTutar;
    }

    /**
     * @return the list
     */
    public List<Siparis> getListGelir() {
        return listGelir;
    }

    /**
     * @param list the list to set
     */
    public void setListGelir(List<Siparis> listGelir) {
        this.listGelir = listGelir;
    }
    
    
    
}
