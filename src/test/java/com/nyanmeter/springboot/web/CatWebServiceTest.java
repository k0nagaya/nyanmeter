package com.nyanmeter.springboot.web;

import com.nyanmeter.springboot.model.Cat;
import com.nyanmeter.springboot.model.CatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatWebServiceTest {

    private CatWebService webService;

    @Mock
    private CatRepository repository;

    Cat c4 = new Cat(4L, "4", "4.jpg"),
        c2 = new Cat(2L, "2", "2.jpg"),
        c3 = new Cat(3L, "3", "3.jpg"),
        c1 = new Cat(1L, "1", "1.jpg");

    @BeforeEach
    void setup() {
        repository = mock(CatRepository.class);

        webService = new CatWebService(repository);

        c4.setRate(4);
        c2.setRate(2);
        c3.setRate(3);
        c1.setRate(1);
        when(repository.findAll()).thenReturn(Arrays.asList(c1, c2, c3, c4));
    }

    @Test
    public void testGetRating() {
        assertEquals(Arrays.asList(c4, c3, c2, c1), webService.getRating());
    }
}
