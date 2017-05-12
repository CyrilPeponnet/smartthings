# Fibaro Motion Sensor Device handler

This is my attempt to improve the existing device-handler by adding *ALL* available zwave paramaters from the application.

## How it looks

Main screen

<img src="https://dl.dropboxusercontent.com/u/2663552/Github/Smartthings/FibaroMotionSensor/thumb_IMG_0399_1024.jpg" width="300px">

Settings part:

<img src="https://dl.dropboxusercontent.com/u/2663552/Github/Smartthings/FibaroMotionSensor/thumb_IMG_0400_1024.jpg" width="300px">

Pending state:

<img src="https://dl.dropboxusercontent.com/u/2663552/Github/Smartthings/FibaroMotionSensor/thumb_IMG_0401_1024.jpg" width="300px">

## About the preferences synchronization (Synced/Pending state)

As you may know zwave battery powered devices are sleepy guys. They wake up every 2 hours (in this case) and you can send them commands during 15s.

Until the device is fully synchronized, the tile ``synced`` will show ``pending`` with a yellow background.

From there, you have two solutions:

- Wait for the synchronization to converge (this could require several wake up from the device)
- Force the synchronization by pushing the B-Button on the device and press on ``pending`` tile in the second after.

If you look at the live logging from the IDE you will see actions been done.

## FAQ

- I have no luminance nor temperature reports
    + You should wait for the next wake up or force the configuration by pressing B-Button and press ``Synced|Pending`` tile.

- Vibration is always on
    + This is the default behavior for tampering alerts, you should change the *paramter 24* to value *4* *vibration*. This will report the vibration level.

## Known issues

- Current values are not shown once set on iOS. ~~This is a [smartthings issue](http://community.smartthings.com/t/reported-input-number-in-device-handler-weird-behavior/18717) and should be resolved soon.~~ FIXED

## Use full links:

[Fibaro Motion Sensor Manual](http://www.fibaro.com/manuals/en/Motion-Sensor/Motion-Sensor_EN_5.3.14.pdf)
