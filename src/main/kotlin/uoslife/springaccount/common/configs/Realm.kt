package uoslife.springaccount.common.configs

enum class Realm(
    val code: String,
    val schoolName: String,
    val allowedDomains: List<String>
) {
    UOS("UOS", "서울시립대학교", listOf("uos.ac.kr")),
    KHU("KHU", "경희대학교", listOf("khu.ac.kr")),
    HUFS("HUFS", "한국외국어대학교", listOf("hufs.ac.kr"));

    companion object {
        // Find Realm by domain
        fun findByDomain(domain: String): Realm? {
            return values().find { realm ->
                realm.allowedDomains.contains(domain)
            }
        }

        // Find Realm by code
        fun findByCode(code: String): Realm? {
            return values().find {
                it.code.equals(code, ignoreCase = true)
            }
        }
    }
}
