package custum.com.toread;


import android.support.annotation.Nullable;
import android.support.v4.content.Loader;





import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener,
        android.support.v4.app.LoaderManager.LoaderCallbacks<List<Book>> {


    private static final String USGS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes";

    private static final int Book_LOADER_ID = 1;


    private BookAdaptor mAdapter;

    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.activity_book_list, container, false);

        TextView mEmptyStateTextView = (TextView) rootView.findViewById(R.id.no_internet);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_books);
        mAdapter = new BookAdaptor(getActivity(), new ArrayList<Book>());
        listView.setAdapter(mAdapter);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book currentBook = mAdapter.getItem(i);
                Log.i("BookList Title: ",currentBook.getTitle());
                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                intent.putExtra("currentbook",currentBook);
                startActivity(intent);

            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                rootView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Loader<List<Book>> loaderManager = getLoaderManager().initLoader(Book_LOADER_ID, null,this);

        } else {

            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        return rootView;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.Subject)) ||
                key.equals(getString(R.string.MaxValue))){

            mAdapter.clear();

            View loadingIndicator = getActivity().findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.VISIBLE);

            getLoaderManager().restartLoader(Book_LOADER_ID, null,this);
        }

    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

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

        return new BookLoader(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View loadingIndicator = getActivity().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();


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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(getActivity(), SettingActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
