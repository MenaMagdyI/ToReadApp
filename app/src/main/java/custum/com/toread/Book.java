package custum.com.toread;


import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title; //
    private String subtitle;//
    private String[] authors;//
    private String publisher;
    private String publishedDate;
    private String description;
    private String rate;
    private int ratingCounts;
    private String[] imageLinks;
    private String Languages;
    private String previewLink;
    private int pagecount;
    private int infoindecator;
    private int imageindecator;

    private String BigPhoto;
    private String FirstAuthor;

    private int ONLY_TITLE = -1;
    private int NO_IMAGE = -1;

    public Book(String mtitle){
        title = mtitle;
        infoindecator = ONLY_TITLE;
    }

    public Book(){
        title = "Failed";
        subtitle = "Failed";
        authors = new String[]{"Failed", "Failed"};
        publisher = "Failed";
        publishedDate = "Failed";
        description = "Failed";
        rate = "0";
        ratingCounts = 0;
        Languages = "Failed";
        previewLink = "Failed";
        BigPhoto = "Failed";
        FirstAuthor = "Failed";
        infoindecator = 1;
        imageindecator = NO_IMAGE;
    }

    public Book(String mtitle,String msubtitle,String[] mauthor, String mpublisher,String mpublisherDate,
                String mdescription,String mrate,int mratingCounts, String mlan,String mPreview){
        title = mtitle;
        subtitle = msubtitle;
        authors = mauthor;
        publisher = mpublisher;
        publishedDate = mpublisherDate;
        description = mdescription;
        rate = mrate;
        ratingCounts = mratingCounts;
        Languages = mlan;
        previewLink = mPreview;
        infoindecator = 1;
        imageindecator = NO_IMAGE;
    }

    public Book(String mtitle,String msubtitle,String[] mauthor, String mpublisher,String mpublisherDate,
                String mdescription,String mrate,int mratingCounts, String[] mImageLinks,String mlan,String mPreview,int mPagecount){
        title = mtitle;
        subtitle = msubtitle;
        authors = mauthor;
        publisher = mpublisher;
        publishedDate = mpublisherDate;
        description = mdescription;
        rate = mrate;
        ratingCounts = mratingCounts;
        imageLinks = mImageLinks;
        Languages = mlan;
        previewLink = mPreview;
        pagecount = mPagecount;
        BigPhoto = mImageLinks[1];
        FirstAuthor = mauthor[0];
        infoindecator = 1;
        imageindecator = 1;
    }


    protected Book(Parcel in) {
        title = in.readString();
        subtitle = in.readString();
        authors = in.createStringArray();
        publisher = in.readString();
        publishedDate = in.readString();
        description = in.readString();
        rate = in.readString();
        ratingCounts = in.readInt();
        imageLinks = in.createStringArray();
        Languages = in.readString();
        previewLink = in.readString();
        pagecount = in.readInt();
        infoindecator = in.readInt();
        imageindecator = in.readInt();
        BigPhoto = in.readString();
        FirstAuthor = in.readString();
        ONLY_TITLE = in.readInt();
        NO_IMAGE = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getPagecount(){
        return pagecount;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getRatingCounts() {
        return ratingCounts;
    }

    public void setRatingCounts(int ratingCounts) {
        this.ratingCounts = ratingCounts;
    }

    public String[] getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String[] imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getThuminal(){
        return imageLinks[1];
    }

    public boolean fullInfo(){
        return (infoindecator == ONLY_TITLE);
    }

    public String getBigPhoto(){
        return BigPhoto;
    }

    public String getFirstAuthor(){
        return FirstAuthor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeStringArray(authors);
        parcel.writeString(publisher);
        parcel.writeString(publishedDate);
        parcel.writeString(description);
        parcel.writeString(rate);
        parcel.writeInt(ratingCounts);
        parcel.writeStringArray(imageLinks);
        parcel.writeString(Languages);
        parcel.writeString(previewLink);
        parcel.writeInt(pagecount);
        parcel.writeInt(infoindecator);
        parcel.writeInt(imageindecator);
        parcel.writeString(BigPhoto);
        parcel.writeString(FirstAuthor);
        parcel.writeInt(ONLY_TITLE);
        parcel.writeInt(NO_IMAGE);
    }
}
