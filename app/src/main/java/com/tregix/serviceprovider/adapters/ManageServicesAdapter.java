package com.tregix.serviceprovider.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tregix.serviceprovider.Interface.OnListInteractionListener;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;
import com.tregix.serviceprovider.R;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 */
public class ManageServicesAdapter extends RecyclerView.Adapter<ManageServicesAdapter.ViewHolder> {

    private List<ProfileServices> mValues;
    private OnListInteractionListener listener;

    public ManageServicesAdapter(List<ProfileServices> items, OnListInteractionListener listener) {
        mValues = items;
        this.listener = listener;
    }

    public List<ProfileServices> getList(){
        return mValues;
    }

    public void addService(ProfileServices service) {
        if (mValues == null)
            mValues = new ArrayList();

        mValues.add(service);
        notifyItemInserted(mValues.size()-1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ui_manage_service_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        holder.title.setText(mValues.get(position).getTitle());

        if(mValues.get(position).getTitle() == null || mValues.get(position).getTitle().isEmpty()){
            holder.toggleCardViewnHeight(getScreenHeight(holder.mView.getContext()));
        }

        String[] type =holder.editPriceType.getResources().getStringArray(R.array.service_price_type);
        for(int i = 0;i<type.length; i++){
            if(type[i].equals(holder.mItem.getType())){
                holder.editPriceType.setSelection(i);
            }
        }

        holder.basicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               holder.toggleCardViewnHeight(getScreenHeight(holder.mView.getContext()));
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
               listener.removeItem(position);
            }
        });

    }

    public static int getScreenHeight(Context c) {
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.y;
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void removeAt(int position) {
        mValues.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mValues.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title;
        public final ImageView delete;
        public final EditText editTitle;
        public final EditText editprice;
        public final EditText editDescription;
        public final Spinner editPriceType;
        public final CheckBox appointmentList;
        public final CheckBox freeService;
        public ProfileServices mItem;
        public int minHeight;
        public RelativeLayout basicView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.service_title);
            delete = view.findViewById(R.id.service_delete);
            basicView = view.findViewById(R.id.layout_title);
            editTitle = view.findViewById(R.id.edit_service_title);
            editprice = view.findViewById(R.id.edit_service_price);
            editDescription = view.findViewById(R.id.edit_service_description);
            editPriceType = view.findViewById(R.id.edit_service_price_type);
            appointmentList = view.findViewById(R.id.manage_service_appointment_check);
            freeService = view.findViewById(R.id.manage_service_free_check);

          editPriceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    if(pos != 0 && mItem!= null && parent.getItemAtPosition(pos) != null ){
                        mItem.setType((String)parent.getItemAtPosition(pos));
                    }
                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            mView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mView.getViewTreeObserver().removeOnPreDrawListener(this);
                    minHeight = basicView.getHeight();
                    ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                    layoutParams.height = minHeight;
                    mView.setLayoutParams(layoutParams);

                    return true;
                }
            });


            editTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mItem.setTitle(s.toString());
                    title.setText(s.toString());
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                }
            });

            editprice.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mItem.setPrice(s.toString());
                }
                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                }
            });

            editDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    mItem.setDescription(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                }
            });

            appointmentList.setOnCheckedChangeListener
                    (new CompoundButton.OnCheckedChangeListener() {
                       @Override
                       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                           if(isChecked){
                               mItem.setAppointment("on");
                           }else{
                               mItem.setAppointment("off");
                           }

                       }
                    }
            );

            freeService.setOnCheckedChangeListener
                    (new CompoundButton.OnCheckedChangeListener() {
                         @Override
                         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                             if(isChecked){
                                 mItem.setFreeservice("yes");
                             }else{
                                 mItem.setFreeservice("no");
                             }
                         }
                     }
                    );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }

        void toggleCardViewnHeight(int height) {

            if (mView.getHeight() == minHeight) {
                expandView(height); //'height' is the height of screen which we have measured already.
            } else {
                collapseView();
            }
        }

        void collapseView() {
            basicView.setBackgroundColor(basicView.getResources().getColor(android.R.color.white));
            delete.setVisibility(View.GONE);

            ValueAnimator anim = ValueAnimator.ofInt(mView.getMeasuredHeightAndState(),
                    minHeight);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                    layoutParams.height = val;
                    mView.setLayoutParams(layoutParams);

                }
            });
            anim.start();
        }

        public void expandView(int height) {
            basicView.setBackgroundColor(basicView.getResources().getColor(R.color.background));
            delete.setVisibility(View.VISIBLE);
            editTitle.setText(mItem.getTitle());
            editprice.setText(mItem.getPrice());
            editDescription.setText(mItem.getDescription());
            if("on".equals(mItem.getAppointment())){
                appointmentList.setChecked(true);
            }

            if("yes".equals(mItem.getFreeservice())){
                freeService.setChecked(true);
            }

            ValueAnimator anim = ValueAnimator.ofInt(mView.getMeasuredHeightAndState(),
                    height);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    double val = (Integer) valueAnimator.getAnimatedValue() * 0.70 ;
                    ViewGroup.LayoutParams layoutParams = mView.getLayoutParams();
                    layoutParams.height = (int) val;
                    mView.setLayoutParams(layoutParams);
                }
            });
            anim.start();

        }
    }
}
