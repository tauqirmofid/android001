package com.example.assignment001;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<ClassDetails>> listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<ClassDetails>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(headerTitle);
        textView.setTextSize(18); // Larger font for group headers
        textView.setPadding(40, 20, 40, 20); // Padding for a cleaner look
        textView.setTextColor(context.getResources().getColor(android.R.color.black)); // Set text color
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ClassDetails classDetails = (ClassDetails) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item_with_button, null);
        }

        TextView textView = convertView.findViewById(R.id.classTextView);
        textView.setText(classDetails.getClassTime() + " - " + classDetails.getSubjectCode() + " - " + classDetails.getRoomNumber());
        textView.setTextSize(16); // Smaller font for child items
        textView.setPadding(20, 15, 20, 15); // Padding for better readability
        textView.setTextColor(context.getResources().getColor(android.R.color.darker_gray)); // Set text color for child items

        Button detailsButton = convertView.findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(v -> showPopupDialog(classDetails));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    // Method to show a popup dialog with an animation
    private void showPopupDialog(ClassDetails classDetails) {
        // Create a dialog with the custom theme
        Dialog dialog = new Dialog(context, R.style.CustomDialogTheme);
        dialog.setContentView(R.layout.popup_dialog);
        dialog.setCancelable(false); // Disable outside touch to close

        // Set the dialog to a fixed width and height
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Load the slide-in animation
        Animation slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in);
        dialog.getWindow().getDecorView().startAnimation(slideIn);

        // Extract the detailed information
        String[] details = getDetailedInfo(classDetails.getSubjectCode()).split(" \\| ");
        String subject = details[0]; // Text on the left of "|"
        String instructor = details.length > 1 ? details[1] : "";

        // Populate the TextViews
        TextView subjectTextView = dialog.findViewById(R.id.subjectTextView);
        subjectTextView.setText(subject); // Display the subject in bold

        TextView instructorTextView = dialog.findViewById(R.id.instructorTextView);
        instructorTextView.setText(instructor); // Display the instructor's name

        TextView classTimeTextView = dialog.findViewById(R.id.classTimeTextView);
        classTimeTextView.setText("Class Time: " + classDetails.getClassTime());

        TextView subjectCodeTextView = dialog.findViewById(R.id.subjectCodeTextView);
        subjectCodeTextView.setText("Subject Code: " + classDetails.getSubjectCode());

        TextView roomNumberTextView = dialog.findViewById(R.id.roomNumberTextView);
        roomNumberTextView.setText("Room Number: " + classDetails.getRoomNumber());

        // Close button in the top-right corner
        Button closeButton = dialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> {
            // Dismiss the dialog immediately
            dialog.dismiss();
        });

        dialog.show();
    }

    // Method to get detailed information based on the course code
    private String getDetailedInfo(String subjectCode) {
        if (subjectCode.equals("CSE-3232") || subjectCode.equals("CSE-3231")) {
            return "Computer Networks | AHQ-Arafat Habib Quraishi";
        } else if (subjectCode.equals("CSE-3212")) {
            return "Android | NJN-Nargish Jahan";
        } else if (subjectCode.equals("CSE-3213") || subjectCode.equals("CSE-3214")) {
            return "Software Engineering | MSR-MD.Shoaib Rahman";
        } else if (subjectCode.equals("CSE-3202") || subjectCode.equals("CSE-3201")) {
            return "Micro Processor | RWA-Rezwana Afrin";
        } else if (subjectCode.equals("GED-2115")) {
            return "Micro Economics | NNN-Nowshin Noweer Nisa";
        } else if (subjectCode.equals("GED-1116")) {
            return "Management | ASM-Sufiyan";
        } else {
            return "No additional details available.";
        }
    }
}
