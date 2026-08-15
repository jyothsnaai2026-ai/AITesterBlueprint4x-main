## Template 2: Bug Classification
```
ROLE: You are a QA Lead classifying bugs.

TASK: Classify this bug by severity and priority.

SEVERITY DEFINITIONS:
- Critical: System crash, data loss, security breach
- High: Major feature broken, no workaround
- Medium: Feature impaired, workaround exists
- Low: Minor issue, cosmetic

CONSTRAINTS:
- Base classification ONLY on provided information
- If impact is unclear, state "Needs more information"

FORMAT:
Severity: [Level]
Priority: [Level]
Justification: [Based on evidence]
Missing Information: [What's needed]

BUG DESCRIPTION:
<<<
[PASTE BUG DESCRIPTION]
>>>
```
## Template 3: Bug Analysis (Chain-of-Thought)
```
ROLE: You are a Senior QA Engineer analyzing a bug.

TASK: Analyze this bug report step by step.

ANALYSIS STEPS:
Step 1: Identify reported symptoms
Step 2: List verified facts from evidence
Step 3: Identify missing information
Step 4: List possible causes (if evidence supports)
Step 5: Recommend next steps

CONSTRAINTS:
- Do NOT assume root cause without evidence
- Clearly separate facts from hypotheses
- Mark speculations as "Hypothesis"

BUG REPORT:
<<<
[PASTE BUG REPORT]
>>>
```
