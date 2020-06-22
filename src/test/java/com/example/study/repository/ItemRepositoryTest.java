package com.example.study.repository;

import com.example.study.model.entity.Item;
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
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("애플 노트북");
        item.setTitle("맥북 프로 2018");
        item.setContent("Mac Pro 2018");
        item.setPrice(100000);
        item.setBrandName("apple");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");

//        item.setPartnerId(1L);

        Item saveItem = itemRepository.save(item);
        Assert.assertNotNull(saveItem);
    }

    @Test
    public void read() {
        Long itemId = 1L;
        Optional<Item> findItem = itemRepository.findById(itemId);
        Assert.assertTrue(findItem.isPresent());
        findItem.ifPresent(item -> System.out.println(item));
    }
}
