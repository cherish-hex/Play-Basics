package security;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import controllers.routes;

/**
 * Created by cherish.sham on 23/2/2017.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("name");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        //return redirect(routes.LoginController.authenticate());
        return badRequest("Unauthorised. Please login!");
    }
}
