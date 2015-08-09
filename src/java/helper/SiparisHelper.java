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
import model.Masa;
import model.Personel;
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
public class SiparisHelper implements Serializable{

    private SessionFactory sessionFactory;

    public SiparisHelper() {

        sessionFactory = MyHibernateUtil.getSessionFactory();
    }

    public void insert(Siparis siparis) throws Exception {

        Session session = null;
        Transaction tx = null;

        try {

            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(siparis);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Siparis siparis) throws Exception {

        Session session = null;
        Transaction tx = null;

        try {

            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(siparis);
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        finally{
            session.close();
        }
    }
    
    public List<Siparis> listele(int masaNoId) throws Exception{
    
        Session session = null;
        List<Siparis> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Siparis.class,"siparis")
                    .createAlias("siparis.masa", "masa")
                    .createAlias("siparis.stok", "stok") 
                    .createAlias("stok.urun", "urun")
                    .createAlias("masa.masaNo","masaNo")
                    .add(Restrictions.eq("masaNo.masaNoId", masaNoId))
                    .add(Restrictions.eq("masa.isOdendi", false))
                    .add(Restrictions.ne("masa.isAktif", false))
                    .add(Restrictions.eq("siparis.isIptal",false))
                    .add(Restrictions.ne("siparis.isOdendi", true))
                    .add(Restrictions.ne("siparis.isAktif", false))
                    .list();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            session.close();
        }
        return list; 
    }
    
    public Masa checkMasa(int masaNoId) throws Exception{
    
        List<Siparis> list = new ArrayList<>();
        Session session = null; 
    
        try {  
            session = sessionFactory.openSession();
            list =  session.createCriteria(Siparis.class,"siparis")
                    .createAlias("siparis.masa", "masa")
                    .createAlias("masa.masaNo", "masaNo")
                    .add(Restrictions.eq("masaNo.masaNoId", masaNoId))
                    .add(Restrictions.eq("siparis.isOdendi", false))
                    .add(Restrictions.eq("siparis.isIptal", false))
                    .add(Restrictions.ne("siparis.isAktif",false))
                    .add(Restrictions.ne("masa.isAktif",false))
                    .list();
            
        } catch (Exception e) {
            throw e;
        }
        finally{
            session.close();
        }
        
         if (list.size()!= 0) {
                return list.get(0).getMasa();
            }
        return null;
    }

    public List<Siparis> listReport(Date pStartDate, Date pEndDate ) throws Exception{    //Satilan ürünlerin raporları getiriliyor.
    
    
        Session session = null;
        List<Siparis> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Siparis.class,"siparis")
                    .createAlias("siparis.masa", "masa")
                    .createAlias("siparis.stok", "stok")
                    .createAlias("stok.urun", "urun")
                    .add(Restrictions.ne("stok.isAktif", false))
                    .add(Restrictions.ne("siparis.isAktif", false))
                    .add(Restrictions.ne("masa.isAktif", false)) 
                    .add(Restrictions.eq("masa.isOdendi", true))
                    .add(Restrictions.eq("siparis.isOdendi", true))
                    .add(Restrictions.between("siparis.satisTarihi", pStartDate, pEndDate))
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
    
    
    // Personel Raporları icin
    public List<Siparis> listPersonelRapor(Personel personel, Date startDate, Date endDate) throws Exception{
    
        List<Siparis> list = new ArrayList<>();
        Session session = null;
        
        System.out.println("Helper: "+   personel.getPersonelId());
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Siparis.class, "siparis")
                    .createAlias("siparis.personel", "personel")
                    .createAlias("personel.yetki", "yetki")
                    .createAlias("siparis.masa", "masa")
                    .createAlias("siparis.stok","stok")
                    .createAlias("stok.urun", "urun")
                    .add(Restrictions.eq("personel.personelId", personel.getPersonelId()))
                    .add(Restrictions.between("siparis.satisTarihi", startDate, endDate))
                    .add(Restrictions.ne("siparis.isAktif", false))
                    .add(Restrictions.ne("personel.isAktif", false))
                    .add(Restrictions.ne("masa.isAktif", false))
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
