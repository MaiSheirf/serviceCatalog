package com.service.catalog.controller;

import com.service.catalog.data.helper.ResponseHandler;
import com.service.catalog.data.response.Response;
import com.service.catalog.service.ServiceRouting;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class GetUsersController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ServiceRouting serviceRouter;

    public GetUsersController(ServiceRouting serviceRouter) {
        this.serviceRouter = serviceRouter;
    }

    @GetMapping("/{databaseName}/{user}")
    public Response getUsers(@PathVariable("databaseName") String databaseName
            ,@PathVariable("user") String user
    ) {
        logger.trace("Controller will serve func [{}] through DB [{}] with variables sent [{}]",
                "ServiceNames",databaseName,user);
        user ="%" + user + "%" ;

//        if (user.equals("NA")){
//            user = null;
//        }

        List<Object> getUsers = serviceRouter.getUsers(user ,databaseName);
        return ResponseHandler.handleResponse(getUsers, "getUsers");
    }
}
