package PreProcessData;
import Classes.Stemmer;

/**
 * Created by huang on 11/27/17.
 */
public class WordNormalizer {


    /**
     * @param chars
     * @return lowercase chars
     */
    public char[] lowercase( char[] chars ) {
        //transform the uppercase characters in the word to lowercase
        for(int i = 0; i<chars.length;i++){
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return chars;
    }

    /**
     * This method is to stem on input word
     * @param chars
     * @return stemmed word
     */
    public String stem(char[] chars)
    {
        //use the stemmer in Classes package to do the stemming on input word, and return the stemmed word
        Stemmer s = new Stemmer();
        s.add(chars, chars.length);
        s.stem();
        // here should be new String(s)
        //charArray toString may cause some info loss
        return s.toString();
    }

}

