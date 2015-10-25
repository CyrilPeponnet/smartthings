/**
 *  Hue Scene
 *
 *  Author: CyrilPeponnet
 */
// for the UI
metadata {
    // Automatically generated. Make future change here.
    definition (name: "Hue Scene", namespace: "smartthings", author: "CyrilPeponnet") {
        capability "Actuator"
        capability "Switch"
        capability "Momentary"
        capability "Sensor"

        attribute "lights", "string"

    }

    // simulator metadata
    simulator {
    }

    tiles (scale: 2){
        multiAttributeTile(name:"switch", type: "momentary", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on",  label:'Push', action:"momentary.push", icon:"st.lights.philips.hue-multi", backgroundColor:"#07A4D2"
            }
            tileAttribute ("lights", key: "SECONDARY_CONTROL") {
                attributeState "default", label:'${currentValue}'
            }
        }
        main "switch"
        details "switch"
    }
}

def parse(String description) {
}

def push() {
    parent.pushScene(this)
    sendEvent(name: "momentary", value: "pushed", isStateChange: true)
}