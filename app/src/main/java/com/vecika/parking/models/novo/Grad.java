package com.vecika.parking.models.novo;

import java.util.ArrayList;

/**
 * Created by Vedran on 18.09.2017..
 */

public class Grad {

	private String ime;
	private ArrayList<Zona> zone;

	public Grad() {
	}

	public Grad(String ime, ArrayList<Zona> zone) {
		this.ime = ime;
		this.zone = zone;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public ArrayList<Zona> getZone() {
		return zone;
	}

	public void setZone(ArrayList<Zona> zone) {
		this.zone = zone;
	}
}
