package com.example.study.repository;

import com.example.study.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("맥북");

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
