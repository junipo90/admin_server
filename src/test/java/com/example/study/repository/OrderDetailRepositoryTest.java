package com.example.study.repository;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.enumclass.OrderDetailStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(OrderDetailStatus.CONFIRM);
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(10);
        orderDetail.setTotalPrice(BigDecimal.valueOf(1000000));

//        orderDetail.setOrderGroupId(1L);
//        orderDetail.setItemId(1L);

        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(saveOrderDetail);
    }
}
