package com.alp.mvp.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alp.mvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionView extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.section_wrapper)
    LinearLayout sectionWrapper;

    private Context context;
    private View previousPeriod;
    private String selectedPeriod;

    public SectionView(Context context) {
        this(context, null);
    }

    public SectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_section_view, this);
        ButterKnife.bind(this);

        this.context = context;
    }

    public void setSectionCount(List<String> sections) {
        LayoutParams param = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
        param.setMargins(10, 10, 10, 10);
        for (int i = 0; i < sections.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_period, null, false);

            ((TextView) view.findViewById(R.id.name)).setText(sections.get(i));
            view.setOnClickListener(this);
            sectionWrapper.addView(view, param);
        }
    }

    @Override
    public void onClick(View v) {
        selectedPeriod = ((TextView) v.findViewById(R.id.name)).getText().toString();
        changeColor(v);

        if (previousPeriod != null) {
            changeColor(previousPeriod);
        }

        previousPeriod = v;
    }

    private void changeColor(View v) {
        CardView wrapper = (CardView) v.findViewById(R.id.period_wrapper);
        TextView textView = (TextView) v.findViewById(R.id.name);

        float elevation = wrapper.getCardElevation();

        if (elevation > 0) {
            wrapper.setCardElevation(0);
            textView.setBackground(getResources().getDrawable(R.drawable.bg_border));
            textView.setTextColor(Color.BLACK);
        } else {
            wrapper.setCardElevation(8);
            textView.setBackgroundColor(ContextCompat.getColor(context, com.alp.library.R.color.colorPrimary));
            textView.setTextColor(Color.WHITE);
        }
    }

    public String getSelectedPeriod() {
        return selectedPeriod;
    }

}
