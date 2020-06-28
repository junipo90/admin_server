package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.request.AdminUserAPiRequest;
import com.example.study.model.network.response.AdminUserAPiResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController extends CrudController<AdminUserAPiRequest, AdminUserAPiResponse, AdminUser> {

}
