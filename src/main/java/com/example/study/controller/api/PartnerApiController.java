package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.service.PartnerApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    PartnerApiLogicService partnerApiLogicService;

    @GetMapping("")
    public Header<List<PartnerApiResponse>> search(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15)Pageable pageable){
        return partnerApiLogicService.search(pageable);
    }

}
