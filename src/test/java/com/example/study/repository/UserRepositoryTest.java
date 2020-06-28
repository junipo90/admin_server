package com.example.study.repository;

import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {

        String account = "TestUser01";
        String password = "TestUser01_password";
        String status = "REGISTERED";
        String email = "TestUser01@gmail.com";
        String phoneNumber = "010-111-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);
        user.setRegisteredAt(registeredAt);

        User newUser = userRepository.save(user);
        System.out.println(newUser);
        Assert.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read() {

//        Optional<User> user = userRepository.findById(1L);

//        user.ifPresent(selectUser -> {
//            System.out.println("selectUser = " + selectUser);
//            System.out.println("selectUser.getEmail() = " + selectUser.getEmail());
//        });

//        Optional<User> user = userRepository.findByAccount("TestUser04");
//
//        user.ifPresent(selectUser -> {
//            selectUser.getOrderDetailList().stream().forEach(orderDetail ->{
//                Item item = orderDetail.getItem();
//                System.out.println(item);
//            });
//        });

        User findUser = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-111-0001");
        if (findUser != null) {
            findUser.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("================주문 묶음=================");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총 수량 : " + orderGroup.getTotalQuantity());
                System.out.println("총 금액 : " + orderGroup.getTotalPrice());

                System.out.println("================주문 상세=================");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("제품 이름 : " + orderDetail.getItem().getName());
                    System.out.println("콜센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("도착 시간 : " + orderDetail.getArrivalDate());
                    System.out.println("주문 상태 : " + orderDetail.getStatus());
                });
            });
        }
//        Assert.assertNotNull(findUser);

    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectUser -> {
            selectUser.setAccount("updateTestUser02");
            selectUser.setEmail("updateTestUser02@gmail.com");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional //test 하고 데이터 다시 rollback
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent());

    }
}
