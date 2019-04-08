package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.Pipeline;
import com.model.Type;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@RestController
@RequestMapping(value = "/api/v1")
public class NerController {

	//@Autowired
	private StanfordCoreNLP stanfordCoreNLP;
	
	
	@PostMapping
	@RequestMapping(value = "/pos")
	public List<String> getPOS(@RequestBody String input){
	
		StanfordCoreNLP stanfordCoreNLP;
        stanfordCoreNLP = Pipeline.getPipeline();

        CoreDocument coreDocument = new CoreDocument(input);

        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        List<String> listPos = new ArrayList<>();
        for(CoreLabel coreLabel : coreLabelList) {

            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            System.out.println(coreLabel.originalText() + " = "+ pos);
            listPos.add(coreLabel.originalText() + " = "+ pos);
        }
		return listPos;
	}
	
	
	@PostMapping
	@RequestMapping(value = "/ner")
	public List<String> getNerResultat(@RequestBody String input,@RequestParam Type type){
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		
		CoreDocument coreDocument;
		coreDocument = new CoreDocument(input);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
        return collectList(coreLabels, type);	
	}
	
	@PostMapping
	@RequestMapping(value = "/nerAll")
	public List<String> getNerResult2(@RequestBody String input) {
		List<String> list = new ArrayList<>();

		 StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();

	        CoreDocument coreDocument;
	        coreDocument = new CoreDocument(input);

	        stanfordCoreNLP.annotate(coreDocument);

	        List<CoreLabel> coreLabels = coreDocument.tokens();

	        for(CoreLabel coreLabel : coreLabels) {

	            String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
	            System.out.println(coreLabel.originalText() + " = "+ ner);
	            if( coreLabel.originalText().equals("Visa") && ner.equals("O")) {
		            list.add(coreLabel.originalText() + " = Visa");

	            } else {
		            list.add(coreLabel.originalText() + " = "+ ner);

	            }
	        }
	        
	        
		return list;
	}
	
	
	//Recuperer la list de type choisie
	public List<String> collectList(List<CoreLabel> corelist, Type type){
		List<String> list = new ArrayList<>();
		for(int i=0; i<corelist.size(); i++) {
			if((corelist.get(i).get(CoreAnnotations.NamedEntityTagAnnotation.class)).equalsIgnoreCase(type.getName())) {
				list.add(corelist.get(i).originalText());
			}
		}
		return list;
	}
}
