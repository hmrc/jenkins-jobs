# MDTP Contributor Guidelines

Hello! Thank you for taking the time to contribute to the [HMRC Multichannel Digital Tax Platform](https://hmrc.github.io) (MDTP).

Every service or library on the MDTP is owned by a single team, in order to ensure no central repositories and/or teams constrain service delivery. Therefore this page describes the process for an individual or a team (the "Pull Requestor") to contribute to a repository (the "Repository") belonging to another team (the "Owning Team"). 

The contribution process is twofold: 
- MDTP Guidelines: uniform guidelines applying to all repositories
- Repository Guidelines: contextual guidelines for a specific repository

It is the responsibility of both the Pull Requestor and Owning Team to follow these guidelines. If the Pull Requestor does not follow these guidelines, the Owning Team will reject the changes and may resist future Pull Requests. 

> It is important to remember that an Issue and a Pull Request are a placeholder for a conversation between the Pull Requestor and the Owning Team - people and interactions are important than processes and tools. The Pull Requestor should contact the Owning Team as soon as changes to the Repository are considered.

## MDTP Guidelines 

1. The Owning Team stands ready for Pull Requests and reserves some per-team capacity to monitor, review, and test Pull Requests
2. The Pull Requestor opens an Issue on the Repository and talks with the Owning Team about the proposed changes. 
3. The Owning Team informs the Pull Requestor:
    1. If the proposed change is unnecessary i.e. if the Repository already supports the desired outcome
    2. If the proposed change is undesirable e.g. rejected in the past from a different Pull Requestor
    3. If the proposed change can be reviewed and released into production in a timeline acceptable to both the Pull Requestor and the Owning Team
4. The Pull Requestor forks the Repository and makes the proposed changes - including new tests
5. The Pull Requestor runs the Repository build locally and ensures all tests pass
6. The Pull Requestor pushes their changes to their branch and raises a Pull Request against the Repository Issue
7. The Owning Team reviews the Pull Request according to the following criteria:
    1. Can the changes be compiled and tested?
    2. Do the changes clearly indicate what they are trying to achieve?
    2. Are the changes aligned with our coding standards and our architectural approach?
    3. Are the changes compatible with other in-flight changes to the Repository?
    4. Do the changes introduec any new operational concerns e.g. performance, security?
8. The Owning Team accepts or rejects the Pull Request
9. If rejected, the Pull Requestor asks the Owning Team for feedback
10. If accepted, the Owning Team merges the changes into the Repository and creates a new release
11. The Pull Requestor and Owning Team discuss the optimal timing of pre-production and production releases
12. The Owning Team release the changes into the relevant pre-production environment(s)
13. The Pull Requestor and Owning Team validate the functional and operational impact in pre-production
14. The Owning Team schedules a release of the changes into production
15. The Pull Requestor and Owning Team validate the functional and operational impact in production

## Repository Guidelines

**Note: ** Changes to job definition files do not require a Pull Request if the Pull Requestor has write access. The below checklist is for changes to job builders, which do require a Pull Request.

1. Ensure your changes are consistent with the Jobs DSL, Job Builder, job definitions abstractions
2. Ensure your changes do not increase complexity in job definitions - changes to job definitions should always be subtractive
3. Run `./gradlew clean test` locally to test your changes
