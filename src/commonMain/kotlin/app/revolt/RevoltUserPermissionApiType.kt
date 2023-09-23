package app.revolt

enum class RevoltUserPermissionApiType(val type: String) {
    Access("Access"),
    ViewProfile("ViewProfile"),
    SendMessage("SendMessage"),
    Invite("Invite")
}