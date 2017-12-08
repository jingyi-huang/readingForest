package PreProcessData;

import Classes.Path;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 11/27/17.
 */
public class PreProcessMain {

    public static void main(String[] args) throws Exception {

        PreProcessMain pre = new PreProcessMain();
        pre.preProcess();
    }

    public void preProcess() throws Exception {
        ReadJSON reader = new ReadJSON();
        StopWordRemover stopwordRemover = new StopWordRemover();
        WordNormalizer normalizer = new WordNormalizer();

        FileWriter wr = new FileWriter(Path.resultData);
        Map<String, String> doc = null;

        // process the corpus, document by document, iteractively
        int count = 0;
        while ((doc = reader.readJsonObject()) != null) {
            // load document number of the document
            String docno = doc.get("docNo");

            // load document
            //char[]content = doc.get("subtitles").toCharArray();
            char[] subtitles = doc.get("subtitles").toCharArray();
            char[] tags = doc.get("tags").toCharArray();
            char[] description = doc.get("description").toCharArray();
            char[] title = doc.get("title").toCharArray();
            List<char[]> contents = new ArrayList<>();
            contents.add(title);
            contents.add(description);
            contents.add(tags);
            contents.add(subtitles);
            // write docno into the result file
            wr.append(docno + "\n");


            List<WordTokenizer> tokenizerList = new ArrayList<>();
            for (char[] content : contents) {
                WordTokenizer tokenizer = new WordTokenizer(content);
                tokenizerList.add(tokenizer);
            }



            // process the document word by word iteratively
            for (WordTokenizer tokenizer : tokenizerList) {
                char[] word = null;
                while ((word = tokenizer.nextWord()) != null) {
                    // each word is transformed into lowercase
                    word = normalizer.lowercase(word);

                    // filter out stopword, and only non-stopword will be written
                    // into result file
                    if (!stopwordRemover.isStopword(word))
                        wr.append(normalizer.stem(word) + " ");
                    //stemmed format of each word is written into result file
                }
                wr.append("\n");

            }
            count++;
        }
        System.out.println("totaly document count:  "+count);
        wr.close();
    }
}
