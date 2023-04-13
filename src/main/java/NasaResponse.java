import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaResponse {
    private final String date;
    private final String explanation;
    private final String hdUrl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public NasaResponse(@JsonProperty ("date") String date,
                        @JsonProperty ("explanation") String explanation,
                        @JsonProperty ("hdurl") String hdUrl,
                        @JsonProperty ("media_type") String mediaType,
                        @JsonProperty ("service_version") String serviceVersion,
                        @JsonProperty ("title") String title,
                        @JsonProperty ("url") String url
    ) {
        this.date = date;
        this.explanation = explanation;
        this.hdUrl = hdUrl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    @Override
    public String toString() {
        return "Date: " + date +
                "\nExplanation: " + explanation +
                "\nURL_HD: " + hdUrl +
                "\nMedia Service: " + mediaType +
                "\nService Version: " + serviceVersion +
                "\nTitle: " + title +
                "\nURL: " + url;
    }
}
