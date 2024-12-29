# AndroidAdmin
Android Device Admin + App Pinning (Restricting navigation from top-bottom)
This app is small module for the following points.
1. To pin the app via button (enable/disable). This blocks the topbar, Home and recent button from the controls.
2. To Lock/Unlock as Admin App for the device (make as system app).


# Conclusion
1. ADB command is required to set the app is device owner -- adb shell dpm set-device-owner com.mr.adminlocker/.MyDeviceAdminReceiver
2. ADB command to remove the active device owner -- adb shell dpm remove-active-admin com.mr.adminlocker/.MyDeviceAdminReceiver
3. Unable to make the admin app from the click of button programmatically.
4. ***********    Do not try on real devices it may block the phone functionality ***************
5. Device Reset is one of the solution to remove the application from the phone...

Research & Developed on 29/12/2024
