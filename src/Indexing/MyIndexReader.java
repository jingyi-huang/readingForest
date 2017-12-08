package Indexing;

import Classes.Path;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by huang on 11/25/17.
 */
public class MyIndexReader {
    private Directory directory;
    private DirectoryReader ireader;
    private IndexSearcher isearcher;

    public MyIndexReader() throws IOException{
        directory = FSDirectory.open(Paths.get(Path.IndexDir));
        ireader = DirectoryReader.open(directory);
        isearcher = new IndexSearcher(ireader);
    }

    /*public int[][] getPostingList(String token) throws IOException{
        Term tm = new Term("SUBTITLES",token);
        int df = ireader.docFreq(tm);
        if(df == 0)
            return null;
        Query query = new TermQuery(tm);
        // find the top n hits for query
        TopDocs tops = isearcher.search(query,df);
        //ScoreDoc: holds one hit in TopDocs
        ScoreDoc[]scoreDoc = tops.scoreDocs;
        int[][]posting = new int[df][];
        int ix = 0;
        Terms vector;
        TermsEnum termsEnum;
        BytesRef text;
        for (ScoreDoc score : scoreDoc){
            //get document number
            int id = score.doc;
            int freq=0;
            vector = ireader.getTermVector(id, "SUBTITLES");
            //step through all terms
            termsEnum = vector.iterator();
            while ((text = termsEnum.next()) != null) {
                if(text.utf8ToString().equals(token))
                    //Returns the total number of occurrences of this term across all documents
                    freq+= (int) termsEnum.totalTermFreq();
            }
            posting[ix] = new int[] { id, freq };
            ix++;
        }
        return posting;
    }*/

    /**
     * Return the number of documents that contain the token
     * @param token
     * @return
     * @throws IOException
     */
    /*public int docFreq(String token) throws IOException{
        Term tm = new Term("SUBTITLES",token);
        int df = ireader.docFreq(tm);
        return df;
    }*/

    /**
     * return the total occurrence  of the token in the collection
     * @param token
     * @return
     * @throws IOException
     */
    /*public long collectionFreq(String token)throws IOException{
        Term tm = new Term("SUBTITLES",token);
        long ctf = ireader.totalTermFreq(tm);
        return ctf;
    }*/

    public int getDocid(String docno) throws IOException{
        Query query = new TermQuery(new Term("DOCNO",docno));
        TopDocs tops = isearcher.search(query,1);
        return tops.scoreDocs[0].doc;
    }

    public String getDocno(int docid) throws IOException{
        Document doc = ireader.document(docid);
        return (doc == null)?null:doc.get("DOCNO");
    }




    public void close() throws IOException{
        ireader.close();
        directory.close();
    }
}
