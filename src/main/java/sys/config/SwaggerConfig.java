package sys.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket testApi() {
    	ParameterBuilder ticketPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<Parameter>();    
        ticketPar.name("token").description("鉴权")  
        .modelRef(new ModelRef("string")).parameterType("header")   
        .required(false).build(); //header中的ticket参数非必填，传空也可以  
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数 
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.basePackage("sys.controller"))  
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .globalOperationParameters(pars)
                .apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        ApiInfo apiInfo = new ApiInfo(
        		"后台管理系统接口",//大标题
                "后台管理系统接口",//小标题
                "2.0",//版本
                "123",
                "123",//作者
                "后台管理系统",//链接显示文字
                ""//网站链接
        );

        return apiInfo;
    }
    
}
