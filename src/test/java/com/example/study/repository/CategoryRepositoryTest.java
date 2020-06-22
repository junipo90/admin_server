package com.example.study.repository;

import com.example.study.model.entity.Category;
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
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category saveCategory = categoryRepository.save(category);
        Assert.assertNotNull(saveCategory);
        Assert.assertEquals(saveCategory.getType(), type);
        Assert.assertEquals(saveCategory.getTitle(), title);


    }

    @Test
    public void read(){
        String type = "COMPUTER";
//        Optional<Category> findCategory = categoryRepository.findById(1L);
        Optional<Category> findCategory = categoryRepository.findByType(type);
        findCategory.ifPresent(category -> {

            Assert.assertEquals(category.getType(), type);
            System.out.println(category.getType());
            System.out.println(category.getTitle());
        });
    }
}
