# Self-Validation (per ANTI-HALLUCINATION.rules.md)

## Verified Facts (extracted from vwo_prd.md)

- Product URL: https://app.vwo.com/ (Web-based SaaS platform).
- Security > Authentication: Email + password; SSO support; Two-factor authentication (2FA).
- Security > Authorization: Role-based access control (RBAC).
- Security > Data Protection: Encryption in transit (TLS); Encryption at rest; Activity logging.
- Experimentation: A/B Testing; Split URL Testing; Multivariate Testing; Experiment scheduling; Experiment reporting and SmartStats analysis.
- Behavioral Insights: Heatmaps (click, scroll, focus); Session recordings; Funnel analytics; On-page surveys and feedback.
- Personalization: Real-time targeting; Audience segmentation; Dynamic content delivery.
- Program Management: Experiment backlog; Kanban-style workflow; Collaboration for CRO teams.
- Integrations: Analytics tools (Google Analytics, Mixpanel); CRM platforms (Salesforce); Data platforms (Snowflake, Segment); CMS/Commerce (WordPress, Shopify, Drupal).
- Client SDK > Web SDK: JavaScript snippet; collects behavioral data; executes experiment variations.
- Environments: Development; Staging; Production (https://app.vwo.com/).
- Performance Requirements: Dashboard and editing workflows respond within 2 seconds; experiment delivery in milliseconds.
- Scalability: millions of visitor events per day; concurrent experiment execution; large-scale behavioral analytics queries.
- Key Constraints: real-time experimentation and targeting; statistically reliable experiment results; high availability (99.9% uptime SLA).
- Data Privacy & Compliance: GDPR; CCPA; user consent tracking; data anonymization; secure data retention policies.

## Missing / Unknown Information

- UI flows, navigation paths, button labels, and field names (PRD contains none).
- Acceptance criteria for each capability (PRD contains none).
- Concrete test data values (credentials, accounts, environment credentials).
- Error messages, error codes, lockout rules, and password policy.
- Feature/requirement IDs (FR-xxx / NFR-xxx).
- API endpoints and API contracts.
- Automation tooling information (none stated; all cases marked "No").
- Failure/negative behavior (PRD states no failure or error behavior, so no negative test cases were derived).

## Generated Output

- `vwo_test_cases.csv` — 41 test cases, 13 columns in the order: Scenario, TID, Test Data, Test Case Description, Pre-Condition, Test Steps, Expected Result, Actual Result, Status, Executed By (QA Name), Misc (Comments), Priority, Is Automated.

## Self-Validation Check

1. **Traceability** — Every `Test Case Description` contains `Traced to:` pointing at a section/sub-item that exists verbatim in `vwo_prd.md`. ✅
2. **No invented content** — No feature IDs, error codes, API names, UI element names, or invented features are present. ✅
3. **Gap marking** — All unstated specifics use the exact phrase `Insufficient information to determine.` ✅
4. **No inferences** — No `Inference (low confidence)` tags were needed; all content is either directly stated or gap-marked. ✅
5. **Negative scenarios** — Intentionally omitted: the PRD states no failure/error behavior, and inventing negatives would violate the anti-hallucination rules. ✅
6. **Priority / Is Automated** — QA metadata only (not PRD assertions): High = security/RBAC/TLS/availability/performance/compliance; Medium = feature capabilities, integrations, scalability. Is Automated = "No" because the PRD states no automation info. ✅
7. **Determinism** — Same inputs (vwo_prd.md + anti-hallucination rules) reproduce the identical CSV. ✅
8. **Column count/order** — Exactly 13 columns in the required order; fields containing commas are double-quoted. ✅
