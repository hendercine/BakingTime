/*
 * Created by James Henderson on 2017
 * Copyright (c) Hendercine Productions and James Henderson 2017.
 * All rights reserved.
 *
 * Last modified 11/3/17 6:37 PM
 */

package com.hendercine.android.bakingtime.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.app.NotificationCompat.MediaStyle;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.hendercine.android.bakingtime.R;
import com.hendercine.android.bakingtime.data.bundlers.StepBundler;
import com.hendercine.android.bakingtime.data.models.Step;
import com.hendercine.android.bakingtime.utils.RemoveFragmentListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;

import static android.content.Context.NOTIFICATION_SERVICE;

public class StepsDetailFragment extends Fragment implements ExoPlayer.EventListener, PlaybackControlView.VisibilityListener {

    private static final String TAG = StepsDetailFragment.class.getSimpleName();
    private static final String CHANNEL_ID = "media_playback_channel";
    private static final String CURRENT_VIDEO_POSITION = "CURRENT_VIDEO_POSITION";
    private static final String STEP_INDEX = "STEP_INDEX";
    private static final String VIDEO_VISIBLE = "VIDEO_VISIBLE";
    private static MediaSessionCompat mMediaSession;
    private SimpleExoPlayer mExoPlayer;
    private PlaybackStateCompat.Builder mStateBuilder;
    private NotificationManager mNotificationManager;
    private RemoveFragmentListener removeListener;
    private ActionBar mActionBar;
    StepsDetailFragment stepsDetailFragment;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.step_description_text_view)
    TextView stepDescriptionView;
    @Nullable
    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView exoPlayerView;
    @Nullable
    @BindView(R.id.step_thumbnail_view)
    ImageView stepThumbnailView;
    @Nullable
    @BindView(R.id.no_vid_no_thumb_view)
    TextView noVidOrThumbView;
    @Nullable
    @BindView(R.id.next_step_btn)
    Button nextStepButton;
    @Nullable
    @BindView(R.id.prev_step_btn)
    Button prevStepButton;

    @State(StepBundler.class)
    Step mStep;
    @State
    Uri mStepVideoURL;
    @State
    Uri mStepThumbnailURL;
    @State
    String mStepDescription;
    @State
    ArrayList<Step> mStepDetailsList;
    @State
    String mRecipeName;
    @State
    int mStepIndex;
    @State
    long mVideoPosition;
    @State
    boolean mIsDualPane;
    @State
    boolean mIsVideoVisible;

    public StepsDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            removeListener = (RemoveFragmentListener) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(getActivity()
                    .getClass().getSimpleName()
                    + getString(R.string.remove_fragment_listener_error), e);
        }
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.hide();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            mRecipeName = getArguments().getString("recipe_name");
            mStep = Parcels.unwrap(getArguments().getParcelable("selected_step"));
            mStepDetailsList = Parcels.unwrap(getArguments().getParcelable("steps_list"));
            mStepIndex = getArguments().getInt("step_index");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this, rootView);

        // If view is not Dual Pane show the toolbar and send callback to
        // parent activity.
        if (!mIsDualPane && toolbar != null) {
            toolbar.setTitle(mRecipeName);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeListener.onRemoveFragment(TAG);

                }
            });
        }

        if (exoPlayerView != null) {
            exoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        }

        if (savedInstanceState != null) {
            mVideoPosition = savedInstanceState
                    .getLong(CURRENT_VIDEO_POSITION, C.TIME_UNSET);
        } else {
            mVideoPosition = C.TIME_UNSET;
        }

        // Initialize data variables
        initializeData(mStepIndex);

        // Initialize the Media Session
        initializeMediaSession();

        // Map the data to the views.
        showStepInstructions();

        if (!mIsDualPane) {
            nextStepButton = rootView.findViewById(R.id.next_step_btn);
            prevStepButton = rootView.findViewById(R.id.prev_step_btn);

            // Initialize the Navigation Buttons
            initializeNavButtons();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mVideoPosition = savedInstanceState.getLong(CURRENT_VIDEO_POSITION);
            mStepIndex = savedInstanceState.getInt(STEP_INDEX);
            mIsVideoVisible = savedInstanceState.getBoolean(VIDEO_VISIBLE);
            stepsDetailFragment = (StepsDetailFragment) getFragmentManager()
                    .getFragment(savedInstanceState, TAG);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mStepVideoURL != null) {
            showStepInstructions();
        }
        if (mExoPlayer != null) {
            mVideoPosition = mExoPlayer.getCurrentPosition();
            if (mVideoPosition != C.TIME_UNSET)
                mExoPlayer.seekTo(mVideoPosition);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mVideoPosition = mExoPlayer.getCurrentPosition();
        }
        releasePlayer();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActionBar.show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
        if (stepsDetailFragment != null) {
            getFragmentManager().putFragment(outState, TAG, stepsDetailFragment);
        }

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }


    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onVisibilityChange(int visibility) {

    }

    /**
     * Check that permission have been granted.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializePlayer(mStepVideoURL);
        } else {
            Toast.makeText(getContext(), R.string.permission_denied, Toast
                    .LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Helper method to store the data step data
     *
     * @param stepIndex is the position of the Step item touched by the user.
     */
    private void initializeData(int stepIndex) {
        mStepVideoURL = Uri.parse(mStepDetailsList.get(stepIndex).getVideoURL());
        mStepThumbnailURL = Uri.parse(mStepDetailsList.get(stepIndex).getThumbnailURL());
        mStepDescription = mStepDetailsList.get(stepIndex).getDescription();

    }

    /**
     * Initializes the Media Session to be enabled with media buttons, transport controls, callbacks
     * and media controller.
     */
    private void initializeMediaSession() {
        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(getContext(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        // MySessionCallback has methods that handle callbacks from a media controller.
        mMediaSession.setCallback(new MySessionCallback());

        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);
    }

    /**
     * Initializes navigation buttons for next and previous steps.
     */
    private void initializeNavButtons() {
        if (nextStepButton != null && prevStepButton != null) {
            nextStepButton.setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onClick(View v) {
                    if (mStepIndex < mStepDetailsList.size() - 1) {
                        mStepIndex++;
                    }
                    initializeData(mStepIndex);
                    if (exoPlayerView.getVisibility() == View.VISIBLE) {
                        releasePlayer();
                    } else {
                        exoPlayerView.setVisibility(View.VISIBLE);
                        stepThumbnailView.setVisibility(View.GONE);
                        noVidOrThumbView.setVisibility(View.GONE);
                    }
                    mVideoPosition = C.TIME_UNSET;
                    showStepInstructions();
                }
            });
            prevStepButton.setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onClick(View v) {
                    if (mStepIndex > mStep.getStepId()) {
                        mStepIndex--;
                    }
                    initializeData(mStepIndex);
                    if (exoPlayerView.getVisibility() == View.VISIBLE) {
                        releasePlayer();
                    } else {
                        exoPlayerView.setVisibility(View.VISIBLE);
                        stepThumbnailView.setVisibility(View.GONE);
                        noVidOrThumbView.setVisibility(View.GONE);
                    }
                    mVideoPosition = C.TIME_UNSET;
                    showStepInstructions();
                }
            });
        }
    }

    /**
     * Initialize ExoPlayer. Prep the MediaSource and create an instance of the
     * player. Set the EventListener and the player to the view. Store the
     * video time position and start the video at the current time position.
     *
     * @param mediaUri The URI of the video to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "BakingTime");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(),
                    null,
                    null);
            // Create an instance of the ExoPlayer.
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory trackFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(trackFactory);
            RenderersFactory renderersFactory = new DefaultRenderersFactory(getContext());
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(
                    renderersFactory,
                    trackSelector,
                    loadControl);
            // Set the player to the view.
            if (exoPlayerView != null) {
                exoPlayerView.setPlayer(mExoPlayer);
            }
            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);
            // Start playing the video from the media source at the current position.
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.seekTo(mVideoPosition);

        }
    }

    /**
     * Displays the video, thumbnail or step description.
     */
    @SuppressWarnings("ConstantConditions")
    private void showStepInstructions() {

        if (URLUtil.isNetworkUrl(mStepVideoURL.toString())) {
            // Initialize the Media Player
            initializePlayer(mStepVideoURL);
        } else if (URLUtil.isNetworkUrl(mStepThumbnailURL.toString())) {
            if (mStepThumbnailURL.toString().endsWith(".mp4")) {
                exoPlayerView.setVisibility(View.GONE);
                stepThumbnailView.setVisibility(View.GONE);
                noVidOrThumbView.setVisibility(View.VISIBLE);
                noVidOrThumbView.setText(mStepDescription);
            } else {
                exoPlayerView.setVisibility(View.GONE);
                stepThumbnailView.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(mStepThumbnailURL)
                        .into(stepThumbnailView);
            }
        } else {
            exoPlayerView.setVisibility(View.GONE);
            stepThumbnailView.setVisibility(View.GONE);
            noVidOrThumbView.setVisibility(View.VISIBLE);
            noVidOrThumbView.setText(mStepDescription);
            Toast.makeText(getContext(),
                    R.string.video_not_found,
                    Toast.LENGTH_SHORT).show();
        }

        if (stepDescriptionView != null) {
            stepDescriptionView.setText(mStepDescription);
        }
    }

    /**
     * Store the video position and then stop, release and nullify the player.
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            mVideoPosition = mExoPlayer.getCurrentPosition();
            mNotificationManager.cancelAll();
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    /**
     * Method that is called when the ExoPlayer state changes. Used to update the MediaSession
     * PlayBackState to keep in sync, and post the media notification.
     *
     * @param playWhenReady true if ExoPlayer is playing, false if it's paused.
     * @param playbackState int describing the state of ExoPlayer. Can be STATE_READY, STATE_IDLE,
     *                      STATE_BUFFERING, or STATE_ENDED.
     */
    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());
        showNotification(mStateBuilder.build());
    }

    /**
     * Shows Media Style notification, with actions that depend on the current MediaSession
     * PlaybackState.
     *
     * @param state The PlaybackState of the MediaSession.
     */
    private void showNotification(PlaybackStateCompat state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getContext(), CHANNEL_ID);

        int icon;
        String play_pause;
        if (state.getState() == PlaybackStateCompat.STATE_PLAYING) {
            icon = R.drawable.exo_controls_pause;
            play_pause = getString(R.string.pause);
        } else {
            icon = R.drawable.exo_controls_play;
            play_pause = getString(R.string.play);
        }

        NotificationCompat.Action playPauseAction = new NotificationCompat.Action(
                icon, play_pause,
                MediaButtonReceiver.buildMediaButtonPendingIntent(getContext(),
                        PlaybackStateCompat.ACTION_PLAY_PAUSE));

        NotificationCompat.Action restartAction = new android.support.v4.app.NotificationCompat
                .Action(R.drawable.exo_controls_previous, getString(R.string.restart),
                MediaButtonReceiver.buildMediaButtonPendingIntent
                        (getContext(), PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS));

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (getContext(), 0, new Intent(getContext(), StepsDetailFragment.class),
                        0);

        builder.setContentTitle(getString(R.string.instructions))
                .setContentText(getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.exo_controls_play)
                .addAction(restartAction)
                .addAction(playPauseAction)
                .setStyle(new MediaStyle()
                        .setMediaSession(mMediaSession.getSessionToken())
                        .setShowActionsInCompactView(0, 1));


        mNotificationManager = (NotificationManager) getContext()
                .getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }

    /**
     * Method to create channel for devices running API 26+
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationManager
                mNotificationManager =
                (NotificationManager) getContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);
        // The user-visible name of the channel.
        CharSequence name = "Media playback";
        // The user-visible description of the channel.
        String description = "Media playback controls";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        // Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.setShowBadge(false);
        mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        if (mNotificationManager != null) {
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    /**
     * Inner class to handle the media session callback.
     */
    private class MySessionCallback extends MediaSessionCompat.Callback {

        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekToDefaultPosition();
        }
    }

    /**
     * Broadcast Receiver registered to receive the MEDIA_BUTTON intent coming from clients.
     */
    public static class MediaReceiver extends BroadcastReceiver {

        public MediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }
}
