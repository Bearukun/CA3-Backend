/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Book;
import facades.BookFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author plaul1
 */
@Path("demoall")
public class All {

  BookFacade bf;
  Gson gson = new GsonBuilder().setPrettyPrinting().create();  
  
  @Context
  private UriInfo context;

  /**
   * Creates a new instance of A
   */
  public All() {
         bf = new BookFacade();
  }

  /**
   * Retrieves representation of an instance of rest.All
   * @return an instance of java.lang.String
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getText() {
    return " {\"message\" : \"result for all\"}";
  }
  
  @GET
  @Path("all")
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllBooks() {
      
      //Get all books from database
     
      List<Book> b1 = bf.readBooks();
      
      //Convert list of books to json
      //Will return json data
      return gson.toJson(b1);
      
      
  }

}
