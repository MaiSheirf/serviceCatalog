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
@RequestMapping("/callers")
public class GetCallersController {
    private final ServiceRouting serviceRouter;

    public GetCallersController(ServiceRouting serviceRouter) {
        this.serviceRouter = serviceRouter;
    }

    @GetMapping("/{databaseName}/{caller}")
    public Response getCallers(@PathVariable("databaseName") String databaseName
        ,@PathVariable("caller") String caller
    ) {
        caller ="%" + caller + "%" ;

        if (caller.equals("NA")){
            caller = null;
        }

        List<Object> getCallers = serviceRouter.getCallers(caller ,databaseName);
        return ResponseHandler.handleResponse(getCallers, "getCallers");
    }
}
