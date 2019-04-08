package com.core;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service
public class Pipeline {

	
	 //declaration des attributs
    private static Properties properties;
    private static String propertiesName = "tokenize, ssplit, pos, lemma, ner, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;
    
    //Constructeur 
    private Pipeline() { }
    
    static {
    	properties = new Properties();
    	properties.setProperty("annotators", propertiesName);
    }
    
    //Methode pour retourner un objet StanfordCoreNLP
    
    @Bean(name = "stanfordCoreNLP")
    public static StanfordCoreNLP getPipeline() {
    	//singleton pour assuré une seulle instance
    	if(stanfordCoreNLP == null) {
    		stanfordCoreNLP = new StanfordCoreNLP(properties);
   
    	}
    	return stanfordCoreNLP;
    }



}