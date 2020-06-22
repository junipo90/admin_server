package com.example.study.repository;

import com.example.study.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderAt(LocalDateTime.now());
////        orderDetail.setUserId(1L);
////        orderDetail.setItemId(1L);
//
//        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
//        Assert.assertNotNull(saveOrderDetail);
    }
}
