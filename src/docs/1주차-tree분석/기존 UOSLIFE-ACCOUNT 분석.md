
## 구조

```
├── app
│   ├── app.controller.ts
│   ├── app.module.ts
│   ├── auth
│   │   ├── auth.config.ts
│   │   ├── auth.module.ts
│   │   ├── commands
│   │   │   ├── auth.commands.ts
│   │   │   └── jwt.commands.ts
│   │   ├── controllers
│   │   │   └── auth.v1.controller.ts
│   │   ├── dto
│   │   │   ├── phone-auth.dto.ts
│   │   │   └── token.dto.ts
│   │   ├── enums
│   │   │   └── jwt.enum.ts
│   │   ├── guards
│   │   │   ├── api-key.guard.ts
│   │   │   ├── sms-throttle.guard.ts
│   │   │   └── token.guard.ts
│   │   └── services
│   │       ├── auth.service.ts
│   │       └── jwt.service.ts
│   ├── device
│   │   ├── commands
│   │   │   └── device.commands.ts
│   │   ├── controllers
│   │   │   └── device.v1.controller.ts
│   │   ├── device.module.ts
│   │   ├── dto
│   │   │   └── device.dto.ts
│   │   └── services
│   │       └── device.service.ts
│   ├── gateway
│   │   ├── commands
│   │   │   └── gateway.commands.ts
│   │   ├── controllers
│   │   │   └── gateway.controller.ts
│   │   ├── gateway.module.ts
│   │   └── services
│   │       └── gateway.service.ts
│   ├── identity
│   │   ├── controllers
│   │   │   └── identity.v1.controller.ts
│   │   ├── dto
│   │   │   └── identity.dto.ts
│   │   ├── identity.module.ts
│   │   └── services
│   │       └── identity.service.ts
│   ├── metadata
│   │   ├── controllers
│   │   │   └── realm.v1.controller.ts
│   │   ├── dto
│   │   │   └── realm.dto.ts
│   │   └── metadata.module.ts
│   ├── moderator
│   │   ├── commands
│   │   │   └── notion-job.commands.ts
│   │   ├── controllers
│   │   │   └── moderator.v1.controller.ts
│   │   ├── dto
│   │   │   └── moderator.dto.ts
│   │   ├── jobs
│   │   │   └── sync-from-notion.job.ts
│   │   ├── moderator.module.ts
│   │   └── services
│   │       └── moderator.service.ts
│   ├── user
│   │   ├── commands
│   │   │   └── user.commands.ts
│   │   ├── controllers
│   │   │   └── user.v1.controller.ts
│   │   ├── dto
│   │   │   ├── profile.dto.ts
│   │   │   └── register.dto.ts
│   │   ├── listeners
│   │   │   ├── user.update.listener.ts
│   │   │   └── user.verification.listener.ts
│   │   ├── services
│   │   │   └── user.service.ts
│   │   └── user.module.ts
│   └── verification
│       ├── commands
│       │   └── email-verification.commands.ts
│       ├── controllers
│       │   ├── email-verification.v1.controller.ts
│       │   └── portal-verification.v1.controller.ts
│       ├── dto
│       │   ├── email-verification.dto.ts
│       │   └── portal-verification.dto.ts
│       ├── listeners
│       │   └── user.logged-in.listener.ts
│       ├── services
│       │   ├── email-verification.service.ts
│       │   └── portal-verification.service.ts
│       ├── verification.config.ts
│       └── verification.module.ts
├── common
│   ├── configs
│   │   ├── api-tags.ts
│   │   ├── realms.ts
│   │   └── supabase.ts
│   ├── decorators
│   │   ├── api-auth.decorator.ts
│   │   ├── guard-auth.decorator.ts
│   │   └── is-mobile-phone.decorator.ts
│   ├── errors
│   │   ├── auth.errors.ts
│   │   ├── device.errors.ts
│   │   ├── user.errors.ts
│   │   └── verification.errors.ts
│   ├── events
│   │   ├── event-codes.ts
│   │   ├── user.event.ts
│   │   └── verification.event.ts
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

간단하게 1~2줄 정도로 각 역할에 대해 서술

참고 - https://zzsza.github.io/development/2020/07/19/opensource-analysis/


## 기능 분석

![[Pasted image 20240728235906.png]]


1번 이후 각 도메인별로 분석해서 문서 작성

