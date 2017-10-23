package com.vecika.parking.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vecika.parking.R;
import com.vecika.parking.utils.ConstantsHelper;
import com.vecika.parking.utils.TinyDB;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vedran on 08.07.2017..
 */


public class PlatesDialogAdapter extends RecyclerView.Adapter<PlatesDialogAdapter.ViewHolder>{

	private Context mContext;
	private ArrayList<String> mArrayListPlates = new ArrayList<>();

	public PlatesDialogAdapter (Context context, ArrayList<String> arrayListPlates){
		this.mContext = context;
		this.mArrayListPlates = arrayListPlates;
	}



	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view;
		view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plates, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		holder.mTextViewPlates.setText(mArrayListPlates.get(position));

	}

	@Override
	public int getItemCount() {
		return mArrayListPlates.size();
	}



	public class ViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.textViewPlatesItem)		TextView 		mTextViewPlates;
		@BindView(R.id.linearLayoutParentItem)
		CardView mLinearLayoutParent;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
