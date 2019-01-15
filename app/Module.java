import client.explain.ExplanationExtractorClient;
import com.google.inject.AbstractModule;
import play.Environment;
import play.api.Play;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Models.ExampleQueries;
import dictionaries.elastic.Entities;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    String propFileName="demo.properties";

    @Override
    public void configure() {


        InputStream inputStream = Environment.simple().resourceAsStream(propFileName);

        Properties prop=new Properties();

        try {
        if (inputStream != null) {

                prop.load(inputStream);

        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }


    bind(ExplanationExtractorClient.class).toInstance(ExplanationExtractorClient.getInstance(prop.getProperty("server_url")));
        bind(Entities.class).toInstance(Entities.getInstance(prop.getProperty("autocomplete_url")));
        bind(ExampleQueries.class).toInstance(ExampleQueries.getInstance());

    }

}
