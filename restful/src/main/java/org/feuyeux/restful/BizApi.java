package org.feuyeux.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

@Api(value = "业务API", description = "demo项目业务API")
@Component
@Path("biz")
public class BizApi {
    /**
     * 查看RESTful接口定义：http://localhost:9666/api/application.wadl
     * 查看swagger动态文档：http://localhost:9666/api/swagger.json
     * springboot静态web资源：http://localhost:9666/ok.html
     * springboot swagger静态资源：
     *      http://localhost:9666/index.html
     *      注意要修改index.html的url = "/api/swagger.json";
     * 测试你好接口 curl "http://localhost:9666/api/biz/hello?name=buaa"
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "你好接口", httpMethod = "GET")
    @GET
    @Path("hello")
    public String hello(@QueryParam("name") String name) {
        return "hello " + name;
    }
}
