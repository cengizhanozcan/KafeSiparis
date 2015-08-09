/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;
 
import static controls.Time.getTimeOfToday;
import helper.LogHelper;
import java.io.Serializable;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Cihaz;
import model.Log;
import model.LogDurum;
import model.Personel;
import model.Tablo;

/**
 *
 * @author cengizhan
 */
public class LogInsert implements Serializable{
 
    public static void insert(int pTablo, Personel pPersonel, int pLogDurum, int pPrimaryKey) {
        LogHelper logHelper = new LogHelper();
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        
        Cihaz cihaz = new Cihaz();
        cihaz.setCihazId(1);
        
        LogDurum logDurum = new LogDurum();
        logDurum.setLogDurumId(pLogDurum);
        
        Tablo tablo = new Tablo();
        tablo.setTabloId(pTablo);
        
        Log log = new Log(cihaz, logDurum, pPersonel, tablo, pPrimaryKey, Time.getTimeOfToday(), " ", true);
        
        
        try {
            
            logHelper.insertLog(log);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }

    public static void error(int pTablo, Personel pPersonel, String pAciklama) {

        LogHelper logHelper = new LogHelper();
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        //-Xms1024m -Xmx10246m -XX:NewSize=256m -XX:MaxNewSize=356m -XX:PermSize=256m -XX:MaxPermSize=356m
        
        Cihaz cihaz = new Cihaz();
        cihaz.setCihazId(1);
        
        LogDurum logDurum = new LogDurum();
        logDurum.setLogDurumId(5);
        
        Tablo tablo = new Tablo();
        tablo.setTabloId(pTablo);
        
        Log log = new Log(cihaz, logDurum, pPersonel, tablo, 0, Time.getTimeOfToday(), pAciklama, true);
   
        try {
            
            logHelper.insertLog(log);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
         
}
