package com.sprtcoding.asla.Model;

public class ModerateOptionsModel {
    int SelectedOptions;

    public ModerateOptionsModel() {
    }

    public ModerateOptionsModel(int selectedOptions) {
        SelectedOptions = selectedOptions;
    }

    public int getSelectedOptions() {
        return SelectedOptions;
    }

    public void setSelectedOptions(int selectedOptions) {
        SelectedOptions = selectedOptions;
    }
}
