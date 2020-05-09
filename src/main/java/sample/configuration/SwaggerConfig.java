package sample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("sample"))
                .paths(PathSelectors.any())
                .build();
    }


    public ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring boot rest api")
                .description("Rest api for book shops")
                .version("1.0")
                .contact(new Contact("Dima Schepachkov", "http://google.com", "dimsa-1998@yandex.ru"))
                .build();
    }
}
