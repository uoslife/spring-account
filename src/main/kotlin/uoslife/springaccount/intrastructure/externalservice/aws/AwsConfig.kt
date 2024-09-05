package uoslife.springaccount.intrastructure.externalservice.aws

class AwsConfig {
    companion object {
        const val SESSION_TTL = 60 * 60L  // 1 hour
    }
    object KMS {
        const val ALGORITHM = "RSASSA_PSS_SHA_512"
        object KEYS {
            const val TOKEN_SIGNING="arn:aws:kms:ap-northeast-2:755333364809:alias/uoslife/account-key"
            const val PORTAL_PASSWORD = "arn:aws:kms:ap-northeast-2:755333364809:alias/uoslife/portal-account"
        }
        const val ROLE_ARN = "arn:aws:iam::755333364809:role/uoslife-v2--kms"
        const val ROLE_SESSION_NAME= "uoslife-v2--kms"
    }
    object SES {
        object EMAIL {
            const val TITLE ="UOSLIFE 인증 메일입니다."
            const val FROM = "시대생팀 <no-reply@uoslife.team>"
            const val REPLY_TO = "시대생팀 <support@uoslife.team>"
            const val VERIFY_URL = "https://api.uoslife.com/v1/verification/email/verify"
        }
        const val ROLE_ARN = "arn:aws:iam::755333364809:role/uoslife-v2--ses"
        const val ROLE_SESSION_NAME = "uoslife-v2--ses"
    }
}