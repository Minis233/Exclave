package io.nekohasekai.sagernet.ui.profile

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import io.nekohasekai.sagernet.Key
import io.nekohasekai.sagernet.R
import io.nekohasekai.sagernet.database.DataStore
import io.nekohasekai.sagernet.database.preference.EditTextPreferenceModifiers
import io.nekohasekai.sagernet.fmt.snell.SnellBean
import io.nekohasekai.sagernet.ktx.unwrapIDN

class SnellSettingsActivity : ProfileSettingsActivity<SnellBean>() {

    override fun createEntity() = SnellBean()

    override fun SnellBean.init() {
        DataStore.profileName = name
        DataStore.serverAddress = serverAddress
        DataStore.serverPort = serverPort
        DataStore.serverPassword = psk
        DataStore.serverObfs = obfs
        DataStore.serverProtocolVersion = version ?: SnellBean.VERSION_4
        DataStore.serverMux = reuse == true
    }

    override fun SnellBean.serialize() {
        name = DataStore.profileName
        serverAddress = DataStore.serverAddress.unwrapIDN()
        serverPort = DataStore.serverPort
        psk = DataStore.serverPassword
        obfs = DataStore.serverObfs?.ifEmpty { SnellBean.OBFS_OFF } ?: SnellBean.OBFS_OFF
        version = DataStore.serverProtocolVersion.takeIf { it == 4 || it == 5 } ?: SnellBean.VERSION_4
        reuse = DataStore.serverMux
    }

    override fun PreferenceFragmentCompat.createPreferences(
        savedInstanceState: Bundle?,
        rootKey: String?,
    ) {
        addPreferencesFromResource(R.xml.snell_preferences)
        findPreference<EditTextPreference>(Key.SERVER_PORT)!!.apply {
            setOnBindEditTextListener(EditTextPreferenceModifiers.Port)
        }
        findPreference<EditTextPreference>(Key.SERVER_PASSWORD)!!.apply {
            summaryProvider = PasswordSummaryProvider
        }
    }
}
