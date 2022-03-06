package com.example.uploadingfiles;

import com.example.uploadingfiles.storage.FileSystemStorageService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.uploadingfiles.storage.StorageProperties;
import com.example.uploadingfiles.storage.StorageService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class UploadingFilesApplication {
	@Autowired
	private static ApplicationContext context;
    public static void main(String[] args) {
		context.
//		SpringApplication springApplication =new SpringApplication();
//		springApplication.setBeanNameGenerator();
       SpringApplication.run(UploadingFilesApplication.class, args);
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/parser-application.properties");
            property.load(fis);

            String dir = property.getProperty("upload_dir");


        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        DefaultListableBeanFactory context =
                new DefaultListableBeanFactory();
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(FileSystemStorageService.class);
        gbd.setBeanClass(StorageProperties.class);
        MutablePropertyValues propertyValues =new MutablePropertyValues();
        propertyValues.addPropertyValue("location", property.getProperty("upload_dir"));
        gbd.setPropertyValues(propertyValues);


    }


//    GenericBeanDefinition gbd = new GenericBeanDefinition();
//      gbd.setBeanClass(MyBean .class);
//    MutablePropertyValues mpv = new MutablePropertyValues();
//      mpv.add("date",new
//
//    Date());
//    //alternatively we can use:
//    // gbd.getPropertyValues().addPropertyValue("date", new Date());
//      gbd.setPropertyValues(mpv);
//      context.registerBeanDefinition("myBeanName",gbd);
//    MyBean bean = context.getBean(MyBean.class);
//      bean.doSomething();
//}


//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
//            storageService.init();
//        };
//    }


}
