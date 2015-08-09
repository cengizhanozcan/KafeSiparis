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
import model.Personel;
import model.Siparis;
import ws.WSPersonelRapor;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "personelRaporBean")
@ViewScoped
public class PersonelRaporBean implements Serializable{

    WSPersonelRapor wsPersonelRapor;
    private List<Personel> listPersonel;
    private List<Siparis> listRapor; 
    private Personel personel;
    private String toplamTutar;
    private Date startDate;
    private Date finishDate;
    
    public PersonelRaporBean() {
        
        wsPersonelRapor = new WSPersonelRapor(); 
        personel = new Personel();
        
        listPersonel = new ArrayList<>();
        listRapor = new ArrayList<>();
        
        personelListele();
    }
    
    public void personelListele(){
    
        listPersonel = new ArrayList<>();
        listPersonel = wsPersonelRapor.listPersonel();
        
    } 
    
    public void raporListele(){
    
        listRapor = new ArrayList<>(); 
        System.out.println("PersonelId: " + personel.getPersonelId());
        listRapor = wsPersonelRapor.listPersonelRapor(personel,startDate, finishDate );
        
        System.out.println("List Size : " + listRapor.size());
        if(listRapor.size()>0)
        System.out.println("Satis Fiyati : " + listRapor.get(0).getSatisFiyati());
        
        toplamTutarHesapla();
    }
    
    public void toplamTutarHesapla(){
     
        float tutar = 0;
        for(Siparis siparis : listRapor){
        
            tutar = tutar + siparis.getSatisFiyati();
        }
         
        DecimalFormat df = new DecimalFormat("#.##"); 
        df.setMaximumFractionDigits(2);
        toplamTutar = String.valueOf(df.format(tutar)); 
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
     * @return the listRapor
     */
    public List<Siparis> getListRapor() {
        return listRapor;
    }

    /**
     * @param listRapor the listRapor to set
     */
    public void setListRapor(List<Siparis> listRapor) {
        this.listRapor = listRapor;
    }
  
    /**
     * @return the toplamTutar
     */
    public String getToplamTutar() {
        return toplamTutar;
    }

    /**
     * @param toplamTutar the toplamTutar to set
     */
    public void setToplamTutar(String toplamTutar) {
        this.toplamTutar = toplamTutar;
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
     * @return the finishDate
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * @param finishDate the finishDate to set
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * @return the personel
     */
    public Personel getPersonel() {
        System.out.println("personel get: " + personel.getPersonelId());
        return personel;
    }

    /**
     * @param personel the personel to set
     */
    public void setPersonel(Personel personel) {
        System.out.println("personel set: " + personel.getPersonelId());
        this.personel = personel;
    }
    
    
    
}
