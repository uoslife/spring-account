package uoslife.springaccount.intrastructure.utils

class MaskUtil {

    fun maskPhoneNumber(phoneNumber:String):String{
        val visibleDigits = 4
        val maskedPart = "*".repeat(phoneNumber.length - visibleDigits)
        val visiblePart = phoneNumber.takeLast(visibleDigits)
        return maskedPart + visiblePart
    }
}