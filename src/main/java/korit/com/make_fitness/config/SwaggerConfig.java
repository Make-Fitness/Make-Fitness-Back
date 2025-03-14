package korit.com.make_fitness.config;



import java.util.Arrays;
import java.util.List;


public class SwaggerConfig {


//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("korit.com.make_fitness.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(getApiInfo())
//                .securitySchemes(Arrays.asList((getApiKey())))
//                .securityContexts(Arrays.asList(getSecurityContext()));
//    }
//
//    private ApiInfo getApiInfo() {
//
//        return new ApiInfoBuilder()
//                .title("API 문서 제목")
//                .description("API 문서 설명")
//                .version("1.8")
//                .contact(new Contact("관리자", "주소", "이메일"))
//                .build();
//    }
//
//    private ApiKey getApiKey() {
//
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext getSecurityContext() {
//
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }


}
