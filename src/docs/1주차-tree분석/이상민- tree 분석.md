```
├── app
│   ├── app.controller.ts // 서비스 health check endpoint
│   ├── app.module.ts
│   ├── auth
│   │   ├── auth.config.ts 
│   │   ├── auth.module.ts 
│   │   ├── commands
│   │   │   ├── auth.commands.ts // 서비스에서 사용하는 command
│   │   │   └── jwt.commands.ts // jwt payload
│   │   ├── controllers
│   │   │   └── auth.v1.controller.ts // 번호인증인가, 토큰 발급 컨트롤러
│   │   ├── dto
│   │   │   ├── phone-auth.dto.ts // 번호인증 dto
│   │   │   └── token.dto.ts // 토큰 관련 dto(토큰 발급사유, 토큰 response)
│   │   ├── enums
│   │   │   └── jwt.enum.ts // 토큰 타입 dto
│   │   ├── guards
│   │   │   ├── api-key.guard.ts // api-key 검증 로직, supabase 사용
│   │   │   ├── sms-throttle.guard.ts // sms 인증 제한 로직
│   │   │   └── token.guard.ts // jwt 토큰 검증 로직
│   │   └── services
│   │       ├── auth.service.ts // 토큰 갱신과 발급 서비스
│   │       └── jwt.service.ts // 토큰 생성 로직, kms 이용, jwt-guard 로직
│   ├── device
│   │   ├── commands
│   │   │   └── device.commands.ts // 서비스에서 사용하는 command
│   │   ├── controllers
│   │   │   └── device.v1.controller.ts
│   │   ├── device.module.ts
│   │   ├── dto
│   │   │   └── device.dto.ts // 디바이스 서비스 dto
│   │   └── services
│   │       └── device.service.ts // 디바이스 등록, 조회, 수정
│   ├── gateway // 포탈 스크래퍼 proxy
│   │   ├── commands
│   │   │   └── gateway.commands.ts 
│   │   ├── controllers
│   │   │   └── gateway.controller.ts
│   │   ├── gateway.module.ts
│   │   └── services
│   │       └── gateway.service.ts
│   ├── identity // 신분 서비스
│   │   ├── controllers
│   │   │   └── identity.v1.controller.ts
│   │   ├── dto
│   │   │   └── identity.dto.ts
│   │   ├── identity.module.ts
│   │   └── services
│   │       └── identity.service.ts // 이벤트 기반 업데이트, 조회
│   ├── metadata // user role
│   │   ├── controllers
│   │   │   └── realm.v1.controller.ts // 어떤 도메인이 있는지만 조회
│   │   ├── dto
│   │   │   └── realm.dto.ts
│   │   └── metadata.module.ts
│   ├── moderator // 노션의 팀 목록 조회
│   │   ├── commands
│   │   │   └── notion-job.commands.ts 
│   │   ├── controllers
│   │   │   └── moderator.v1.controller.ts // 팀 목록 조회
│   │   ├── dto
│   │   │   └── moderator.dto.ts
│   │   ├── jobs
│   │   │   └── sync-from-notion.job.ts // schedule로 싱크 맞추는 기능
│   │   ├── moderator.module.ts
│   │   └── services
│   │       └── moderator.service.ts
│   ├── user // 유저 도메인
│   │   ├── commands
│   │   │   └── user.commands.ts
│   │   ├── controllers
│   │   │   └── user.v1.controller.ts
│   │   ├── dto
│   │   │   ├── profile.dto.ts
│   │   │   └── register.dto.ts
│   │   ├── listeners
│   │   │   ├── user.update.listener.ts // 유저 업데이트시 캐쉬 삭제
│   │   │   └── user.verification.listener.ts // 유저 인증시 캐쉬 삭제
│   │   ├── services
│   │   │   └── user.service.ts // 유저 가입,삭제,조회,수정
│   │   └── user.module.ts
│   └── verification // 유저인증
│       ├── commands
│       │   └── email-verification.commands.ts
│       ├── controllers
│       │   ├── email-verification.v1.controller.ts
│       │   └── portal-verification.v1.controller.ts
│       ├── dto
│       │   ├── email-verification.dto.ts
│       │   └── portal-verification.dto.ts
│       ├── listeners
│       │   └── user.logged-in.listener.ts // 로그인시 포탈 재인증
│       ├── services
│       │   ├── email-verification.service.ts
│       │   └── portal-verification.service.ts
│       ├── verification.config.ts
│       └── verification.module.ts
├── common
│   ├── configs
│   │   ├── api-tags.ts // api 분류 정보
│   │   ├── realms.ts // 학교 분류 정보
│   │   └── supabase.ts // supabase 정보
│   ├── decorators
│   │   ├── api-auth.decorator.ts // guard-auth에서 인증 방식을나타낼때사용?
│   │   ├── guard-auth.decorator.ts // annotation으로 가드 방식추가
│   │   └── is-mobile-phone.decorator.ts // 번호 validator
│   ├── errors 
│   │   ├── auth.errors.ts
│   │   ├── device.errors.ts
│   │   ├── user.errors.ts
│   │   └── verification.errors.ts
│   ├── events 
│   │   ├── event-codes.ts // 이벤트 종류 분류
│   │   ├── user.event.ts // 유저 이벤트
│   │   └── verification.event.ts // 인증이벤트
│   ├── interceptors
│   │   └── bigint.interceptor.ts
│   └── request.ts
├── infrastructure
│   ├── database
│   │   ├── database.module.ts
│   │   ├── database.service.ts
│   │   ├── migrations
│   │   │   ├── 20240409083944_v1_init_tables_account
│   │   │   │   └── migration.sql
│   │   │   ├── 20240416024659_v2_create_moderators_table
│   │   │   │   └── migration.sql
│   │   │   ├── 20240416034119_v3_add_info_fields_to_moderators
│   │   │   │   └── migration.sql
│   │   │   ├── 20240416052930_v4_update_constraints_for_users_table
│   │   │   │   └── migration.sql
│   │   │   ├── 20240427134304_v5_link_moderator_to_users
│   │   │   │   └── migration.sql
│   │   │   ├── 20240429075214_v6_add_deleted_at_to_users
│   │   │   │   └── migration.sql
│   │   │   ├── 20240502052348_v7_add_realm_to_users
│   │   │   │   └── migration.sql
│   │   │   └── migration_lock.toml
│   │   ├── schema.prisma
│   │   └── supabase
│   │       ├── commands
│   │       │   └── api-key.ts
│   │       ├── supabase.module.ts
│   │       └── supabase.service.ts
│   ├── external_services // 외부 요청 서비스
│   │   ├── aws
│   │   │   ├── aws.config.ts
│   │   │   ├── aws.module.ts
│   │   │   ├── commands
│   │   │   │   └── assume-role.commands.ts
│   │   │   └── services
│   │   │       ├── kms.service.ts
│   │   │       ├── ses.service.ts
│   │   │       └── sts.service.ts
│   │   ├── slack
│   │   │   ├── slack.module.ts
│   │   │   └── slack.service.ts
│   │   ├── sms
│   │   │   ├── commands
│   │   │   │   └── sms.commands.ts
│   │   │   ├── sms.module.ts
│   │   │   └── sms.service.ts
│   │   └── uos
│   │       ├── commands
│   │       │   └── uos-verification.commands.ts
│   │       ├── forms
│   │       │   └── uos.forms.ts // 요청 파라미터
│   │       ├── services
│   │       │   ├── uos-profile.service.ts // 포탈 정보 스크래핑
│   │       │   └── uos-session.service.ts // sso 로그인
│   │       ├── uos.config.ts
│   │       └── uos.module.ts
│   ├── infrastructure.module.ts
│   ├── swagger
│   │   └── swagger.service.ts
│   └── utils
│       ├── fastify.util.ts
│       ├── index.ts
│       ├── logger.util.ts
│       ├── mask.util.ts
│       ├── tracer.ts
│       └── unicode.util.ts
├── main.module.ts
├── main.ts
└── resources
    └── email_templates
        └── email_verification.mjml // 인증 이메일 양식
```