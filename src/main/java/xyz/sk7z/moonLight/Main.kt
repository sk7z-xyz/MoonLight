package xyz.sk7z.moonLight

import twitter4j.TwitterFactory
import java.util.*
import kotlin.math.roundToInt

fun main() {
    val twitter = TwitterFactory().instance
    val user = twitter.showUser(twitter.id)
    val oldUserName = user.name
    var userName = user.name
    var moonEmojis = "(🌑|🌒|🌓|🌔|🌕|🌖|🌗|🌘)".toRegex()

    val regex = ".*$moonEmojis+.*".toRegex()
    if(userName.matches(regex)){
        println("update")
        userName = userName.replace(moonEmojis, getTodayMoonEmoji())
    }else{
        println("add")
        userName += getTodayMoonEmoji()
    }
    twitter.updateProfile(userName,user.url,user.location,user.description)
    println("名前を変更しました: $oldUserName -> ${user.name}")



}

fun getTodayMoonEmoji():String{
    return getDayEmoji(Date())
}
fun getDayEmoji(day:Date):String{
    val moonEmoji = arrayOf("\uD83C\uDF11","\uD83C\uDF12","\uD83C\uDF13","\uD83C\uDF14","\uD83C\uDF15","\uD83C\uDF16","\uD83C\uDF17","\uD83C\uDF18","\uD83C\uDF11")
    val c = intArrayOf(0,2,0,2,2,4,5,6,7,8,9,10)
    val moonAge = (((day.year-11f)%19)*11+c[day.month]+day.date)%30
    val index = ((moonAge/30*8)).roundToInt()
    return moonEmoji[index]

}