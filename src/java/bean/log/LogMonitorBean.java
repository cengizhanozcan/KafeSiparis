/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Log;
import model.LogDurum;
import ws.WSLog;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "logMonitorBean")
@ViewScoped
public class LogMonitorBean implements Serializable{

    private List<LogDurum> listLogDurum;
    private List<Log> listLog;
    private int logDurumId;
    
    WSLog logWS;
    
    public LogMonitorBean() {
        
        logWS = new WSLog();
        
        listeleLogDurum();
        listeleAllLog();
    }
    
    public void listeleLogDurum(){
    
        listLogDurum = new ArrayList<>();
        listLogDurum = logWS.listLogDurum();
    }
    
    public void listeleAllLog(){
        
        listLog = new ArrayList<>();
        listLog = logWS.listLog(); 
    }
    
    public void listeleLog(){
        System.out.println("Listele Log" + logDurumId);
        listLog = new ArrayList<>();
        
        LogDurum logDurum = new LogDurum();
        logDurum.setLogDurumId(logDurumId);
        
        listLog = logWS.listLog(logDurum);
        System.out.println("Listele Log" + listLog.size());
    }

    /**
     * @return the listLogDurum
     */
    public List<LogDurum> getListLogDurum() {
        return listLogDurum;
    }

    /**
     * @param listLogDurum the listLogDurum to set
     */
    public void setListLogDurum(List<LogDurum> listLogDurum) {
        this.listLogDurum = listLogDurum;
    }

    /**
     * @return the listLog
     */
    public List<Log> getListLog() {
        return listLog;
    }

    /**
     * @param listLog the listLog to set
     */
    public void setListLog(List<Log> listLog) {
        this.listLog = listLog;
    }

    /**
     * @return the logDurumId
     */
    public int getLogDurumId() {
        return logDurumId;
    }

    /**
     * @param logDurumId the logDurumId to set
     */
    public void setLogDurumId(int logDurumId) {
        this.logDurumId = logDurumId;
    }

    
    
}
