package com.paging.com.mysample;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class ExampleTest {
    Main2Activity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(Main2Activity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void chekProgressVisibility(){
        Button button = activity.findViewById(R.id.click);

        ProgressBar progressBar = activity.findViewById(R.id.progress);
        assertEquals(progressBar.getVisibility(), View.VISIBLE);
        button.performClick();
        assertEquals(progressBar.getVisibility(), View.GONE);
    }
//    @Test
//    public void continueShouldLaunchMineActivity() {
//        // define the expected results
//        Intent expectedIntent = new Intent(activity, FullImageActivity.class);
//        // click the continue button
//        activity.findViewById(R.id.fab).callOnClick();
//        // get the actual results
//        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
//        Intent actualIntent = shadowActivity.getNextStartedActivity();
//        // check if the expected results match the actual results
//        assertTrue(expectedIntent.filterEquals(actualIntent));
//    }
//
//    @Test
//    public void toastMsg() {
//        // define the expected results
//        Intent expectedIntent = new Intent(activity, FullImageActivity.class);
//        // click the continue button
//        activity.findViewById(R.id.fab).callOnClick();
//        assertEquals("RAM", ShadowToast.getTextOfLatestToast());  // --> ShadowToast.getTextOfLatestToast() is always null
//    }

}
