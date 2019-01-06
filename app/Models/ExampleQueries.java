package Models;

import web.data.Query;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;


@Singleton
public class ExampleQueries{


    List<Query> examples=new ArrayList<>();

    private static ExampleQueries eq=new ExampleQueries();

    ExampleQueries(){
        examples.add(new Query(1,"Einstein 1","<Albert_Einstein>", "<wasBornIn>", "<Germany>",  "wasBornIn(?x,?y):- birthPlace(?x,?z), in(?z,?y).\nwasBornIn(?x,?y):-  birthPlace(?x,?z), city_in(?z,?y).\n wasBornIn(?x,?y):-  wasBornIn(?x,?z), isA(?z,'city'), isLocatedIn(?z,?y), ?z!=?y."));

        examples.add(new Query(2,"Einstein 2","Albert_Einstein", "wasBornIn", "Germany",  "wasBornIn(?x,?y):- wasBornIn(?x,?z), in(?z,?y).\nin(?x,?y):-  isLocatedIn(?x,?z),  isLocatedIn(?z,?y)."));
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