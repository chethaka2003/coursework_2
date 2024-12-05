package com.example.coursework;

import com.example.coursework.projects.Projects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RandomSpotlightControllerTest {

    private RandomSpotlightController controller;

    @BeforeEach
    void setUp() {
        controller = new RandomSpotlightController();
    }

    @Test
    void judgeFourRemove() {
        controller.judge_4 = 3;
        assertEquals(3, controller.judge_4);
    }

    @Test
    void judgeFouradd() {
        controller.judge_4 = 2;
        assertEquals(2, controller.judge_4);
    }

    @Test
    void judgeOneRemove() {
        controller.judge_1 = 1;
        assertEquals(1, controller.judge_1);
    }

    @Test
    void judgeOneadd() {
        controller.judge_1 = 0;
        assertEquals(0, controller.judge_1);
    }

    @Test
    void judgeThreeRemove() {
        controller.judge_3 = 4;
        assertEquals(4, controller.judge_3);
    }

    @Test
    void judgeThreeadd() {
        controller.judge_3 = 3;
        assertEquals(3, controller.judge_3);
    }

    @Test
    void judgetwoRemove() {
        controller.judge_2 = 5;
        assertEquals(5, controller.judge_2);
    }

    @Test
    void judgetwoadd() {
        controller.judge_2 = 4;
        assertEquals(4, controller.judge_2);
    }

    @Test
    void nextItem() {
        // Setup projects for testing
        addProject_controller.projects.put(1, new Projects(1, "Project1", "Description1", "Category1", new String[]{"Member1"}, "Country1", "path1"));
        addProject_controller.projects.put(2, new Projects(2, "Project2", "Description2", "Category2", new String[]{"Member2"}, "Country2", "path2"));
        controller.shuffledAllKeys.add(1);
        controller.shuffledAllKeys.add(2);

        controller.judge_1 = 1;
        controller.judge_2 = 2;
        controller.judge_3 = 3;
        controller.judge_4 = 4;


        assertEquals(1, controller.judge_1);
        assertEquals(2, controller.judge_2);
        assertEquals(3, controller.judge_3);
        assertEquals(4, controller.judge_4);
    }


    @Test
    void shuffleKeys() {
        HashMap<Integer, Projects> projectsMap = new HashMap<>();
        projectsMap.put(1, new Projects(1, "Project1", "Description1", "Category1", new String[]{"Member1"}, "Country1", "path1"));
        projectsMap.put(2, new Projects(2, "Project2", "Description2", "Category2", new String[]{"Member2"}, "Country2", "path2"));

        List<Integer> shuffledKeys = controller.shuffleKeys(projectsMap);

        assertEquals(2, shuffledKeys.size());
        assertTrue(shuffledKeys.contains(1));
        assertTrue(shuffledKeys.contains(2));
    }

    @Test
    void resetStars() {
        controller.judge_1 = 1;
        controller.judge_2 = 2;
        controller.judge_3 = 3;
        controller.judge_4 = 4;


        assertEquals(1, controller.judge_1);
        assertEquals(2, controller.judge_2);
        assertEquals(3, controller.judge_3);
        assertEquals(4, controller.judge_4);
    }
}
