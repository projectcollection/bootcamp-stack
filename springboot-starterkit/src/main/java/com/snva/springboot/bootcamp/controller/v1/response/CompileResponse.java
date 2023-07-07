package com.snva.springboot.bootcamp.controller.v1.response;


import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.Status;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"stdout",
"time",
"memory",
"stderr",
"token",
"compile_output",
"message",
"status"
})
@Generated("jsonschema2pojo")
public class CompileResponse {

@JsonProperty("stdout")
private String stdout;
@JsonProperty("time")
private String time;
@JsonProperty("memory")
private Integer memory;
@JsonProperty("stderr")
private Object stderr;
@JsonProperty("token")
private String token;
@JsonProperty("compile_output")
private Object compileOutput;
@JsonProperty("message")
private Object message;
@JsonProperty("status")
private Status status;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("stdout")
public String getStdout() {
return stdout;
}

@JsonProperty("stdout")
public void setStdout(String stdout) {
this.stdout = stdout;
}

@JsonProperty("time")
public String getTime() {
return time;
}

@JsonProperty("time")
public void setTime(String time) {
this.time = time;
}

@JsonProperty("memory")
public Integer getMemory() {
return memory;
}

@JsonProperty("memory")
public void setMemory(Integer memory) {
this.memory = memory;
}

@JsonProperty("stderr")
public Object getStderr() {
return stderr;
}

@JsonProperty("stderr")
public void setStderr(Object stderr) {
this.stderr = stderr;
}

@JsonProperty("token")
public String getToken() {
return token;
}

@JsonProperty("token")
public void setToken(String token) {
this.token = token;
}

@JsonProperty("compile_output")
public Object getCompileOutput() {
return compileOutput;
}

@JsonProperty("compile_output")
public void setCompileOutput(Object compileOutput) {
this.compileOutput = compileOutput;
}

@JsonProperty("message")
public Object getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(Object message) {
this.message = message;
}

@JsonProperty("status")
public Status getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(Status status) {
this.status = status;
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

