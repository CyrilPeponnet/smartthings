# Hue (ReConnect)

This is my attempt to improve the existing HueConnect smartapp by adding scene handling.

You can now add the scene you want this will create for you a push button to enable scenes. \o/

## How it looks

SmartApp

<img src="https://dl.dropboxusercontent.com/u/2663552/Github/Smartthings/HueConnect/IMG_0774.jpg" width="300px">

Hue Scene device

<img src="https://dl.dropboxusercontent.com/u/2663552/Github/Smartthings/HueConnect/IMG_0776.jpg" width="300px">


## How to install

Remove all your existing bulbs / and HueConnect SmartApp.

Then create a new smartapp in IDE from code and also add each devices type you can find under HueDevices folder.

## Important notes

You may found the list of scene a little bit of messy. This is a Hue API limitation.

<img src="https://dl.dropboxusercontent.com/u/2663552/Github/Smartthings/HueConnect/IMG_0775.jpg" width="300px">

From API documentation:

    Scenes are only "cached" and can be recycled when not enough resources are available to create a new scene.
    Additionally, bridge scenes should not be confused with the preset scenes stored in the Android and iOS Hue apps. In the apps these scenes are stored internally. Once activated they may then appear as a bridge scene.

It means that you can have 200 scenes per bridges and they are recycled. Each time you will create / change a scene using the Hue Application, this will create a new scene.

The trick here, to quickly find the good scene you want to use, is to change the name (so you'll be sure to retrive it through the list of scenes)

## Known issues

From time to time when pushing a scene, the state of the bulbs involved is not properly refreshed (it will be in the next 5 minutes).