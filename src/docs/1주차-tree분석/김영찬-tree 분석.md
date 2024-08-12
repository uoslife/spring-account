
## 구조

```
├── app
│   ├── app.controller.ts  
│   ├── app.module.ts
│   ├── auth
│   │   ├── auth.config.ts
│   │   ├── auth.module.ts
│   │   ├── commands
│   │   │   ├── auth.commands.ts // 전화 인증 세션, OTP 검증, 토큰 갱신 요청 및 응답 형식을 정의
│   │   │   └── jwt.commands.ts // JWT 페이로드의 구조 정의
│   │   ├── controllers
│   │   │   └── auth.v1.controller.ts // 전화 인증 요청, 검증 및 토큰 갱신 기능을 제공 컨트롤러
│   │   ├── dto
│   │   │   ├── phone-auth.dto.ts // 전화 인증 요청 및 응답을 위한 DTO
│   │   │   └── token.dto.ts // 토큰 요청과 인증 토큰 발급을 위한 DTO
│   │   ├── enums
│   │   │   └── jwt.enum.ts // JWT 토큰 유형을 정의하는 Enum 클래스
│   │   ├── guards
│   │   │   ├── api-key.guard.ts // API 키 인증을 위한 미들웨어
│   │   │   ├── sms-throttle.guard.ts // SMS 인증 요청 관리 미들웨어
│   │   │   └── token.guard.ts // 토큰 유효성 검증 미들웨어
│   │   └── services
│   │       ├── auth.service.ts // 토큰 인증 서비스
│   │       └── jwt.service.ts // KMS로 JWT를 생성 및 검증 서비스
│   ├── device
│   │   ├── commands
│   │   │   └── device.commands.ts // 디바이스 type
│   │   ├── controllers
│   │   │   └── device.v1.controller.ts // 디바이스 CRUD 기능 컨트롤러
│   │   ├── device.module.ts
│   │   ├── dto
│   │   │   └── device.dto.ts // 디바이스 기능 dto
│   │   └── services
│   │       └── device.service.ts // 디바이스 CRUD 관련 서비스 비즈니스 로직
│   ├── gateway
│   │   ├── commands
│   │   │   └── gateway.commands.ts
│   │   ├── controllers
│   │   │   └── gateway.controller.ts // 프록시 요청을 위한 컨트롤러
│   │   ├── gateway.module.ts 
│   │   └── services
│   │       └── gateway.service.ts // 프록시 요청을 처리하는 비즈니스 로직
│   ├── identity
│   │   ├── controllers
│   │   │   └── identity.v1.controller.ts // 신분 조회 및 대표 신분 선택 엔드포인트 제공
│   │   ├── dto
│   │   │   └── identity.dto.ts // 유저 프로필 dto
│   │   ├── identity.module.ts
│   │   └── services
│   │       └── identity.service.ts // 신분 조회, 대표 신분 설정 비즈니스 로직
│   ├── metadata // 메타데이터
│   │   ├── controllers
│   │   │   └── realm.v1.controller.ts // 사용가능 도메인 조회 엔드포인트
│   │   ├── dto
│   │   │   └── realm.dto.ts
│   │   └── metadata.module.ts
│   ├── moderator
│   │   ├── commands
│   │   │   └── notion-job.commands.ts
│   │   ├── controllers
│   │   │   └── moderator.v1.controller.ts // 노션 팀 명부 조회 컨트롤러
│   │   ├── dto
│   │   │   └── moderator.dto.ts
│   │   ├── jobs
│   │   │   └── sync-from-notion.job.ts // 노션 팀 명부 동기화
│   │   ├── moderator.module.ts
│   │   └── services
│   │       └── moderator.service.ts // 노션 팀 명부 조회 비즈니스 로직
│   ├── user
│   │   ├── commands
│   │   │   └── user.commands.ts
│   │   ├── controllers
│   │   │   └── user.v1.controller.ts // 회원가입, 프로필 조회 및 수정, 회원 탈퇴 기능
│   │   ├── dto
│   │   │   ├── profile.dto.ts
│   │   │   └── register.dto.ts
│   │   ├── listeners
│   │   │   ├── user.update.listener.ts // 이벤트 관련 캐시 삭제
│   │   │   └── user.verification.listener.ts // 인증 이벤트 처리 및 업데이트
│   │   ├── services
│   │   │   └── user.service.ts // 사용자 생성, 프로필 조회, 업데이트, 탈퇴 비즈니스 로직
│   │   └── user.module.ts
│   └── verification
│       ├── commands
│       │   └── email-verification.commands.ts
│       ├── controllers
│       │   ├── email-verification.v1.controller.ts
│       │   └── portal-verification.v1.controller.ts
│       ├── dto
│       │   ├── email-verification.dto.ts // 이메일 인증 dto
│       │   └── portal-verification.dto.ts // 포탈 인증 dto
│       ├── listeners
│       │   └── user.logged-in.listener.ts // 로그인 시, 발생하는 이벤트 정보 수집
│       ├── services
│       │   ├── email-verification.service.ts //  이메일 도메인 검증, 인증 코드 생성 및 이메일 전송, 코드 인증 비즈니스 로직
│       │   └── portal-verification.service.ts // 포탈 인증 비즈니스 로직
│       ├── verification.config.ts
│       └── verification.module.ts
├── common
│   ├── configs
│   │   ├── api-tags.ts //  Swagger 문서화에 사용될 API 태그를 정의
│   │   ├── realms.ts // 경희대,외대,시립대 정보 정의
│   │   └── supabase.ts // supabase 키값
│   ├── decorators
│   │   ├── api-auth.decorator.ts // 액세스 토큰, 등록 토큰, API 키를 사용하는 인증 방식을 설정 및 스웨거에 반영
│   │   ├── guard-auth.decorator.ts
│   │   └── is-mobile-phone.decorator.ts // 전화번호 형식을 검증
│   ├── errors
│   │   ├── auth.errors.ts // 계정 에러 및 예외 상황 메시지 정의
│   │   ├── device.errors.ts // 디바이스 에러 상황 메시지 정의
│   │   ├── user.errors.ts // 유저 에러 메시지 정의
│   │   └── verification.errors.ts // 인증 에러 메시지 정의
│   ├── events
│   │   ├── event-codes.ts // 이벤트 타입을 정의하는 enum
│   │   ├── user.event.ts // 로그인 및 유저 정보 업데이트 이벤트 클래스 정의
│   │   └── verification.event.ts // 검증 이벤트 클래스 정의
│   ├── interceptors
│   │   └── bigint.interceptor.ts // json 응답을 위한 인터셉터
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