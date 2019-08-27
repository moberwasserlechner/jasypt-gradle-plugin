# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.1] - 2019-08-27
### Added
- CHANGELOG file to keep track of changes directly in the repo
- Add task group and descriptions so the plugins shows up when running `./gradlew tasks`

### Changed
- Rename plugin id by adding the groupId to it `com.byteowls.jasypt` instead of just `jasypt`

### Fixed
- Fix travis CI config

## [1.0.0] - 2019-08-24
### Added
- Encryption and decryption of properties files
- Encryption and decryption of text

[Unreleased]: https://github.com/moberwasserlechner/jasypt-gradle-plugin/compare/1.0.1...HEAD
[1.0.1]: https://github.com/moberwasserlechner/jasypt-gradle-plugin/compare/1.0.0...1.0.1
[1.0.0]: https://github.com/moberwasserlechner/jasypt-gradle-plugin/releases/tag/1.0.0
