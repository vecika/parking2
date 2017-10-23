package com.vecika.parking.utils;

import com.vecika.parking.models.ZonaStaro;

/**
 * Created by Vedran on 24.06.2017..
 */

public class Singletons {

	private String zoneSelected;
	private ZonaStaro selectedZone;

	public ZonaStaro getSelectedZone() {
		return selectedZone;
	}

	public void setSelectedZone(ZonaStaro selectedZone) {
		this.selectedZone = selectedZone;
	}

	public String getZoneSelected() {
		return zoneSelected;
	}

	public void setZoneSelected(String zoneSelected) {
		this.zoneSelected = zoneSelected;
	}

	private static final Singletons ourInstance = new Singletons();

	public static Singletons getInstance() {
		return ourInstance;
	}

	private Singletons() {
	}
}
