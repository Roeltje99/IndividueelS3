package application.Dal.ExeptionHandling;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiError
{
    @JsonProperty
    private HttpStatus status;
    @JsonProperty
    private String message;
    @JsonProperty
    private String details;
    @JsonProperty
    private Date timestamp;

    public ApiError(HttpStatus status, String message, String details, Date timestamp) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
}
