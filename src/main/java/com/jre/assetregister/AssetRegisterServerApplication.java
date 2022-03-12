package com.jre.assetregister;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AssetRegisterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetRegisterServerApplication.class, args);
	}

}
