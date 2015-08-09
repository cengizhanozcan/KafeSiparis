/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import helper.YetkiHelper;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Personel;
import model.Yetki;

/**
 *
 * @author cengizhan
 */
@Path("/WSYetki")
public class WSYetki implements Serializable{

    private YetkiHelper yetkiHelper;

    public WSYetki() {
        yetkiHelper = new YetkiHelper();
    }

    @Path("/Listele")
    @Produces(MediaType.APPLICATION_JSON)
    @POST()
    public List<Yetki> listYetki() {

        return yetkiHelper.list();
    }

}
