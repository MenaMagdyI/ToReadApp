package custum.com.toread;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import custum.com.toread.data.BookContract;

public class ProfileActivity extends AppCompatActivity  {

    private static final int EXISTING_BOOK_LOADER = 0;
    private Uri mCurrentPetUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Book Info");

        final boolean found;

        View loadingIndicator = findViewById(R.id.imageprogress2);
        loadingIndicator.setVisibility(View.VISIBLE);




        Intent i = getIntent();
        final Book MyBook = i.getParcelableExtra("currentbook");

        found = is_exist(MyBook.getTitle(),MyBook.getFirstAuthor());

        //Log.i("image Link",MyBook.getImageLinks()[0]);
        //Log.i("Raaaaaaate",MyBook.getRate().toString());
        ImageView image = (ImageView) findViewById(R.id.profile_image);
        TextView title = (TextView) findViewById(R.id.profile_title);
        //TextView subtitle = (TextView) findViewById(R.id.profile_subtitle);
        TextView rate = (TextView)findViewById(R.id.profile_rate);
        TextView auther = (TextView)findViewById(R.id.profile_author);
        TextView des = (TextView)findViewById(R.id.profile_des);
        TextView publishDate = (TextView)findViewById(R.id.profile_publisher);
        TextView lan = (TextView)findViewById(R.id.profile_language);
        TextView link = (TextView)findViewById(R.id.profile_previewlink);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(found == true){

            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.deletebook));

        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (found == false){

                    insertBook(MyBook.getTitle(),MyBook.getFirstAuthor(),MyBook.getRate());
                    Toast.makeText(getApplicationContext(), "Added to your Book List !",
                            Toast.LENGTH_LONG).show();
                    finish();

                }
                else if(found == true){

                    deletebook(MyBook.getTitle(),MyBook.getFirstAuthor());
                    Toast.makeText(getApplicationContext(), "Deleted from your Book List !",
                            Toast.LENGTH_LONG).show();
                    finish();
                }


            }
        });
        title.setText(MyBook.getTitle());

        //subtitle.setText(MyBook.getSubtitle());
        des.setText("Rate: "+MyBook.getDescription());
        publishDate.setText("Published On: "+MyBook.getPublishedDate());
        lan.setText("Language: "+MyBook.getLanguages());
        link.setText("Preview Link: "+MyBook.getPreviewLink().substring(0,25)+"...");
        Picasso.with(ProfileActivity.this)
                .load(MyBook.getBigPhoto())
                .error(ContextCompat.getDrawable(ProfileActivity.this, R.drawable.content))
                .into(image);


        rate.setText(MyBook.getRate());
        auther.setText(MyBook.getFirstAuthor());
        loadingIndicator.setVisibility(View.GONE);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri PreviewLink = Uri.parse(MyBook.getPreviewLink());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, PreviewLink);
                startActivity(websiteIntent);
            }
        });

    }

    private void insertBook(String title, String author,String rate) {

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_BOOK_TITLE, title);
        values.put(BookContract.BookEntry.COLUMN_BOOK_AUTHOR, author);
        values.put(BookContract.BookEntry.COLUMN_BOOK_RATE, rate);


        Uri newUri = getContentResolver().insert(BookContract.BookEntry.CONTENT_URI, values);

        Log.i("profile Uri",newUri.toString());
    }


    private int deletebook(String title ,String author){

        String selection = BookContract.BookEntry.COLUMN_BOOK_TITLE + "=? AND "+BookContract.BookEntry.COLUMN_BOOK_AUTHOR+ "= ?";

        String[] selectionArgs = new String[] {title, author};

        int effectedrows = getContentResolver().delete(BookContract.BookEntry.CONTENT_URI,selection,selectionArgs);

        return effectedrows;
    }

    private boolean is_exist(String title , String author){
        String[] projection = {
                BookContract.BookEntry._ID,
                BookContract.BookEntry.COLUMN_BOOK_TITLE,
                BookContract.BookEntry.COLUMN_BOOK_AUTHOR,
                BookContract.BookEntry.COLUMN_BOOK_RATE };

        String selection = BookContract.BookEntry.COLUMN_BOOK_TITLE + "=? AND "+BookContract.BookEntry.COLUMN_BOOK_AUTHOR+ "= ?";

        String[] selectionArgs = new String[] {title, author};



        Cursor mCursor = getContentResolver().query(BookContract.BookEntry.CONTENT_URI,projection,selection,selectionArgs,null);

        if(!(mCursor.moveToFirst()) || mCursor.getCount() ==0){
            return false;
        }
        else
            return true;

    }

}
