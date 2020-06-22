package com.example.study.controller;

import com.example.study.model.SearchParameter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //Localhost:8080/api
public class GetController {

    //Localhost:8080/api/getMethod
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest(){

        return "Hi getMethod";

    }

    //Localhost/api/getParameter?id=1234&password=yyyymmdd
    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "abcd";
        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id+pwd;
    }

    //Localhost:8080/api/getMultiParameter?account=abcd&email=study@gamil.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParameter getMultiParameter(SearchParameter searchParameter){
        System.out.println(searchParameter.getAccount());
        System.out.println(searchParameter.getEmail());
        System.out.println(searchParameter.getPage());

        //JSON
        // { "account" = "", "email" = "", "page" = 0 }

        return searchParameter;
    }


}
