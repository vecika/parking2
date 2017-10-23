package com.vecika.parking.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

/**
 * @author tturcic
 *         \date 20.9.2017.
 */
public class AlertDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_NEG_BUTTON = "negativeBtn";
    private static final String KEY_POS_BUTTON = "positiveBtn";

    private static final String KEY_LAYOUT_ID = "layoutId";
    private static final String KEY_CLICKABLE_VIEWS = "views";
    private static final String KEY_CLICKS_DISMISS = "clicksDismiss";

    private static AlertDialogFragment newInstance(Builder b){
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, b.title);
        args.putString(KEY_MESSAGE, b.message);
        args.putString(KEY_NEG_BUTTON, b.negativeButton);
        args.putString(KEY_POS_BUTTON, b.positiveButton);
        args.putInt(KEY_LAYOUT_ID, b.layoutId);
        args.putParcelableArray(KEY_CLICKABLE_VIEWS, b.clickableViews);
        args.putBoolean(KEY_CLICKS_DISMISS, b.clicksDismiss);
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    private BuildableDialogClickInterface clickInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BuildableDialogClickInterface){
            clickInterface = (BuildableDialogClickInterface) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        Bundle bundle = getArguments();
        if(bundle != null){

            String title = bundle.getString(KEY_TITLE);
            if(title != null) {
                b.setTitle(bundle.getString(KEY_TITLE));
            }

            String msg = bundle.getString(KEY_MESSAGE);
            if(msg != null){
                b.setMessage(msg);
            }

            String negativeBtn = bundle.getString(KEY_NEG_BUTTON);
            if(negativeBtn != null){
                b.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyListener(which);
                    }
                });
            }

            String positiveBtn = bundle.getString(KEY_POS_BUTTON);
            if(positiveBtn != null){
                b.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyListener(which);
                    }
                });
            }

            int layoutResId = bundle.getInt(KEY_LAYOUT_ID);
            if(layoutResId != 0){
                View v = View.inflate(getActivity(), layoutResId, null);

                ClickableView[] clickableViews = (ClickableView[]) bundle.getParcelableArray(KEY_CLICKABLE_VIEWS);
                if(clickableViews != null){
                    for (ClickableView clickableView : clickableViews) {
                        View childView = v.findViewById(clickableView.viewId);
                        if (childView != null) {
                            childView.setTag(clickableView);
                            childView.setOnClickListener(this);
                        } else {
                            Log.w("BuildableDialogFragment", "Child not found.");
                        }
                    }
                }

                b.setView(v);
            }
        }

        return b.create();
    }

    @Override
    public void onClick(View v) {
        notifyListener(v.getId());
        ClickableView clickableView = (ClickableView) v.getTag();
        if(getArguments().getBoolean(KEY_CLICKS_DISMISS) || (clickableView != null && clickableView.dismisses)){
            dismiss();
        }
    }

    private void notifyListener(int clickedViewId){
        if(clickInterface != null){
            clickInterface.onDialogViewClicked(this, clickedViewId);
        }
    }

    public interface  BuildableDialogClickInterface {

        /**
         * Notifies which view was clicked. Clicks on positive and negative buttons will also be notified via this method.
         * @param dialog - instance of {@link AlertDialogFragment} from which the click came. You can use this method to dismiss it if it wasn't already dismissed.
         * @param viewId - id of the view which was clicked. Can be id of positive and negative buttons ({@link DialogInterface.BUTTON_POSITIVE} and {@link DialogInterface.BUTTON_NEGATIVE})
         */
        void onDialogViewClicked(AlertDialogFragment dialog, int viewId);

    }

    public static class Builder {

        private String title;
        private String message;
        private String negativeButton;
        private String positiveButton;

        private boolean clicksDismiss;
        private int layoutId;
        private ClickableView[] clickableViews;

        /**
         * If set, shows native dialog title view.
         */
        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        /**
         * If set, shows native dialog message view.
         */
        public Builder withMessage(String message){
            this.message = message;
            return this;
        }

        /**
         * If set, shows native dialog negative button.
         */
        public Builder withNegativeButton(String text){
            this.negativeButton = text;
            return this;
        }

        /**
         * If set, shows native dialog positive button.
         */
        public Builder withPositiveButton(String text){
            this.positiveButton = text;
            return this;
        }

        /**
         * If set, adds a custom view to dialog.
         */
        public Builder withCustomView(@LayoutRes int layoutId){
            this.layoutId = layoutId;
            return this;
        }

        /**
         * If custom view has been set, child views with provided ids will be clickable.
         */
        public Builder withClickableViews(int... viewIds){
            this.clickableViews = new ClickableView[viewIds.length];
            for (int i = 0; i < viewIds.length; i++) {
                clickableViews[i] = new ClickableView(viewIds[i]);
            }
            return this;
        }

        /**
         * If custom view has been set, child views with provided ids will be clickable.
         */
        public Builder withClickableViews(ClickableView... clickableViews){
            this.clickableViews = clickableViews;
            return this;
        }

        /**
         * Clicking any clickable views will dismiss dialog.
         */
        public Builder clicksDismiss(){
            this.clicksDismiss = true;
            return this;
        }

        /**
         * Create new {@link AlertDialogFragment}. You will still have to call appropriate methods to display the dialog.
         */
        public AlertDialogFragment build(){
            return AlertDialogFragment.newInstance(this);
        }

    }

    public static final class ClickableView implements Parcelable {

        private final int viewId;
        private final boolean dismisses;

        /**
         * @param viewId - id of the view which will be clickable.
         * @param dismisses - if true, clicking this view will dismiss the dialog.
         */
        public ClickableView(int viewId, boolean dismisses) {
            this.viewId = viewId;
            this.dismisses = dismisses;
        }

        public ClickableView(int viewId) {
            this(viewId, false);
        }

        protected ClickableView(Parcel in) {
            viewId = in.readInt();
            dismisses = in.readByte() != 0;
        }

        public static final Creator<ClickableView> CREATOR = new Creator<ClickableView>() {
            @Override
            public ClickableView createFromParcel(Parcel in) {
                return new ClickableView(in);
            }

            @Override
            public ClickableView[] newArray(int size) {
                return new ClickableView[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(viewId);
            dest.writeByte((byte) (dismisses ? 1 : 0));
        }
    }
}
