package com.uwaterloo.watodo;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class SharingService {
    public SharingService(){

    }

    public void shareTaskEmail(String title, String description, int ddlDay, int ddlMonth, int ddlYear, Activity activity) {
        // Compose the email
        Intent intentMail = new Intent(Intent.ACTION_SEND);
        intentMail.setType("message/rfc822");
        intentMail.putExtra(Intent.EXTRA_SUBJECT, String.format("WATodo Reminder - %s due on %d/%d/%d", title, ddlDay, ddlMonth, ddlYear));
        intentMail.putExtra(Intent.EXTRA_TEXT, String.format("Task: %s\n Description: %s\n Due Date: %d/%d/%d", title, description, ddlDay, ddlMonth, ddlYear));

        // Send the email
        try {
            activity.startActivity(Intent.createChooser(intentMail, "Message to User to do what next"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.getAppContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
