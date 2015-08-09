/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import helper.LogDurumHelper;
import helper.LogHelper;
import java.io.Serializable;
import java.util.List;
import model.Log;
import model.LogDurum;

/**
 *
 * @author cengizhan
 */
public class WSLog implements Serializable{
    
    LogHelper logHelper;
    LogDurumHelper logDurumHelper;
    
    public WSLog(){
    
        logHelper = new LogHelper();
        logDurumHelper = new LogDurumHelper();
    }
    
    public List<LogDurum> listLogDurum(){
    
        try {
            
            return logDurumHelper.listLogDurum();
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public List<Log> listLog(){
    
        try {
            
            return logHelper.listLog();
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public List<Log> listLog(LogDurum logDurum){
    
        try {
            
            return logHelper.listLog(logDurum);
        } catch (Exception e) {
        }
        
        return null;
    }
    
}
