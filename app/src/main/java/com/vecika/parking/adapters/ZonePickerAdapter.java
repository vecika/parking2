package com.vecika.parking.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vecika.parking.R;
import com.vecika.parking.models.novo.Zona;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vedran on 18.09.2017..
 */

public class ZonePickerAdapter extends RecyclerView.Adapter<ZonePickerAdapter.ViewHolder> {

	private ArrayList<Zona> mData = new ArrayList<>();
	private int checkedPos;
	private int lastPos = -1;
	private IAdapterImageClicked mCallback;
	private Zona selectedZone;


	public ZonePickerAdapter(ArrayList<Zona> mData/*, int checkedPos*/) {
		this.mData = mData;
//		this.checkedPos = checkedPos;
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_layout_zones, parent, false);
		return new ZonePickerAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		if (mData.get(position).getNaziv().equals("IV. 1. zona") || mData.get(position).getNaziv().equals("IV. 2. zona") || mData.get(position).getNaziv().equals("IV. 3. zona")) {
			holder.mTextViewDescription.setText("Cijena po danu: " + mData.get(position).getCijena() + " \n + naknada za SMS poruku");
		}else {
			holder.mTextViewDescription.setText("Cijena sata: " + mData.get(position).getCijena() + " \n + naknada za SMS poruku");
		}
		holder.mTextViewZoneName.setText(mData.get(position).getNaziv());


		holder.mCheckbox.setChecked(mData.get(position).isChecked());

	}

	public Zona getSelectedZone() {
		for (Zona zona : mData) {
			if (zona.isChecked()) {
				return zona;
			}
		}
		return null;
	}

	public void setSelectedZone(int position) {
		mData.get(position).setChecked(true);
		for (int i = 0; i < mData.size(); i++) {
			if (i != position) {
				mData.get(i).setChecked(false);
			}
		}
		notifyDataSetChanged();

	}


	@Override
	public int getItemCount() {
		return mData.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.textViewDescription)
		TextView mTextViewDescription;
		@BindView(R.id.zoneName)
		TextView mTextViewZoneName;
		@BindView(R.id.checkbox)
		CheckBox mCheckbox;


		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		@OnClick(R.id.checkbox)
		public void onCheckClicked() {
			mData.get(getAdapterPosition()).setChecked(true);
			selectedZone = mData.get(getAdapterPosition());
			for (int i = 0; i < mData.size(); i++) {
				if (i != getAdapterPosition()) {
					mData.get(i).setChecked(false);
				}
			}
			notifyDataSetChanged();
		}

		@OnClick(R.id.imageViewInfoZona)
		public void setOnImageClicked() {
			mCallback.imageClicked(mData.get(getAdapterPosition()).getDrawable());
		}


	}

	public interface IAdapterImageClicked {

		void imageClicked(int drawable);
	}


	public void setmCallback(IAdapterImageClicked mCallback) {
		this.mCallback = mCallback;
	}
}
