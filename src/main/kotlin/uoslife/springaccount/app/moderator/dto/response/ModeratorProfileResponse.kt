package uoslife.springaccount.app.moderator.dto.response

import uoslife.springaccount.app.moderator.domain.entity.Moderators

/**
 * export class ModeratorProfileResponse {
 *
 * @ApiProperty({ description: '팀원 ID', type: String }) id: bigint;
 * @ApiProperty({ description: '이름' }) realName: string;
 * @ApiProperty({ description: '이메일' }) email: string;
 * @ApiProperty({ description: '기수' }) generation: string;
 * @ApiProperty({ description: '전화번호' }) phoneNumber: string;
 * @ApiProperty({ description: '학번' }) studentNumber: string;
 * @ApiProperty({ description: '챕터' }) chapter: string;
 * @ApiProperty({ description: '포지션' }) role: string;
 *
 *   constructor(data: ModeratorProfileResponse) { this.id = data.id; this.realName =
 *   Masker.maskName(data.realName); this.email = data.email; this.generation = data.generation;
 *   this.phoneNumber = Masker.maskPhoneNumber(data.phoneNumber); this.studentNumber =
 *   data.studentNumber.slice(0, -3) + '***'; this.chapter = data.chapter; this.role = data.role; }
 *   }
 */
class ModeratorProfileResponse(
    val id: Long,
    realName: String,
    val email: String,
    val generation: String,
    phoneNumber: String,
    studentNumber: String,
    val chapter: String,
    val role: String,
) {
    val realName = realName.replaceRange(1, realName.length, "*")
    val phoneNumber = phoneNumber.replaceRange(1, phoneNumber.length, "*")
    val studentNumber = studentNumber.replaceRange(3, studentNumber.length - 3, "***")

    companion object {
        fun of(moderators: Moderators): ModeratorProfileResponse {
            return ModeratorProfileResponse(
                moderators.id!!,
                moderators.realName,
                moderators.email,
                moderators.generation,
                moderators.phoneNumber,
                moderators.studentNumber,
                moderators.chapter,
                moderators.role
            )
        }
    }
}
