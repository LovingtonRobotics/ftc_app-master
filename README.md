## Welcome!
This GitHub repository contains the source code that is used to build an Android app to control a *FIRST* Tech Challenge competition robot.  To use this SDK, download/clone the entire project to your local computer.

If you are new to the *FIRST* Tech Challenge software and control system, you should visit the online wiki to learn how to install, configure, and use the software and control system:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://github.com/ftctechnh/ftc_app/wiki

Note that the wiki is an "evergreen" document that is constantly being updated and edited.  It contains the most current information about the *FIRST* Tech Challenge software and control system.

## Downloading the Project
It is important to note that this repository is large and can take a long time and use a lot of space to download. If you would like to save time and space, there are some options that you can choose to download only the most current version of the Android project folder:

* If you are a git user, *FIRST* recommends that you use the --depth command line argument to only clone the most current version of the repository:

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;git clone --depth=1 https://github.com/ftctechnh/ftc_app.git</p>

* Or, if you prefer, you can use the "Download Zip" button available through the main repository page.  Downloading the project as a .ZIP file will keep the size of the download manageable.

* You can also download the project folder (as a .zip or .tar.gz archive file) from the Downloads subsection of the Releases page for this repository.

Once you have downloaded and uncompressed (if needed) your folder, you can use Android Studio to import the folder  ("Import project (Eclipse ADT, Gradle, etc.)").

## Getting Help
### User Documentation and Tutorials
*FIRST* maintains an online wiki with information and tutorials on how to use the *FIRST* Tech Challenge software and robot control system.  You can access the wiki at the following address:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://github.com/ftctechnh/ftc_app/wiki

### Javadoc Reference Material
The Javadoc reference documentation for the FTC SDK is now available online.  Visit the following URL to view the FTC SDK documentation as a live website:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://ftctechnh.github.io/ftc_app/doc/javadoc/index.html    

Documentation for the FTC SDK is also included with this repository.  There is a subfolder called "doc" which contains several subfolders:

 * The folder "apk" contains the .apk files for the FTC Driver Station and FTC Robot Controller apps.
 * The folder "javadoc" contains the JavaDoc user documentation for the FTC SDK.

### Online User Forum
For technical questions regarding the SDK, please visit the FTC Technology forum:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;http://ftcforum.usfirst.org/forumdisplay.php?156-FTC-Technology

**************************************************************************************
# Release Information
**************************************************************************************

Version 4.3 (built on 18.10.31)
 * Includes missing TensorFlow-related libraries and files.

**************************************************************************************
# Release Information
**************************************************************************************

Version 4.2 (built on 18.10.30)
 * Includes fix to avoid deadlock situation with WatchdogMonitor which could result in USB communication errors.
     - Comm error appeared to require that user disconnect USB cable and restart the Robot Controller app to recover.
     - robotControllerLog.txt would have error messages that included the words "E RobotCore: lynx xmit lock: #### abandoning lock:"
 * Includes fix to correctly list the parent module address for a REV Robotics Expansion Hub in a configuration (.xml) file.
     - Bug in versions 4.0 and 4.1 would incorrect list the address module for a parent REV Robotics device as "1".
     - If the parent module had a higher address value than the daisy-chained module, then this bug would prevent the Robot Controller from communicating with the downstream Expansion Hub.
 * Added requirement for ACCESS_COARSE_LOCATION to allow a Driver Station running Android Oreo to scan for Wi-Fi Direct devices.
 * Added google() repo to build.gradle because aapt2 must be downloaded from the google() repository beginning with version 3.2 of the Android Gradle Plugin.
     - Important Note: Android Studio users will need to be connected to the Internet the first time build the ftc_app project.
     - Internet connectivity is required for the first build so the appropriate files can be downloaded from the Google repository.
     - Users should not need to be connected to the Internet for subsequent builds.
     - This should also fix buid issue where Android Studio would complain that it "Could not find com.android.tools.lint:lint-gradle:26.1.4" (or similar).
 * Added support for REV Spark Mini motor controller as part of the configuration menu for a servo/PWM port on the REV Expansion Hub.
 * Provide examples for playing audio files in an Op Mode.
 * Block Development Tool Changes
     - Includes a fix for a problem with the Velocity blocks that were reported in the FTC Technology forum (Blocks Programming subforum).
     - Change the "Save completed successfully." message to a white color so it will contrast with a green background.
     - Fixed the "Download image" feature so it will work if there are text blocks in the op mode.    
 * Introduce support for Google's TensorFlow Lite technology for object detetion for 2018-2019 game.
     - TensorFlow lite can recognize Gold Mineral and Silver Mineral from 2018-2019 game.
     - Example Java and Block op modes are included to show how to determine the relative position of the gold block (left, center, right).

**************************************************************************************
# Release Information
**************************************************************************************

Version 4.1 (released on 18.09.24)

Changes include:
 * Fix to prevent crash when deprecated configuration annotations are used.
 * Change to allow FTC Robot Controller APK to be auto-updated using FIRST Global Control Hub update scripts.
 * Removed samples for non supported / non legal hardware.
 * Improvements to Telemetry.addData block with "text" socket.
 * Updated Blocks sample op mode list to include Rover Ruckus Vuforia example.
 * Update SDK library version number.
     
**************************************************************************************
# Release Information
**************************************************************************************

Version 4.0 (released on 18.09.12)

Changes include:
 * Initial support for UVC compatible cameras 
    - If UVC camera has a unique serial number, RC will detect and enumerate by serial number.
    - If UVC camera lacks a unique serial number, RC will only support one camera of that type connected.
    - Calibration settings for a few cameras are included (see TeamCode/src/main/res/xml/teamwebcamcalibrations.xml for details).
    - User can upload calibration files from Program and Manage web interface.
    - UVC cameras seem to draw a fair amount of electrical current from the USB bus.
         + This does not appear to present any problems for the REV Robotics Control Hub.
	 + This does seem to create stability problems when using some cameras with an Android phone-based Robot Controller.
	 + FTC Tech Team is investigating options to mitigate this issue with the phone-based Robot Controllers.
    - Updated sample Vuforia Navigation and VuMark Op Modes to demonstrate how to use an internal phone-based camera and an external UVC webcam.    

 * Support for improved motor control.
    - REV Robotics Expansion Hub firmware 1.8 and greater will support a feed forward mechanism for closed loop motor control.
    - FTC SDK has been modified to support PIDF coefficients (proportional, integral, derivative, and feed forward).
    - FTC Blocks development tool modified to include PIDF programming blocks.
    - Deprecated older PID-related methods and variables.
    - REV's 1.8.x PIDF-related changes provide a more linear and accurate way to control a motor.

 * Wireless
    - Added 5GHz support for wireless channel changing for those devices that support it.
        + Tested with Moto G5 and E4 phones.
	+ Also tested with other (currently non-approved) phones such as Samsung Galaxy S8.

* Improved Expansion Hub firmware update support in Robot Controller app
    - Changes to make the system more robust during the firmware update process (when performed through Robot Controller app).
    - User no longer has to disconnect a downstream daisy-chained Expansion Hub when updating an Expansion Hub's firmware.
        + If user is updating an Expansion Hub's firmware through a USB connection, he/she does not have to disconnect RS485 connection to other Expansion Hubs.
	+ The user still must use a USB connection to update an Expansion Hub's firmware.
	+ The user cannot update the Expansion Hub firmware for a downstream device that is daisy chained through an RS485 connection.
    - If an Expansion Hub accidentally gets "bricked" the Robot Controller app is now more likely to recognize the Hub when it scans the USB bus.
        + Robot Controller app should be able to detect an Expansion Hub, even if it accidentally was bricked in a previous update attempt.
	+ Robot Controller app should be able to install the firmware onto the Hub, even if if accidentally was bricked in a previous update attempt.
 
 * Resiliency
    - FTC software can detect and enable an FTDI reset feature that is available with REV Robotics v1.8 Expansion Hub firmware and greater.
        + When enabled, the Expansion Hub can detect if it hasn't communicated with the Robot Controller over the FTDI (USB) connection.
	+ If the Hub hasn't heard from the Robot Controller in a while, it will reset the FTDI connection.
	+ This action helps system recover from some ESD-induced disruptions.
    - Various fixes to improve reliability of FTC software.
     
 * Blocks
    - Fixed errors with string and list indices in blocks export to java.
    - Support for USB connected UVC webcams.
    - Refactored optimized Blocks Vuforia code to support Rover Ruckus image targets.
    - Added programming blocks to support PIDF (proportional, integral, derivative and feed forward) motor control.
    - Added formatting options (under Telemetry and Miscellaneous categories) so user can set how many decimal places to display a numerical value.
    - Support to play audio files (which are uploaded through Blocks web interface) on Driver Station in addition to the Robot Controller.
    - Fixed bug with Download Image of Blocks feature.
    - Support for REV Robotics Blinkin LED Controller.
    - Support for REV Robotics 2m Distance Sensor.
    - Added support for a REV Touch Sensor (no longer have to configure as a generic digital device).
    - Added blocks for DcMotorEx methods.
        + These are enhanced methods that you can use when supported by the motor controller hardware.
	+ The REV Robotics Expansion Hub supports these enhanced methods.
	+ Enhanced methods include methods to get/set motor velocity (in encoder pulses per second), get/set PIDF coefficients, etc..

 * Modest Improvements in Logging
    - Decrease frequency of battery checker voltage statements.
    - Removed non-FTC related log statements (wherever possible).
    - Introduced a "Match Logging" feature.
        + Under "Settings" a user can enable/disable this feature (it's disabled by default).
	+ If enabled, user provides a "Match Number" through the Driver Station user interface (top of the screen).
	    * The Match Number is used to create a log file specifically with log statements from that particular Op Mode run.
	    * Match log files are stored in /sdcard/FIRST/matlogs on the Robot Controller.
	    * Once an op mode run is complete, the Match Number is cleared.
	    * This is a convenient way to create a separate match log with statements only related to a specific op mode run.
 
 * New Devices
    - Support for REV Robotics Blinkin LED Controller.
    - Support for REV Robotics 2m Distance Sensor.
    - Added configuration option for REV 20:1 HD Hex Motor.
    - Added support for a REV Touch Sensor (no longer have to configure as a generic digital device).
    
 * Miscellaneous
    - Fixed some errors in the definitions for acceleration and velocity in our javadoc documentation.
    - Added ability to play audio files on Driver Station
    - When user is configuring an Expansion Hub, the LED on the Expansion Hub will change blink pattern (purple-cyan)  to indicate which Hub is currently being configured.
    - Renamed I2cSensorType to I2cDeviceType.
    - Added an external sample Op Mode that demonstrates localization using 2018-2019 (Rover Ruckus presented by QualComm) Vuforia targets.
    - Added an external sample Op Mode that demonstrates how to use the REV Robotics 2m Laser Distance Sensor.
    - Added an external sample Op Mode that demonstrates how to use the REV Robotics Blinkin LED Controller.
    - Re-categorized external Java sample Op Modes to "TeleOp" instead of "Autonomous".
    
Known issues:
 * Initial support for UVC compatible cameras
    - UVC cameras seem to draw significant amount of current from the USB bus.
        + This does not appear to present any problems for the REV Robotics Control Hub.
	+ This does seem to create stability problems when using some cameras with an Android phone-based Robot Controller.
	+ FTC Tech Team is investigating options to mitigate this issue with the phone-based Robot Controllers.
    - There might be a possible deadlock which causes the RC to become unresponsive when using a UVC webcam with a Nougat Android Robot Controller.

 * Wireless
    - When user selects a wireless channel, this channel does not necessarily persist if the phone is power cycled.
        + Tech Team is hoping to eventually address this issue in a future release.
	+ Issue has been present since apps were introduced (i.e., it is not new with the v4.0 release).
    - Wireless channel is not currently displayed for WiFi Direct connections.

 * Miscellaneous
    - The blink indication feature that shows which Expansion Hub is currently being configured does not work for a newly created configuration file.
        + User has to first save a newly created configuration file and then close and re-edit the file in order for blink indicator to work.


**************************************************************************************
# Release Information
**************************************************************************************

Version 3.6 (built on 17.12.18)

Changes include:
 * Blocks Changes
     - Uses updated Google Blockly software to allow users to edit their op modes on Apple iOS devices (including iPad and iPhone).
     - Improvement in Blocks tool to handle corrupt op mode files.
     - Autonomous op modes should no longer get switched back to tele-op after re-opening them to be edited.
     - The system can now detect type mismatches during runtime and alert the user with a message on the Driver Station.
 * Updated javadoc documentation for setPower() method to reflect correct range of values (-1 to +1).
 * Modified VuforiaLocalizerImpl to allow for user rendering of frames
     - Added a user-overrideable onRenderFrame() method which gets called by the class's renderFrame() method.

**************************************************************************************
# Release Information
**************************************************************************************

Version 3.5 (built on 17.10.30)

Changes with version 3.5 include:
 * Introduced a fix to prevent random op mode stops, which can occur after the Robot Controller app has been paused and then resumed (for example, when a user temporarily turns off the display of the Robot Controller phone, and then turns the screen back on).
 * Introduced a fix to prevent random op mode stops, which were previously caused by random peer disconnect events on the Driver Station.
 * Fixes issue where log files would be closed on pause of the RC or DS, but not re-opened upon resume.
 * Fixes issue with battery handler (voltage) start/stop race.
 * Fixes issue where Android Studio generated op modes would disappear from available list in certain situations.
 * Fixes problem where OnBot Java would not build on REV Robotics Control Hub.
 * Fixes problem where OnBot Java would not build if the date and time on the Robot Controller device was "rewound" (set to an earlier date/time).
 * Improved error message on OnBot Java that occurs when renaming a file fails.
 * Removed unneeded resources from android.jar binaries used by OnBot Java to reduce final size of Robot Controller app.
 * Added MR_ANALOG_TOUCH_SENSOR block to Blocks Programming Tool.

**************************************************************************************
# Release Information
**************************************************************************************

Version 3.4 (built on 17.09.06)

Changes with version 3.4 include:
 * Added telemetry.update() statement for BlankLinearOpMode template.
 * Renamed sample Block op modes to be more consistent with Java samples.
 * Added some additional sample Block op modes.
 * Reworded OnBot Java readme slightly.

**************************************************************************************

Version 3.3 (built on 17.09.04)

This version of the software includes improves for the FTC Blocks Programming Tool and the OnBot Java Programming Tool.

Changes with verion 3.3 include:
 * Android Studio ftc_app project has been updated to use Gradle Plugin 2.3.3.
 * Android Studio ftc_app project is already using gradle 3.5 distribution.
 * Robot Controller log has been renamed to /sdcard/RobotControllerLog.txt (note that this change was actually introduced w/ v3.2).
 * Improvements in I2C reliability.
 * Optimized I2C read for REV Expansion Hub, with v1.7 firmware or greater.
 * Updated all external/samples (available through OnBot and in Android project folder).
 * Vuforia
    - Added support for VuMarks that will be used for the 2017-2018 season game.
 * Blocks
    - Update to latest Google Blockly release.
    - Sample op modes can be selected as a template when creating new op mode.
    - Fixed bug where the blocks would disappear temporarily when mouse button is held down.
    - Added blocks for Range.clip and Range.scale.
    - User can now disable/enable Block op modes.
    - Fix to prevent occasional Blocks deadlock.
 * OnBot Java
    - Significant improvements with autocomplete function for OnBot Java editor.
    - Sample op modes can be selected as a template when creating new op mode.
    - Fixes and changes to complete hardware setup feature.
    - Updated (and more useful) onBot welcome message.
    
Known issues:
 * Android Studio
    - After updating to the new v3.3 Android Studio project folder, if you get error messages indicating "InvalidVirtualFileAccessException" then you might need to do a File->Invalidate Caches / Restart to clear the error.
 * OnBot Java
    - Sometimes when you push the build button to build all op modes, the RC returns an error message that the build failed.  If you press the build button a second time, the build typically suceeds.
    
**************************************************************************************

Version 3.2 (built on 17.08.02)

This version of the software introduces the "OnBot Java" Development Tool.  Similar to the FTC Blocks Development Tool, the FTC OnBot Java Development Tool allows a user to create, edit and build op modes dynamically using only a Javascript-enabled web browser.

The OnBot Java Development Tool is an integrated development environment (IDE) that is served up by the Robot Controller.  Op modes are created and edited using a Javascript-enabled browser (Google Chromse is recommended).  Op modes are saved on the Robot Controller Android device directly.  

The OnBot Java Development Tool provides a Java programming environment that does NOT need Android Studio.



Changes with version 3.2 include:
 * Enhanced web-based development tools
    - Introduction of OnBot Java Development Tool.
    - Web-based programming and management features are "always on" (user no longer needs to put Robot Controller into programming mode).
    - Web-based management interface (where user can change Robot Controller name and also easily download Robot Controller log file).
    - OnBot Java, Blocks and Management features available from web based interface.

* Blocks Programming Development Tool:
    - Changed "LynxI2cColorRangeSensor" block to "REV Color/range sensor" block.
    - Fixed tooltip for ColorSensor.isLightOn block.
    Added blocks for ColorSensor.getNormalizedColors and LynxI2cColorRangeSensor.getNormalizedColors.

* Added example op modes for digital touch sensor and REV Robotics Color Distance sensor.
* User selectable color themes.
* Includes many minor enhancements and fixes (too numerous to list).

Known issues:
* Auto complete function is incomplete and does not support the following (for now):
     - Access via *this* keyword
     - Access via *super* keyword
     - Members of the super cloass, not overridden by the class
     - Any methods provided in the current class
     - Inner classes
     - Can't handle casted objects
     - Any objects coming from an parenthetically enclosed expression

**************************************************************************************

Version 3.10 (built on 17.05.09)

This version of the software provides support for the REV Robotics Expansion Hub.  This version also includes improvements in the USB communication layer in an effort to enhance system resiliency.  If you were using a 2.x version of the software previously, updating to version 3.1 requires that you also update your Driver Station software in addition to updating the Robot Controller software.

Also note that in version 3.10 software, the setMaxSpeed and getMaxSpeed methods are no longer available (not deprecated, they have been removed from the SDK). Also note that the the new 3.x software incorporates motor profiles that a user can select as he/she configures the robot.

Changes include:
 * Blocks changes
    - Added VuforiaTrackableDefaultListener.getPose and Vuforia.trackPose blocks.
    - Added optimized blocks support for Vuforia extended tracking.
    - Added atan2 block to the math category.
    - Added useCompetitionFieldTargetLocations parameter to Vuforia.initialize block.  If set to false, the target locations are placed at (0,0,0) with target orientation as specified in https://github.com/gearsincorg/FTCVuforiaDemo/blob/master/Robot_Navigation.java tutorial op mode.
 * Incorporates additional improvements to USB comm layer to improve system resiliency (to recover from a greater number of communication disruptions).

**************************************************************************************

Additional Notes Regarding Version 3.00 (built on 17.04.13)

In addition to the release changes listed below (see section labeled "Version 3.00 (built on 17.04.013)"), version 3.00 has the following important changes:

1. Version 3.00 software uses a new version of the FTC Robocol (robot protocol).  If you upgrade to v3.0 on the Robot Controller and/or Android Studio side, you must also upgrade the Driver Station software to match the new Robocol.
2. Version 3.00 software removes the setMaxSpeed and getMaxSpeed methods from the DcMotor class.  If you have an op mode that formerly used these methods, you will need to remove the references/calls to these methods.  Instead, v3.0 provides the max speed information through the use of motor profiles that are selected by the user during robot configuration.
3. Version 3.00 software currently does not have a mechanism to disable extra i2c sensors.  We hope to re-introduce this function with a release in the near future.

**************************************************************************************

Version 3.00 (built on 17.04.13)

*** Use this version of the software at YOUR OWN RISK!!! ***

This software is being released as an "alpha" version.  Use this version at your own risk!

This pre-release software contains SIGNIFICANT changes, including changes to the Wi-Fi Direct pairing mechanism, rewrites of the I2C sensor classes, changes to the USB/FTDI layer, and the introduction of support for the REV Robotics Expansion Hub and the REV Robotics color-range-light sensor.  These changes were implemented to improve the reliability and resiliency of the FTC control system.

Please note, however, that version 3.00 is considered "alpha" code.  This code is being released so that the FIRST community will have an opportunity to test the new REV Expansion Hub electronics module when it becomes available in May.  The developers do not recommend using this code for critical applications (i.e., competition use).

*** Use this version of the software at YOUR OWN RISK!!! ***

Changes include:
 * Major rework of sensor-related infrastructure.  Includes rewriting sensor classes to implement synchronous I2C communication.
 * Fix to reset Autonomous timer back to 30 seconds.
 * Implementation of specific motor profiles for approved 12V motors (includes Tetrix, AndyMark, Matrix and REV models).
 * Modest improvements to enhance Wi-Fi P2P pairing.
 * Fixes telemetry log addition race.
 * Publishes all the sources (not just a select few).
 * Includes Block programming improvements
    - Addition of optimized Vuforia blocks.
    - Auto scrollbar to projects and sounds pages.
    - Fixed blocks paste bug.
    - Blocks execute after while-opModeIsActive loop (to allow for cleanup before exiting op mode).
    - Added gyro integratedZValue block.
    - Fixes bug with projects page for Firefox browser.
    - Added IsSpeaking block to AndroidTextToSpeech.  
 * Implements support for the REV Robotics Expansion Hub
    - Implements support for integral REV IMU (physically installed on I2C bus 0, uses same Bosch BNO055 9 axis absolute orientation sensor as Adafruit 9DOF abs orientation sensor).    - Implements support for REV color/range/light sensor.
    - Provides support to update Expansion Hub firmware through FTC SDK.
    - Detects REV firmware version and records in log file.
    - Includes support for REV Control Hub (note that the REV Control Hub is not yet approved for FTC use).
    - Implements FTC Blocks programming support for REV Expansion Hub and sensor hardware.
    - Detects and alerts when I2C device disconnect.

**************************************************************************************

Version 2.62 (built on 17.01.07)
  * Added null pointer check before calling modeToByte() in finishModeSwitchIfNecessary method for ModernRoboticsUsbDcMotorController class.
  * Changes to enhance Modern Robotics USB protocol robustness.

**************************************************************************************

Version 2.61 (released on 16.12.19)
  * Blocks Programming mode changes:
     - Fix to correct issue when an exception was thrown because an OpticalDistanceSensor object appears twice in the hardware map (the second time as a LightSensor).

**************************************************************************************

Version 2.6 (released on 16.12.16)
  * Fixes for Gyro class:
     - Improve (decrease) sensor refresh latency.
     - fix isCalibrating issues.
  * Blocks Programming mode changes:
     - Blocks now ignores a device in the configuration xml if the name is empty. Other devices work in configuration work fine.

**************************************************************************************

Version 2.5 (internal release on released on 16.12.13)
  * Blocks Programming mode changes:
     - Added blocks support for AdafruitBNO055IMU.
     - Added Download Op Mode button to FtcBocks.html.
     - Added support for copying blocks in one OpMode and pasting them in an other OpMode. The clipboard content is stored on the phone, so the programming mode server must be running.
     - Modified Utilities section of the toolbox.
     - In Programming Mode, display information about the active connections.
     - Fixed paste location when workspace has been scrolled.
     - Added blocks support for the android Accelerometer.
     - Fixed issue where Blocks Upload Op Mode truncated name at first dot.
     - Added blocks support for Android SoundPool.
     - Added type safety to blocks for Acceleration.
     - Added type safety to blocks for AdafruitBNO055IMU.Parameters.
     - Added type safety to blocks for AnalogInput.
     - Added type safety to blocks for AngularVelocity.
     - Added type safety to blocks for Color.
     - Added type safety to blocks for ColorSensor.
     - Added type safety to blocks for CompassSensor.
     - Added type safety to blocks for CRServo.
     - Added type safety to blocks for DigitalChannel.
     - Added type safety to blocks for ElapsedTime.
     - Added type safety to blocks for Gamepad.
     - Added type safety to blocks for GyroSensor.
     - Added type safety to blocks for IrSeekerSensor.
     - Added type safety to blocks for LED.
     - Added type safety to blocks for LightSensor.
     - Added type safety to blocks for LinearOpMode.
     - Added type safety to blocks for MagneticFlux.
     - Added type safety to blocks for MatrixF.     
     - Added type safety to blocks for MrI2cCompassSensor.
     - Added type safety to blocks for MrI2cRangeSensor.
     - Added type safety to blocks for OpticalDistanceSensor.
     - Added type safety to blocks for Orientation.
     - Added type safety to blocks for Position.
     - Added type safety to blocks for Quaternion.
     - Added type safety to blocks for Servo.
     - Added type safety to blocks for ServoController.
     - Added type safety to blocks for Telemetry.
     - Added type safety to blocks for Temperature.
     - Added type safety to blocks for TouchSensor.
     - Added type safety to blocks for UltrasonicSensor.
     - Added type safety to blocks for VectorF.
     - Added type safety to blocks for Velocity.
     - Added type safety to blocks for VoltageSensor.
     - Added type safety to blocks for VuforiaLocalizer.Parameters.
     - Added type safety to blocks for VuforiaTrackable.
     - Added type safety to blocks for VuforiaTrackables.
     - Added type safety to blocks for enums in AdafruitBNO055IMU.Parameters.
     - Added type safety to blocks for AndroidAccelerometer, AndroidGyroscope, AndroidOrientation, and AndroidTextToSpeech.

**************************************************************************************

Version 2.4 (released on 16.11.13)
  * Fix to avoid crashing for nonexistent resources.
  * Blocks Programming mode changes:
     - Added blocks to support OpenGLMatrix, MatrixF, and VectorF.
     - Added blocks to support AngleUnit, AxesOrder, AxesReference, CameraDirection, CameraMonitorFeedback, DistanceUnit, and TempUnit.
     - Added blocks to support Acceleration.
     - Added blocks to support LinearOpMode.getRuntime.
     - Added blocks to support MagneticFlux and Position.
     - Fixed typos.
     - Made blocks for ElapsedTime more consistent with other objects.
     - Added blocks to support Quaternion, Velocity, Orientation, AngularVelocity.
     - Added blocks to support VuforiaTrackables, VuforiaTrackable, VuforiaLocalizer, VuforiaTrackableDefaultListener.
     - Fixed a few blocks.
     - Added type checking to new blocks.
     - Updated to latest blockly.
     - Added default variable blocks to navigation and matrix blocks.
     - Fixed toolbox entry for openGLMatrix_rotation_withAxesArgs.
     - When user downloads Blocks-generated op mode, only the .blk file is downloaded.
     - When user uploads Blocks-generated op mode (.blk file), Javascript code is auto generated.
     - Added DbgLog support.
     - Added logging when a blocks file is read/written.
     - Fixed bug to properly render blocks even if missing devices from configuration file.
     - Added support for additional characters (not just alphanumeric) for the block file names (for download and upload).
     - Added support for OpMode flavor (‚ÄúAutonomous‚Äù or ‚ÄúTeleOp‚Äù) and group.
  * Changes to Samples to prevent tutorial issues.
  * Incorporated 3q a%ppd@ b(a(C3 "$M puB((` `% h  0  ÄúB@`HA e " ppb¬Äê	,  * @lC% SE22! GDADa`a# 2e Rbit !pGp d "  
  F 4pe2 H)`s bÄònC%lQBÄ dH$ Edid	n` ` Co$F@gpT)Ln  @DD @$!"#    $JP@ `d BhAH DQ ` D "a``A Da  Hc ,`  hD) @d  "$)@!b!`f&    @@ @Aa  ,b* tc  a @ D! jhR  T y D @ B'B#` C`D`O(He@ a`p r D%"ihA0$@  k0 PAe,  @H  0c`  L  1` Hi&) 
   $Dd A"@lpp PE p s$E ,f fRA- d(` a%t"o,He2
   B)Xe  a c%hSacp%h!h @M0 A&#!$A2TEi#it   * L)lhD q"dDe  A 	 @@@@  
` DEEDbq  	F cr `o$C  p ! `D TA(d(DT2A.p @D!( `*r AhbC0JDe @.  Ta-$  @aLB@b  @ `` @ %  &*r TDH!m P@a Lh `    LD  ad 1a dA D ) Eh!! e`,*
 ((
 (" *
   "  *(   (*""" 
 **** * 
 **
 **((( 
 "
 * 
*" **

**
e`baG@ 0*1% ( eAsa` "  0 .4)    @$m#hx 0rG#p lm lc ld % @%!o6d Enfd#$C!rq hDlE() bA`l f a BFj3 `r b%& Pmh& 
  " ("""""
 
(( ( "*"* ( "* *

"*(
( "
*(
* "*  " ( ("* *"  ((**" TEpbAGD  3   aDE!pAd L  &0  1)  
  lBA
dI Pb@ebE((e m@        eB ! S  @@a$  R``  liab@1 $`  %  di Ppg`!%Ig MM`@ Se@$$" njPB ( a l D ti      T+ !"od  ld@t@2 bL+r @Rd @s0a%  ID `@tE@MBh8`$ f-deR#
      @dddd p@H@@ D0 DIdAD`(@ !(F$D
      @  E 3tp`O`$ "(  &  LPI@G$``0 ),PR`fab2      A@ a  Pt0 (0  L" I
  2Ro`TbrH"!HA`Q!`N"       @da` B`@pMRT fdb TB d#%AdL3"      % D !D S%0Pk`  o@  n lfI&pe     - `DAD 31, P lr `AdaOd5t     $ F)x FMb @A asQDh`" CDAo`e @lIbI   *  qFo2i      @i8  a`d$aa + eakE c`d2! a a p!(,A`  3h)L  tfnbh  )S Rth""#"     	 QpD  A TA Tfmpa "
0!   ebl-A& d` "( $) A @d G	)F e ) `(@ QE"qb`$ (Eip(
D$"     I@ J0 `U4C,Eld@ #  A`Bd@ 0alp  g 7@`p  t	)AB Ta3 	 DD`DaD $P$@ (Ue  t @p @pd $f hAVe `)(a 	$  
 .P` @A D)vE #H!"`E & `D`b dd *%` !&4r p $ A  P B@h@@A vt( ed#B#aD 0 qE`LH` `F`th 0`Ma`u  .
    Fh( da `6
`` re!d!D `2 `0e&   pda4$  `E0dD @ Hndep   a pA2`  L-	  `@ @HX   j0!"L  E" & @!JAD     $$D D`C D l J S L E,
   AL @hE  q0 @d`)  "AiAp ih a@@H! /P m@ B  J ba  *0)`pDHt &@ph  P @t @%a$`&
    @)   h  $lt 5Ra C& I2S! hrQ!nb@0""((* 
"
(
* *
" "" *"*  * *"* *
(** *(**** *
( 
("""
"**(*"***( *(
****F!ps(of 0"2   re e!``d `   (,0( 
  * u  hbt "c2 H/DaRL Pn`)D@BB A, !##  &.B(    T pAr0 $h (@@0n Ra@-p `B P`&"% @ 
`-R*   @ " @e  $A  (Ai!1 &(" E"h"#D   )p( 0E d$  @#h  a L! Q @3ed hB P"`bI` eua .  
 ha`  t# 2e  H   ApC k rSD*ab3 $ "aB@ !b `!Es    s PQ`K%2e$L` A h d#`REp   
 Dh``A #kepTa v(paf` `/`e tg Q iP aj `" hV!`4 l 0"lr` $iCa.%  Pep L D 0Eq%iRaAd.  "p(- @C.   
 `,dr DaR*    @`O	 H h`dddlD"      %p@e00  $2 GD4@&! `nf     ( B`@@p 	0 Ad@$g' @a0&     %  a@b `j A@@db Jt `
      PuGp @" L`A`4  Bcf@
     % PP'pt  -P S$B @d`T" le0&

(""
  *(   (*
*
  
 (" "
 *"(*(
 
*"""*"(*(* *
(" **"
(
*"  * ***@pc e` 0$1  peL` S$ Ff   
0   * At0pNBt Do@ ADf`u$ AD.
 * I-pbBv@m%n4C Td 	cd@BHDb+Di 3HbHRn b`arr
       GBB NF RA#D ` J !PAB      @( 0a4)%(  P`0P( 02q$ 	(  BiR-   "a@(br p)* $   @`DA` 2@%P(% ipi `0)gB 	
  @hiaAha $
 a`c-a`a     "p`R@ oP aFdLH$(` @ `3GHb`     	 ``d` "0pp)2 $ap D$ @d$i(D
    ! )m "ve loh nd !biBhH  i o` b,l#ir      #q@IBt $hr np@aS sE&q'(
    - p`2  fR u(tPA3nHa aDS)b(     p``d`@ )b 	QE *r,
     A@`m"p D   "
    $ SU0p@T  BB AG$+` REn0
R$    ) a0Ppn  " P ARB%b
     p"a@$ P"ab 4( #aLIe5Pd 0g`    fe a2aNB 0Rh bg * (
 ( PrD) %# aId`Dq P+ @H!hD a@I+ c@&
  `riGpS qa Xs `*` am`RMdeets
 *(*
 
 *((*"**

* ( *
*
*(**("(**("  "(
 "*"*"(* ""
 **"* 
(
*"** 
*"**


De@p)
n . 0 (2` D!#aD m 06& .08	
 ( 	 `@ @HA  E 0dh$@S@ )" `@D P` *AH ! 204( 01 DH @ @Bh B DlA`  @@CF&    J(e #h *E )S %Ha`dd` 	& @% BD -@bD Ah,p2
h,A    ` dR Ho0
   hd   #$ AE HHN!b, ( A3e01 aN net "D  Bn
` `!nC Dm `@cHC`E0/`ib!"A `!@p L  Ek `S$  A@l# iF d`E A,`RjI@ CC( @ a@d buI   t+I, BqEiEe`n4q  M!lsdJ5$ a  1*( @Qal   olS ,0 3	.
  Bt!$`!b$ixe` dAP3 hb @N``Oa iPud  *   A     %$  B   P@f ALl"% 3`h0@P B$bpDP
 * cD$HABjadc@ @f @$@C@a"D$	*D !RD  AE@FD  d@ke @e  E@A B,`M,(Ep c`aq2  (a hB (H`kp$a,p % #` ! L*J`` D@ t` `OeR d
dd2 t@pGT@ p! -f`. "cfpO h`r$  
 %tM%d  a &dDL	`E wE`e `D@A@   4h !@AbR @(a&  
   mNT!bunCPod 4)+bdb6n CaC  H!s BeE* @dddd $J $ a   D *  @d%"A`@	 , P(G  `@a " a )`!B+!DD$ 3%  t #  00(Pt p A  mPD !Ta, dR !&,  "$ !(@ @p (BEE$ &
   RLL  #  @d3 `aFe b% ` m $   $a$ iaD. s  @De  a. `v ` RD  ``* a`BeAs a`-c brb$ @`e hap$4` e h`0. * T1p  d nOC dt  @a H@ d `VEb bhA`iGR flB `  @ `ofr C t@a  d   mD0 a $ `r k 
 "La` EH$  nsp r Rd2	*
   @p/o@Ip @@K``H Pra#aen @k` h@B BD$     ``  d  D m`
0 Ald,L`  QAaBB B@D `LabE 0H @%@ BAJ` A`d@ A@* P@ap I.$a D P%N D! ` @2I$  !h  A A (@0,  t`!T  a#   @aF!#@@p  Hb, $ "ccE0 `l 0pHta @oA+h8P`  Lp o@1 `)"E!@$	 &`+ $(a B%n0 BA. r%,Lr
 * Tq%bp A` $&u aobfup $(d 2jbJt 2!,#tEdI p`o1d t`@ DC 0i6 r ta)GH A`0  * @H@r'	   p5@(% 00Ej%bt Sp@lps &`ROi S$aD`h 2*1(x a.$ m`)e BK VdRQi&D   @A2q%@D.
 " @@@$ A@ BNH00 2 Pi@   	fPAEP@0ed H  L D B	*  rDr2 @J ERD p!	`  S(b
 0@E2 M  a L	b`L `@`I	N HjeIAth cF ! 2P!NdaR@ F@ B(dD`  " @pO@$"0 P "E"D2d  `p Bn r`#p`&("d` #d  (!T 4`` d Ic $%!    aa`Cmd% a`k fd T(! t1Dr! @@n es! TJ  `b  p(Ai@ Hnc`+ Tbt.! Cp hdEA `Ntf @(H 0Aak c" " @b`D )En $a,     h  lTABa d iDPN  hd   P.aD D"ML@ alD D pe S`TF  p`"  D `&J Da H`JM! ¢ÄÑ 0   0 *
 * % @ a! @a2e  $ )db@`N"a@D  $Pl @C S 
" " " p l  (anq- Dd!  $I  @ @b  BLdd"(d%" #MFiT2ap /d @#t(t!9,  @Ar ah` 0e )@ q`5 ``6` vg a	pp ak*t2k$`e q .n A blb+t, aN$ mU I)q)$!bph@DD tdH ). `,t @%(F`gra)n. bH `( 9Ne @ah 4Qe t`  v p Be$p To rs!p 4@! @e("E# )tH!"     	`)A`R $He( bi!  @* G4 $I `@  `R% ! %AD Hha `eJpP 	* Th@ !F` AEr!$d @LFd D%0 H! 1* d$a"ab( "  @ h@B@a&C-  d@! G @D  @bd2  D  A A   n Alc0j*a 	E$!dD @aI`p    Fb E` @ %  0U`0g   ! 3r	 b,
 n( e0  a%R ln Y%!2 2$bt& HA5 R%p$`#e tHE @PoH , -J$ed% w`p( A f!5 MA`ee$ @i!` !p  Dib$eB!Ld rAp`!H NambaB F oh (` JhghNh q% v cKd`/  eb.  Xu @@D Ps dh%  IX BE$tCb T$ a5 ih`Da $d)  "A
$AguPE `M "  GBadU"`thnN d  I   4(  c "a D f!` $f T(d f% dF@%E " 	(@E`A J@ $! ! D` &	` raP@dh Ja` @&   RP.$``"%l`#2 ` DHD 04a  Dr A.!!bM`Dda @ t#` `/# aAbT "hr ` $e$	!prI`P  (	 dA UPdt! t D D l@-t	 dAP@ %n `@E d2Ivdb ctA``gJ   I# `@A0`	 ebHadI! eh3U0%3 D`ad t@% d2@v@r 0`@+* g!t 0@e t` @T   d t@ @RieB 0 aD a4 h #A!  0`!"  P    D4# *  EPe d4N!`AA" M" t $ @O"P Bhn4Bd  B )  ,*b 0 `(`  AR%d  HD ThEb h0 a "*$	+.@! pP`D (`J  @)""A"pB@0@/"   a   4e "@* "  e@4  pO 0``d h`  1D  EjDi`er%  A( hHm ` B` E`eD 0 @E c`,0 c`Nb)u D a  *`o4 O@ 0hiq  y0!
  Dh@ m#`# +  4aB  B1.vAi `P em`%   nh @ t(@ D ,`@bPAO`` `N  M Lj  DIpeq) !f   g amB$ $ha "Qd, `d AtDK e )4 (`0 E 		 `@@@I$ L% D``d 
   F)P H A AA$ (BBMBl@ADa$ C  @Ap ef@$%ch     'A  d $`Pad " 'Ad `! "CPP`bt @c A"(aH`L  S

"
(
*(*" 
(***(


   (
(  (**(
 **
 
 (
"("((*
* *
*"** *
"*** (
e`@P$ !4 .  
   n2 the "tcM p PrA*e`  ha 'Pa` d BH(@ (!vA @a%L n$)FIdd 0c s%pPdB Re)  uii 0P&

((
(  (* 
* "(  
(*** *(
* " 

*  ***
*(""""" 
(
  *"*****((* ( 
* d$@`! $0$# 
 * 2 0@  E   0 HH@E@4A0, tHa DC d@ "$L     d d 3 Hi(@ ` !D "dP$a` epPac J  D a D1` ag` N" `a`  Aq g" n0n"EJP * B	d #`!
b% weP% l`de T ha 1 u `@An @o#ic Ti d.0u2d p ` 2kbUrt ph%p$+&h kF 3MEe ` neB RB qE  AE"
 " A b @D#a `3 maD$ ti Da(d`O0AL% sb !q . A, *D ! E@4 @ `d`d dE B% A D#D $@ )c@! 0@ M @a 2  B h! P%pe)r`D  ,r 0 d A  A*BE@DA"  * @AVa` !m 0H&% @0@add.
((( ("
    ("""*      (


*
 " 
**" *"" 
"(("( (" *
*(( * 
 ("( *"""(
d  `` 0 3 8
   BhaH `s ,!`! Tg a)` PhE BC SD RI c@jBuc (Bh'Jh I an C(fA    - 3 !pfFDd(@ rd`beCa`,@ ( `F$ C   `RDD8$ !2` 0A@cA ) be ` lghEAr j`d! @ @ `@be bedB d!@p%cA`%$(      0 kdE() (M  H learOpDOd!) a fO1 DBqpla$  PN(  "HA4ae% " URa  !!@#Th@E 4@P@ $*      gOp( (de `j PAGD@) hS $kd @d uP($D rLi h 0 3 '3 h!2w@b !`$.ch`e hr$`$ 
    ( eP(C$R  2@ x*H2anA`.     D'b exAph%( i Ao` adD s@4d(DcHnd'0CfL rnl$a0RaMe `.DSTSDNCDE S( `-P   mjtAB  0(D D.`o e2 hR cq p jd$  O " d@  'HE@ `(E letHo` cAlh ip bCmp$ E    , `   a  ,+`!    H  "Jip`D "d `Qd Nk  ih`p a! @
 TG'C(E B$ 4dL 2E!@ A$   H% %g`%2 v@ 
 RAd L` 2! r T@(4)*# 0 @ eAC` dv     B` e# !ada 0N AJ!&`e dHha 	 iDa/b  T2D@ `pPh*e R  d`edt"  ( @a.C`` eA$D k iah o$ t B%AD 1  . ( @``u "ayRobD  $@@ SD dh  ae0!bEN@p`$d$ r b@  oddpkD  B  
R UH@l ! $  `@ @@i  a)`@`@ D1 (pA !@' D !c (A0r a A DA	 H` eE ( $% @r@HAP b Da0pJ ! DA  p!!Pd$(  " `REG@`  t$b aO* 	n lP%@0)j.   LgD r  J is HdD!  `! dJU lE!" " AHa`GA ,!da  a hA`r%pE E Aa!h 0%i b	D)9 `d `gu#tnAsA. * Ade$ ppPm$ kr rO`te @f`	`A0o o H%e`#a (FHT%l!pAp)L( aO4#" #)FpB$h0s* ( B @*c%q e@ e " pr$"Da A` RdB a`a 	,(@` dr o `cD$`&    ,   D)@eaBN d@@ @$ CB FHHH #TF0 r(a
  `  a @4D@!dPc AJ RdEp@de !  @  f0Lta&  Q2r D c ( @!` T' p02h d A f0 @Qp$F lF 4ha  Ip R aT oD,
    ! @D  $ P L`a  3 @opP$  `` `% ``i4`R @A4i..  p@ P`ep% aR ! @5 sAaQ@	"t r2 p0`b$d phRdA pAip)"g $h  A` hdL (hg `J aB b" m@pSadE hEf K0 $ )tAA d `" cB q` 4d pEp @D f`7! hea .
 " Dph b D p	
. U MM`a )d  4n d)pl`( HDEBD $ 202` bED!d  % +e ` "e*  d!(0a&@  @   DeRh &       "aR Qt !.b 	 !E`Afh   pL  A d @-@ @ b@BA2MEj` b/P cTBejT b+ t   D (g
   eLhjc $h/, @@`1D $GA2  `8pri0 `/v "a  Fd	,
 (  2A .! `L p/"e$  DIT# !J$ d  Pa/(`D Bl!!
   AdD  ` @ea(d iA 0m A`%u ! aBAtA  -0 dHT  Ta` (N    k`@ ##"DeLL%" AP@ " Iqp(!9 'AL I@ ad@ d`C@r N @p(`0 d`0ILN ABN@    &d((   d$%D Ie6( dRfB(AepD  * ADde  @2# a$)` RX,# `@!dabE*
 
   `   3%o
dS ! aD$ ` ,)pdnL Q( t` da`s dT ma  cr #L@rI@ .    ddd@ d!B!hd@ B#C#tLD  `@  "A A"("@(
 * A@d%` Ahsi&E Bl!ABI0!Pb@Aa 	l`F `a&  * Ad@d` b&d  @m c0a@P% lLg m p`da R```  R!i0dE "gr H*ar@@ad `H@H
 
 (@ p/ DI ! @`d  AEH@`E @#4(bh$  (,H jG ,nBAP !%h@  0t  L%  A)a . " A@@`d P % a``l(th $   `c)bP @$ A@0`" D!  c `D`b`b  a R!p!`@% "/ ha Ig`%2l BD"ntIb Exb,
 ""(*  *(*****
*" (
**"
 * 
 "*
*(**" **((
*
  
 ** *(*""* * 
(*" (*" 
 R!`aAre 0 ",0
  * 	$po `  adte"y AheADP @$@tee pg T !` RcH$!d` 6 !$ #d` rdPEshdd fu R@x dRR! 0 Ma$a &n @B`2$  R!d`j (DS t% h @`$    $PpE Rg"@Ab` 2	 $(AP P"E0 C+J$Md(A  B !  r LDa` )aBA 2!0 Ald  `   ÄBd$B	@!!*A‡Ä P A@ `pbjhf  pS(     H !PE `4 	@ t%  `2 	Pa3t`t R ' @( gnE Cr @*0e Hg$q% m RaanE, i4 w)d dibp$!I ! u&	n bd  cTHlH sTa"0 dP.    , h$ rt,iG a. @  l$d (  bFd R Elb  ,duLs bEDS Di#"l*j`BpE`$ $H` BB  S H(  (c0 X Q!fn&@ aD` 0'"$p 1aL  #A`  /& 3ai(% 
 2`)P% I $H!   2`  g T`e s!"    	 	f @ D)Q!e.BAe aI$D !a@C `@H!(! d@! Pa#.d@`E  THA RB uA , !D `$4a4 `@@ aH`$l @(  pH$   ALl D@)* AlB B$h o" $h  rEb@ Pl1 c!H B0dD g$Q!.
     RARi& AeC`Dd3  2` iJ (a(%d (`D%`h@)%p the p`e oF la T,@ @a$‡Äôr l	3ILf @d5C apS PB ad@H! &q@@0)*     "  E   #ha&Es p/ BH`  e Jp,L GaD` d @!$adfD sd upE@3 pRi PF r!F`2dB! t  #@I` @ 	B 4 ! hhP ! 0MR0I/
 b t`ea2 0  "      T  iH$ #E(#jP Fpu ` J. 020@HQ SA`LDD,  0@ (!   D@%# )Ih @aT@ T' r   *AP @ D(B DH&h@ t`RE1 )D v!hd3 @ tha r N@ hi`eC * B @ u"` i4A`&! e Ee@A$ AAn fip A dad" UH ds`@`a2   $H! ma4h)BA a!d@d h DI3bmje`%` kr !f  ! E@ !PD `ED  dQi'h!d@ `P   $f%r%t drhe (  
 Rdb"d @2	t.@DH PM FCAD @!	O" &E  d2 n.  a !Q0,aeD hl  b(qt 3 bdEn l( P aL  S a`0 
  H`d``d0de@   @I"0dA! Ah$D" h" `(b)JA C!P eH T) FA(d!R M  D@c2  p  .` âD 1r   !   TD MDR‡ ò @'Pi!  PHa3 dl4$p B!  E `@.d$ "& 4, shl1 aDh hBh  	R `T de6 cDr,  
 U0 `4 d t$X  An @A!E sD faDe.
 * FhX!   rlDHL @( !n 0 ia@ `RP`
bEd`a*	tIg 	&
 ( HX`d aqBta 'j !p( !   Ja ÄÄú`Äù) @aHAE d!dE THa` p-P@D $Aq@aP4 B@ D``d`  !!"i.  * $ ! (@"gD Qe T@   $Hd UA@I @*`  a*  b`` B9 $`J   L Bd   a ` i`@ (A  T!B   c, T(D  C @	, `iDAAa.% t) B@. 
 m `p% 
av dh#  Ej B!a,   @A`IFD  oe l d@* Id Da`ep  @ ruper (@FDuAbe `o#al``AtA l 	F tHE &Et1B@.**"
*(*(

(""
"*""
(
"
"* "**
*"(*(* * **(** *
* (***
*(* * (( "*((( de!#a 61.04
   p$aTe bC P)( S$jT"HLd bGr a Pa
 *  B`2$Bt SAd  `rCD EFDD"," 0bV B A2	( $  * R$Lap$d 1 Ua$ HA0)0D $ +H  "a$eR  P!T 
  
 BbP@ATE@ "D@`$ 
d  (FP D#H  BDe"    DETDdtaad Ad .'a`lFddA  C.A."e(  $dN np,H * a 1 "h!c) B
r BBCLQ@I
F lAQat"h  @h0 FM" Tle%e$r1 @(b h3 /Bf)b8n e $pB#@c i )0s d!t! #P hc 3H	*a 	  dr B @d (!)@!`.q `rIB
 * Ts$r te@Ea$ x oAtPP 2 brTAd*  * A`d  d" i`@0 C @h`s 0% D`eD `  @Dtb @@H 
  "bdE -B hfHad 0b !@hw bO   L*hg h	P 	  	  HH@@ 
`L` &   @a(` 3 T% 	(0 *6e  @pe@  !`D ` fD @C@I@`, @P@ERaAAGJ    @@ @*r "-ip2HjG  a2 !   per  / c`NP.HD`B `I0b+hf@BD@` ``C dpADa AP(n  `pG 
 
 & p F   F p$ D00n Af IFhtSar0 #au`%s p"m" eES" `b`#` AB `ob n`H ADq and! p  hB @  `@ead .   ``D@ C,h% lE' TtDlEt3 "K Dhbd@  ()Fd  Y d
  Lnbe$  l `@ REDp ,!B  b % `F )hdAaareEP	+DA() &mB  +`Ua`h`QA   H hdd` m @` 0I ,H@HAtD rj # @E #J@D)0I$   D  u`l(  i$4@n$!  *  A &AR m `0p A$ @Hab c(d 	FH @ BEb   `% B n   `	J%@rA` ``E  @!baB@%q$  ( fh8 ( #I0dA0 pae BmbAPAK( @aTu@%`  p%h(  !h$ c o#D(	 ID dA$VI$`B!J.AB`EQ$`l`a`d  5P  D @ DPit%
  $    @@a$r	EpN@Abhdtfa`D  *t!2f`c  u hh" " ! e  *f $b`+Ne $0rG2s  n C!%aA  Gl3@$PiB   Ig4A$ 1DA)B( @va0d )P`-An`  	Bs ).Pn t@ehb      H!g`
  @H@@@ `@ebaFhC camd@@d  @LD
@ A(c  C `(D ( G!2` ha`ra08  @ jce  DHCNQ BAE $M 1 B "e21i,$& " `@xDD ` @ !+"hH.F W`Dl bp   Dar$ 	-`"N n`D`a! B@ @`AC!P 
 
 Eed`k`C Ab t d ClePB%FA/ C@AcS%3 h re `e l pa(b!n
%D. * c( $Cd%  )CBq)   "D!4%3 d re`,Aat `n@   H0hl 
 * c,R2`C$D "@ak" @td$GB hdIaoD" 
 p`e  n$uppMrp$d   i$0h$ I& `h! A8@(B$*B" bD!Sc a3 A(!`!d n 0BotdC`` (i q(la,d .lD ` @Bd)B 
( 
(   
 *("("
 "  (**   "(  *("
  "*
**
  
  (*(  ***

(
"  
( *B`,!S@        * @e` SA` +PT g  L.  L RK`o`@C B9Pl*
  %  % p"kPd*"cB b@aQ $nw !PrD ph  B Ax m BdHSmr
   - s  b bA& AA# qS ` `Di." $d ( id0 Z !`hs    QrEbC baF `(S  A`c` a H+  A ` (H$ ,  B A8%0,
  - @XA-    MBim@ ,j`r  hP MbD` IdLt@!d 
 * E0BcAD DP&2 M%#S`g@s     D p````d@f$ eaP $D@"!a F $8 $(BnC !$ %3dr "f A  @$!`$  c(@'R  A    Aha ` E`@ d @ B d g `d D  AF 1%d"`@  "%2 ( hP o 0HaT  	rR @at fF APP $ets t(@ aa d f(a `!0``!L$ (  d`DA!
   PS@-"@@t%` mDd BhAb`e` pf h`)d @S  i% m(pa bE!@	DPa! fd tg d(a`lax l"E aH i#A d""%r aEqa ec
  , D!X 1m $ `P  P  Bi(h p%aO e`  rg`A0 0 (B $he QC@ 1s rAtE".q cp`fE DAta  ! IX U B a@aT,bdi+h 2aAe C$da4HnN*   B%p !2ri" "! #  `!P!F D A pE,"    'B@ dh L)"h4 A  b $ r DeRana  B`!Dp"e@.    FhH`D `  Pm ` a0 QB eVac Hp A*C`  hD @Ed $*I0 Ad
Q  ED k` `AB FI$ aD  `&  B ``d ti%b A	 	p D
 " Fix ` du l)CAd *`e  @D" H`'`# Mh e a #``	"P  A/F)
 ( !xdd r!b! A-h@II$( A& 2 LdD#@M!J %A"   Dx `i + @  Ree"dLc`# @`B  E(!d qp`tAb C`ad aD,   nr L@#!cq L!tBX IMD/rr%Bf Bnh@rM(L%RQ 0!mgb$d Dd!sip1 kB 00$N$A  		p  @H@ AE"dE" DL Cc`D2) l`   )Ec*   `a $  T ! @g" 2 bSA  RA"! d U !  hJb0AbT  bPd c E2fPP)#srL%A`!xdE #hrC    `@@p%D @R `hmR Se`	B $pH4r $+ qE BEnt pS &bLa Ha`"nmb` @SP3"DepieHLeb"a@`@mDHA c,!br  AJr2e#$lq h  A 	  @$$  `# @DAe ` @hh `o s`n`RA
  E `!`/#la%  4  (@ Ii`$C.  ( UP`ad%D #Ni%eBt2 HN L)h% @I0AD EsA`@dE&j 2` ep`l`D G  idA   ``d`` D` "Hr  +  Q`!&h%@L$$ @P   ` E/@A" T, eAt"( 4 A f v f@ T D  `
0Ab  E@ @)D,    amDE   )@t$gD((%$J`t` G  l.d`  ) A$ LPy`oP`s0*`2 0 hG ` (`eljNT ApaB (fw po uSa R 90h eNOp!   $ @D d  MBD( $@hA&h!! 	@ 	%  `eE*b@a @0 h*7 @o #` IB @hO  PE.R/@ *
  - A`@a$ @B P E0@d(B Ta ,  C$e  A($bQ` tEA  oA @l Pc P dEAAcx  Fdf" qEj#I !(
  - A  `@ H@tr)x@'.D"0@dlg.ja6a (de-H3 b @DQ J  k P@E $@  cq D!p2(p @dl@r!* 
 dA0%$ (!"  N! DdApe%`pH%$ 
  p@!DE  "eD c` "`0# Dieb "cb @`bD Nd4"lEB !d DiDA` t!t`G$ @`PA. 
T. D'@OfEH"B , 00 
("* **"*" ""**(* **" *
 * *
 * " *
*
(*" * ""

*  " 
**("(*" **

*" "(**
**
R dDad $"! , 6   
 
 AD ad SU0`m`t do da! h a 0@h 1 6T 	c4hrp`Rvk cntBolep
 * @l` "!  u@  ,@P@Dld  ("    DH
C H  jD `D$ I`% )b   Ir!B tAlL A @ @,m$ "Fh0l$,2 a Pa,
 ( ("$" d -b iD @D0B!/i# @n3C" (B As "$ "iFh#1"!` `dB ,AE!`I  AdUDe pOR$   `B 4
  ENA
	Ct.bER 2( 0 5(*("" 
*
***
"""*
( "*(
*(****
( * ""
*
(
"( "" 
"(* 


""
*(
"( * *
I, p@i2 lA4!S4 V$rbo, b T`D @T R@K ( 1000 3 1( 4D bD,FAFF B(H    (HThD  ! Ted2
 * $G TDP AJ4r"!! `Eb    a2`P  !4a h @dD D  @aT DdPbhD@ !P@ $  ( AL @*T() (4` 	S A$D d `G h$ L@& A `D @A*
    n" Dha Dl@ A, (B)d( I $ i#$@dD @af@4 bb'Be H` C`Apd(! %d`d    Det hdX, ` e  *h 	 Me@ (  a@d b tR( gAR%@ a( t` u1a" PbSsp a(  H" "Qd`il Dl d2hd$  Rp!t	Mb   , The i$ 4(( @Dd $`p  eD@Ndr r` HnW RaqUard` a@. ,d@D pi  ! #" 	  aJ ` 4@d a"` '@ )AD "   % T@  saPP ! lD Sp-P( eETh dC  `$ G !E$ . " A ` S iaR	PE ! @dqS a3 aF be$eDd

   , A )2 C` aP     @`ar`@$ lC$E th #peTA @ @iFA!0 (fO `d`  pi!*( prEA Mg@%.   - T- caF D ,gb+i,# A`` eaN$ L#d r@AD. aep(	 a D(	j A h(HAr F  CD*  TH% @PA L2 t@a LA`cH K`a@  a, a D`F	@A 	bEDacd  O uLa @`&$ b@% % dt%D.   , I  0  L*@ c UI ` p D E`BQ !dd ` nF' wE`	hg
 "  @@ H B r pD l  `@a bE@* Pa%d d!r   DDe0 `DM e!hC  

D, nE
Aga10 3 2  $