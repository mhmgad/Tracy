package Models;

import extendedsldnf.datastructure.TextualSource;
import web.data.Query;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Singleton
public class ExampleQueries{


    List<Query> examples=new ArrayList<>();

    private static ExampleQueries eq=new ExampleQueries();

    ExampleQueries(){
        examples.add(new Query(1,"Einstein 1", Arrays.asList("yago"),Arrays.asList(new TextualSource("ELASTIC",0.5)), "<Albert_Einstein>", "<wasBornIn>", "<Germany>",  "wasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).\nwasBornIn(?x,?y):-  birthPlace(?x,?z), city_in(?z,?y).\n wasBornIn(?x,?y):-  wasBornIn(?x,?z), isA(?z,'city'), isLocatedIn(?z,?y), ?z!=?y."));

        examples.add(new Query(2,"Einstein 2", Arrays.asList("yago"), Arrays.asList(new TextualSource("ELASTIC",0.5)), "Albert_Einstein", "wasBornIn", "Germany",  "wasBornIn(?x,?y):- wasBornIn(?x,?z), in(?z,?y).\nin(?x,?y):-  isLocatedIn(?x,?z),  isLocatedIn(?z,?y)."));

        examples.add(new Query(3,"Sadiq Khan",Arrays.asList("yago"), Arrays.asList(new TextualSource("ELASTIC",0.5)), "Sadiq_Khan", "isCitizenOf", "United_Kingdom",  "isCitizenOf(?x,?y):- hasCapital(?y,?z), mayorOf(?x,?z).\n" +
                "isCitizenOf(?x,?y):-  wasBornIn(?x,?z), isLocatedIn(?z,?y)."));

        examples.add(new Query(4,"Christopher. Nalon",Arrays.asList("yago"),Arrays.asList(new TextualSource("ELASTIC",0.5)), "Christopher_Nolan", "influencedBy", "George_Lucas", "influencedBy(?x,?y):- inspiredByCreation(?x,?y).\ninspiredByCreation(?x,?y):- created(?y,?z), influencedBy(?x,?z).\ninfluencedBy(?x,?y):- liked(?x,?y). \ninfluencedBy(?x,?y):- workedWith(?x,?y).\nworkedWith(?x,?y):- directed(?x,?z), directed(?y,?z)."));
        examples.add(new Query(5,"Marissa Mayer",Arrays.asList("yago"),Arrays.asList(new TextualSource("ELASTIC",0.5)), "Marissa_Mayer", "workedWith", "Larry_Page", "workedWith(?x,?y):- created(?y,?z), firstEmployee(?x,?z).\nworkedWith(?x,?y):- isAffiliatedTo(?x,?z), isAffiliatedTo(?y,?z)."));

    }


    public Query get(int i){
        return examples.get(i);

    }

    public static ExampleQueries getInstance(){
        return eq;

    }

    public List<Query> getList(){
        return examples;
    }

}