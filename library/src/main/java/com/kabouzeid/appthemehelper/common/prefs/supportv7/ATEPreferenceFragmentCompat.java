package com.kabouzeid.appthemehelper.common.prefs.supportv7;

import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.kabouzeid.appthemehelper.common.prefs.supportv7.dialogs.ATEEditTextPreferenceDialogFragmentCompat;
import com.kabouzeid.appthemehelper.common.prefs.supportv7.dialogs.ATEListPreferenceDialogFragmentCompat;
import com.kabouzeid.appthemehelper.common.prefs.supportv7.dialogs.ATEPreferenceDialogFragment;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public abstract class ATEPreferenceFragmentCompat extends PreferenceFragmentCompat {
    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        if (this.getCallbackFragment() instanceof PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) {
            ((PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) this.getCallbackFragment()).onPreferenceDisplayDialog(this, preference);
            return;
        }

        if (this.getActivity() instanceof PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) {
            ((PreferenceFragmentCompat.OnPreferenceDisplayDialogCallback) this.getActivity()).onPreferenceDisplayDialog(this, preference);
            return;
        }

        if (this.getFragmentManager().findFragmentByTag("android.support.v7.preference.PreferenceFragment.DIALOG") == null) {
            DialogFragment dialogFragment = null;
            if (preference instanceof ATEEditTextPreference) {
                dialogFragment = ATEEditTextPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else if (preference instanceof ATEListPreference) {
                dialogFragment = ATEListPreferenceDialogFragmentCompat.newInstance(preference.getKey());
            } else if (preference instanceof ATEDialogPreference) {
                dialogFragment = ATEPreferenceDialogFragment.newInstance(preference.getKey());
            }

            if (dialogFragment != null) {
                dialogFragment.setTargetFragment(this, 0);
                dialogFragment.show(this.getFragmentManager(), "android.support.v7.preference.PreferenceFragment.DIALOG");
                return;
            }
        }

        super.onDisplayPreferenceDialog(preference);
    }
}
