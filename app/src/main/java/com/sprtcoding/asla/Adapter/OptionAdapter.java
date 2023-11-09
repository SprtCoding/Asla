package com.sprtcoding.asla.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sprtcoding.asla.Menu.ModerateCategory.GuessMePlay;
import com.sprtcoding.asla.Model.ModerateOptionsModel;
import com.sprtcoding.asla.Model.ModerateQuestionsModel;
import com.sprtcoding.asla.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionAdapter extends BaseAdapter {
    private Context context;
    private int[] imageIds;
    private List<ModerateOptionsModel> moderateOptionsModels;
    private List<ModerateQuestionsModel> moderateQuestionsModels;
    private int selectedPosition = -1;

    public OptionAdapter(Context context, int[] imageIds, List<ModerateQuestionsModel> moderateQuestionsModels) {
        this.context = context;
        this.imageIds = imageIds;
        this.moderateQuestionsModels = moderateQuestionsModels;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return imageIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        moderateOptionsModels = new ArrayList<>();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        RadioButton radioButton = view.findViewById(R.id.radioButton);

        imageView.setImageResource(imageIds[position]);
        radioButton.setChecked(position == selectedPosition);

        imageView.setOnClickListener(v -> {
            // Check if the current option is already selected
            if (position == selectedPosition) {
                return; // Do nothing if it's already selected
            }

            // Toggle the selected state of the RadioButton
            selectedPosition = position;
            moderateOptionsModels.add(new ModerateOptionsModel(position + 1));
            GuessMePlay.selectedAnswers.add(selectedPosition);

            GuessMePlay.selected_option = moderateOptionsModels.get(0).getSelectedOptions();
            GuessMePlay.answer = moderateQuestionsModels.get(GuessMePlay.currentItemNo - 1).getAnswer();

//            if (moderateOptionsModels.get(0).getSelectedOptions() == moderateQuestionsModels.get(0).getAnswer()) {
//                GuessMePlay.score++;
//            } else {
//                if (GuessMePlay.score > 0) {
//                    GuessMePlay.score--;
//                }
//            }

            notifyDataSetChanged();
        });

        imageView.setOnLongClickListener(view1 -> {
            showEnlargedImage(position);
            return true; // Consume the long-press event
        });

        return view;
    }

    private void showEnlargedImage(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.enlarged_image_layout, null);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView enlargedImageView = dialogView.findViewById(R.id.enlargedImageView);
        enlargedImageView.setImageResource(imageIds[position]);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}
