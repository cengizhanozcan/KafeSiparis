package model;
// Generated May 14, 2015 12:58:39 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Firma generated by hbm2java
 */
public class Firma  implements java.io.Serializable {


     private Integer firmaId;
     private String firmaAd;
     private String telefon;
     private String fax;
     private String adres;
     private boolean isAktif;
     private Set<Stok> stoks = new HashSet<Stok>(0);

    public Firma() {
    }

	
    public Firma(String firmaAd, boolean isAktif) {
        this.firmaAd = firmaAd;
        this.isAktif = isAktif;
    }
    public Firma(String firmaAd, String telefon, String fax, String adres, boolean isAktif, Set<Stok> stoks) {
       this.firmaAd = firmaAd;
       this.telefon = telefon;
       this.fax = fax;
       this.adres = adres;
       this.isAktif = isAktif;
       this.stoks = stoks;
    }
   
    public Integer getFirmaId() {
        return this.firmaId;
    }
    
    public void setFirmaId(Integer firmaId) {
        this.firmaId = firmaId;
    }
    public String getFirmaAd() {
        return this.firmaAd;
    }
    
    public void setFirmaAd(String firmaAd) {
        this.firmaAd = firmaAd;
    }
    public String getTelefon() {
        return this.telefon;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getAdres() {
        return this.adres;
    }
    
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public boolean isIsAktif() {
        return this.isAktif;
    }
    
    public void setIsAktif(boolean isAktif) {
        this.isAktif = isAktif;
    }
    public Set<Stok> getStoks() {
        return this.stoks;
    }
    
    public void setStoks(Set<Stok> stoks) {
        this.stoks = stoks;
    }




}

