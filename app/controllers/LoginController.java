package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import models.User;

import javax.inject.Inject;

/**
 * Created by cherish.sham on 23/2/2017.
 */


public class LoginController extends Controller{

    private final FormFactory formFactory;

    @Inject
    public LoginController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    public Result authenticate() {
        Form<LoginForm> loginForm = formFactory.form(LoginForm.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest("login Unsuccessful");
        } else {
            User u = User.find.where().eq("name",loginForm.get().getName()).findUnique();
            if(u == null)
                return badRequest("user not found");
            if(u.getPassword().equals(loginForm.get().getPassword())) {
                session().clear();
                session("name", loginForm.get().name);
                return ok("login Success");
            }
            else
                return badRequest("Invalid username / password");
        }
    }

    public Result logout() {
        String username = request().body().asJson().get("name").textValue();
        if(session("name").equals(username)) {
            session().clear();
            return ok("logout success");
        }
        else
            return badRequest("user does not exist");
    }
}
