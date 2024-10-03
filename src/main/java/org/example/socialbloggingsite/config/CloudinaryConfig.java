package org.example.socialbloggingsite.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dm3frsorf",
                "api_key", "698536212837215",
                "api_secret", "L4uve4NlE36uGi3-72vQccqyPV4",
                "secure", true
        ));
    }
}
