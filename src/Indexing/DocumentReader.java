package Indexing; /**
 * Created by huang on 11/25/17.
 */
import Classes.Path;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


import Classes.Path;

public class DocumentReader {

    private FileInputStream instream;
    private BufferedReader br;


    public DocumentReader()throws IOException {

            //instream = new FileInputStream(Path.JSONData);
            instream = new FileInputStream(Path.resultData);
            br = new BufferedReader(new InputStreamReader(instream));

    }


    public Map<String,String> nextDocument()throws IOException,JSONException{
        String line;
        String docNo="";
        while((line = br.readLine())!= null){
            docNo = line;
            String title= br.readLine();
            String description = br.readLine();
            String tags = br.readLine();
            String subtitles = br.readLine();
            Map<String,String> doc = new HashMap<>();
            doc.put("docNo",docNo);
            doc.put("title",title);
            doc.put("description",description);
            doc.put("tags",tags);
            doc.put("subtitles",subtitles);
            return doc;
        }

        instream.close();
        br.close();
        return null;
    }


}
