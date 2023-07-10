package com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode;




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
"source_code",
"language_id",
"stdin",
"expected_output"
})
@Generated("jsonschema2pojo")
public class CompileRequest {

@JsonProperty("source_code")
private String sourceCode;


@JsonProperty("language_id")
private String languageId;
@JsonProperty("stdin")
private String stdin;
@JsonProperty("expected_output")
private String expectedOutput;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("source_code")
public String getSourceCode() {
return sourceCode;
}

@JsonProperty("source_code")
public void setSourceCode(String sourceCode) {
this.sourceCode = sourceCode;
}

@JsonProperty("language_id")
public String getLanguageId() {
return languageId;
}

@JsonProperty("language_id")
public void setLanguageId(String languageId) {
this.languageId = languageId;
}

@JsonProperty("stdin")
public String getStdin() {
return stdin;
}

@JsonProperty("stdin")
public void setStdin(String stdin) {
this.stdin = stdin;
}

@JsonProperty("expected_output")
public String getExpectedOutput() {
return expectedOutput;
}

@JsonProperty("expected_output")
public void setExpectedOutput(String expectedOutput) {
this.expectedOutput = expectedOutput;
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