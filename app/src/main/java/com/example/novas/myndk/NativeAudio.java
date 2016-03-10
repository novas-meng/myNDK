/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.novas.myndk;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class NativeAudio extends Activity {

    //static final String TAG = "NativeAudio";

    static final int CLIP_NONE = 0;
    static final int CLIP_HELLO = 1;
    static final int CLIP_ANDROID = 2;
    static final int CLIP_SAWTOOTH = 3;
    static final int CLIP_PLAYBACK = 4;

    static String URI;
    static AssetManager assetManager;

    static boolean isPlayingAsset = false;
    static boolean isPlayingUri = false;

    static int numChannelsUri = 0;
    Audio audio;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        assetManager = getAssets();

        // initialize native audio system
        audio=new Audio();
        audio.createEngine();
        audio.createBufferQueueAudioPlayer();

        // initialize URI spinner
        Spinner uriSpinner = (Spinner) findViewById(R.id.uri_spinner);
        ArrayAdapter<CharSequence> uriAdapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_expandable_list_item_1);
      //  ArrayAdapter<> uriAdapter = ArrayAdapter.createFromResource(
           //     this, R.array.uri_spinner_array, android.R.layout.simple_spinner_item);
        uriAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uriSpinner.setAdapter(uriAdapter);
        uriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                URI = parent.getItemAtPosition(pos).toString();
            }

            public void onNothingSelected(AdapterView parent) {
                URI = null;
            }

        });

        // initialize button click handlers

        ((Button) findViewById(R.id.hello)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                // ignore the return value
                audio.selectClip(CLIP_HELLO, 5);
            }
        });

        ((Button) findViewById(R.id.android)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                // ignore the return value
                audio.selectClip(CLIP_ANDROID, 7);
            }
        });

        ((Button) findViewById(R.id.sawtooth)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                // ignore the return value
                audio.selectClip(CLIP_SAWTOOTH, 1);
            }
        });

        ((Button) findViewById(R.id.reverb)).setOnClickListener(new OnClickListener() {
            boolean enabled = false;
            public void onClick(View view) {
                enabled = !enabled;
                if (!audio.enableReverb(enabled)) {
                    enabled = !enabled;
                }
            }
        });

        ((Button) findViewById(R.id.embedded_soundtrack)).setOnClickListener(new OnClickListener() {
            boolean created = false;
            public void onClick(View view) {
                if (!created) {
                    created = audio.createAssetAudioPlayer(assetManager, "background.mp3");
                }
                if (created) {
                    isPlayingAsset = !isPlayingAsset;
                    audio. setPlayingAssetAudioPlayer(isPlayingAsset);
                }
            }
        });

        ((Button) findViewById(R.id.uri_soundtrack)).setOnClickListener(new OnClickListener() {
            boolean created = false;
            public void onClick(View view) {
                if (!created && URI != null) {
                    created = audio.createUriAudioPlayer(URI);
                }
             }
        });

        ((Button) findViewById(R.id.pause_uri)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                audio. setPlayingUriAudioPlayer(false);
             }
        });

        ((Button) findViewById(R.id.play_uri)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                audio.setPlayingUriAudioPlayer(true);
             }
        });

        ((Button) findViewById(R.id.loop_uri)).setOnClickListener(new OnClickListener() {
            boolean isLooping = false;
            public void onClick(View view) {
                isLooping = !isLooping;
                audio.setLoopingUriAudioPlayer(isLooping);
             }
        });

        ((Button) findViewById(R.id.mute_left_uri)).setOnClickListener(new OnClickListener() {
            boolean muted = false;
            public void onClick(View view) {
                muted = !muted;
                audio.setChannelMuteUriAudioPlayer(0, muted);
             }
        });

        ((Button) findViewById(R.id.mute_right_uri)).setOnClickListener(new OnClickListener() {
            boolean muted = false;
            public void onClick(View view) {
                muted = !muted;
                audio.setChannelMuteUriAudioPlayer(1, muted);
             }
        });

        ((Button) findViewById(R.id.solo_left_uri)).setOnClickListener(new OnClickListener() {
            boolean soloed = false;
            public void onClick(View view) {
                soloed = !soloed;
                audio.setChannelSoloUriAudioPlayer(0, soloed);
             }
        });

        ((Button) findViewById(R.id.solo_right_uri)).setOnClickListener(new OnClickListener() {
            boolean soloed = false;
            public void onClick(View view) {
                soloed = !soloed;
                audio.setChannelSoloUriAudioPlayer(1, soloed);
             }
        });

        ((Button) findViewById(R.id.mute_uri)).setOnClickListener(new OnClickListener() {
            boolean muted = false;
            public void onClick(View view) {
                muted = !muted;
                audio.setMuteUriAudioPlayer(muted);
             }
        });

        ((Button) findViewById(R.id.enable_stereo_position_uri)).setOnClickListener(
                new OnClickListener() {
            boolean enabled = false;
            public void onClick(View view) {
                enabled = !enabled;
                audio.enableStereoPositionUriAudioPlayer(enabled);
             }
        });

        ((Button) findViewById(R.id.channels_uri)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (numChannelsUri == 0) {
                    numChannelsUri = audio.getNumChannelsUriAudioPlayer();
                }
                Toast.makeText(NativeAudio.this, "Channels: " + numChannelsUri,
                        Toast.LENGTH_SHORT).show();
             }
        });

        ((SeekBar) findViewById(R.id.volume_uri)).setOnSeekBarChangeListener(
                new OnSeekBarChangeListener() {
            int lastProgress = 100;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                assert progress >= 0 && progress <= 100;
                lastProgress = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                int attenuation = 100 - lastProgress;
                int millibel = attenuation * -50;
                audio.setVolumeUriAudioPlayer(millibel);
            }
        });

        ((SeekBar) findViewById(R.id.pan_uri)).setOnSeekBarChangeListener(
                new OnSeekBarChangeListener() {
            int lastProgress = 100;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                assert progress >= 0 && progress <= 100;
                lastProgress = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                int permille = (lastProgress - 50) * 20;
                audio.setStereoPositionUriAudioPlayer(permille);
            }
        });

        ((Button) findViewById(R.id.record)).setOnClickListener(new OnClickListener() {
            boolean created = false;
            public void onClick(View view) {
                if (!created) {
                    created = audio.createAudioRecorder();
                }
                if (created) {
                    audio.startRecording();
                }
            }
        });

        ((Button) findViewById(R.id.playback)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                // ignore the return value
                audio.selectClip(CLIP_PLAYBACK, 3);
            }
        });

    }

    /** Called when the activity is about to be destroyed. */
    @Override
    protected void onPause()
    {
        // turn off all audio
        audio.selectClip(CLIP_NONE, 0);
        isPlayingAsset = false;
        audio.setPlayingAssetAudioPlayer(false);
        isPlayingUri = false;
        audio.setPlayingUriAudioPlayer(false);
        super.onPause();
    }

    /** Called when the activity is about to be destroyed. */
    @Override
    protected void onDestroy()
    {
        audio.shutdown();
        super.onDestroy();
    }


}
