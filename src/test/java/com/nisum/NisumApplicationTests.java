package com.nisum;

import com.nisum.controllers.PersonaInfoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class NisumApplicationTests {

    @Autowired
    private PersonaInfoController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
