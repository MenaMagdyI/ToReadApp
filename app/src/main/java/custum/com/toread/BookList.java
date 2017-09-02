package custum.com.toread;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/*



                          Not Used AnyMoreeeeeee




 */
public class BookList extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>>,
        SharedPreferences.OnSharedPreferenceChangeListener{
    private static final String LOG_TAG = Book.class.getName();

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes";

    private static final int Book_LOADER_ID = 1;


    private BookAdaptor mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


/*
        final ArrayList<Book> books = new ArrayList<Book>();
        Log.i("BookListActivity","Before links");
        String[] authors = {"Marziah Karch"};
        books.add(new Book ("Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("2 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("3 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("4 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("5 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("6 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("7 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("8 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("9 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("10 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));
        books.add(new Book ("11 Android for Work","Productivity for Professionals",authors,"Apress","2010-09-01","Android is new, Android is open, and Android is fun. It’s also serious about business. Android for Work shows you how to harness the power of Android to stay productive and take your office on the road. This book also sheds light on the often daunting task of finding the right Android phone for the business user. Whether this is your first smartphone, your first Android smartphone, or your first attempt to make your phone into a productivity tool, Android for Work gets you started. You’ll learn how to manage email and tasks, but you’ll also learn how to weed through the sea of games to find specialized productivity tools for a variety of professions. For those that are more interested in an enterprise wide deployment, the book includes an appendix of information on administering Android phones, creating custom interfaces, and creating specialized apps for your enterprise. You’ll also learn more about integrating Android with other Google Apps for enterprise. What you’ll learn Select the Android phone that is right for you Integrate your work email and calendar tools Navigate business trips and meetings with ease Find specialized apps for your profession Collaborate with coworkers in large and small groups Harness the power of Android customization Who this book is for This book is for anyone who is considering an Android phone or who has recently purchased one. Whether you are a web designer, writer, medical professional, lawyer, or educator, an Android phone can help you be more productive and finally find a reason for having a phone with a data plan. Table of Contents Buying and Activating an Android Phone Using Your Phone for the First Time Going Online with Android Android Calling Managing Texting Wrangling Your E-mail The Calendar Android in a Microsoft World Photos and Video Web Browsing Social Media and Work Maps and Mobile The Remaining Android Apps The Android Market General Business Applications Specialized Apps for Professionals Advanced Customization and Troubleshooting Resources for Managing Enterprise-Wide Android Deployment Resources for Developing Android Apps",3.5,18,"18","http://books.google.com.eg/books?id=6tLAyQLSzG0C&printsec=frontcover&dq=android&hl=&cd=1&source=gbs_api"));


        Log.i("BookListActivity","After Initialization");
        BookAdaptor adaptor = new BookAdaptor(this,books);
        Log.i("BookListActivity","After adaptor constructor");
        ListView listView = (ListView) findViewById(R.id.list_view_books);
        Log.i("BookListActivity","Before send adaptor to list view");
        listView.setAdapter(adaptor);*/


        TextView mEmptyStateTextView = (TextView) findViewById(R.id.no_internet);
        ListView listView = (ListView) findViewById(R.id.list_view_books);
        mAdapter = new BookAdaptor(this, new ArrayList<Book>());
        listView.setAdapter(mAdapter);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book currentBook = mAdapter.getItem(i);
                Log.i("BookList Title: ",currentBook.getTitle());
                Intent intent = new Intent(BookList.this,ProfileActivity.class);
                intent.putExtra("currentbook",currentBook);
                startActivity(intent);

            }
        });


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();


            loaderManager.initLoader(Book_LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {

        //q=android&maxResults=10

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String g = sharedPrefs.getString( getString(R.string.Subject), getString(R.string.Android));

        String maxResult = sharedPrefs.getString(
                getString(R.string.MaxValue),
                getString(R.string.valueOfMaxValue)
        );


        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", g);
        uriBuilder.appendQueryParameter("maxResults", maxResult);


        Log.i("BookList URL: ",uriBuilder.toString());

        //return new BookLoader(this, uriBuilder.toString());
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();

        //mEmptyStateTextView.setText("sorry, Try something else");


        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
            mAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
            mAdapter.clear();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.Subject)) ||
                key.equals(getString(R.string.MaxValue))){

            mAdapter.clear();

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.VISIBLE);

            getLoaderManager().restartLoader(Book_LOADER_ID, null, this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


