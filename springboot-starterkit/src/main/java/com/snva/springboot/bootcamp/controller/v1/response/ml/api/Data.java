
package com.snva.springboot.bootcamp.controller.v1.response.ml.api;

import java.util.LinkedHashMap;
import java.util.List;
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
    "email",
    "phone",
    "name",
    "total_exp",
    "university",
    "designition",
    "degree",
    "skills",
    "Companies worked at"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("name")
    private String name;
    @JsonProperty("total_exp")
    private int totalExp;
    @JsonProperty("university")
    private List<Object> university;
    @JsonProperty("designition")
    private List<String> designition;
    @JsonProperty("degree")
    private List<Object> degree;
    @JsonProperty("skills")
    private List<String> skills;
//    @JsonProperty("Companies worked at")
//    private List<Object> companiesworkedat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("total_exp")
    public int getTotalExp() {
        return totalExp;
    }

    @JsonProperty("total_exp")
    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    @JsonProperty("university")
    public List<Object> getUniversity() {
        return university;
    }

    @JsonProperty("university")
    public void setUniversity(List<Object> university) {
        this.university = university;
    }

    @JsonProperty("designition")
    public List<String> getDesignition() {
        return designition;
    }

    @JsonProperty("designition")
    public void setDesignition(List<String> designition) {
        this.designition = designition;
    }

    @JsonProperty("degree")
    public List<Object> getDegree() {
        return degree;
    }

    @JsonProperty("degree")
    public void setDegree(List<Object> degree) {
        this.degree = degree;
    }

    @JsonProperty("skills")
    public List<String> getSkills() {
        return skills;
    }

    @JsonProperty("skills")
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

//    @JsonProperty("companiesworkedat")
//    public List<Object> getCompaniesworkedat() {
//        return companiesworkedat;
//    }
//
//    @JsonProperty("companiesworkedat")
//    public void setCompaniesworkedat(List<Object> companiesworkedat) {
//        this.companiesworkedat = companiesworkedat;
//    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
