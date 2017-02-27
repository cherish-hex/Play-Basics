package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import exception.BasicApplicationException;
import models.User;
import security.Secured;
import services.UserService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by cherish.sham on 21/2/2017.
 */

@Security.Authenticated(Secured.class)
public class MainController extends Controller  {

    private final UserService service;
    private final FormFactory formFactory;

    @Inject
    public MainController(UserService service, FormFactory formFactory) {
        this.service = service;
        this.formFactory = formFactory;
    }

    public Result getUsers(){

        List<User> userData = service.getUserDetails();

        return ok(Json.toJson(userData));

    }

    public Result getUser(String name){
        User u = service.getUser(name);
        return ok(Json.toJson(u));
    }

    public Result addUser(){

        User u;
        JsonNode userData = request().body().asJson();

        Form<User> userForm = formFactory.form(User.class).bind(userData);
        if(userForm.hasErrors())
            //return badRequest(userForm.errorsAsJson());
        throw new BasicApplicationException(userForm.errorsAsJson().toString(),"400");
        else
             u = Json.fromJson(userData,User.class);

        String result = service.AddUser(u);
        if(result.equals("success"))
            return ok("User Added!");
        else
            return badRequest();
    }

    public Result updateUser(){
        User u = Json.fromJson(request().body().asJson(),User.class);
        String result = service.UpdateUser(u);
        if (result.equals("success"))
            return ok(Json.toJson(u));
        else
            return badRequest();
    }

    public Result deleteUser(Long id){
        String result = service.deleteUser(id);
        switch (result) {
            case "success": return ok("User Deleted");
            case "failure": return badRequest("User not found");
            default : return badRequest();
        }
    }
}
