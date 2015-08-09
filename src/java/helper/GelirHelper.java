/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Gelir;
import model.Siparis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class GelirHelper implements Serializable{
    
    SessionFactory sessionFactory;
    
    public GelirHelper(){
        sessionFactory = MyHibernateUtil.getSessionFactory();
        
    }
    
    public void insert(Gelir gelir) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        try {
            
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(gelir);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        finally{
            session.close();
        }
    }
    
    public void update(Gelir gelir) throws Exception{
    
        Session session = null;
        Transaction tx = null;
        try {
            
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(gelir);
            tx.rollback();
        } catch (Exception e) {
            if (tx!= null) {
                tx.rollback();
            }
        }
        finally{
            session.close();
        }
    }
    
    
    // Gelir Raporları icin
    public List<Siparis> listGelirRapor(Date startDate, Date endDate) throws Exception{
    
        List<Siparis> list = new ArrayList<>();
        Session session = null;
         
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Siparis.class, "siparis")
                    .createAlias("siparis.personel", "personel")
                    .createAlias("personel.yetki", "yetki")
                    .createAlias("siparis.masa", "masa")
                    .createAlias("masa.gelir", "gelir")
                    .createAlias("siparis.stok","stok")
                    .createAlias("stok.urun", "urun") 
                    .createAlias("stok.firma", "firma") 
                    .createAlias("urun.urunTur", "urunTuru")
                    .add(Restrictions.between("siparis.satisTarihi", startDate, endDate))
                    .add(Restrictions.ne("siparis.isAktif", false))
                    .add(Restrictions.ne("personel.isAktif", false))
                    .add(Restrictions.ne("masa.isAktif", false))
                    .add(Restrictions.ne("gelir.isAktif", false))
                    .add(Restrictions.eq("siparis.isOdendi", true))
                    .add(Restrictions.eq("masa.isOdendi", true))
                    .add(Restrictions.ne("siparis.isIptal", true))
                    .addOrder(Order.desc("siparis.satisTarihi"))
                    .list();
            
            
        } catch (Exception e) {
            
            throw e;
        }
        finally{
            session.close();
        }
        
        return list;
    }
    
    
}
