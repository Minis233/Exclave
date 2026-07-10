# Snell (personal build)

Branch: `feature/snell-support`

- App: https://github.com/Minis233/Exclave/tree/feature/snell-support
- Core: https://github.com/Minis233/exclave-core/tree/feature/snell-support

`library/core/go.mod` replace:

```
replace github.com/exclavenetwork/exclave-core/v5 => github.com/Minis233/exclave-core v5.0.0-20260710024930-e59e6dd5cbaf
```

Supports Snell **v4/v5** only (not v6).

## Build APK (GitHub Actions)

Actions → **Debug Build** → Run workflow on `feature/snell-support`.
Artifact: `APK`.

## Local build

```bash
cd library/core && bash build.sh
./gradlew :app:assembleOssDebug
```
