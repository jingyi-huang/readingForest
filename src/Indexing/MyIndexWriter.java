package Indexing;
import Classes.Path;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by huang on 11/25/17.
 */
public class MyIndexWriter {
    private IndexWriter ixwriter;
    private Directory directory;
    private Analyzer analyzer;
    private FieldType fieldType;

    public MyIndexWriter() throws IOException{
        directory = FSDirectory.open(Paths.get(Path.IndexDir));
        analyzer = new WhitespaceAnalyzer();
        IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
        indexConfig.setMaxBufferedDocs(10000);
        ixwriter = new IndexWriter(directory, indexConfig);
        fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        fieldType.setStoreTermVectors(true);
        fieldType.setStored(false);
    }


    public void index(String docno,String title,String desc,String tags,String subtitles) throws IOException{
      Document doc = new Document();
      doc.add(new StoredField("DOCNO",docno));
      doc.add(new Field("TITLE",title,fieldType));
      doc.add(new Field("DESCRIPTION",desc,fieldType));
      doc.add(new Field("TAGS",tags,fieldType));
      doc.add(new Field("SUBTITLES",subtitles,fieldType));
      ixwriter.addDocument(doc);
    }

    public void close()throws IOException{
        ixwriter.close();
        directory.close();

    }
}
