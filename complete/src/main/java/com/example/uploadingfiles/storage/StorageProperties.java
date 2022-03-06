package com.example.uploadingfiles.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties("storage")
public class StorageProperties {


	/**
	 * Folder location for storing files
	 */
	private String location ;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
