package com.vecika.parking.models.novo;

import java.util.ArrayList;

/**
 * Created by Vedran on 18.09.2017..
 */

public class Zona {

	private boolean isChecked;
	private String naziv;
	private String cijena;
	private String brojTelefona;
	private int drawable;
	private ArrayList<LatLngModel> latLngModels;

	public Zona(String naziv, String cijena, String brojTelefona, int drawable, ArrayList<LatLngModel> latLngModels) {
		this.naziv = naziv;
		this.cijena = cijena;
		this.brojTelefona = brojTelefona;
		this.drawable = drawable;
		this.latLngModels = latLngModels;
	}

	public Zona() {
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getCijena() {
		return cijena;
	}

	public void setCijena(String cijena) {
		this.cijena = cijena;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public int getDrawable() {
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}

	public ArrayList<LatLngModel> getLatLngModels() {
		return latLngModels;
	}

	public void setLatLngModels(ArrayList<LatLngModel> latLngModels) {
		this.latLngModels = latLngModels;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}
}
