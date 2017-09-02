package custum.com.toread;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }


    public static List<Book> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Book> books = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<Book> extractFeatureFromJson(String booksJSON) {
        if (TextUtils.isEmpty(booksJSON)) {
            return null;
        }

         List<Book> books = new ArrayList<>();




        try {

            JSONObject baseJsonResponse = new JSONObject(booksJSON);

            if (baseJsonResponse.has("items")){
                JSONArray booksArray = baseJsonResponse.getJSONArray("items");
                for (int i = 0; i < booksArray.length(); i++) {

                    JSONObject currentBook = booksArray.getJSONObject(i);
                    JSONObject properties = currentBook.getJSONObject("volumeInfo");

                    String title  = properties.getString("title");


                    String subtitle;
                    if (properties.has("subtitle")){
                        subtitle= properties.getString("subtitle");
                    }
                    else
                    {
                        subtitle = "No Subtitle";
                    }


                    JSONArray authors;
                    String[] Authors;
                    if (properties.has("authors")){
                        authors= properties.getJSONArray("authors");
                        Authors = new String[authors.length()];
                        for(int j = 0; j < authors.length(); j++)
                            Authors[j] = authors.getString(j);
                    }
                    else
                    {
                        Authors =new String[1];
                        Authors[0] = "Not Found";
                    }


                    String publisher;
                    if (properties.has("publisher")){
                        publisher = properties.getString("publisher");
                    }
                    else {
                        publisher = "Un Known";
                    }


                    String publishedDate;
                    if (properties.has("publishedDate")){
                        publishedDate = properties.getString("publishedDate");
                    }
                    else {
                        publishedDate = "UnKnown";
                    }


                    String description;
                    if (properties.has("description")){
                        description = properties.getString("description");
                    }
                    else {
                        description = "No Description";
                    }



                    String rate;
                    int ratingCounts;
                    if (properties.has("averageRating")){
                        rate = properties.getString("averageRating");
                        ratingCounts = properties.getInt("ratingsCount");
                    }
                    else {
                        rate = "0";
                        ratingCounts = 0;
                    }



                    JSONObject imageLinks = properties.getJSONObject("imageLinks");
                    String[] ImagesLinks;
                    if (properties.has("imageLinks")){
                         ImagesLinks = new String[imageLinks.length()];
                        ImagesLinks[0] = imageLinks.getString("smallThumbnail");
                        ImagesLinks[1] = imageLinks.getString("thumbnail");
                    }
                    else {
                        ImagesLinks = new String[1];
                        ImagesLinks[0] = "No Image";
                    }


                    String Languages = properties.getString("language");
                    String previewLink = properties.getString("previewLink");

                    int pagecounts;
                    if(properties.has("pageCount")){
                        pagecounts = properties.getInt("pageCount");
                    }
                    else {
                        pagecounts = 0;
                    }

                    Book book = new Book(title, subtitle, Authors, publisher,publishedDate,description,rate,ratingCounts,ImagesLinks,Languages,previewLink,pagecounts);


                    books.add(book);

            }

            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the Book JSON results", e);
        }

        return books;
    }


}
