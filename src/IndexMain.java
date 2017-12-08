import Indexing.MyIndexReader;
import Indexing.MyIndexWriter;
import Indexing.DocumentReader;

import java.util.Map;

/**
 * Created by huang on 11/27/17.
 */
public class IndexMain {

    public static void main (String[] args)throws Exception{
        IndexMain im = new IndexMain();
        long startTime=System.currentTimeMillis();
        im.writeIndex();
        long endTime=System.currentTimeMillis();
        System.out.println("index running time: "+(endTime-startTime)/60000.0+" min");
        /*startTime=System.currentTimeMillis();
        im.readIndex( "invert");
        endTime=System.currentTimeMillis();
        System.out.println("load index & retrieve running time: "+(endTime-startTime)/60000.0+" min");*/
    }

    public void writeIndex()throws Exception{
        // Initiate pre-processed collection file reader
        DocumentReader corpus = new DocumentReader();

        // initiate the output object
        MyIndexWriter output=new MyIndexWriter();

        // initiate a doc object, which can hold document number and document content of a document
        Map<String, String> doc = null;

        int count=0;
        // build index of corpus document by document
        while ((doc = corpus.nextDocument()) != null) {
            // load document number and content of the document
            String docno = doc.get("docNo");
            String title = doc.get("title");
            String desc = doc.get("description");
            String tags = doc.get("tags");
            String subtitles = doc.get("subtitles");
            //String url = doc.get("url");

            // index this document
            //output.index(docno, url,subtitles);
            output.index(docno,title,desc,tags,subtitles);
            count++;
            /*if(count%10000==0)
                System.out.println("finish "+count+" docs");*/
        }
        System.out.println("totaly document count:  "+count);
        output.close();

    }

    /*public void readIndex(String token)throws Exception{
        // Initiate the index file reader
        MyIndexReader ixreader=new MyIndexReader();

        // do retrieval
        int df = ixreader.docFreq(token);
        long ctf = ixreader.collectionFreq(token);
        System.out.println(" >> the token \""+token+"\" appeared in "+df+" documents and "+ctf+" times in total");
        if(df>0){
            int[][] posting = ixreader.getPostingList(token);
            for(int ix=0;ix<posting.length;ix++){
                int docid = posting[ix][0];
                int freq = posting[ix][1];
                String docno = ixreader.getDocno(docid);
                System.out.printf("    %20s    %6d    %6d\n", docno, docid, freq);
            }
        }
        ixreader.close();
    }*/

}
