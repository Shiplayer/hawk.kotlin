package so.hawk.catcher

import so.hawk.catcher.provider.DeviceSpecificProvider
import so.hawk.catcher.provider.UserProvider
import so.hawk.catcher.provider.VersionProvider

/**
 * Main class with common settings for build [HawkExceptionCatcher]
 * @param token Integration token that can get after creating project
 */
class HawkCatcher(
    private val token: String
) : IHawkCatcher {
    /**
     * Version provider that used for sending events
     */
    private var _versionProvider = VersionProvider.defaultVersionProvider

    /**
     * User provider that
     */
    private var _userProvider: UserProvider = UserProvider.default

    private var _deviceSpecificProvider: DeviceSpecificProvider = DeviceSpecificProvider()

    /**
     * Enable additional logging for more information
     */
    private var _isDebug = false

    /**
     * Set version provider
     * @param versionProvider Your own version provider
     * @return That the same instance
     */
    override fun versionProvider(versionProvider: VersionProvider): HawkCatcher {
        _versionProvider = versionProvider
        return this
    }

    /**
     * Set user info provider
     * @param userProvider Your own user information provider
     * @return That the same instance
     */
    override fun userProvider(userProvider: UserProvider): HawkCatcher {
        _userProvider = userProvider
        return this
    }

    override fun deviceSpecificProvider(deviceSpecificProvider: DeviceSpecificProvider): IHawkCatcher {
        _deviceSpecificProvider = deviceSpecificProvider
        return this
    }

    /**
     * Setup debugging mode for showing additional information
     * @param isDebug flag for additional information
     */
    override fun isDebug(isDebug: Boolean): HawkCatcher {
        _isDebug = isDebug
        return this
    }

    /**
     * Build [HawkExceptionCatcher] with provides and flag for debugging
     */
    override fun build(): HawkExceptionCatcher {
        return HawkExceptionCatcher(
            token = token,
            versionProvider = _versionProvider,
            userProvider = _userProvider,
            deviceSpecificProvider = _deviceSpecificProvider,
            isDebug = _isDebug
        )
    }

}