package hu.ppke.itk.pogma.newsrecommender.todelete;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.hu.HungarianAnalyzer;
import org.apache.lucene.analysis.hunspell.HunspellDictionary;
import org.apache.lucene.analysis.hunspell.HunspellStemmer;
import org.apache.lucene.analysis.hunspell.HunspellStemmer.Stem;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.mahout.classifier.AbstractVectorClassifier;
import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.classifier.naivebayes.training.TrainNaiveBayesJob;







public class TesztMain {

	
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	   
			try {
				InputStream affixStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("hu_HU/hu_HU.aff");
	    	    InputStream dictStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("hu_HU/hu_HU.dic");
    	    
	    	    HunspellDictionary dictionary;
				dictionary = new HunspellDictionary(affixStream, dictStream, Version.LUCENE_36);
				HunspellStemmer stemmer = new HunspellStemmer(dictionary);
				
				
				List<Stem> stems;
		    	
				
		        

	            //List<String> result = new ArrayList<String>();
				StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36, HungarianAnalyzer.getDefaultStopSet());
	                    
				
	            
				TokenStream tokenStream  = analyzer.tokenStream("asd", new StringReader("A amikor; már	 nagy kocsik, környezetet, babakocsikat. pénzért: nagypapám"));

				tokenStream = new LowerCaseFilter(Version.LUCENE_36, tokenStream);
				
				//OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
				CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
				List<String> words = new ArrayList<String>();
				Stem stem;
				while (tokenStream.incrementToken()) {
				   
					
				    String term = charTermAttribute.toString();
				    System.out.println(term);
				    stems = stemmer.stem(term);
				    stem = stems.get(0);
				    
				    if(stem!=null)
				    {
				    	words.add(new String(stem.getStem()));
				    }
				    else
				    {
				    	words.add(term);
				    }
				    System.out.println(words.toString());
				    
			    	
				}
				
				
		        	    	    
	    	    affixStream.close();
	    	    dictStream.close();
	    	    analyzer.close();
	    	    tokenStream.close();
	    	    


	    	    
				
	    	    
	    	    
	    	    
	    	    
	    	    
	    	    
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	    
			
			
			
			
			
        
    }
}





