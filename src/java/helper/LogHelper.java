/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Log;
import model.LogDurum;
import model.Tablo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class LogHelper implements Serializable{
    
    SessionFactory sessionFactory;
    
    public LogHelper(){
    
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public List<Tablo> listTablo() throws Exception{
    
        Session session  = null;
        List<Tablo> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list  = session.createCriteria(Tablo.class, "tablo")
                    .add(Restrictions.ne("tablo.isAktif", false))
                    .list();
            
        } catch (Exception e) {
            
            throw  e;
        }
        finally{
            session.close();
        }
        return list;
    }
    
    public void insertLog(Log log) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        
        try {
            session =sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(log);
            tx.commit();
            
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            
            throw e;
        }
        finally{
            session.close();
        }
    }
    
    public List<Log> listLog() throws Exception{
    
        Session session = null;
        List<Log> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Log.class, "log")
                    .createAlias("log.tablo", "tablo")
                    .createAlias("log.logDurum", "logDurum")
                    .createAlias("log.cihaz", "cihaz")
                    .createAlias("log.personel","personel")
                    .add(Restrictions.ne("tablo.isAktif", false))
                    .add(Restrictions.ne("logDurum.isAktif", false))
                    .add(Restrictions.ne("cihaz.isAktif", false))
                    .add(Restrictions.ne("log.isAktif", false))
                    .addOrder(Order.desc("log.tarih"))
                    .list();
            
        } catch (Exception e) {
            
            throw e;
        }
        finally{
            session.close();
        }
        return list;
    }
    
     public List<Log> listLog(LogDurum logDurum) throws Exception{
    
        Session session = null;
        List<Log> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Log.class, "log")
                    .createAlias("log.tablo", "tablo")
                    .createAlias("log.logDurum", "logDurum")
                    .createAlias("log.cihaz", "cihaz")
                    .createAlias("log.personel","personel")
                    .add(Restrictions.eq("logDurum.logDurumId", logDurum.getLogDurumId()))
                    .add(Restrictions.ne("tablo.isAktif", false))
                    .add(Restrictions.ne("logDurum.isAktif", false))
                    .add(Restrictions.ne("cihaz.isAktif", false))
                    .add(Restrictions.ne("log.isAktif", false))
                    .addOrder(Order.desc("log.tarih"))
                    
                    .list();
            System.out.println("list : " + list.size() +" logDurum " +logDurum.getLogDurumId());
        } catch (Exception e) {
            
            throw e;
        }
        finally{
            session.close();
        }
        return list;
    }
    
}
