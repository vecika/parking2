package com.vecika.parking.models;

import java.util.ArrayList;

/**
 * Created by Vedran on 11.07.2017..
 */

public class ZoneTocke {

	private int imeZone;
	private ArrayList<Tocke> tocke;

	public ZoneTocke() {
	}

	public ZoneTocke(int imeZone, ArrayList<Tocke> tocke) {
		this.imeZone = imeZone;
		this.tocke = tocke;
	}

	public int getImeZone() {
		return imeZone;
	}

	public void setImeZone(int imeZone) {
		this.imeZone = imeZone;
	}

	public ArrayList<Tocke> getTocke() {
		return tocke;
	}

	public void setTocke(ArrayList<Tocke> tocke) {
		this.tocke = tocke;
	}
}
