package custum.com.toread;



import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import custum.com.toread.data.BookContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToReadListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {



    private static final int book_LOADER = 5;

    BookCursorAdaptor mCursorAdapter;

    public ToReadListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(book_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.activity_to_read_list, container, false);


        ListView bookListView = (ListView) rootView.findViewById(R.id.toreadlist);
        mCursorAdapter = new BookCursorAdaptor(getContext(), null);
        bookListView.setAdapter(mCursorAdapter);
        //Loader<Cursor> loaderManager = getLoaderManager().initLoader(book_LOADER, null, this);
        //Loader<Cursor> loaderManager = getActivity().getSupportLoaderManager().initLoader(book_LOADER, null, this);
        return rootView;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BookContract.BookEntry._ID,
                BookContract.BookEntry.COLUMN_BOOK_TITLE,
                BookContract.BookEntry.COLUMN_BOOK_AUTHOR,
                BookContract.BookEntry.COLUMN_BOOK_RATE };


        return new CursorLoader(getActivity(),   // Parent activity context
                BookContract.BookEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
