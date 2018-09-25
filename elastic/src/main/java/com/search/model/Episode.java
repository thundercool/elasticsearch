package com.search.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@Entity
@JsonPropertyOrder({
"title",
"metadata",
"status",
"id",
"date"
})
@Document(indexName = "video", type = "episode", shards = 1)
public class Episode {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("title")
	@NotNull(message="Title cannot be empty")
	@Size(min=1)
	private String title;
	
//	@OneToOne(cascade = CascadeType.ALL)
	@Embedded
	@JoinColumn(name = "metaId")
	@JsonProperty("metadata")
	@Field(type = FieldType.Nested, includeInParent = true)
	private Metadata metadata;
	
	@JsonProperty("status")
	@NotNull(message="Status cannot be blank")
	private String status;
	
	@JsonProperty("date")
	private long date;
	
	public Episode() {
		super();
	}

	public Episode(String title, Metadata metadata, String status, long date) {
		super();
		this.title = title;
		this.metadata = metadata;
		this.status = status;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

}