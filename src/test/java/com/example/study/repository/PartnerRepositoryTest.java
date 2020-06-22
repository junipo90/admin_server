package com.example.study.repository;

import com.example.study.model.entity.Partner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PartnerRepositoryTest {

    @Autowired
    PartnerRepository partnerRepository;

    @Test
    public void create(){
        String name = "Partner01";
        String status = "REGISTERED";
        String address = "군포시 산본역";
        String callCenter = "070-111-1111";
        String partnerNumber = "010-111-1111";
        String businessNumber = "1234567890123";
        String ceoName = "이순신";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);

//        partner.setCategoryId(1L);

        Partner savePartner = partnerRepository.save(partner);

        Assert.assertNotNull(savePartner);
        Assert.assertEquals(savePartner.getName(), name);

    }

    @Test
    public void read(){

    }
}
