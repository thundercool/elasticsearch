package com.search.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@Entity
@Embeddable
@JsonPropertyOrder({
"region",
"long_synopsis",
"meta_desc",
"tags"
})
public class Metadata implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "metaId", unique = true)
	@JsonIgnore
	private String metaId;
	
	@JsonProperty("region")
	@NotNull(message="Region cannot be blank")
	@Size(min=1)
	private String region;
	
	@JsonProperty("long_synopsis")
	private String longSynopsis;
	
	@JsonProperty("meta_desc")
	private String metaDesc;
	
	@Column
	@ElementCollection(targetClass = String.class)
	@JsonProperty("tags")
	@Size(min=1)
	@Field(type = FieldType.String, store = true)
	private List<String> tags;


	public Metadata() {
		super();
	}

	public Metadata(String region, String longSynopsis, String metaDesc, List<String> tags) {
		super();
		this.region = region;
		this.longSynopsis = longSynopsis;
		this.metaDesc = metaDesc;
		this.tags = tags;
	}

	public String getMetaId() {
		return metaId;
	}

	public void setMetaId(String metaId) {
		this.metaId = metaId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLongSynopsis() {
		return longSynopsis;
	}

	public void setLongSynopsis(String longSynopsis) {
		this.longSynopsis = longSynopsis;
	}

	public String getMetaDesc() {
		return metaDesc;
	}

	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}