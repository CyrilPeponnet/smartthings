/**
 *  Hue White Ambiance Bulb
 *
 *  Philips Hue Type "Color Temperature Light"
 *
 *  Author: SmartThings
 */

// for the UI
metadata {
    // Automatically generated. Make future change here.
    definition (name: "Hue White Ambiance Bulb", namespace: "smartthings", author: "SmartThings") {
        capability "Switch Level"
        capability "Actuator"
        capability "Color Temperature"
        capability "Switch"
        capability "Refresh"
        capability "Health Check"

        command "refresh"
        command "alertBlink"
        command "alertPulse"
        command "alertNone"
        
        attribute "alertMode", "string"
    }

    simulator {
        // TODO: define status and reply messages here
    }

    tiles (scale: 2){
        multiAttributeTile(name:"rich-control", type: "lighting", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"st.lights.philips.hue-single", backgroundColor:"#79b821", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"switch.on", icon:"st.lights.philips.hue-single", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.lights.philips.hue-single", backgroundColor:"#79b821", nextState:"turningOff"
                attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.lights.philips.hue-single", backgroundColor:"#ffffff", nextState:"turningOn"
            }
            tileAttribute ("device.level", key: "SLIDER_CONTROL") {
                attributeState "level", action:"switch level.setLevel", range:"(0..100)"
            }
                tileAttribute ("device.level", key: "SECONDARY_CONTROL") {
                attributeState "level", label: 'Level ${currentValue}%'
            }
        }

        controlTile("colorTempSliderControl", "device.colorTemperature", "slider", width: 4, height: 2, inactiveLabel: false, range:"(2200..6500)") {
            state "colorTemperature", action:"color temperature.setColorTemperature"
        }

        valueTile("colorTemp", "device.colorTemperature", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
            state "colorTemperature", label: 'WHITES'
        }

        standardTile("refresh", "device.refresh", height: 2, width: 2, inactiveLabel: false, decoration: "flat") {
            state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
        }
        
       standardTile("alertSelector", "device.alertMode", decoration: "flat", width: 2, height: 2) {
       state "blink", label:'${name}', action:"alertBlink", icon:"st.Lighting.light11", backgroundColor:"#ffffff", nextState:"pulse"
       state "pulse", label:'${name}', action:"alertPulse", icon:"st.Lighting.light11", backgroundColor:"#e3eb00", nextState:"off"
       state "off", label:'${name}', action:"alertNone", icon:"st.Lighting.light13", backgroundColor:"#79b821", nextState:"blink"
       }

        main(["rich-control"])
        details(["rich-control", "colorTempSliderControl", "colorTemp", "refresh", "alertSelector"])
    }
}

void installed() {
	sendEvent(name: "checkInterval", value: 60 * 12, data: [protocol: "lan"], displayed: false)
}

// parse events into attributes
def parse(description) {
    log.debug "parse() - $description"
    def results = []

    def map = description
    if (description instanceof String)  {
        log.debug "Hue Ambience Bulb stringToMap - ${map}"
        map = stringToMap(description)
    }

    if (map?.name && map?.value) {
        results << createEvent(name: "${map?.name}", value: "${map?.value}")
    }
    results
}

// handle commands
void on() {
    log.trace parent.on(this)
}

void off() {
    log.trace parent.off(this)
}

void setLevel(percent) {
    log.debug "Executing 'setLevel'"
    if (percent != null && percent >= 0 && percent <= 100) {
        log.trace parent.setLevel(this, percent)
    } else {
        log.warn "$percent is not 0-100"
    }
}

void setColorTemperature(value) {
    if (value) {
        log.trace "setColorTemperature: ${value}k"
        log.trace parent.setColorTemperature(this, value)
    } else {
        log.warn "Invalid color temperature"
    }
}

void refresh() {
    log.debug "Executing 'refresh'"
    parent.manualRefresh()
}

def ping() {
    log.debug "${parent.ping(this)}"
}

def setAlert(v) {
    log.debug "setAlert: ${v}, $this"
    parent.setAlert(this, v)
    sendEvent(name: "alert", value: v, isStateChange: true)
}

def alertNone() {
	log.debug "Alert option: 'none'"
    setAlert("none")
}

def alertBlink() {
	log.debug "Alert option: 'select'"
    setAlert("select")
}

def alertPulse() {
	log.debug "Alert option: 'lselect'"
    setAlert("lselect")
}
