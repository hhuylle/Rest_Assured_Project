package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {

    @JsonProperty("region_id") //-->hey Jackson, find region_id from JSON response and set value to regionId variable like below
    private int regionId;

    @JsonProperty("region_name")
    private String regionName;

    private List<Link> links;

}
