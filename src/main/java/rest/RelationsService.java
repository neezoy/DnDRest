package rest;

import dataTypes.IItemDTO;
import database.DAO;
import database.IDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("relation")
public class RelationsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("characteritems/{id}")
    public ArrayList<IItemDTO> getCharacterItems(@PathParam("id") int id) throws SQLException {

        IDAO dao = new DAO();

        //get item id's
        ArrayList a = dao.getItemIDs(id);

        //get items in list

        ArrayList<IItemDTO> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++){
            int c = (int) a.get(i);
            b.add(dao.getItem(c));
        }


        return b ;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("usercharacters/{id}")
    public ArrayList<IItemDTO> getUserCharacters(@PathParam("id") int id) throws SQLException {

        IDAO dao = new DAO();

        //get item id's
        ArrayList a = dao.getItemIDs(id);

        //get items in list

        ArrayList<IItemDTO> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++){
            int c = (int) a.get(i);
            b.add(dao.getItem(c));
        }


        return b ;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("charactergroups/{id}")
    public ArrayList<IItemDTO> getCharacterGroups(@PathParam("id") int id) throws SQLException {

        IDAO dao = new DAO();

        //get item id's
        ArrayList a = dao.getItemIDs(id);

        //get items in list

        ArrayList<IItemDTO> b = new ArrayList<>();
        for (int i = 0; i < a.size(); i++){
            int c = (int) a.get(i);
            b.add(dao.getItem(c));
        }


        return b ;

    }





}
