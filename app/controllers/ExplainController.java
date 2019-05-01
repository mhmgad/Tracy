package controllers;

import client.explain.ExplanationExtractorClient;
import com.google.inject.Inject;
import extendedsldnf.datastructure.IQueryExplanations;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import basics.FactComponent;
import web.data.Paraphrase;
import java.util.List;
import com.google.gson.Gson;

import views.html.*;
import web.data.Query;

import java.io.IOException;

import Models.ExampleQueries;

import play.routing.JavaScriptReverseRouter;
import play.mvc.Controller;
import play.mvc.Result;
import dictionaries.elastic.Entities;

import play.libs.Json;
import play.libs.Json.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class ExplainController extends Controller {



    private final FormFactory formFactory;
    private final ExplanationExtractorClient client;

    private final ExampleQueries exampleQueries;

    private final Entities entitiesDict;

    @Inject
    public ExplainController(final FormFactory formFactory,  ExplanationExtractorClient client, ExampleQueries exampleQueries,  Entities entitiesDict) {
        this.formFactory = formFactory;
        this.client=client;
        this.exampleQueries=exampleQueries;
        this.entitiesDict=entitiesDict;


    }

//        @Inject
//    public ExplainController(final FormFactory formFactory ) {
//        this.formFactory = formFactory;
//        this.client=ExplanationExtractorClient.getInstance();
//
//    }

    public Result javascriptRoutes() {
        return ok(
                JavaScriptReverseRouter.create("jsRoutes",
                        routes.javascript.ExplainController.getEntities(),
                        routes.javascript.ExplainController.getPredicates()
                )
        ).as("text/javascript");
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

//        Query exampleQ = new Query("<Albert_Einstein>", "<wasBornIn>", "<Germany>",  "wasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).\nwasBornIn(?x,?y):-  birthPlace(?x,?z), city_in(?z,?y).\n wasBornIn(?x,?y):-  wasBornIn(?x,?z), isA(?z,'city'), isLocatedIn(?z,?y), ?z!=?y.");
//        Query exampleQ = new Query("Albert_Einstein", "wasBornIn", "Germany",  "wasBornIn(?x,?y):- isA(?y,'city').\nwasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).");

        Query exampleQ = new Query("", "", "",  "");
//        Query exampleQ=exampleQueries.get(1);

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
        System.out.println(qf.rawData());

        Query q=qf.get();

        boolean yago=qf.rawData().getOrDefault("yago", "off").equals("on")? true:false;
        boolean wikidata=qf.rawData().getOrDefault("wikidata", "off").equals("on")? true:false;

        boolean wikipedia=qf.rawData().getOrDefault("wikipedia", "off").equals("on")? true:false;
        boolean web=qf.rawData().getOrDefault("web", "off").equals("on")? true:false;

        double wikipediaTrust=Double.parseDouble(qf.rawData().get("Wikipedia_weight"));
        double webTrust=Double.parseDouble(qf.rawData().get("Wikipedia_weight"));





        System.out.println( "Options{" +
                "yago=" + yago +
                ", wikidata=" + wikidata +
                ", wikipedia=" + wikipedia +
                ", web=" + web +
                ", wikipediaTrust=" + wikipediaTrust +
                ", webTrust=" + webTrust +
                '}');


//        System.out.println("yago "+yago);
//        System.out.println(qf.requestData());
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

    public Result getPredicates(){
        String predicatesString="[{\"value\":\"actedIn\"}" +
                "," +
                "{\"value\":\"byTransport\"}," +
                "{\"value\":\"created\"}," +
                "{\"value\":\"dealsWith\"}," +
                "{\"value\":\"diedIn\"}," +
                "{\"value\":\"directed\"}," +
                "{\"value\":\"edited\"}," +
                "{\"value\":\"graduatedFrom\"}," +
                "{\"value\":\"happenedIn\"}," +
                "{\"value\":\"hasAcademicAdvisor\"}," +
                "{\"value\":\"hasCapital\"}," +
                "{\"value\":\"hasChild\"}," +
                "{\"value\":\"hasCurrency\"}," +
                "{\"value\":\"hasDuration\"}," +
                "{\"value\":\"hasGender\"}," +
                "{\"value\":\"hasMusicalRole\"}," +
                "{\"value\":\"hasOfficialLanguage\"}," +
                "{\"value\":\"hasWonPrize\"}," +
                "{\"value\":\"influences\"}," +
                "{\"value\":\"isAffiliatedTo\"}," +
                "{\"value\":\"isCitizenOf\"}," +
                "{\"value\":\"isConnectedTo\"}," +
                "{\"value\":\"isInterestedIn\"}," +
                "{\"value\":\"isKnownFor\"}," +
                "{\"value\":\"isLeaderOf\"}," +
                "{\"value\":\"isLocatedIn\"}," +
                "{\"value\":\"isMarriedTo\"}," +
                "{\"value\":\"isPoliticianOf\"}," +
                "{\"value\":\"livesIn\"}," +
                "{\"value\":\"owns\"}," +
                "{\"value\":\"participatedIn\"}," +
                "{\"value\":\"playsFor\"}," +
                "{\"value\":\"wasBornIn\"}," +
                "{\"value\":\"worksAt\"}," +
                "{\"value\":\"wroteMusicFor\"}]";

       return  ok(predicatesString);
    }

//    public Result searchEntities(String str){
//        String query ="{ \"query\": { \"bool\":{ \"must\": [ {\"match\": { \"mention\": \""+str+"\"}} ], \"should\":[ {\"match\": { \"id\": \""+FactComponent.forYagoEntity(str)+"\"}} ] } } }";
//
//

//    }
public Result getEntities(String query){
    System.out.println(query);

    List<Paraphrase> entitiesPara=entitiesDict.suggestEntities(query);
//    System.out.println(entitiesPara);

    Gson gson=new Gson();



    return  ok(gson.toJson(entitiesPara));
}



}
