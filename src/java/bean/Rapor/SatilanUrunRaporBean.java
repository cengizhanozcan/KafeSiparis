/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.Rapor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Siparis;
import ws.WSSatilanUrunRapor;

/**
 *
 * @author cengizhan
 */
@ManagedBean(name = "satilanUrunRaporBean")
@ViewScoped
public class SatilanUrunRaporBean implements Serializable {

    /**
     * Creates a new instance of SatilanUrunRaporBean
     */
    private WSSatilanUrunRapor wsSatilanUrunRapor;
    private Date startDate;
    private Date finishDate;
    private List<Siparis> listReport;

    public SatilanUrunRaporBean() {

        wsSatilanUrunRapor = new WSSatilanUrunRapor();
        listReport = new ArrayList<>();
    }

    public void listOfReport() {

        listReport = new ArrayList<>();
        List<Siparis> listReportTemp = new ArrayList<>();

        listReportTemp = wsSatilanUrunRapor.listReport(getStartDate(), getFinishDate());
        Set<String> listUrunId = new HashSet<String>();

        for (Siparis siparis : listReportTemp) {
            listUrunId.add(siparis.getStok().getUrun().getUrunId().toString());
        }

        for (Siparis siparis : listReportTemp) {
            for (String urunId : listUrunId) {
                if (siparis.getStok().getUrun().getUrunId() == Integer.parseInt(urunId)) {
                    for (int i = 0; i < listReport.size(); i++) {
                        if (listReport.get(i).getStok().getUrun().getUrunId()
                                == Integer.parseInt(urunId)) {
                            listReport.get(i).setSatilanAdet(listReport.get(i).getSatilanAdet() + siparis.getSatilanAdet());
                            break;
                        }
                    }

                    boolean isTrue = false;
                    for (Siparis tempSiparis : listReport) {
                        if (tempSiparis.getStok().getUrun().getUrunId() == Integer.parseInt(urunId)) {
                            isTrue = true;
                        }
                    }
                    if (!isTrue) {
                        listReport.add(siparis);
                    }

                }
            }
        }
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the finishDate
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * @param finishDate the finishDate to set
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * @return the listReport
     */
    public List<Siparis> getListReport() {
        return listReport;
    }

    /**
     * @param listReport the listReport to set
     */
    public void setListReport(List<Siparis> listReport) {
        this.listReport = listReport;
    }
}
