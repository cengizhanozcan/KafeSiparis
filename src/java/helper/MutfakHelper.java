/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class MutfakHelper implements Serializable{
    
    SessionFactory sessionFactory;
    
    public MutfakHelper(){
        
        sessionFactory = MyHibernateUtil.getSessionFactory();
    }
    
    public List<Siparis> list() throws Exception{
    
        Session session = null;
        List<Siparis> list = new ArrayList<>();
        
        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Siparis.class,"siparis")
                    .createAlias("siparis.stok", "stok")
                    .createAlias("stok.urun", "urun")
                    .createAlias("urun.urunTur", "urunTur")
                    .createAlias("siparis.masa", "masa")
                    .createAlias("masa.masaNo", "masaNo")
                    .add(Restrictions.eq("siparis.isIptal", false))
                    .add(Restrictions.ne("siparis.isAktif", false))
                    .add(Restrictions.ne("stok.isAktif", false))
                    .add(Restrictions.eq("siparis.isHazir", false))
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
