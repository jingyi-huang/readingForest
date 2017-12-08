package PreProcessData;

import Classes.Path;

import org.json.JSONObject;
import org.json.*;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



/**
 * Created by huang on 11/27/17.
 */
public class ReadJSON {

    private FileInputStream instream;
    private BufferedReader br;



    private int count = 0;


    public ReadJSON() throws IOException {

        instream = new FileInputStream(Path.JSONData);
        br = new BufferedReader(new InputStreamReader(instream));

    }


    public Map<String, String> readJsonObject() throws Exception {

        String line;
        String docNo = "";

        while ((line = br.readLine()) != null) {
            if (line.contains("###")) {
                StringBuilder builder = new StringBuilder();
                builder.append("{");
                docNo = line.split(":")[0].replaceAll("\"","").replace("###","").trim();
                //System.out.println(docNo);
                while (!line.contains("    }")) {
                    line = br.readLine();
                    builder.append(line);
                }
                builder.append("}");
                JSONObject jsonObject = new JSONObject(builder.toString());
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String tags = jsonObject.getString("tags");
                String subtitles = jsonObject.getString("subtitles");
                Map<String, String> doc = new HashMap<>();
                doc.put("docNo", docNo);
                doc.put("title", title);
                doc.put("description", description);
                doc.put("tags", tags);
                doc.put("subtitles", subtitles);


                /*System.out.println("description: "+ jsonObject.getString("description"));
                System.out.println("subtitles: "+jsonObject.getString("subtitles"));
                System.out.println("tags: "+jsonObject.getString("tags"));
                System.out.println("title: "+jsonObject.getString("title"));*/

                return doc;
            }

        }

        instream.close();
        br.close();
        return null;

    }

}
