package SearchLucene;

import Classes.Document;
import Classes.Path;
import Classes.*;
import Indexing.MyIndexReader;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 11/26/17.
 */
public class QueryRetrieval {
    protected MyIndexReader indexReader;
    private Directory directory;
    private DirectoryReader ireader;
    private IndexSearcher indexSearcher;
    private String[]fields;
    private Map<String,Float> boosts;
    private final String PREFIX="https://www.youtube.com/watch?v=";

    public QueryRetrieval(MyIndexReader ixreader) throws IOException {
            directory = FSDirectory.open(Paths.get(Path.IndexDir));
            ireader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(ireader);
            fields = new String[]{"TITLE","DESCRIPTION","TAGS","SUBTITLES"};
            boosts = new HashMap<>();
            boosts.put("TITLE",8.0f);
            boosts.put("DESCRIPTION",6.0f);
            boosts.put("TAGS",4.0f);
            boosts.put("SUBTITLES",2.0f);

    }

    public List<Document> retrieveQuery(Classes.Query aQuery, int TopN) throws Exception{
        List<Document> results = new ArrayList<Document>();
        //Query luceneQuery = new QueryParser("SUBTITLES", new WhitespaceAnalyzer()).parse(aQuery.getQueryContent());
        Query luceneQuery = new MultiFieldQueryParser(fields,new WhitespaceAnalyzer(),boosts).parse(aQuery.getQueryContent());
        ScoreDoc[]scoreDoc = indexSearcher.search(luceneQuery,TopN).scoreDocs;
        for(ScoreDoc score:scoreDoc){
            results.add(new Document(score.doc + "", ireader.document(score.doc).get("DOCNO"), score.score));
        }
        return results;

    }

    public List<String> getResultURL(List<Document>results){
        List<String> URLs = new ArrayList<>();
        if(results != null){
            for(Document result:results){
                URLs.add(PREFIX+result.docno());
            }
        }
        return URLs;
    }
}
