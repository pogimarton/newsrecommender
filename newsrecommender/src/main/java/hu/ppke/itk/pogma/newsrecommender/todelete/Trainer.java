package hu.ppke.itk.pogma.newsrecommender.todelete;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.classifier.AbstractVectorClassifier;
import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.classifier.naivebayes.training.TrainNaiveBayesJob;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector.Element;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.utils.SplitInput;
import org.apache.mahout.utils.vectors.VectorDumper;
import org.apache.mahout.vectorizer.SparseVectorsFromSequenceFiles;
import org.apache.*;

public class Trainer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
	    Configuration conf = new Configuration();

	    
	    
	    //"--input", "data/seq/tweets-seq/chunk-0", 
	    //"--output", "nb_data/output" 
	    //"--tempDir", "recommender/tmp
	    
	    File inputSeqFile = new File("data/seq/tweets-seq");
	    File inputVecFile = new File("data/seq/tweets-vec");
	    File model = new File("data/model");
	    model.delete();
	    File tempDir = new File("data/tmp");	    
	    tempDir.delete();
	    File readable = new File("data/readable");
	    
	    
	    
	    
	    SparseVectorsFromSequenceFiles  svfs = new SparseVectorsFromSequenceFiles();
	    
	    svfs.setConf(conf);
	    svfs.run(new String[]{"-i",inputSeqFile.getAbsolutePath() , "-o", inputVecFile.getAbsolutePath() });
	    
	    
	    
	    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+inputVecFile.getAbsolutePath()+"\n\n\n\n\n\n\n\n\n");
	    
	    
	    
	    File trainVecFile = new File("data/seq/train");
		File testVecFile = new File("data/seq/test");
		
		SplitInput splitInput = new SplitInput();
	    splitInput.setConf(conf );
	    splitInput.run(new String[]{
	    		"-i",inputVecFile.getAbsolutePath()+"/tfidf-vectors", 
	    		"--trainingOutput", trainVecFile.getAbsolutePath(), 
	    		"--testOutput", testVecFile.getAbsolutePath(), 
	    		"--randomSelectionPct", "40", 
	    		"--overwrite", "--sequenceFiles", "-xm", "sequential" });
	    
	    
	    
	    TrainNaiveBayesJob trainNaiveBayes = new TrainNaiveBayesJob();
	    
	    trainNaiveBayes.setConf(conf);
	    trainNaiveBayes.run(new String[] { "--input", trainVecFile.getAbsolutePath(), "--output", model.getAbsolutePath(),
	            "-el", "--tempDir", tempDir.getAbsolutePath() });

		
	        
	        
	        
		
	        
	        
	        
	        
	       
	       
	       
	        
	        
	        
	        
	        
	        
	        
	        
		

	}

}
