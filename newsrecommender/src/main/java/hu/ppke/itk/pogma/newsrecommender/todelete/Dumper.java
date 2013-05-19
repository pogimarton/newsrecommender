package hu.ppke.itk.pogma.newsrecommender.todelete;

import java.io.File;

import org.apache.mahout.classifier.naivebayes.test.TestNaiveBayesDriver;
import org.apache.mahout.utils.vectors.VectorDumper;

public class Dumper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		File readable = new File("data/readable");
		File inputVecFile = new File("data/seq/tweets-vec");
		try {
			
			VectorDumper.main(new String[]{"--seqFile", inputVecFile.getAbsolutePath()+"/wordcount/part-r-00000", "--output", readable.getAbsolutePath()+"/vec/vec"});
			//VectorDumper.main(new String[]{"-s", inputVecFile.getAbsolutePath()+"/tfidf-vectors", "-o", readable.getAbsolutePath()+"/vec"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
