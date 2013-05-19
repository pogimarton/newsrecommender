package hu.ppke.itk.pogma.newsrecommender.todelete;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.mahout.classifier.naivebayes.test.TestNaiveBayesDriver;
import org.apache.mahout.utils.SplitInput;

public class Testalg {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		

		
		/*usage: <command> [Generic Options] [Job-Specific Options]
				Generic Options:
				 -archives <paths>              comma separated archives to be unarchived
				                                on the compute machines.
				 -conf <configuration file>     specify an application configuration file
				 -D <property=value>            use value for given property
				 -files <paths>                 comma separated files to be copied to the
				                                map reduce cluster
				 -fs <local|namenode:port>      specify a namenode
				 -jt <local|jobtracker:port>    specify a job tracker
				 -libjars <paths>               comma separated jar files to include in
				                                the classpath.
				 -tokenCacheFile <tokensFile>   name of the file with the tokens
				Job-Specific Options:                                                           
				  --input (-i) input              Path to job input directory.                  
				  --output (-o) output            The directory pathname for output.            
				  --overwrite (-ow)               If present, overwrite the output directory    
				                                  before running job                            
				  --overwrite (-ow)               If present, overwrite the output directory    
				                                  before running job                            
				  --model (-m) model              The path to the model built during training   
				  --testComplementary (-c)        test complementary?                           
				  --runSequential (-seq)          run sequential?                               
				  --labelIndex (-l) labelIndex    The path to the location of the label index   
				  --help (-h)                     Print out help                                
				  --tempDir tempDir               Intermediate output directory                 
				  --startPhase startPhase         First phase to run                            
				  --endPhase endPhase             Last phase to run                             
				Specify HDFS directories while running on hadoop; else specify local file       
				system directories      */
		
	    Configuration conf = new Configuration();
		File inputVecFile = new File("data/seq/tweets-vec");
		File trainVecFile = new File("data/seq/train");
		File testVecFile = new File("data/seq/test");
		File model = new File("data/model");
	    
	    
	    
	    TestNaiveBayesDriver test = new TestNaiveBayesDriver();
	    test.setConf(conf);
	    //mahout testnb 
	    //	-i train-vectors 
	    //	-m model 
	    //	-l labelindex 
	    //	-ow 
	    //	-o tweets-testing 
	    //	-c
	    test.run(new String[]{
	    	"-i", trainVecFile.getAbsolutePath(), 
	    	"-m", model.getAbsolutePath(),
	    	
	    });
	}

}
