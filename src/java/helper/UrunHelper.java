/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Stok;
import model.Urun;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cengizhan
 */
public class UrunHelper implements Serializable{

    private SessionFactory sessionFactory;

    public UrunHelper() {

        sessionFactory = MyHibernateUtil.getSessionFactory();
    }

    //Stok bilgileri de buradan getirilcek değişeecek suanlık deneme
    public List<Stok> listUrun() throws Exception {

        Session session = null;
        List<Stok> list = new ArrayList<>();

        try {
            session = sessionFactory.openSession();
            list = session.createCriteria(Stok.class, "stok")
                    .createAlias("stok.urun", "urun")
                    .createAlias("urun.urunTur", "urunTur")
                    .add(Restrictions.ne("urun.isAktif", false))
                    .add(Restrictions.ne("stok.isAktif", false))
                    .list();

        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public Urun findUrun(Urun urun) throws Exception {

        Session session = null;
        Urun getUrun = new Urun();
        try {

            session = sessionFactory.openSession();
            getUrun = (Urun) session.createCriteria(Urun.class, "urun")
                    .add(Restrictions.eq("urun.urunId", urun.getUrunId()))
                    .add(Restrictions.ne("urun.isAktif", false))
                    .uniqueResult();

        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return getUrun;
    }

    public Urun findUrun(int urunId) throws Exception {

        Session session = null;
        Urun getUrun = new Urun();
        try {

            session = sessionFactory.openSession();
            getUrun = (Urun) session.createCriteria(Urun.class, "urun")
                    .add(Restrictions.eq("urun.urunId", urunId))
                    .add(Restrictions.ne("urun.isAktif", false))
                    .uniqueResult();

        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return getUrun;
    }

    public void insert(Urun urun) throws Exception {

        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(urun);
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

    public void update(Urun urun) throws Exception {

        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(urun);
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
}
