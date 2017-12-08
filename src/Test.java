import Indexing.DocumentReader;
import PreProcessData.ReadJSON;

import java.util.Map;

/**
 * Created by huang on 11/25/17.
 */
public class Test {

    public static void main(String[]args){

        Test test = new Test();
        try{
            Map<String,String> doc = null;

            ReadJSON reader = new ReadJSON();
            int count = 0;
            while((doc = reader.readJsonObject())!=null){
                //System.out.println(doc.get("docNo"));
                count++;
            }
            System.out.println("total "+count+" documents");

        }catch (Exception e){
            e.printStackTrace();
        }
        /*String i = "    \"###-yY82DD9_EI\": {";
        i = i.split(":")[0].replaceAll("\"","").replace("###","");
        System.out.print(i);*/


    }


}
