package com.airtnt.backend.image;

import com.airtnt.common.entity.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ImageRepositoryTest {

    @Autowired
    private ImageRepository repository;

    @Test
    public void testAddImage() {
        Image image = Image.builder().image("").build();

        repository.save(image);
    }

}