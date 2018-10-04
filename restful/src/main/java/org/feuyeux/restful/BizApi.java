package org.feuyeux.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

@Api(value = "业务API")
@Component
@Path("biz")
public class BizApi {
    @ApiOperation(value = "你好接口", httpMethod = "GET")
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("name") String name) {
        return "hello " + name;
    }
}
