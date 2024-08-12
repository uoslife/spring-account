```
├── app
│   ├── app.controller.ts
│   ├── app.module.ts
│   ├── auth
│   │   ├── auth.config.ts // 인증 관련 설정
│   │   ├── auth.module.ts // 인증 모듈을 정의
│   │   ├── commands
│   │   │   ├── auth.commands.ts // 인증과 관련된 type alias를 정의
│   │   │   └── jwt.commands.ts // JWT와 관련된 type alias를 정의
│   │   ├── controllers
│   │   │   └── auth.v1.controller.ts // 인증 관련 컨트롤러
│   │   ├── dto
│   │   │   ├── phone-auth.dto.ts // 전화 인증을 위한 DTO를 정의
│   │   │   └── token.dto.ts // 토큰 관련 DTO를 정의
│   │   ├── enums
│   │   │   └── jwt.enum.ts // JWT와 관련된 열거형을 정의
│   │   ├── guards
│   │   │   ├── api-key.guard.ts // API 키 인증을 처리하는 가드
│   │   │   ├── sms-throttle.guard.ts // SMS 인증 관련 제한을 처리하는 가드
│   │   │   └── token.guard.ts // 토큰 기반 인증을 처리하는 가드
│   │   └── services
│   │       ├── auth.service.ts // 인증 로직을 처리하는 서비스
│   │       └── jwt.service.ts // JWT 생성 및 검증을 처리하는 서비스
│   ├── device
│   │   ├── commands
│   │   │   └── device.commands.ts // 디바이스 관련 type alias를 정의
│   │   ├── controllers
│   │   │   └── device.v1.controller.ts // 디바이스 관련 컨트롤러를 정의
│   │   ├── device.module.ts // 디바이스 모듈을 정의
│   │   ├── dto
│   │   │   └── device.dto.ts // 디바이스 관련 DTO를 정의
│   │   └── services
│   │       └── device.service.ts // 디바이스 관련 로직을 처리하는 서비스
│   ├── gateway
│   │   ├── commands
│   │   │   └── gateway.commands.ts // 게이트웨이 관련 type alias를 정의
│   │   ├── controllers
│   │   │   └── gateway.controller.ts // 게이트웨이 관련 컨트롤러
│   │   ├── gateway.module.ts // 게이트웨이 모듈을 정의
│   │   └── services
│   │       └── gateway.service.ts // 게이트웨이 관련 로직을 처리하는 서비스
│   ├── identity
│   │   ├── controllers
│   │   │   └── identity.v1.controller.ts // 사용자 신분 관련 컨트롤러
│   │   ├── dto
│   │   │   └── identity.dto.ts // 사용자 신분 관련 DTO를 정의
│   │   ├── identity.module.ts // 사용자 신분 관련 모듈을 정의
│   │   └── services
│   │       └── identity.service.ts // 사용자 신분 관련 로직을 처리하는 서비스
│   ├── metadata
│   │   ├── controllers
│   │   │   └── realm.v1.controller.ts
│   │   ├── dto
│   │   │   └── realm.dto.ts
│   │   └── metadata.module.ts
│   ├── moderator
│   │   ├── commands
│   │   │   └── notion-job.commands.ts // 시대생팀 구성원 관련 type alias를 정의
│   │   ├── controllers
│   │   │   └── moderator.v1.controller.ts // 시대생팀 구성원 관련 컨트롤러
│   │   ├── dto
│   │   │   └── moderator.dto.ts // 시대생팀 구성원 관련 DTO를 정의
│   │   ├── jobs
│   │   │   └── sync-from-notion.job.ts // notion 동기화 관련
│   │   ├── moderator.module.ts // 시대생팀 구성원 관련 모듈을 정의
│   │   └── services
│   │       └── moderator.service.ts // 시대생팀 구성원 관련 로직을 처리하는 서비스
│   ├── user
│   │   ├── commands
│   │   │   └── user.commands.ts // 사용자 관련 type alias를 정의
│   │   ├── controllers
│   │   │   └── user.v1.controller.ts // 사용자 관리 컨트롤러
│   │   ├── dto
│   │   │   ├── profile.dto.ts // 사용자 프로필 관련 DTO를 정의
│   │   │   └── register.dto.ts //  회원가입 시에 사용하는 DTO를 정의
│   │   ├── listeners
│   │   │   ├── user.update.listener.ts
│   │   │   └── user.verification.listener.ts
│   │   ├── services
│   │   │   └── user.service.ts // 사용자 관리 로직을 처리하는 서비스
│   │   └── user.module.ts
│   └── verification
│       ├── commands
│       │   └── email-verification.commands.ts // 이메일 인증 관련 type alias를 정의
│       ├── controllers
│       │   ├── email-verification.v1.controller.ts // 이메일 인증 관련 컨트롤러
│       │   └── portal-verification.v1.controller.ts // 포털 연동 관련 컨트롤러
│       ├── dto
│       │   ├── email-verification.dto.ts // 이메일 인증 관련 DTO를 정의
│       │   └── portal-verification.dto.ts // 포털 연동 관련 DTO를 정의
│       ├── listeners
│       │   └── user.logged-in.listener.ts // 포털 정보 갱신 관련
│       ├── services
│       │   ├── email-verification.service.ts // 이메일 인증 관련 로직을 처리하는 서비스
│       │   └── portal-verification.service.ts // 포털 연동 관련 로직을 처리하는 서비스
│       ├── verification.config.ts // 인증 관련 설정 (이메일 유효시간)
│       └── verification.module.ts // 인증 관련 모듈을 정의
├── common
│   ├── configs
│   │   ├── api-tags.ts // API 태그를 정의
│   │   ├── realms.ts // 소속 대학 관련 정의
│   │   └── supabase.ts // Supabase 관련 설정
│   ├── decorators
│   │   ├── api-auth.decorator.ts // JWT, API 키를 통한 사용자 정보
│   │   ├── guard-auth.decorator.ts // API 문서화를 위한 데코레이터 함수를 정의
│   │   └── is-mobile-phone.decorator.ts // 주어진 값이 한국의 전화번호 형식에 맞는지 검증
│   ├── errors
│   │   ├── auth.errors.ts // 인증 관련 오류를 정의
│   │   ├── device.errors.ts // 디바이스 관련 오류를 정의
│   │   ├── user.errors.ts // 사용자 관련 오류를 정의
│   │   └── verification.errors.ts // 인증 관련 오류를 정의
│   ├── events
│   │   ├── event-codes.ts // 이벤트 코드를 정의
│   │   ├── user.event.ts // 사용자 관련 이벤트(로그인, 업데이트)를 정의
│   │   └── verification.event.ts // 인증(포털, 이메일) 관련 이벤트를 정의
│   ├── interceptors
│   │   └── bigint.interceptor.ts // BigInt 타입 데이터를 문자열로 변환하여 응답하는 인터셉터를 정의
│   └── request.ts // Fastify를 사용하여 요청 객체에 추가적인 타입 정보를 정의
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
│   ├── external_services
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
│   │       │   └── uos.forms.ts
│   │       ├── services
│   │       │   ├── uos-profile.service.ts
│   │       │   └── uos-session.service.ts
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
        └── email_verification.mjml
```
