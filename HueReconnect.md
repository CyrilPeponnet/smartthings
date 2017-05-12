# Hue (ReConnect)

This is my attempt to improve the existing HueConnect smartapp by adding Groups, Scenes and Hue Beyond support.

## How it looks

SmartApp

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/App.png" width="300px">

Hue Scene device

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/scene.png" width="300px">

Hue White Ambiance Bulb

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/White%20Ambiance%20Bulb.png" width="300px">

Hue Group or Hew Beyond Lights

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/group.png" width="300px">

Hue Colour Lights

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/Colour%20Bulb.png" width="300px">

Hue Lux Lights

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/White%20Bulb.png" width="300px">

Hue Bridge

<img src="https://github.com/CyrilPeponnet/smartthings/Screenshots/bridge.png" width="300px">

## How to install

Remove all your existing bulbs / and HueConnect SmartApp.

Use github integration to add smartApp and device handlers.

**TIPS:**
On the hue application when you create a new scene, trigger it once, and also trigger the off button on the left when you click on the scene (where you can also change the luminosity). Then you can add it to SmartThings using the smartApp. This will allow you to turn off the scene from SmartThings as well :) (When you manually trigger the Off button on the Hue application this will generate a hidden scene to turn of the current scene). This can be done after you added the scene as well.
