package com.vecika.parking.models;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Vedran on 26.06.2017..
 */

public class ZonaStaro {

	private String ime;
	private String cijena;
	private String broj;
	private String telefonskiBroj;
	private int drawable;


	public ZonaStaro(String ime, String cijena, String broj, String telefonskiBroj, int drawable) {
		this.ime = ime;
		this.cijena = cijena;
		this.broj = broj;
		this.telefonskiBroj = telefonskiBroj;
		this.drawable = drawable;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getCijena() {
		return cijena;
	}

	public void setCijena(String cijena) {
		this.cijena = cijena;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getTelefonskiBroj() {
		return telefonskiBroj;
	}

	public void setTelefonskiBroj(String telefonskiBroj) {
		this.telefonskiBroj = telefonskiBroj;
	}

	public int getDrawable() {
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}
}
