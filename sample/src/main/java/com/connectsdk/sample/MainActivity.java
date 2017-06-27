package com.connectsdk.sample;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.connectsdk.core.MediaInfo;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.ConnectableDeviceListener;
import com.connectsdk.device.DevicePicker;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.command.ServiceCommandError;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DiscoveryManager.init(this);
        setContentView(R.layout.activity_main);
        DiscoveryManager.getInstance().start();
        findViewById(R.id.discover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DevicePicker dp = new DevicePicker(MainActivity.this);
                AlertDialog dialog = dp.getPickerDialog("show", new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ConnectableDevice device = (ConnectableDevice) parent.getItemAtPosition(position);
                        device.addListener(new ConnectableDeviceListener() {
                            @Override
                            public void onDeviceReady(ConnectableDevice device) {
                                String mediaURL = "http://www.connectsdk.com/files/8913/9657/0225/test_video.mp4"; // credit: Blender Foundation/CC By 3.0
                                String iconURL = "http://www.connectsdk.com/files/2013/9656/8845/test_image_icon.jpg"; // credit: sintel-durian.deviantart.com
                                String title = "Sintel Trailer";
                                String description = "Blender Open Movie Project";
                                String mimeType = "video/mp4";
                                MediaInfo mediaInfo = new MediaInfo.Builder(mediaURL, mimeType)
                                        .setTitle(title)
                                        .setDescription(description)
                                        .setIcon(iconURL)
                                        .build();
                                device.getMediaPlayer().playMedia(mediaInfo, false, new MediaPlayer.LaunchListener() {
                                    @Override
                                    public void onSuccess(MediaPlayer.MediaLaunchObject object) {
                                    }

                                    @Override
                                    public void onError(ServiceCommandError error) {

                                    }
                                });
                            }

                            @Override
                            public void onDeviceDisconnected(ConnectableDevice device) {

                            }

                            @Override
                            public void onPairingRequired(ConnectableDevice device, DeviceService service, DeviceService.PairingType pairingType) {

                            }

                            @Override
                            public void onCapabilityUpdated(ConnectableDevice device, List<String> added, List<String> removed) {

                            }

                            @Override
                            public void onConnectionFailed(ConnectableDevice device, ServiceCommandError error) {

                            }
                        });
                        device.connect();
                    }
                });
                dialog.show();
            }
        });
        Log.v("zhangge", "end of mainactivity");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
