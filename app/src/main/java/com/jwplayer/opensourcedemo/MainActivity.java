package com.jwplayer.opensourcedemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.ads.Ad;
import com.longtailvideo.jwplayer.media.ads.AdBreak;
import com.longtailvideo.jwplayer.media.ads.AdSource;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements VideoPlayerEvents.OnFullscreenListener {

	/**
	 * Reference to the JW Player View
	 */
	JWPlayerView mPlayerView;

	/**
	 * An instance of our event handling class
	 */
	private JWEventHandler mEventHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPlayerView = (JWPlayerView)findViewById(R.id.jwplayer);
		TextView outputTextView = (TextView)findViewById(R.id.output);

		// Handle hiding/showing of ActionBar
		mPlayerView.addOnFullscreenListener(this);

		// Instantiate the JW Player event handler class
		mEventHandler = new JWEventHandler(mPlayerView, outputTextView);

		// Load a media source
		//PlaylistItem pi = new PlaylistItem("http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8");

		final int timestamp = (int) (System.currentTimeMillis() / 1000L);
		Ad ad = new Ad(AdSource.IMA, "https://ads-pebblemedia.adhese.com/ad/sl_test-sporza_preroll_-preroll?t=" + timestamp);

		AdBreak adBreak = new AdBreak("pre", ad);
		LinkedList<AdBreak> schedule = new LinkedList<>();
		schedule.add(adBreak);

		PlaylistItem pi = new PlaylistItem.Builder()
			.file(
				"http://vod.stream.vrt.be/mediazone_canvas/_definst_/smil:2016/01/mz-ast-0ae9ee02-9cae-48de-9a71-9ba5d1c7d29d/video.smil/playlist.m3u8")
			.adSchedule(schedule)
			.build();


		mPlayerView.load(pi);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// Set fullscreen when the device is rotated to landscape
		mPlayerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onResume() {
		// Let JW Player know that the app has returned from the background
		mPlayerView.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		// Let JW Player know that the app is going to the background
		mPlayerView.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// Let JW Player know that the app is being destroyed
		mPlayerView.onDestroy();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Exit fullscreen when the user pressed the Back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mPlayerView.getFullscreen()) {
				mPlayerView.setFullscreen(false, true);
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Handles JW Player going to and returning from fullscreen by hiding the ActionBar
	 *
	 * @param fullscreen true if the player is fullscreen
	 */
	@Override
	public void onFullscreen(boolean fullscreen) {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			if (fullscreen) {
				actionBar.hide();
			} else {
				actionBar.show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
