# AndroidAdmin
Android Device Admin + App Pinning (Restricting navigation from top-bottom)

This repository contains a small module designed to help with Android device administration and app pinning functionality. It provides two key features:

1. App Pinning: The ability to pin an app via a button (enable/disable), blocking navigation from the top bar, Home button, and Recent Apps button.
2. Device Admin Lock/Unlock: Allows the app to be set as a device administrator, which is useful for managing device security policies.
Features
3. Pinning an App: Restrict access to system navigation (Top bar, Home, and Recent buttons) for the pinned app.
4. Admin Mode: Lock or unlock the app as the admin app on the device by making it a system app, offering better control over device management.


# ADB Commands: 
To configure the app as a device owner, you must use ADB commands to interact with the Android Device Policy Manager.
Setup Instructions
1. Setting the App as Device Owner
Use the following ADB command to set the app as the device owner:
adb shell dpm set-device-owner com.mr.adminlocker/.MyDeviceAdminReceiver

2. Removing the Device Owner
To remove the active device owner, use the following command:
adb shell dpm remove-active-admin com.mr.adminlocker/.MyDeviceAdminReceiver
Note: Admin privileges cannot be set programmatically via the button click in this app. You will need to use ADB to configure the device owner.

# Warnings
Important: Do not attempt to use this app on a real device without understanding the consequences. It may restrict phone functionality and could require a factory reset to remove the app.
Device Reset: If you are unable to remove the app, performing a device reset is one way to restore the phone to its default settings.
Conclusion
This app is intended for development and testing purposes. Please exercise caution when using it on real devices, as it can limit functionality, and you may need to perform a factory reset to remove it.

External References
Android Management API Documentation --https://developers.google.com/android/management
Device Administration API Documentation --https://developer.android.com/work/device-admin


Developed and tested on: 29/12/2024
