# Jasypt gradle plugin

[![Download](https://img.shields.io/bintray/v/moberwasserlechner/maven/jasypt-gradle-plugin.svg)](https://bintray.com/moberwasserlechner/maven/jasypt-gradle-plugin/_latestVersion)
[![Travis](https://img.shields.io/travis/moberwasserlechner/jasypt-gradle-plugin/master.svg?maxAge=2592000)](https://travis-ci.org/moberwasserlechner/jasypt-gradle-plugin)
[![Twitter Follow](https://img.shields.io/twitter/follow/michaelowl_web.svg?style=social&label=Follow&style=flat-square)](https://twitter.com/michaelowl_web)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/moberwasserlechner)



## Usage

Add a buildscript dependency:
```
buildscript {
	repositories {
		jcenter()
	}
	dependencies {
		classpath "com.byteowls:jasypt-gradle-plugin:1.0.0"
	}
}
```
Apply the plugin:
```
apply plugin: "jasypt"
```

## Tasks

All below tasks need the `--password` option to work.

Use `--strong-encryption` option so the `StrongTextEncryptor` is used internally which runs a high-strength algorithm.
(you may need to download and install the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files to use it).

### encryptProperties

Search recursively for all `.properties` files for values wrapped with `ENCRYPT()` and encrypted the values.
```
./gradlew encryptProperties --password=encryptor_password
```
Search recursively for non production property files and encrypt their wrapped values.
```
./gradlew encryptProperties --file-filter-pattern='application-((?!prod).*)\.properties' --password=encryptor_password
```
The original files are always backed up unless you add `--no-backup`.

### encryptText

```
./gradlew encryptText --text=hello --password=encryptor_password
```

### decryptProperties

Search recursively for all `.properties` files for values wrapped with `ENC()` and decrypted the values.
```
./gradlew decryptProperties --password=encryptor_password
```
Search recursively for non production property files and decrypt their wrapped values.
```
./gradlew decryptProperties --file-filter-pattern='application-((?!prod).*)\.properties' --password=encryptor_password
```
The original files are always backed up unless you add `--no-backup`.

### decryptText

```
./gradlew decryptText --text=UjNjJVq8ly/oU3JGMNiQXw== --password=encryptor_password
```

## License

MIT. Please see [LICENSE](https://github.com/moberwasserlechner/jasypt-gradle-plugin/blob/master/LICENSE).
