package com.example.study.repository;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.enumclass.OrderGroupStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderGroupRepositoryTest {

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus(OrderGroupStatus.COMPLETE);
        orderGroup.setOrderType(OrderType.ALL);
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("이순신");
        orderGroup.setPaymentType(PaymentType.CARD);
        orderGroup.setTotalPrice(BigDecimal.valueOf(100000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());

        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");

//        orderGroup.setUserId(1L);

        OrderGroup saveOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(saveOrderGroup);
    }

    @Test
    @Transactional
    public void read(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);
        orderGroup.ifPresent(orderGroup1 -> {
            System.out.println(orderGroup1.getRevAddress());
            System.out.println(orderGroup1.getPaymentType());
        });
        Assert.assertNotNull(orderGroup);

    }
}
