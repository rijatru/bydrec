package com.test.business.fixtures;

import com.test.business.fixtures.api.Fixture;
import com.test.business.fixtures.api.FixturesController;
import com.test.business.fixtures.api.FixturesControllerFactory;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FixturesControllerImplIntegrationTest {

    @Test
    public void test_getFixtures_returnsListOfFixtures() {
        FixturesController fixturesController = FixturesControllerFactory.getFixturesController();

        List<Fixture> fixtures = fixturesController.getFixtures();

        assertNotNull(fixtures);
        assertEquals(17, fixtures.size());
    }
}
