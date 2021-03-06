package com.techelevator.services.yelpfusion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpFusion {
    private static final String ACCESS_TOKEN =
            "BSlG5RWR1DJPC4Zf4vabLNFi0YS5-qS2iXkrxpCSY-i02RQWrXuc9CBJtvkeW _EbgUPJ_8oNLscX_yvmu67cQ2HNLxxR6CwFGFoTiUlPsIsDEz-GRtHV88D7dBFBYHYx";
    HttpHeaders headers = new HttpHeaders();

    public YelpFusion() {

        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.set("Authorization", "Bearer "+ ACCESS_TOKEN);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        String result = restTemplate.postForObject(url, entity, String.class);
    }
}
