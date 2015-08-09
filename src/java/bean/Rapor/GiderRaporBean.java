/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Rapor;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Firma;
import model.Gider;
import ws.WSFirma;
import ws.WSGider;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "giderRaporBean")
@ViewScoped
public class GiderRaporBean implements Serializable{

    /**
     * Creates a new instance of GiderRaporBean
     */
    
    private Date startDate;
    private Date endDate;
    private float toplamTutar;
    
    private List<Gider> listGider;
    private List<Firma> listFirma;
    
    private WSGider giderWS;
    private WSFirma firmaWS;
    
    public GiderRaporBean() {
        giderWS = new WSGider();
        firmaWS = new WSFirma();
        listeleFirma();
        
    }
    
    public void listeleFirma(){
    
        listFirma = new ArrayList<>();
        
        listFirma = firmaWS.listeleFirma();
    }
    
    public void listeleGider(){
    
        listGider = new ArrayList<>();
        
        listGider = giderWS.listStok(startDate, endDate);
        toplam();
    } 
    
    public void toplam(){
        
        toplamTutar = 0;
        for(Gider gider : listGider){
        
            toplamTutar = toplamTutar + gider.getMiktar();
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
     * @return the listGider
     */
    public List<Gider> getListGider() {
        return listGider;
    }

    /**
     * @param listGider the listGider to set
     */
    public void setListGider(List<Gider> listGider) {
        this.listGider = listGider;
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
    
    
    
}
