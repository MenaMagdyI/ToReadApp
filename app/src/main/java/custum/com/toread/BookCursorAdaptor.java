package custum.com.toread;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import custum.com.toread.data.BookContract;

public class BookCursorAdaptor extends CursorAdapter {

    public BookCursorAdaptor(Context context, Cursor c) {
        super(context, c, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.to_read_list_item, parent, false);
    }



    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView title = (TextView)view.findViewById(R.id.to_read_title);
        TextView author = (TextView)view.findViewById(R.id.to_read_author);
        TextView rate = (TextView)view.findViewById(R.id.to_read_rate);
        TextView id = (TextView)view.findViewById(R.id._id);

        int titleColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_TITLE);
        int authorColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_AUTHOR);
        int rateColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_RATE);
        int idColumnIndex = cursor.getColumnIndex(BookContract.BookEntry._ID);

        final String ftitle = cursor.getString(titleColumnIndex);
        final String fauthor = cursor.getString(authorColumnIndex);
        String frate = cursor.getString(rateColumnIndex);
        String fid = cursor.getString(idColumnIndex);

        title.setText("Title: "+ftitle);
        author.setText("Author: "+fauthor);
        rate.setText("rate: "+frate);
        id.setText(fid+".");

        ImageView image = (ImageView)view.findViewById(R.id.delete_item);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletebook(ftitle,fauthor,context);
                Toast.makeText(context.getApplicationContext(), "Deleted !",
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    private int deletebook(String title ,String author, Context context){

        String selection = BookContract.BookEntry.COLUMN_BOOK_TITLE + "=? AND "+BookContract.BookEntry.COLUMN_BOOK_AUTHOR+ "= ?";

        String[] selectionArgs = new String[] {title, author};

        int effectedrows = context.getContentResolver().delete(BookContract.BookEntry.CONTENT_URI,selection,selectionArgs);

        return effectedrows;
    }
}
