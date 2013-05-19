package hu.ppke.itk.pogma.newsrecommender.todelete;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;

public class TesztTstToSeq {
	public static void main(String args[]) throws Exception {
		
		
		//$ java -cp target/twitter-naive-bayes-example-1.0-jar-with-dependencies.jar 
		//com.chimpler.example.bayes.TweetTSVToSeq 
		//data/tweets-train.tsv
		//data/tweets-seq

		String inputFileName = "data/raw/tweets-train.tsv";
		String outputDirName = "data/seq/tweets-seq";
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		Writer writer = new SequenceFile.Writer(fs, configuration, new Path(outputDirName + "/chunk-0"),
				Text.class, Text.class);

		int count = 0;
		BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
		Text key = new Text();
		Text value = new Text();
		while(true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			String[] tokens = line.split("\t", 3);
			if (tokens.length != 3) {
				System.out.println("Skip line: " + line);
				continue;
			}
			String category = tokens[0];
			String id = tokens[1];
			String message = tokens[2];
			key.set("/" + category + "/" + id);
			value.set(message);
			writer.append(key, value);
			count++;
		}
		writer.close();
		System.out.println("Wrote " + count + " entries.");
	}
}
