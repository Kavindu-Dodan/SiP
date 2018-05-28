package Common;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    private static String tokenResponse = null;

    public static void setTokenResponse(final String tokenResponse) {
        Utils.tokenResponse = tokenResponse;
    }

    public static String getTokenResponse() {
        return tokenResponse;
    }

    public static JsonObject getJsonResponseFromURL(
            final String URL,
            final String inputs,
            final String httpMethod) throws RuntimeException {

        final String jsonResponse;

        try {
            final java.net.URL url = new URL(URL);

            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod(httpMethod);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            final OutputStream outputStream = urlConnection.getOutputStream();
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            // write content
            bufferedWriter.write(inputs);

            bufferedWriter.flush();
            bufferedWriter.close();

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder builder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            bufferedReader.close();
            inputStream.close();

            jsonResponse = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Invalid registration details", e);
        }

        return Json.createReader(new StringReader(jsonResponse)).readObject();
    }
}
