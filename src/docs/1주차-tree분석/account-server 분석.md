# account-server 분석(우진)

```
├── app : 기능을 보관하는 폴더
│   ├── app.controller.ts : 서비스 작동 확인
│   ├── app.module.ts : 컨트롤러 등록하는 듯?
│   ├── auth : 인증정보 (토큰)와 관련된 기능을 담당
│   │   ├── auth.config.ts : 인증과 관련된 상수들 (TTL 등) 보관
│   │   ├── auth.module.ts
│   │   ├── commands
│   │   │   ├── auth.commands.ts : DTO에서 변환해서 사용하는 듯
│   │   │   └── jwt.commands.ts : JWT 전용 DTO
│   │   ├── controllers 
│   │   │   └── auth.v1.controller.ts : 인증 담당 컨트롤러
│   │   ├── dto
│   │   │   ├── phone-auth.dto.ts : 폰 검증 관련 DTO
│   │   │   └── token.dto.ts : 토큰 관련 DTO
│   │   ├── enums
│   │   │   └── jwt.enum.ts : 토큰 종류 ( 리프레시, 액세스, 등록 )
│   │   ├── guards
│   │   │   ├── api-key.guard.ts : API key 검증 하는 듯..?
│   │   │   ├── sms-throttle.guard.ts
│   │   │   └── token.guard.ts : 헤더 분해해서 토큰 유효성 검증 수행
│   │   └── services
│   │       ├── auth.service.ts : 전화번호 검증, 인증 6자리 생성, 6자리 검증, 리프레시 토큰
																	검증, 토큰 전달
│   │       └── jwt.service.ts : 토큰 생성, 토큰 해석
│   ├── device
│   │   ├── commands
│   │   │   └── device.commands.ts : 디바이스 entity
│   │   ├── controllers
│   │   │   └── device.v1.controller.ts : 유저 핸드폰 정보 등록, 수정, 제거
│   │   ├── device.module.ts
│   │   ├── dto
│   │   │   └── device.dto.ts : 기기 응답 요청 DTO
│   │   └── services
│   │       └── device.service.ts : 디바이스 CRUD
│   ├── gateway
│   │   ├── commands
│   │   │   └── gateway.commands.ts : Http 관련 데이터인데, fastify가 응답용도인듯?
│   │   ├── controllers
│   │   │   └── gateway.controller.ts : 포털 연동 프록시
│   │   ├── gateway.module.ts 
│   │   └── services
│   │       └── gateway.service.ts : 유저 정보 받고 프록시 데이터 전달
│   ├── identity
│   │   ├── controllers
│   │   │   └── identity.v1.controller.ts : 유저 신분 요청
│   │   ├── dto
│   │   │   └── identity.dto.ts : DB 엔티티
│   │   ├── identity.module.ts
│   │   └── services
│   │       └── identity.service.ts : 유저 관련 DB (근데 왜 신분이 여러개지?)
│   ├── metadata
│   │   ├── controllers
│   │   │   └── realm.v1.controller.ts : 서비스 도메인 목록
│   │   ├── dto
│   │   │   └── realm.dto.ts : Config realm -> DTO
│   │   └── metadata.module.ts
│   ├── moderator
│   │   ├── commands
│   │   │   └── notion-job.commands.ts : 노션 정보인듯
│   │   ├── controllers
│   │   │   └── moderator.v1.controller.ts : 시대생 목록
│   │   ├── dto
│   │   │   └── moderator.dto.ts : 응답 DTO
│   │   ├── jobs
│   │   │   └── sync-from-notion.job.ts : 노션과 DB 싱크 맞추기용 크론
│   │   ├── moderator.module.ts
│   │   └── services
│   │       └── moderator.service.ts : 목록 DB 전부 가져옴
│   ├── user
│   │   ├── commands
│   │   │   └── user.commands.ts : 유저 엔티티
│   │   ├── controllers
│   │   │   └── user.v1.controller.ts : 유저 회원가입, 유저 조회, 수정, 탈퇴
│   │   ├── dto
│   │   │   ├── profile.dto.ts : 정보 DTO
│   │   │   └── register.dto.ts : 등록 DTO
│   │   ├── listeners
│   │   │   ├── user.update.listener.ts : 
│   │   │   └── user.verification.listener.ts 
│   │   ├── services
│   │   │   └── user.service.ts : 유저정보 접근, 에러 처리, 정보 캐싱
│   │   └── user.module.ts
│   └── verification
│       ├── commands
│       │   └── email-verification.commands.ts
│       ├── controllers
│       │   ├── email-verification.v1.controller.ts : 이메일 정보 검증? 시대팅때 쓴건가
│       │   └── portal-verification.v1.controller.ts : 포털 정보 처리
│       ├── dto
│       │   ├── email-verification.dto.ts : 이메일 DTO
│       │   └── portal-verification.dto.ts : 포털 DTO
│       ├── listeners
│       │   └── user.logged-in.listener.ts
│       ├── services
│       │   ├── email-verification.service.ts : 이메일 정보
│       │   └── portal-verification.service.ts : 유저 포탈 찾기, 비밀번호 암호화, 세션 생성
│       ├── verification.config.ts : 이메일 인증
│       └── verification.module.ts
├── common
│   ├── configs
│   │   ├── api-tags.ts
│   │   ├── realms.ts
│   │   └── supabase.ts
│   ├── decorators
│   │   ├── api-auth.decorator.ts
│   │   ├── guard-auth.decorator.ts : 로그인 가드같은 느낌?
│   │   └── is-mobile-phone.decorator.ts
│   ├── errors : 각각의 에러 처리 담당
│   │   ├── auth.errors.ts
│   │   ├── device.errors.ts
│   │   ├── user.errors.ts
│   │   └── verification.errors.ts
│   ├── events
│   │   ├── event-codes.ts
│   │   ├── user.event.ts
│   │   └── verification.event.ts
│   ├── interceptors
│   │   └── bigint.interceptor.ts : 네스트 전용 인터셉터 
│   └── request.ts
├── infrastructure
│   ├── database
│   │   ├── database.module.ts
│   │   ├── database.service.ts : DB 접속 담당
│   │   ├── migrations : DB 마이그레이션 정보
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
│   │   │   └── sms.service.ts : SMS 전송 담당
│   │   └── uos
│   │       ├── commands
│   │       │   └── uos-verification.commands.ts : 유저 entity
│   │       ├── forms
│   │       │   └── uos.forms.ts : 로그인 할때, 바디 넣기
│   │       ├── services
│   │       │   ├── uos-profile.service.ts : 유저 포털 정보 
│   │       │   └── uos-session.service.ts : 유저 세션 생성, 로그인
│   │       ├── uos.config.ts : 포털 헤더, 쿠키 등등 기본 값들
│   │       └── uos.module.ts
│   ├── infrastructure.module.ts
│   ├── swagger
│   │   └── swagger.service.ts : 스웨거 등록
│   └── utils
│       ├── fastify.util.ts
│       ├── index.ts
│       ├── logger.util.ts
│       ├── mask.util.ts
│       ├── tracer.ts
│       └── unicode.util.ts
├── main.module.ts 
├── main.ts : 뭐 이것저것 기본 설정 + 실행 로직
└── resources
    └── email_templates
        └── email_verification.mjml : 이메일 전용 템플릿
```