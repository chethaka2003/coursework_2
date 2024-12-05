package com.example.coursework;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ViewProjectsControllerTest {


    @Test
    void sortedAraay() {
        ViewProjectsController controller = new ViewProjectsController();

        // Add test data
        controller.keys = Arrays.asList(5, 3, 2, 4, 1);

        // Call the sorting method
        controller.sortedAraay();

        // Verify that the keys are sorted
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, controller.keys, "Keys should be sorted in ascending order");
    }
}
