import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static final String LINK =
            "https://api.nasa.gov/planetary/apod?api_key=15mcYCb0cvEc8Axk4gA6viU7LppM8TfzFltaoJHL";

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build()
                ).build()
        ) {

            HttpGet request = new HttpGet(LINK);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                NasaResponse nasaResponse = mapper.readValue(
                        response.getEntity()
                                .getContent()
                                .readAllBytes(),
                        NasaResponse.class
                );
                System.out.println(nasaResponse);

                HttpGet requestJPG = new HttpGet(nasaResponse.getHdUrl());
                CloseableHttpResponse responseJPG = httpClient.execute(requestJPG);
                try (FileOutputStream out = new FileOutputStream("hubble_ngc2419_potw1908a_1024.jpg")) {
                    out.write(responseJPG.getEntity().getContent().readAllBytes());
                    out.flush();

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
