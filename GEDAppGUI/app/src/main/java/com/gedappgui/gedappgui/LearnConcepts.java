/*
 * LearnConcepts.java
 *
 * Learn Concepts page activity
 *
 * Gives a "trail" of math concepts that a student can choose to start
 * Each concept has multiple lessons
 *
 * Worked on by:
 * Myanna Harris
 * Kristina Spring
 * Jasmine Jans
 * Jimmy Sherman
 *
 * Last Edit: 11-6-16
 *
 */

package com.gedappgui.gedappgui;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LearnConcepts extends AppCompatActivity {
    File file;
    /*
     * Starts the activity and shows corresponding view on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_concepts);

        // Allow homeAsUpIndicator (back arrow) to display on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Allow user to control audio with volume buttons on phone
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    /*
        Opens the apps database in a file that the class can now read
     */
    public SQLiteDatabase openDB() {
        //file = new File(getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath(), "GEDPrep.db");
        file = new File(this.getApplication().getFilesDir(), "GEDPrep.db");
        return openOrCreateDatabase(file.getPath(), MODE_PRIVATE, null);
    }

    /*
        An example for a specific query you may want to implement
        in this Activity. All queries should follow this structure.
     */
    public void testQuery(SQLiteDatabase db){
        //db.insert("test", String "0", ContentValues values)
        db.execSQL("INSERT INTO test(ID) VALUES (2)");
        Cursor c = db.rawQuery("SELECT * FROM test", null);

        while (c.moveToNext()) {
            System.out.println(c.getString(0));
        }

        c.close();
    }

    /* 
     * Shows and hides the bottom navigation bar when user swipes at it on screen
     * Called when the focus of the window changes to this activity
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            //View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    /*
     * Sets what menu will be in the action bar
     * homeonlymenu has the settings button and the home button
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homeonlymenu, menu);
        return true;
    }

    /*
     * Listens for selections from the menu in the action bar
     * Does action corresponding to selected item
     * home = goes to homescreen
     * settings = goes to settings page
     * android.R.id.home = go to the activity that called the current activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intentHomeConcept = new Intent(this, MainActivity.class);
                intentHomeConcept.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentHomeConcept);
                return true;
            // action with ID action_refresh was selected
            case R.id.action_home:
                Intent intentHome = new Intent(this, MainActivity.class);
                intentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentHome);
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

    /*
     * Called whena concept button is clicked
     * Opens the lesson "trail" page
     */
    public void gotToLesson(View view) {
        Intent intent = new Intent(this, LearnLessons.class);
        startActivity(intent);
    }

}
