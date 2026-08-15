# VWO Login Page — Master Test Plan (IEEE 829)

## 1. Test Plan Identifier

| Attribute | Value |
|---|---|
| Document Title | VWO Login Page — Master Test Plan |
| Test Plan ID | VWO-TP-LOGIN-001 |
| Version | 1.0 |
| Status | Draft — for review |
| Date | 2026-08-15 |
| Prepared By | QA Architect |
| Reviewed By | (to be assigned) |
| Approved By | (to be assigned) |
| Target URL | https://app.vwo.com/#/login |
| Application Type | Single Page Application (SPA) |

---

## 2. Introduction

### 2.1 Purpose
This document defines the overall test strategy, scope, approach, environment, entry/exit criteria, deliverables, risks, schedule, resources, defect management, and traceability for testing the VWO login page (`https://app.vwo.com/#/login`). It is the authoritative reference for all testing activities performed on the authentication entry point of the VWO platform.

### 2.2 Scope
The scope of this Test Plan covers the VWO login page and all authentication-related functionality reachable from it:

- Email/password authentication
- SSO via Google OAuth
- SSO via Microsoft Azure AD
- "Forgot Password" recovery flow
- "Create Account" / "Sign Up" redirection
- Session timeout and persistent login ("Remember Me")
- CAPTCHA challenge after repeated failed attempts
- Rate limiting on login attempts
- Cross-browser and mobile compatibility
- Performance, security, usability, and accessibility of the login experience

### 2.3 Objectives
- Verify that all supported authentication methods work correctly and securely.
- Verify that session management (timeout, persistent login) behaves per specification.
- Verify that anti-abuse controls (CAPTCHA, rate limiting) trigger and block as intended.
- Verify the login page meets the stated performance SLAs.
- Verify compatibility across the mandated browser/OS matrix.
- Verify GDPR-compliant handling of personal data during testing and in the product flow.
- Provide measurable, audit-ready evidence of test readiness and results.

---

## 3. Test Items

### 3.1 In Scope
| Item ID | Test Item |
|---|---|
| TI-01 | Login page UI and rendering (SPA) |
| TI-02 | Email/password authentication flow |
| TI-03 | Google SSO (OAuth) authentication flow |
| TI-04 | Microsoft SSO (Azure AD) authentication flow |
| TI-05 | Forgot Password recovery flow |
| TI-06 | Create Account / Sign Up redirection |
| TI-07 | Session management (timeout, Remember Me) |
| TI-08 | Security controls (CAPTCHA, rate limiting) |
| TI-09 | Client-side session/cookie handling |
| TI-10 | Performance and load behavior |
| TI-11 | Compatibility (desktop + mobile browsers) |
| TI-12 | Accessibility (WCAG 2.1 AA) |

### 3.2 Out of Scope
| Item | Rationale |
|---|---|
| Post-login application features (experiments, reports, personalization, etc.) | Separate Test Plans; this plan is limited to the authentication entry point |
| VWO backend infrastructure internals not publicly visible | Per constraints, no assumptions are made about non-public architecture |
| Mobile native SDKs | Login page is browser-based; native SDKs covered by other plans |
| Payment/billing flows | Not related to login |

---

## 4. Features to be Tested

| Feature ID | Feature | Priority |
|---|---|---|
| F-01 | Email/password login | High |
| F-02 | Google SSO (OAuth) login | High |
| F-03 | Microsoft SSO (Azure AD) login | High |
| F-04 | Forgot Password recovery | High |
| F-05 | Create Account / Sign Up redirection | Medium |
| F-06 | Session timeout | High |
| F-07 | Persistent login (Remember Me) | Medium |
| F-08 | CAPTCHA after multiple failed attempts | High |
| F-09 | Rate limiting on login attempts | High |
| F-10 | Page load performance (< 3s) | High |
| F-11 | API response performance (< 2s) | High |
| F-12 | Cross-browser compatibility | High |
| F-13 | Mobile browser compatibility | Medium |
| F-14 | Accessibility (WCAG 2.1 AA) | Medium |
| F-15 | GDPR-compliant data handling | High |

---

## 5. Features Not to be Tested

| Feature | Rationale for Exclusion |
|---|---|
| VWO internal experiment/analytics engine | Out of scope for the login entry point |
| Admin/back-office configuration screens | Not reachable pre-login |
| Third-party IdP administration (Google/Microsoft consoles) | Vendor-managed; only the SP-initiated flow from VWO is tested |
| Email delivery infrastructure for Forgot Password | Only the in-app recovery flow is tested; actual mailbox delivery verified via test inbox only |
| Production destructive tests | Production limited to smoke tests only |

---

## 6. Test Strategy / Approach

### 6.1 Test Levels

| Level | Scope | Performed By |
|---|---|---|
| Unit | Developer-level verification of client-side components (form validation, routing, API client stubs) | Developers |
| Integration | Auth API contracts, token exchange, SSO callback handling, session store integration | QA + Developers |
| System | End-to-end functional and non-functional testing of the login page | QA |
| UAT | Business sign-off against acceptance criteria | Business stakeholders / QA coordination |

### 6.2 Test Types

**Functional Testing**
- Positive and negative validation of email/password login.
- SSO redirection to Google and Microsoft, consent/authorization, callback handling, and cancellation paths.
- Forgot Password request, reset link handling, and confirmation states.
- Sign Up redirection.
- Remember Me persistence across browser restarts.
- Session timeout enforcement and idle behavior.

**Non-Functional Testing**
- Performance: page load, API response, concurrent user load, and soak.
- Security: authentication bypass attempts, session fixation, cookie flags (HttpOnly, Secure, SameSite), open redirect on SSO callback, brute-force/rate-limit verification, CAPTCHA effectiveness.
- Usability: clarity of error messages, keyboard flow, loading indicators, mobile ergonomics.
- Compatibility: desktop and mobile browser matrix.
- Accessibility: WCAG 2.1 AA via automated scan + manual keyboard/screen-reader checks.

### 6.3 Test Design Techniques
- Equivalence partitioning and boundary value analysis for input fields.
- Decision table testing for CAPTCHA/rate-limit trigger logic.
- State transition testing for session lifecycle (authenticated → idle → expired).
- Negative and exploratory testing for SSO and error paths.
- Pairwise testing for browser/OS combinations.

---

## 7. Test Environment

### 7.1 Environments
| Environment | Use | Data Policy |
|---|---|---|
| Staging/UAT | Primary functional, SSO, performance, and security testing | Synthetic test accounts only |
| Production | Smoke tests only (login render + valid synthetic login if permitted, otherwise read-only checks) | Synthetic accounts only; no production user data |

### 7.2 Hardware / Software
- Desktop clients: Windows 11, macOS 14+, Ubuntu 22.04 (if required).
- Mobile devices/emulators: latest 2 iOS (Safari) and latest 2 Android (Chrome).

### 7.3 Browser / OS Compatibility Matrix (Mandatory)
| Browser | Version | OS |
|---|---|---|
| Chrome | Latest, latest-1 | Windows 11, macOS |
| Firefox | Latest, latest-1 | Windows 11, macOS |
| Safari | Latest, latest-1 | macOS |
| Edge (Chromium) | Latest, latest-1 | Windows 11 |
| iOS Safari | Latest, latest-1 | iOS |
| Android Chrome | Latest, latest-1 | Android |

### 7.4 Tools
| Category | Tool |
|---|---|
| Test Management / Traceability | JIRA + Xray or Zephyr |
| Functional Automation | Playwright (primary), Selenium WebDriver |
| API Testing | Postman / REST-assured |
| Performance/Load | k6 or JMeter |
| Cross-browser/Device | BrowserStack or Sauce Labs |
| Accessibility | axe-core, Lighthouse, WAVE (manual NVDA/VoiceOver pass) |
| Security | OWASP ZAP or Burp Suite Community, browser dev tools |
| Monitoring | New Relic or Grafana (during load tests) |
| Test Data Management | Synthetic account generator (GDPR-safe) |

### 7.5 Test Data
- Synthetic test accounts for email/password (unique per environment).
- Test IdP identities for Google and Microsoft SSO (test tenants only).
- Test inbox for Forgot Password email verification.
- No production user data under any circumstances (GDPR requirement).

---

## 8. Entry and Exit Criteria (per phase)

### 8.1 Unit Testing
- **Entry:** Component code complete; build passing.
- **Exit:** ≥90% unit coverage on auth components; zero open P1/P2 unit defects.

### 8.2 Integration Testing
- **Entry:** All auth API contracts available on staging; IdP test tenants configured.
- **Exit:** All integration scenarios pass; no open Severity 1/2 defects.

### 8.3 System (Functional) Testing
- **Entry:** Build deployed to staging; smoke test passed; test data provisioned; test environment stable.
- **Exit:** 100% of in-scope functional scenarios executed; ≥95% pass; all Severity 1/2 defects closed/retested; all High-priority scenarios passed.

### 8.4 Non-Functional (Performance / Security / Compatibility / Accessibility)
- **Entry:** Functional exit criteria met; stable build; performance test environment provisioned.
- **Exit:** Performance SLAs met (page < 3s, API < 2s, no crash under defined concurrent load); no Critical/High security findings open; compatibility matrix executed with no Severity 1/2 issues; accessibility achieves WCAG 2.1 AA for login flows (no critical violations).

### 8.5 UAT
- **Entry:** System and non-functional exit criteria met; UAT sign-off pack prepared.
- **Exit:** Business acceptance criteria signed off; open defects triaged and accepted/deferred.

---

## 9. Test Deliverables

| Deliverable | Format | Timing |
|---|---|---|
| Master Test Plan (this document) | Markdown/PDF | Before testing |
| Requirements Traceability Matrix | Xray/JIRA export | Before testing |
| Test Scenarios & Test Data | Test management tool | Before execution |
| Automated test scripts | Git repository | During execution |
| Defect reports | JIRA | Continuous |
| Daily/weekly status reports | Email/Dashboard | Continuous |
| Test Execution Report | Test management export | After each phase |
| Performance Test Report | PDF | After NFR phase |
| Security Test Report | PDF | After security phase |
| Test Summary / Sign-off Report | PDF | Test closure |

---

## 10. Risk Assessment Matrix

| Risk ID | Description | Probability | Impact | Mitigation |
|---|---|---|---|---|
| R-01 | SSO misconfiguration on test tenants blocks Google/Microsoft flows | Medium | High | Pre-validate IdP tenants; early integration smoke; engage IdP admin |
| R-02 | CAPTCHA/rate-limit behavior inconsistent with thresholds | Medium | Medium | Decision-table tests; coordinate with dev for threshold config; isolate test traffic |
| R-03 | Production-only features not reproducible in staging | Medium | Medium | Confirm staging parity; production smoke-only plan; flag gaps early |
| R-04 | Performance environment lacks capacity for realistic load | Medium | High | Provision dedicated load env; verify capacity before load phase |
| R-05 | Test data leakage / GDPR non-compliance | Low | High | Synthetic data only; data masking; access controls; audit trail |
| R-06 | Browser/device coverage gaps (real devices unavailable) | Medium | Medium | Use BrowserStack/Sauce Labs; prioritize mandated matrix |
| R-07 | Schedule slippage due to SSO/external dependency | Medium | Medium | Early IdP setup; buffer in schedule; parallelize test types |
| R-08 | Session management defects (fixation/timeout) discovered late | Medium | High | Security tests in parallel with functional; cookie flag assertions early |
| R-09 | Ambiguous acceptance criteria for "Remember Me" | Medium | Low | Clarify with product owner in test design; document assumptions |

---

## 11. Test Schedule

| Phase | Duration (working days) | Milestone |
|---|---|---|
| Test planning & finalization | 2 | Plan approved |
| Test design & data setup | 3 | Scenarios + RTM ready |
| Environment provisioning (incl. IdP tenants) | 2 | Env ready (entry criteria met) |
| Functional test execution | 5 | Functional exit criteria met |
| Security & SSO deep-dive | 4 | Security report |
| Performance & load | 4 | Performance report |
| Compatibility & accessibility | 3 | Compatibility matrix complete |
| UAT | 3 | UAT sign-off |
| Regression & closure | 3 | Test summary report |

**Total estimated effort:** ~29 working days (~6 weeks), subject to environment availability.

---

## 12. Resource Planning

| Role | Headcount | Responsibilities |
|---|---|---|
| QA Lead / Test Manager | 1 | Planning, coordination, reporting, approvals |
| Senior QA Engineer | 2 | Functional + SSO + session test execution |
| Automation Engineer | 1 | Playwright/Selenium framework + regression |
| Performance Engineer | 1 | Load/perf scripts, benchmarks, reports |
| Security Tester | 1 | Security test design & execution |
| Accessibility Specialist | 0.5 | WCAG audit (shared) |
| UAT Coordinator / BA | 1 | UAT facilitation and sign-off |

**Skills required:** SPA testing, OAuth 2.0/OIDC flows, Playwright/Selenium, k6/JMeter, OWASP testing guide, WCAG 2.1, GDPR test-data handling.

---

## 13. Defect Management

### 13.1 Severity Classification
| Severity | Label | Definition | Resolution SLA |
|---|---|---|---|
| S1 | Blocker | Login entirely unavailable; no user can authenticate | 4 hours |
| S2 | Critical | A major auth method broken (e.g., SSO, Forgot Password) | 24 hours |
| S3 | Major | Functional defect with workaround (e.g., one browser broken) | 3 working days |
| S4 | Minor | Cosmetic/UX issue, no functional impact | Next release / agreed |
| S5 | Enhancement | Suggestion, not a defect | Backlog |

### 13.2 Workflow
`New → Triage → Assigned → In Progress → Fixed → Retest → Closed` (with `Reopened` and `Deferred` states).

1. Tester logs defect with steps, expected vs actual, environment, and evidence.
2. Triage assigns severity/priority and owner.
3. Developer fixes; provides root cause.
4. Tester retests; if passed, closes; else reopens.
5. Deferred/Rejected defects require explicit rationale and approver sign-off.

---

## 14. Traceability Matrix (Requirements → Test Scenarios)

| Requirement ID | Requirement | Test Scenario IDs | Test Type |
|---|---|---|---|
| REQ-AUTH-01 | Email/password login | TS-LOG-01, TS-LOG-02 | Functional |
| REQ-AUTH-02 | Google SSO (OAuth) | TS-LOG-03, TS-LOG-05 | Functional |
| REQ-AUTH-03 | Microsoft SSO (Azure AD) | TS-LOG-04, TS-LOG-05 | Functional |
| REQ-AUTH-04 | Forgot Password recovery | TS-LOG-06 | Functional |
| REQ-AUTH-05 | Sign Up redirection | TS-LOG-07 | Functional |
| REQ-SESS-06 | Session timeout | TS-LOG-09 | Functional/Security |
| REQ-SESS-07 | Remember Me (persistent login) | TS-LOG-08 | Functional/Security |
| REQ-SEC-08 | CAPTCHA after failed attempts | TS-LOG-10 | Functional/Security |
| REQ-SEC-09 | Rate limiting | TS-LOG-11 | Functional/Security |
| REQ-NFR-10 | Page load < 3s | TS-LOG-12 | Performance |
| REQ-NFR-11 | API response < 2s | TS-LOG-13 | Performance |
| REQ-NFR-12 | Concurrent user load | TS-LOG-14 | Performance |
| REQ-COMP-13 | Desktop browser compatibility | TS-LOG-15 | Compatibility |
| REQ-COMP-14 | Mobile browser compatibility | TS-LOG-16 | Compatibility |
| REQ-ACC-15 | Accessibility (WCAG 2.1 AA) | TS-LOG-17 | Accessibility |
| REQ-SEC-16 | Secure session/cookie handling | TS-LOG-18 | Security |
| REQ-COMPL-17 | GDPR-compliant data handling | TS-LOG-19 | Compliance/Security |

> Note: `TS-LOG-xx` are high-level test scenario identifiers (one scenario may expand to many test cases during test design). This Test Plan intentionally does not include step-by-step test cases.

---

## 15. Approvals

| Role | Name | Signature | Date |
|---|---|---|---|
| QA Lead / Test Manager | (to be completed) | | |
| Engineering Lead | (to be completed) | | |
| Product Owner | (to be completed) | | |
| Security Officer | (to be completed) | | |
| Compliance / DPO (GDPR) | (to be completed) | | |
| CTO (final approval) | (to be completed) | | |

---

## Appendix A — Performance Benchmarks (Mandatory)

| Metric | Target |
|---|---|
| Login page load (LCP) | < 3 seconds at p95 |
| Authentication API response | < 2 seconds at p95 |
| Concurrent virtual users (sustained) | 1,000 concurrent users, no errors above 0.1% and SLAs maintained |
| Peak load | 5,000 concurrent users for 15 minutes (spike test) |
| Error rate under load | < 0.1% |
| Availability during load | 99.9% |

> Benchmarks marked as initial targets are to be confirmed with engineering/product during test design.

## Appendix B — GDPR Testing Controls

- Use synthetic accounts only; no production user data.
- Mask or anonymize any captured personal data in logs/screenshots.
- Restrict test artifacts (screenshots, HAR files, logs) to the secured test management system.
- Verify the login flow surfaces consent/privacy notices where required.
- Confirm data minimization: no unnecessary PII collected during login.
- Retain test evidence per the defined data retention policy; purge after the retention period.
