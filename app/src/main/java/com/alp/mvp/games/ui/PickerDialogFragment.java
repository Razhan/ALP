package com.alp.mvp.games.ui;

import mobi.upod.timedurationpicker.TimeDurationPicker;
import mobi.upod.timedurationpicker.TimeDurationPickerDialogFragment;
import mobi.upod.timedurationpicker.TimeDurationUtil;

public class PickerDialogFragment extends TimeDurationPickerDialogFragment {

    private AddOperationFragment.OnTimeSetListener listener;

    @Override
    protected long getInitialDuration() {
        return 0;
    }

    @Override
    protected int setTimeUnits() {
        return TimeDurationPicker.MM_SS;
    }

    @Override
    public void onDurationSet(TimeDurationPicker view, long duration) {
        if (listener != null) {
            listener.onTimeSet(TimeDurationUtil.formatMinutesSeconds(duration));
        }
    }

    public PickerDialogFragment setListener(AddOperationFragment.OnTimeSetListener listener) {
        this.listener = listener;
        return this;
    }

}
