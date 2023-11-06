package com.service.catalog.controller;

import com.service.catalog.data.helper.ResponseHandler;
import com.service.catalog.data.response.Response;
import com.service.catalog.service.ServiceRouting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/allbackends")
public class GetAllBackendsController
{
    private final ServiceRouting serviceRouter;

    public GetAllBackendsController(ServiceRouting serviceRouter) {
        this.serviceRouter = serviceRouter;
    }

    @GetMapping("/{databaseName}/{serviceName}")
    public Response getAllBackends(@PathVariable("databaseName") String databaseName,
                                  @PathVariable("serviceName") String serviceName
    ) {
        serviceName = "%" + serviceName + "%";
        List<Object> getAllBackends = serviceRouter.getAllBackends(serviceName,databaseName);
        return ResponseHandler.handleResponse(getAllBackends, "getAllBackends");
    }
}