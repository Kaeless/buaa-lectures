package org.feuyeux.restful;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(BizApi.class);

        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        /**
         * Swagger识别RESTful API后，会生成swagger.json供Swagger UI展示。
         * 我们要将其喂给Swagger UI，src/main/resources/public/index.html：
         * url = "/swagger.json";
         */
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http","https"});
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("org.feuyeux.restful");
        beanConfig.setScan(true);
    }
}
