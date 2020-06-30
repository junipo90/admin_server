package com.example.study.service;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest requestData = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .status(requestData.getStatus())
                .orderType(requestData.getOrderType())
                .revAddress(requestData.getRevAddress())
                .revName(requestData.getRevName())
                .paymentType(requestData.getPaymentType())
                .totalPrice(requestData.getTotalPrice())
                .totalQuantity(requestData.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .arrivalDate(requestData.getArrivalDate())
                .user(userRepository.getOne(requestData.getUserId()))
                .build();
        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return Header.OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> Header.OK(response(orderGroup)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest orderGroupApiRequest = request.getData();
        return baseRepository.findById(orderGroupApiRequest.getId())
                .map(orderGroup -> {
                    orderGroup.setStatus(orderGroupApiRequest.getStatus())
                            .setOrderType(orderGroupApiRequest.getOrderType())
                            .setRevAddress(orderGroupApiRequest.getRevAddress())
                            .setRevName(orderGroupApiRequest.getRevName())
                            .setPaymentType(orderGroupApiRequest.getPaymentType())
                            .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                            .setTotalQuantity(orderGroupApiRequest.getTotalQuantity())
                            .setOrderAt(orderGroupApiRequest.getOrderAt())
                            .setArrivalDate(orderGroupApiRequest.getArrivalDate())
                            .setUser(userRepository.getOne(orderGroupApiRequest.getUserId()));
                    return orderGroup;
                })
                .map(orderGroup -> baseRepository.save(orderGroup))
                .map(orderGroup -> Header.OK(response(orderGroup)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public OrderGroupApiResponse response(OrderGroup orderGroup) {
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return orderGroupApiResponse;
    }

    public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {
        Page<OrderGroup> orderGroups = baseRepository.findAll(pageable);

        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroups.stream()
                .map(orderGroup -> response(orderGroup))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(orderGroups.getTotalPages())
                .totalElements(orderGroups.getTotalElements())
                .currentPage(orderGroups.getNumber())
                .currentElements(orderGroups.getNumberOfElements())
                .build();
        return Header.OK(orderGroupApiResponseList, pagination);
    }
}
