package custum.com.toread.data;

/**
 * Created by Mena on 8/10/2017.
 */


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class BookContract {

    BookContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.android.toread";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BOOKS = "books";

    public static final class BookEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_BOOK_TITLE ="title";

        public final static String COLUMN_BOOK_AUTHOR ="author";

        public final static String COLUMN_BOOK_RATE ="rate";




















    }
}
