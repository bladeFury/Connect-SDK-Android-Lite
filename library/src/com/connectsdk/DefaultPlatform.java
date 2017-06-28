package com.connectsdk;

import com.connectsdk.discovery.provider.SSDPDiscoveryProvider;
import com.connectsdk.discovery.provider.ZeroconfDiscoveryProvider;
import com.connectsdk.service.AirPlayService;
import com.connectsdk.service.DLNAService;

import java.util.HashMap;


public class DefaultPlatform {
	
	

	public DefaultPlatform() {
	}

	public static HashMap<Class, Class> getDeviceServiceMap() {
		HashMap<Class, Class> devicesList = new HashMap<Class, Class>();
//		devicesList.put("com.connectsdk.service.WebOSTVService", "com.connectsdk.discovery.provider.SSDPDiscoveryProvider");
//		devicesList.put("com.connectsdk.service.NetcastTVService", "com.connectsdk.discovery.provider.SSDPDiscoveryProvider");
		devicesList.put(DLNAService.class, SSDPDiscoveryProvider.class);
//		devicesList.put("com.connectsdk.service.DIALService", "com.connectsdk.discovery.provider.SSDPDiscoveryProvider");
//		devicesList.put("com.connectsdk.service.RokuService", "com.connectsdk.discovery.provider.SSDPDiscoveryProvider");
		devicesList.put(AirPlayService.class, ZeroconfDiscoveryProvider.class);
		return devicesList;
	}
	
}
