package com.hendercine.android.bakinbuns.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.hendercine.android.bakinbuns.R;
import com.hendercine.android.bakinbuns.data.models.Step;

import org.parceler.Parcels;

import butterknife.BindView;
import icepick.State;

///**
// * A fragment representing a single Recipe detail screen.
// * This fragment is either contained in a {@link RecipeStepsActivity}
// * in two-pane mode (on tablets) or a {@link StepsDetailActivity}
// * on handsets.
// */

public class StepsDetailFragment extends Fragment {

    //    /**
//     * The fragment argument representing the item ID that this fragment
//     * represents.
//     */
    public static final String ARG_ITEM_ID = "step_details";

    @State
    boolean mIsDualPane;
    private Step mStep;
    private SimpleExoPlayer mExoPlayer;
    private static MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private static final String TAG = StepsDetailFragment.class.getSimpleName();

    @Nullable
    @BindView(R.id.step_description_text_view)
    TextView stepDescriptionView;

    @Nullable
    @BindView(R.id.exo_player_view)
    SimpleExoPlayerView exoPlayerView;

    @Nullable
    @BindView(R.id.step_description_btn)
    Button mNextButton;

    public StepsDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mStep = Parcels.unwrap(getArguments().getParcelable(ARG_ITEM_ID));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View stepsDetailView = inflater.inflate(R.layout.fragment_step_detail,
                container, false);

        initializeMediaSession();

        String stepVideoURLUrl = mStep.getVideoURL();

        if (stepVideoURLUrl == null) {
            Toast.makeText(getActivity(), R.string.video_not_found, Toast.LENGTH_SHORT).show();
        }

        initializePlayer(Uri.parse(stepVideoURLUrl));

        assert stepDescriptionView != null;
        stepDescriptionView.setText(mStep.getDescription());

        return stepsDetailView;
    }

    private void initializeMediaSession() {
        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(getActivity(), TAG);

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
     * Initialize ExoPlayer.
     *
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            RenderersFactory renderersFactory = new RenderersFactory() {
                @Override
                public Renderer[] createRenderers(Handler eventHandler,
                                                  VideoRendererEventListener videoRendererEventListener,
                                                  AudioRendererEventListener audioRendererEventListener,
                                                  TextRenderer.Output textRendererOutput,
                                                  MetadataRenderer.Output metadataRendererOutput) {
                    return new Renderer[0];
                }
            };
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory
                    .newSimpleInstance(renderersFactory, trackSelector, loadControl);
            exoPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener((ExoPlayer.EventListener) getActivity());

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), TAG);
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(getActivity(), userAgent),
                    new DefaultExtractorsFactory(),
                    null,
                    null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    public void setStepDetails(Step stepDetails) {
        mStep = stepDetails;
    }

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

//    @Override
//    public void onItemClick(View view, int position) {
//
//    }
}
