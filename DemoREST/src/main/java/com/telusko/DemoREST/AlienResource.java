package com.telusko.DemoREST;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Alien> getAliens() {
		System.out.println("getAlien callled... ");
		return repo.getAliens();
	}

	@GET
	@Path("alien/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}

	@POST
	@Path("alien")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien createAlien(Alien newAlien) {
		System.out.println(newAlien);
		repo.create(newAlien);
		return newAlien;
	}

	@PUT
	@Path("alien")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien updateAlien(Alien updatedAlien) {
		System.out.println(updatedAlien);
		if (repo.getAlien(updatedAlien.getId()).getId()==0) {
			repo.create(updatedAlien);
		} else
			repo.update(updatedAlien);
		return updatedAlien;
	}

	@DELETE
	@Path("alien/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien deleteAlien(@PathParam("id") int id) {
		
		Alien removableAlien = repo.getAlien(id);
		if(removableAlien.getId()!=0)
			repo.delete(id);
		
		return removableAlien;
	}

}
