package com.testngissue.tests;

import com.testngissue.util.DefaultListener;
import com.testngissue.util.Team;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.fail;

@Test
@Listeners(value = DefaultListener.class)
public class SampleTests {

    // Please run the test class with DefaultListener as a listener.

    // This test is not annotated with @Test and will not be ignored - however, the whole class
    // is annotated with @Test. Seems superfluous.
    @Team(name = "Team2")
    public void notAnnotatedTest() {
        fail("Test supposed to be ignored");
    }

    // This test is annotated with @Test and will be ignored.
    @Test
    @Team(name = "Team2")
    public void annotatedTest() {
        fail("Test supposed to be ignored");
    }
}
