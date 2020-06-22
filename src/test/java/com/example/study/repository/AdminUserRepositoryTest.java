package com.example.study.repository;

import com.example.study.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminUserRepositoryTest {

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser02");
        adminUser.setPassword("AdminUser02");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("SUPER");
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser saveAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(saveAdminUser);
    }

    @Test
    public void update(){
        Optional<AdminUser> findAdminUser = adminUserRepository.findById(2L);
        findAdminUser.ifPresent(adminUser -> {
            adminUser.setAccount("changed02");
            adminUserRepository.save(adminUser);
        });
    }
}
