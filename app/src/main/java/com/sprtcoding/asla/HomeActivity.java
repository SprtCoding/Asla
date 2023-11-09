package com.sprtcoding.asla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sprtcoding.asla.FragmentMenu.DeveloperFragment;
import com.sprtcoding.asla.FragmentMenu.FeedbackFragment;
import com.sprtcoding.asla.FragmentMenu.HomeFragment;
import com.sprtcoding.asla.FragmentMenu.OverviewFragment;
import com.sprtcoding.asla.FragmentMenu.ResourcesFragment;
import com.sprtcoding.asla.Sounds.ClickSoundUtils;
import com.sprtcoding.asla.Sounds.MusicService;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ImageView _volume;
    private MusicService musicService;
    private boolean isMusicBound = false;
    private ServiceConnection musicConnection;
    private ClickSoundUtils clickSoundUtils;
    private boolean isSoundOn = false;
    NavigationView navigationView;
    private AlertDialog.Builder exitAlertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        inits();
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar();

        // Set up the ServiceConnection
        musicConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
                musicService = binder.getService();
                isMusicBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isMusicBound = false;
            }
        };

        // Bind to the MusicService
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, musicConnection, Context.BIND_AUTO_CREATE);

        clickSoundUtils = new ClickSoundUtils(this);

        //exit alert dialog
        exitAlertBuilder = new AlertDialog.Builder(this);

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        if(!isSoundOn) {
            _volume.setImageResource(R.drawable.baseline_volume_off_24);
        }

        _volume.setOnClickListener(view -> {
            if (isSoundOn) {
                // Sound is currently on, turn it off
                _volume.setImageResource(R.drawable.baseline_volume_off_24);
                // Add logic to mute or disable sound here
                clickSoundUtils.playClickSound();
                // Pause the background music
                if (musicService != null) {
                    musicService.pauseMusic();
                }
            } else {
                // Sound is currently off, turn it on
                _volume.setImageResource(R.drawable.volume_up);
                // Add logic to unmute or enable sound here
                clickSoundUtils.playClickSound();
                // Resume or start the background music
                if (musicService != null) {
                    musicService.startMusic();
                }
            }
            // Toggle the sound state
            isSoundOn = !isSoundOn;
            clickSoundUtils.playClickSound();
        });
    }

    private void inits() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        _volume = findViewById(R.id.volume);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_overview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OverviewFragment()).commit();
                break;
            case R.id.nav_resources:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ResourcesFragment()).commit();
                break;
            case R.id.nav_developer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeveloperFragment()).commit();
                break;
            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedbackFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        if (musicService != null) {
            musicService.startMusic();
            isSoundOn = true;
            _volume.setImageResource(R.drawable.volume_up);
        }
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        if (isMusicBound) {
            unbindService(musicConnection);
            isMusicBound = false;
            _volume.setImageResource(R.drawable.baseline_volume_off_24);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            exitAlertBuilder.setTitle("Exit Application")
                    .setMessage("Are you sure want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        // Finish all activities in the stack and exit the app
                        finishAffinity();
                        System.exit(0);
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        dialogInterface.cancel();
                    })
                    .show();
        }
    }
}