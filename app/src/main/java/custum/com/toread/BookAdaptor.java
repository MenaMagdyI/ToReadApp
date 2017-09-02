package custum.com.toread;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.util.List;

/**
 * Created by Mena on 8/1/2017.
 */

public class BookAdaptor extends ArrayAdapter<Book> {


    public BookAdaptor(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_view, parent, false);
        }

        Book currentBook = getItem(position);
        View loadingIndicator = listItemView.findViewById(R.id.imageprogress);
        loadingIndicator.setVisibility(View.VISIBLE);





        ImageView image = (ImageView)listItemView.findViewById(R.id.thumbnail);
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        TextView subtitle = (TextView)listItemView.findViewById(R.id.subtitle);
        TextView author = (TextView) listItemView.findViewById(R.id.author);
        TextView rating = (TextView) listItemView.findViewById(R.id.rating);
        TextView date = (TextView) listItemView.findViewById(R.id.date);


        if (currentBook.fullInfo()){
            image.setVisibility(View.GONE);
            author.setVisibility(View.GONE);
            rating.setVisibility(View.GONE);
            date.setVisibility(View.GONE);
            image.setImageResource(R.drawable.content);
            //loadingIndicator.setVisibility(View.GONE);
        }

        else {

            if(currentBook.getTitle().length()>23){
                String tempTitle = currentBook.getTitle().substring(0,20)+"...";
                title.setText(tempTitle);
            }
            else{
                title.setText(currentBook.getTitle());
            }

            if (currentBook.getSubtitle().length()>30){
                String tempSubtitle = currentBook.getSubtitle().substring(0,28) + "...";
                subtitle.setText(tempSubtitle);
            }
            else{
                subtitle.setText(currentBook.getSubtitle());
            }



            author.setText(currentBook.getAuthors()[0]+",");
            rating.setText(currentBook.getRate());
            date.setText(currentBook.getPublishedDate());


            Picasso.with(getContext())
                    .load(currentBook.getImageLinks()[0])
                    .error(ContextCompat.getDrawable(getContext(), R.drawable.content))
                    .into(image);

            //loadingIndicator.setVisibility(View.GONE);
       }


        return listItemView;
    }
}
