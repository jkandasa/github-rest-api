package org.jkandasa.restclient.github.jaxrs.handlers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jkandasa.restclient.github.model.Repository;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RepositoryHandler {
    @POST
    @Path("user/repos")
    Response createUserRepository(Repository repository);

    @POST
    @Path("orgs/{organization}/repos")
    Response createOrgRepository(@PathParam("organization") String organization, Repository repository);

    @DELETE
    @Path("repos/{owner}/{repository}")
    Response delete(@PathParam("owner") String owner, @PathParam("repository") String repository);

    @GET
    @Path("repos/{owner}/{repository}")
    Response get(@PathParam("owner") String owner, @PathParam("repository") String repository);

    @GET
    @Path("user/repos")
    Response listSelfRepo();

    @GET
    @Path("/orgs/{organization}/repos")
    Response listOrganizationRepo(@PathParam("organization") String organization);

    @GET
    @Path("/users/{username}/repos")
    Response listUserRepo(@PathParam("username") String username);

}
