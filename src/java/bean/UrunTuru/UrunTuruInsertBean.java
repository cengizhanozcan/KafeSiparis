/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.UrunTuru;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.UrunTur;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ws.WSUrunTuru;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "urunTuruInsertBean")
@ViewScoped
public class UrunTuruInsertBean implements Serializable {

    private UrunTur urunTuru;
    private UrunTur deleteUrunTuru;
    private List<UrunTur> listUrunTuru;
    private List<UrunTur> listUrunTuruUstKategori;

    private WSUrunTuru urunTuruWS;

    private TreeNode treeNode;
    private int ustUrunTuruId;
    private boolean isAlt = false;

    public UrunTuruInsertBean() {

        urunTuru = new UrunTur();
        deleteUrunTuru = new UrunTur();
        urunTuruWS = new WSUrunTuru();

        listele();
    }

    public String insertUrunTuru() {

        if (!isAlt) {
            ustUrunTuruId = 0;
        }
        urunTuru.setIsAktif(true);
        urunTuru.setIsAlt(isAlt);
        urunTuru.setUstTur(ustUrunTuruId);
        urunTuruWS.insert(urunTuru);

        listele();

        return "Ekle?faces-redirect=true";
    }

    public void listele() {
        listUrunTuruUstKategori = new ArrayList<>();
        listUrunTuru = new ArrayList<>();

        listUrunTuru = urunTuruWS.listele();

        for (UrunTur urunTurUst : listUrunTuru) {

            if (!urunTurUst.getIsAlt()) {
                listUrunTuruUstKategori.add(urunTurUst);
            }
//            createTree(treeNode, urunTurUst);
        }
    }

//    //Agaç lı bir yapıda gösterebilmek için ağaç oluşturulcak.
//    public void createTree(TreeNode node, UrunTur pUrunTuru) {
//
//        if (node == null) {
//            node = new DefaultTreeNode();
//            treeNode = node;
//        } else { 
//            TreeNode tempNode = new DefaultTreeNode(pUrunTuru, node);
//        }
//    }

    public String delete() {
        listUrunTuru.remove(deleteUrunTuru);
        deleteUrunTuru.setIsAktif(false);

        urunTuruWS.update(deleteUrunTuru);

        return "Ekle?faces-redirect=true";
    }

    /**
     * @return the urunTuru
     */
    public UrunTur getUrunTuru() {
        return urunTuru;
    }

    /**
     * @param urunTuru the urunTuru to set
     */
    public void setUrunTuru(UrunTur urunTuru) {
        this.urunTuru = urunTuru;
    }

    /**
     * @return the listUrunTuru
     */
    public List<UrunTur> getListUrunTuru() {
        return listUrunTuru;
    }

    /**
     * @param listUrunTuru the listUrunTuru to set
     */
    public void setListUrunTuru(List<UrunTur> listUrunTuru) {
        this.listUrunTuru = listUrunTuru;
    }

    /**
     * @return the deleteUrunTuru
     */
    public UrunTur getDeleteUrunTuru() {
        return deleteUrunTuru;
    }

    /**
     * @param deleteUrunTuru the deleteUrunTuru to set
     */
    public void setDeleteUrunTuru(UrunTur deleteUrunTuru) {
        this.deleteUrunTuru = deleteUrunTuru;
    }

    /**
     * @return the isAlt
     */
    public boolean isIsAlt() {
        return isAlt;
    }

    /**
     * @param isAlt the isAlt to set
     */
    public void setIsAlt(boolean isAlt) {
        this.isAlt = isAlt;
    }

    /**
     * @return the listUrunTuruUstKategori
     */
    public List<UrunTur> getListUrunTuruUstKategori() {
        return listUrunTuruUstKategori;
    }

    /**
     * @param listUrunTuruUstKategori the listUrunTuruUstKategori to set
     */
    public void setListUrunTuruUstKategori(List<UrunTur> listUrunTuruUstKategori) {
        this.listUrunTuruUstKategori = listUrunTuruUstKategori;
    }

    /**
     * @return the urunTuruId
     */
    public int getUstUrunTuruId() {
        return ustUrunTuruId;
    }

    /**
     * @param urunTuruId the urunTuruId to set
     */
    public void setUstUrunTuruId(int ustUrunTuruId) {
        this.ustUrunTuruId = ustUrunTuruId;
    }

    /**
     * @return the treeNode
     */
    public TreeNode getTreeNode() {
        return treeNode;
    }

    /**
     * @param treeNode the treeNode to set
     */
    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

}
