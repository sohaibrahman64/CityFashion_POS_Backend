## Quick context for AI assistants

This is a Spring Boot 2.7.x (Java 17) monolith for City Fashion POS. Key entry points and packages:

- Application: `com.cityfashionpos.CityfashionPosBackendApplication` (component scan for `com.cityfashionpos`)
- REST controllers: `src/main/java/com/cityfashionpos/controller` (routes under `/api/*`)
- Services: `src/main/java/com/cityfashionpos/service` and `service/impl` (transaction boundaries here)
- Repositories: `src/main/java/com/cityfashionpos/repository` (extend `JpaRepository`, rely on derived query method names)
- Entities: `src/main/java/com/cityfashionpos/entity` (use `@PrePersist`/`@PreUpdate`, LocalDateTime fields)
- SQL schema & seed: `src/main/resources/sql`

High-level architecture: controllers -> services -> repositories -> MySQL. Services annotate `@Service` and frequently use `@Transactional` (often class-level). Repositories use Spring Data JPA derived methods (e.g. `findAllByOrderById`, `findByDiscountTypeCode`, `existsByDiscountTypeCode`).

Project-specific conventions and patterns

- Controller methods commonly return `ResponseEntity` and perform local try/catch to map to 200/400/500. There is no global `@ControllerAdvice` in the repository; be conservative when changing error handling.
- DTOs are present but many controllers accept and return entity objects directly. Check `dto/` before introducing new DTOs.
- Transactional semantics live in `service.impl` classes. Read-only queries are annotated with `@Transactional(readOnly = true)`.
- Query methods rely on method name semantics. When adding repository methods, follow existing naming styles (e.g. `findAllByOrderById`, not `findAllOrderById`).
- CORS is enabled at controller level using `@CrossOrigin(origins = "*")` on many controllers — maintain this if adding endpoints expected to be called from the frontend.

Environment & run/build notes (concrete)

- Java: 17 (see `pom.xml` property `<java.version>17</java.version>`)
- Spring Boot: 2.7.18
- Database: MySQL expected by default in `src/main/resources/application.properties`:
  - jdbc url: `jdbc:mysql://localhost:3306/cityfashion_pos`
  - user/password: `root`/`root`
  - If you're running tests locally, set up a local MySQL or change properties to an in-memory DB for CI-free runs.
- Image upload path property: `product.image.upload-dir=uploads/images/products` (ensure folder exists when running locally)

Recommended commands (Windows, repository root: `cityfashion_pos`)

Run application in dev (Spring Boot maven wrapper provided):

```
mvnw.cmd spring-boot:run
```

Build (package WAR, skip slow tests for quick iteration):

```
mvnw.cmd -DskipTests package
```

Run tests (note: many test classes are examples/demo and expect DB config; ensure DB is available or adapt `application.properties`):

```
mvnw.cmd test
```

Files & examples to reference when coding

- Example controller + service + repo flow: `controller/DiscountTypeController.java` -> `service/impl/DiscountTypeServiceImpl.java` -> `repository/DiscountTypeRepository.java` -> `entity/DiscountTypeEntity.java`.
- Main application configuration: `CityfashionPosBackendApplication.java` (explicit `@EnableJpaRepositories`, `@EntityScan` and component scan). Use this to locate package boundaries.
- SQL migration-like files: `src/main/resources/sql/*.sql` — used as reference for table schemas.

Common pitfalls and guardrails for AI edits

- Do not change global component-scan packages or package structure; tests and wiring assume `com.cityfashionpos` base scan.
- Because controllers do local exception handling and most services throw runtime exceptions (or IllegalArgumentException for validation), prefer adding small, localized changes instead of introducing heavy global error handling without tests.
- Database schema is not embedded; altering entities may require running SQL in `src/main/resources/sql` or adjusting `spring.jpa.hibernate.ddl-auto` (currently `update`)—be cautious changing this.
- Packaging is `war` and Tomcat starter is `provided` scope. For quick local runs, `spring-boot:run` works. Do not assume a standalone executable jar unless you change packaging.

If you need to add tests

- Add lightweight unit tests for services (mock repositories with Mockito) to avoid requiring a local MySQL instance.
- Integration tests that exercise JPA should either provide an embedded DB (H2) or require a local test database; document whichever approach you take in the test.

When editing code, include one-line references to changed files in the PR and name the PR with the pattern: `area: short-description` (e.g., `discount-type: validate unique code`) to match existing commit style.

If anything below is unclear or you'd like examples expanded (endpoints, DTO shape, or how to run a specific test), tell me which area and I'll extend these instructions or add short examples.
