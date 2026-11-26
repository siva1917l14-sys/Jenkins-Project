# Jenkins Parameterized Pipeline Demo (Windows)

A small Java project demonstrating Jenkins Pipeline parameters on a Windows agent. No Maven or Gradle required.

## Files

- `Jenkinsfile`: Declarative pipeline with Checkout, Show Parameters, Build, Test, Package and Run stages
- `src/HelloJenkins.java`: Java application and smoke test
- `.gitignore`: Excludes compiled outputs

## Prerequisites

- Jenkins with Pipeline and Git plugins
- A Windows Jenkins node labeled `windows` (adjust Jenkinsfile if your agent uses another label)
- JDK 17+ with `java`, `javac`, and `jar` on PATH (JDK 21 works)
- Git installed on the node

## Parameters

| Parameter | Type | Default | Purpose |
| --- | --- | --- | --- |
| `USER_NAME` | String | `Jenkins Learner` | Name printed by Java |
| `ENVIRONMENT` | Choice | `DEV` | DEV, QA or PROD |
| `RUN_TESTS` | Boolean | `true` | Enable/skip the Test stage |

## Setup in Jenkins

1. Select **New Item** → name `Parameterized-Demo` → **Pipeline**.
2. Under **Pipeline**, choose **Pipeline script from SCM**.
3. Select **Git**, and enter `https://github.com/siva1917l14-sys/Jenkins-Project.git`.
4. Branch Specifier: `*/main`; Script Path: `Jenkinsfile`.
5. Save and select **Build Now** once to load parameter definitions.
6. Refresh the page and select **Build with Parameters**.
7. Review **Console Output** and the archived `build/parameterized-demo.jar` artifact.

## Example output

```text
User: Priya
Environment: QA
Run tests: true
PASS: greeting smoke test
Hello, Priya! Environment: QA
Parameterized Jenkins pipeline executed successfully.
```

Uncheck `RUN_TESTS` to see Jenkins skip the Test stage.

**Security:** Never display secret parameters in logs. Use Jenkins Credentials. Validate user-controlled values before passing them to Windows batch commands.
