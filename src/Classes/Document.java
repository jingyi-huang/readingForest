package Classes;

/**
 * Created by huang on 11/25/17.
 */
public class Document {

    protected String docid;
    protected String docno;
    protected String title;
    protected String tags;
    protected String description;
    protected String subtitles;
    protected String url;
    protected double score;

    public Document( String docid, String docno, double score ) {
        this.docid = docid;
        this.docno = docno;
        this.score = score;
    }

    public String docid() {
        return docid;
    }

    public String docno() {
        return docno;
    }

    public double score() {
        return score;
    }

    public void setDocid( String docid ) {
        this.docid = docid;
    }

    public void setDocno( String docno ) {
        this.docno = docno;
    }

    public void setScore( double score ) {
        this.score = score;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
