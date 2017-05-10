package ona.io.androidtests;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;

import com.crotontech.androidtests.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private MainActivity activity;
    FloatingActionButton fab;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
        fab = (FloatingActionButton) activity.findViewById(R.id.fab);

    }

    @Test
    public void testFixtures() throws Exception {
        assertNotNull(activity);
        assertNotNull(fab);
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        fab.callOnClick();
        assertNotNull(activity);
    }

    @Test
    public void checkActivityHasToolbar() throws Exception {
        assertNotNull(activity.findViewById(R.id.toolbar));
    }

    @Test
    public void menuSetUpSuccessfully() throws Exception {
        PopupMenu p = new PopupMenu(activity, null);
        Menu menu = p.getMenu();
        assertTrue(activity.onCreateOptionsMenu(menu));
        assertNotNull(menu.findItem(R.id.action_settings));
    }
}