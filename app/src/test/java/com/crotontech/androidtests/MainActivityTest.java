package com.crotontech.androidtests;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;

import com.crotontech.androidtests.shadows.ShadowSnackbar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, shadows = ShadowSnackbar.class)
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
        // fab.callOnClick();
        assertNotNull(activity);

    }

    @Test
    public void checkActivityHasToolbar() throws Exception {
        assertNotNull(activity.findViewById(R.id.toolbar));
    }

    @Test
    public void testFloatingBtnOnClick() throws Exception {
        activity.onClick(fab);
        Assert.assertNotNull(ShadowSnackbar.getLatestSnackbar());
        Assert.assertEquals("Replace with your own action", ShadowSnackbar.getTextOfLatestSnackbar());
        ShadowSnackbar.reset();

    }

    @Test
    public void menuSetUpSuccessfully() throws Exception {
        PopupMenu p = new PopupMenu(activity, null);
        Menu menu = p.getMenu();
        assertTrue(activity.onCreateOptionsMenu(menu));
        assertNotNull(menu.findItem(R.id.action_settings));
    }
    @Test
    public void menuItemSetUpSuccessfully() throws Exception {
        MenuItem item = new RoboMenuItem(R.id.test_menu_item);

        assertFalse(activity.onOptionsItemSelected(item));
    }

    @Test
    public void menuItemActionSettingsClicked() throws Exception {
        PopupMenu p = new PopupMenu(activity, null);
        Menu menu = p.getMenu();
        assertTrue(activity.onCreateOptionsMenu(menu));
        MenuItem actionSettings = menu.findItem(R.id.action_settings);
        assertTrue(activity.onOptionsItemSelected(actionSettings));
    }
}