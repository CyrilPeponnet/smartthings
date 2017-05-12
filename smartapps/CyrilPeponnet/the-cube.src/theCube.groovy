/**
 *  The Cube
 *  Copyright 2015 Cyril Peponnet
 */

definition(
    name: "The Cube",
    namespace: "cyrilpeponnet",
    author: "Cyril Peponnet",
    description: "Run actions by rotating a cube containing a SmartSense MultiSensor",
    category: "SmartThings Labs",
    iconUrl: "http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/glossy-waxed-wood-icons-symbols-shapes/018958-glossy-waxed-wood-icon-symbols-shapes-cube.png",
    iconX2Url: "http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/glossy-waxed-wood-icons-symbols-shapes/018958-glossy-waxed-wood-icon-symbols-shapes-cube.png"
)

preferences {
    page(name: "mainPage", title: "", nextPage: "facePage", uninstall: true) {
        section("Use the orientation of this cube") {
            input "cube", "capability.threeAxis", required: false, title: "SmartSense Multi sensor"
        }
        section([title: " ", mobileOnly:true]) {
            label title: "Assign a name", required: false
            mode title: "Set for specific mode(s)", required: false
        }
    }
    page(name: "facePage", title: "Scenes", install: true, uninstall: true)
}

def facePage() {
    def faceId = getOrientation()
    def phrases = location.helloHome?.getPhrases()*.label
    phrases.sort()
    return dynamicPage(name:"facePage", nextPage:"", refreshInterval:5) {
        section {
            for (face in 1..6)
                input "${face}", "enum", title: "Face ${face} ${faceId==face ? ' (current)' : ''}", required: false, options: phrases
        }
        section {
            input "leave", "enum", title: "When leave home position", required: false, options: phrases
            input "home", "enum", title: "When back in home position", required: false, options: phrases
        }
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}

def initialize() {
    subscribe cube, "threeAxis", positionHandler
    subscribe cube, "contact",   contactHandler
}

def positionHandler(evt) {

    def faceId = getOrientation(evt.xyzValue)
    log.trace "orientation: $faceId"

    if (faceId != state.lastActiveSceneId) {
        runHomeAction(faceId)
    }
    else {
        log.trace "No status change"
    }
    state.lastActiveSceneId = faceId
}

def contactHandler(evt) {

    def action = evt.value == "open" ? "leave" : "home"
    log.trace "contact ${evt.value} : $action"

    if (action != state.lastActiveAction) {
        runHomeAction(action)
    }
    else {
        log.trace "No status change"
    }
    state.lastActiveAction = action
}

private updateSetting(name, value) {
    app.updateSetting(name, value)
    settings[name] = value
}

private runHomeAction(faceId) {
    if (faceId in 1..6)
        location.helloHome.execute(settings."${faceId}")
    else {
        log.trace "No Home Action Defined for Current State"
    }
}

private getOrientation(xyz=null) {
    final threshold = 250

    def value = xyz ?: cube.currentValue("threeAxis")

    def x = Math.abs(value.x) > threshold ? (value.x > 0 ? 1 : -1) : 0
    def y = Math.abs(value.y) > threshold ? (value.y > 0 ? 1 : -1) : 0
    def z = Math.abs(value.z) > threshold ? (value.z > 0 ? 1 : -1) : 0

    def orientation = 0
    orientation = ( x == 0  && y == -1 && z == 0)  ? 1  : orientation
    orientation = ( x == 1  && y == 0  && z == 1)  ? 2  : orientation
    orientation = ( x == 0  && y == 1  && z == 0)  ? 3  : orientation
    orientation = ( x == -1 && y == 0  && z == -1) ? 4  : orientation
    orientation = ( x == 1  && y == 0  && z == -1) ? 5  : orientation
    orientation = ( x == -1 && y == 0  && z == 1)  ? 6  : orientation
    orientation
}
