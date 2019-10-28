package com.nand_project.moviecatalogue.notification;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;

import com.nand_project.moviecatalogue.R;

public class SettingPref extends AppCompatPref {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class MainPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        MovieDailyReceiver mMovieDailyReceiver = new MovieDailyReceiver();
        MovieUpcomingReceiver mMovieUpcomingReceiver = new MovieUpcomingReceiver();
        SwitchPreference mSwitchReminder;
        SwitchPreference mSwitchToday;

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String key = preference.getKey();
            boolean value = (boolean) newValue;
            Log.d("pref notif", key+" "+newValue.toString());
            if (key.equals(getString(R.string.key_today_reminder))) {
                if (value) {
                    mMovieDailyReceiver.setRepeatingAlarm(getActivity(),MovieDailyReceiver.TYPE_REPEATING,
                            "7:00", "Ada yang baru di Aplikasi Movie Catalogue, nih!!");
                } else {
                    mMovieDailyReceiver.cancelAlarm(getActivity(),MovieDailyReceiver.TYPE_REPEATING);
                }
            } else {
                if (value) {
                    mMovieUpcomingReceiver.setRepeatingAlarm(getActivity(),MovieUpcomingReceiver.TYPE_REPEATING,
                            "8:00");
                } else {
                    mMovieUpcomingReceiver.cancelAlarm(getActivity(),MovieUpcomingReceiver.TYPE_REPEATING);
                }
            }
            return true;
        }



        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_main);
            mSwitchReminder = (SwitchPreference) findPreference(getString(R.string.key_today_reminder));
            mSwitchReminder.setOnPreferenceChangeListener(this);
            mSwitchToday = (SwitchPreference) findPreference(getString(R.string.key_release_reminder));
            mSwitchToday.setOnPreferenceChangeListener(this);
        }
    }
}
