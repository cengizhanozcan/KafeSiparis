/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Yetki;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class YetkiHelper implements Serializable{

    SessionFactory sessionFactory = null;

    public YetkiHelper() {

        this.sessionFactory = MyHibernateUtil.getSessionFactory();
    }

    public void update(Yetki yetki) throws Exception {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(yetki);
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

    public List<Yetki> list() {

        Session session = null;
        List<Yetki> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();

            list = session.createCriteria(Yetki.class, "yetki")
                    .add(Restrictions.ne("yetki.isAktif", false))
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
