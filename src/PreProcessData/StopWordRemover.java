package PreProcessData;
import Classes.Path;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class StopWordRemover {
    private FileInputStream input;
    private BufferedReader br;
    private Set<String> stopWords;

    /**
     * @constructor
     * read the stopword file and load it into a set
     */
    public StopWordRemover( ) throws IOException{
        this.stopWords = new HashSet<>();
        String line="";
        this.input = new FileInputStream(Path.StopwordDir);
        this.br = new BufferedReader(new InputStreamReader(input));
        while((line = br.readLine())!= null){
              if(!line.isEmpty()){
                    stopWords.add(line);
              }
        }
        br.close();
    }

    /**
     * check if a word is a stopword or not
     * @param word
     * @return boolean
     */
    public boolean isStopword( char[] word ) {
        return stopWords.contains(new String(word));
    }
}

