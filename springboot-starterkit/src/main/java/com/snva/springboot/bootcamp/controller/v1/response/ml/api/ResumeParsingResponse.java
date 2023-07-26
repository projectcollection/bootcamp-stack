
package com.snva.springboot.bootcamp.controller.v1.response.ml.api;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status_code",
    "data",
    "message"
})
@Generated("jsonschema2pojo")
public class ResumeParsingResponse {

    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("data")
    private Data data;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("status_code")
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty("status_code")
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
