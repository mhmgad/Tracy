package controllers;

import client.explain.ExplanationExtractorClient;
import com.google.inject.Inject;
import extendedsldnf.datastructure.IQueryExplanations;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import basics.FactComponent;

import views.html.*;
import web.data.Query;

import java.io.IOException;

import Models.ExampleQueries;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class ExplainController extends Controller {



    private final FormFactory formFactory;
    private final ExplanationExtractorClient client;

    private final ExampleQueries exampleQueries;



    @Inject
    public ExplainController(final FormFactory formFactory,  ExplanationExtractorClient client, ExampleQueries exampleQueries) {
        this.formFactory = formFactory;
        this.client=client;
        this.exampleQueries=exampleQueries;


    }

//        @Inject
//    public ExplainController(final FormFactory formFactory ) {
//        this.formFactory = formFactory;
//        this.client=ExplanationExtractorClient.getInstance();
//
//    }


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

//        Query exampleQ = new Query("<Albert_Einstein>", "<wasBornIn>", "<Germany>",  "wasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).\nwasBornIn(?x,?y):-  birthPlace(?x,?z), city_in(?z,?y).\n wasBornIn(?x,?y):-  wasBornIn(?x,?z), isA(?z,'city'), isLocatedIn(?z,?y), ?z!=?y.");
//        Query exampleQ = new Query("Albert_Einstein", "wasBornIn", "Germany",  "wasBornIn(?x,?y):- isA(?y,'city').\nwasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).");

        Query exampleQ=exampleQueries.get(1);

        return ok(index.render(exampleQ,exampleQueries ,null));
    }

    public Result example(int id) {

//        Query exampleQ = new Query("<Albert_Einstein>", "<wasBornIn>", "<Germany>",  "wasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).\nwasBornIn(?x,?y):-  birthPlace(?x,?z), city_in(?z,?y).\n wasBornIn(?x,?y):-  wasBornIn(?x,?z), isA(?z,'city'), isLocatedIn(?z,?y), ?z!=?y.");
//        Query exampleQ = new Query("Albert_Einstein", "wasBornIn", "Germany",  "wasBornIn(?x,?y):- isA(?y,'city').\nwasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).");

        Query exampleQ=exampleQueries.get(id);

        return ok(index.render(exampleQ,exampleQueries ,null));
    }


    public Result explain() {
        Form<Query> qf=formFactory.form(Query.class).bindFromRequest();
        Query q=qf.get();
        q.setSubject(FactComponent.forUri(q.getSubject()));
        q.setObject(FactComponent.forUri(q.getObject()));
        System.out.println("explain "+q);
//        Query exampleQ = new Query("<Albert_Einstein>", "<wasBornIn>", "<Germany>", "wasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).\nwasBornIn(?x,?y):-  birthPlace(?x,?z), city_in(?z,?y).");

        IQueryExplanations queryExplanations= null;
        try {
            queryExplanations = client.getExplanations(q);
        } catch (IOException e) {
            return internalServerError("Error connecting to server!");
        }

        return ok(index.render(q,exampleQueries,queryExplanations));
    }



}
