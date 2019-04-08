package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.Pipeline;
import com.model.Type;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@RestController
@RequestMapping(value = "/api/v1")
public class SentimentAnalysisController {

	private StanfordCoreNLP stanfordCoreNLP;
	
    @PostMapping
    @RequestMapping(value = "/sentiment")
	public List<String> getSentimentResultat(@RequestBody String input){
    	List<String> list = new ArrayList<>();
    	StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
  
    	CoreDocument coreDocument;
		coreDocument = new CoreDocument(input);
		stanfordCoreNLP.annotate(coreDocument);
		 List<CoreSentence> sentences = coreDocument.sentences();
        return collectList(sentences);
    }

    public List<String> collectList(List<CoreSentence> corelist){
		List<String> list = new ArrayList<>();
	  for(CoreSentence sentence : corelist) {

            String sentiment = sentence.sentiment();
            System.out.println(sentiment + "\t" + sentence);
            list.add(sentiment);
        }
	 
		/*for(int i=0; i<corelist.size(); i++) {
			/*if((corelist.get(i).get(CoreSentence.sentiment()).equalsIgnoreCase(type.getName()))) {
				list.add(corelist.get(i).sentiment());
			}
			
		}*/
		return list;
	}
}
