package model;
// Generated May 14, 2015 12:58:39 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Log generated by hbm2java
 */
public class Log  implements java.io.Serializable {


     private Integer logId;
     private Cihaz cihaz;
     private LogDurum logDurum;
     private Personel personel;
     private Tablo tablo;
     private int primaryId;
     private Date tarih;
     private String aciklama;
     private boolean isAktif;

    public Log() {
    }

	
    public Log(Cihaz cihaz, LogDurum logDurum, Personel personel, Tablo tablo, int primaryId, Date tarih, boolean isAktif) {
        this.cihaz = cihaz;
        this.logDurum = logDurum;
        this.personel = personel;
        this.tablo = tablo;
        this.primaryId = primaryId;
        this.tarih = tarih;
        this.isAktif = isAktif;
    }
    public Log(Cihaz cihaz, LogDurum logDurum, Personel personel, Tablo tablo, int primaryId, Date tarih, String aciklama, boolean isAktif) {
       this.cihaz = cihaz;
       this.logDurum = logDurum;
       this.personel = personel;
       this.tablo = tablo;
       this.primaryId = primaryId;
       this.tarih = tarih;
       this.aciklama = aciklama;
       this.isAktif = isAktif;
    }
   
    public Integer getLogId() {
        return this.logId;
    }
    
    public void setLogId(Integer logId) {
        this.logId = logId;
    }
    public Cihaz getCihaz() {
        return this.cihaz;
    }
    
    public void setCihaz(Cihaz cihaz) {
        this.cihaz = cihaz;
    }
    public LogDurum getLogDurum() {
        return this.logDurum;
    }
    
    public void setLogDurum(LogDurum logDurum) {
        this.logDurum = logDurum;
    }
    public Personel getPersonel() {
        return this.personel;
    }
    
    public void setPersonel(Personel personel) {
        this.personel = personel;
    }
    public Tablo getTablo() {
        return this.tablo;
    }
    
    public void setTablo(Tablo tablo) {
        this.tablo = tablo;
    }
    public int getPrimaryId() {
        return this.primaryId;
    }
    
    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }
    public Date getTarih() {
        return this.tarih;
    }
    
    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
    public String getAciklama() {
        return this.aciklama;
    }
    
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
    public boolean isIsAktif() {
        return this.isAktif;
    }
    
    public void setIsAktif(boolean isAktif) {
        this.isAktif = isAktif;
    }




}


