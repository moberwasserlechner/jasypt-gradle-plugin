# Jasypt gradle plugin

[![Download](https://img.shields.io/bintray/v/moberwasserlechner/maven/jasypt-gradle-plugin.svg)](https://bintray.com/moberwasserlechner/maven/jasypt-gradle-plugin/_latestVersion)
[![Travis](https://img.shields.io/travis/moberwasserlechner/jasypt-gradle-plugin/master.svg?maxAge=2592000)](https://travis-ci.org/moberwasserlechner/jasypt-gradle-plugin)
[![Twitter Follow](https://img.shields.io/twitter/follow/michaelowl_web.svg?style=social&label=Follow&style=flat-square)](https://twitter.com/michaelowl_web)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/moberwasserlechner)

## Usage

Add a buildscript dependency:
```
buildscript {
	// use the old plugin repository management style be able to use parameter for the url
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath "com.byteowls:jasypt-gradle-plugin:LATEST_VERSION"
	}
}
```
Apply the plugin:
```
apply plugin: "jasypt"
```

## Tasks



## License

MIT. Please see [LICENSE](https://github.com/moberwasserlechner/jasypt-gradle-plugin/blob/master/LICENSE).
