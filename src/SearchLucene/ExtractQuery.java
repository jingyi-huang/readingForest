package SearchLucene;


import Classes.Path;
import Classes.Query;
import Classes.Stemmer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huang on 11/26/17.
 */
public class ExtractQuery {
    private Set<String> stopWords;
    private BufferedReader stopWordReader;

    public ExtractQuery() throws IOException{
        stopWords = new HashSet<>();
        //br = new BufferedReader(new InputStreamReader(new FileInputStream(Path.TopicDir)));
        stopWordReader = new BufferedReader(new InputStreamReader(new FileInputStream(Path.StopwordDir)));
        String line ="";
        while((line = stopWordReader.readLine())!=null){
            stopWords.add(line);
        }
        stopWordReader.close();
    }

    public Query getQuery(String query){
        String[]words = wordTokenizer(query);
        String content = normalizAndFilter(words);
        Query aquery = new Query();
        aquery.setQueryContent(content);
        return aquery;

    }

    public String[]wordTokenizer(String content){
        String regx = "[^A-Za-z0-9]";
        String newContent = content.replaceAll(regx," ");
        String[]words = newContent.split("\\s+");
        return words;
    }

    public String normalizAndFilter(String[]words){
        StringBuilder sb = new StringBuilder();
        for(String word: words){
            String lowerStr = word.toLowerCase();
            if(!stopWords.contains(lowerStr)){
                char[] chars = lowerStr.toCharArray();
                Stemmer s = new Stemmer();
                s.add(chars,chars.length);
                s.stem();
                sb.append(s.toString()).append(" ");
            }
        }
        return  sb.toString();
    }


}
