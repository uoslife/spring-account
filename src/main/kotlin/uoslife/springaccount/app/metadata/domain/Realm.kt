package uoslife.springaccount.app.metadata.domain

enum class Realm(val code: String, val koreanName: String, val allowedDomains: List<String>) {
    UOS("UOS", "서울시립대학교", listOf("uos.ac.kr")),
    KHU("KHU", "경희대학교", listOf("khu.ac.kr")),
    HUFS("HUFS", "한국외국어대학교", listOf("hufs.ac.kr"));

    fun findRealmByDomain(domain: String): Realm? {
        return entries.find { it.allowedDomains.contains(domain) }
    }
}
