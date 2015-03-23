package ms.services.time.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DateTime {

    @JsonProperty("date_time")
    private LocalDateTime dateTime;
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
}
