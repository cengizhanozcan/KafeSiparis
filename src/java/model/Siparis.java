package model;
// Generated May 14, 2015 12:58:39 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Siparis generated by hbm2java
 */
public class Siparis  implements java.io.Serializable {


     private Integer siparisId;
     private Masa masa;
     private Personel personel;
     private Stok stok;
     private int satilanAdet;
     private float satisFiyati;
     private Date satisTarihi;
     private Boolean isIptal;
     private Boolean isAktif;
     private Boolean isOdendi;
     private Boolean isHazir;

    public Siparis() {
    }

	
    public Siparis(Masa masa, Personel personel, Stok stok, int satilanAdet, float satisFiyati, Date satisTarihi) {
        this.masa = masa;
        this.personel = personel;
        this.stok = stok;
        this.satilanAdet = satilanAdet;
        this.satisFiyati = satisFiyati;
        this.satisTarihi = satisTarihi;
    }
    public Siparis(Masa masa, Personel personel, Stok stok, int satilanAdet, float satisFiyati, Date satisTarihi, Boolean isIptal, Boolean isAktif, Boolean isOdendi, Boolean isHazir) {
       this.masa = masa;
       this.personel = personel;
       this.stok = stok;
       this.satilanAdet = satilanAdet;
       this.satisFiyati = satisFiyati;
       this.satisTarihi = satisTarihi;
       this.isIptal = isIptal;
       this.isAktif = isAktif;
       this.isOdendi = isOdendi;
       this.isHazir = isHazir;
    }
   
    public Integer getSiparisId() {
        return this.siparisId;
    }
    
    public void setSiparisId(Integer siparisId) {
        this.siparisId = siparisId;
    }
    public Masa getMasa() {
        return this.masa;
    }
    
    public void setMasa(Masa masa) {
        this.masa = masa;
    }
    public Personel getPersonel() {
        return this.personel;
    }
    
    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
    public Stok getStok() {
        return this.stok;
    }
    
    public void setStok(Stok stok) {
        this.stok = stok;
    }
    public int getSatilanAdet() {
        return this.satilanAdet;
    }
    
    public void setSatilanAdet(int satilanAdet) {
        this.satilanAdet = satilanAdet;
    }
    public float getSatisFiyati() {
        return this.satisFiyati;
    }
    
    public void setSatisFiyati(float satisFiyati) {
        this.satisFiyati = satisFiyati;
    }
    public Date getSatisTarihi() {
        return this.satisTarihi;
    }
    
    public void setSatisTarihi(Date satisTarihi) {
        this.satisTarihi = satisTarihi;
    }
    public Boolean getIsIptal() {
        return this.isIptal;
    }
    
    public void setIsIptal(Boolean isIptal) {
        this.isIptal = isIptal;
    }
    public Boolean getIsAktif() {
        return this.isAktif;
    }
    
    public void setIsAktif(Boolean isAktif) {
        this.isAktif = isAktif;
    }
    public Boolean getIsOdendi() {
        return this.isOdendi;
    }
    
    public void setIsOdendi(Boolean isOdendi) {
        this.isOdendi = isOdendi;
    }
    public Boolean getIsHazir() {
        return this.isHazir;
    }
    
    public void setIsHazir(Boolean isHazir) {
        this.isHazir = isHazir;
    }




}


